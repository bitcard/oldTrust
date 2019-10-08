package trust.blockchain.mnemonic

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import trust.blockchain.AccountFactory
import trust.blockchain.CoinService
import trust.blockchain.ExporterFactory
import trust.blockchain.Signer
import trust.blockchain.Slip
import trust.blockchain.TransactionSigner
import trust.blockchain.WalletAdapter
import trust.blockchain.WalletCryptographer
import trust.blockchain.entity.Account
import trust.blockchain.entity.AccountKeys
import trust.blockchain.entity.TransactionSign
import trust.blockchain.entity.TransactionUnsigned
import trust.blockchain.entity.WalletDescriptor
import wallet.core.jni.HDWallet
import java.nio.charset.StandardCharsets

class MnemonicAdapter(private val type: Int,
                      private val entropyGenerator: EntropyGenerator,
                      private val cryptographer: WalletCryptographer,
                      private val exporterFactory: ExporterFactory,
                      private val signers: Array<Signer>,
                      private val transactionSigners: Array<TransactionSigner>,
                      private val accountFactories: Array<AccountFactory>) : WalletAdapter {

    private fun decrypt(bArr: ByteArray, str: String): Single<String> {
        return Single
                .fromCallable{
                    cryptographer.decrypt(bArr, str);
                }
                .map{
                    String(it)
                }
    }

    private fun generateAccounts(hDKeysGenerator: HDKeysGenerator, slipArr: Array<Slip>): Observable<Account> {
//        return Observable.fromArray(*slipArr).map(C2779r(this, hDKeysGenerator)).subscribeOn(Schedulers.io())
        return Observable.fromArray<Slip>(*slipArr) // ???
                .map {
                    getHelper(this.accountFactories, it)!!.createAccount(hDKeysGenerator.getKeys(it), it)
                }
                .subscribeOn(Schedulers.io())
    }

    private fun generateDescriptor(str: String, str2: String, slipArr: Array<Slip>): Single<WalletDescriptor> {
        return getKeyChain(str)
                .flatMapObservable<Account> {
                    generateAccounts(it, slipArr);
                }
                .toList()
                .map {
                    WalletDescriptor(this.getType(), this.cryptographer.encrypt(str.toByteArray(charset("UTF-8")), str2), *it.toTypedArray());
                }
    }

    private fun getECKey(bArr: ByteArray, str: String, account: Account): Single<AccountKeys> {
        return decrypt(bArr, str)
                .flatMap<HDKeysGenerator>{
                    getKeyChain(it)
                }.map{
                    it.getKeys(account.coin)
                }
    }

    private fun getHDKeyGenerator(str: String): HDKeysGenerator {
        return TrustHDKeysGenerator(str)
    }

    private fun <T : CoinService> getHelper(tArr: Array<T>, slip: Slip): T? {
        for (coinService in tArr) {
            if (slip.available(coinService.maintainedCoins)) {
                return coinService
            }
        }
        return null
    }

    private fun getKeyChain(str: String): Single<out HDKeysGenerator> {
        return Single
                .fromCallable{
                    getHDKeyGenerator(str)
                }
                .onErrorResumeNext{
                    Single.fromCallable<HDKeysGenerator>{
                        throw Exception("Invalid mnemonic phrase")
                    }
                }
    }

    override fun create(str: String, slipArr: Array<Slip>): Single<WalletDescriptor> {
        return Single
                .fromCallable{
                    HDWallet(this.entropyGenerator.generate(), "").mnemonic();
                }
                .flatMap{
                    generateDescriptor(it, str, slipArr);
                }
    }

    override fun delete(bArr: ByteArray, str: String): Completable {
        return Completable.complete()
    }

    override fun exportKeyStore(bArr: ByteArray, str: String, str2: String, account: Account): Single<ByteArray> {
        return getECKey(bArr, str, account)
                .map<ByteArray>{
                    exporterFactory.get(account.coin).exportKeyStore(it, str2)
                }
    }

    override fun exportPhrase(bArr: ByteArray, str: String): Single<ByteArray> {
        return decrypt(bArr, str)
                .map<ByteArray>{
                    it.toByteArray(StandardCharsets.UTF_8)
                }
    }

    override fun exportPrivateKey(bArr: ByteArray, str: String, str2: String, account: Account): Single<ByteArray> {
        return getECKey(bArr, str, account)
                .map<ByteArray>{
                    exporterFactory.get(account.coin).exportPrivateKey(it);
                }
    }

    override fun getType(): Int {
        return this.type
    }

    override fun importWallet(bArr: ByteArray, str: String, str2: String, slipArr: Array<Slip>): Single<WalletDescriptor> {
        return generateDescriptor(String(bArr), str2, slipArr)
    }

    override fun reimportWallet(bArr: ByteArray, str: String, slipArr: Array<Slip>): Single<WalletDescriptor> {
        return decrypt(bArr, str)
                .flatMap<WalletDescriptor>{
                    generateDescriptor(it, str, slipArr);
                }
    }

    override fun sign(bArr: ByteArray, str: String, account: Account, bArr2: ByteArray, z: Boolean): Single<ByteArray> {
        val transactionSigner = getHelper(this.transactionSigners, account.coin)
        return if (transactionSigner != null) {
            decrypt(bArr, str)
                    .map<ByteArray> {
                        transactionSigner.sign(HDWallet(it, "").getKeyForCoin(account.coin.coinType()), bArr2)
                    }
        } else getECKey(bArr, str, account)
                .flatMap<ByteArray>{
                    getHelper(this.signers, account.coin)!!.sign(it, bArr2, z)
                }
    }

    override fun signTransaction(bArr: ByteArray, str: String, transactionUnsigned: TransactionUnsigned): Single<TransactionSign> {
        return decrypt(bArr, str)
                .map<TransactionSign>{
                    getHelper(this.transactionSigners, transactionUnsigned.account().coin)!!.sign(HDWallet(it, ""), transactionUnsigned);
                }
    }

}
