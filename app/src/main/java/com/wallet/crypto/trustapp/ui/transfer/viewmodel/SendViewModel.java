package com.wallet.crypto.trustapp.ui.transfer.viewmodel;

import android.content.Context;
import android.text.TextUtils;
import androidx.lifecycle.MutableLiveData;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.CoinConfig;
import com.wallet.crypto.trustapp.entity.ErrorEnvelope;
import com.wallet.crypto.trustapp.router.ConfirmationRouter;
import com.wallet.crypto.trustapp.ui.BaseViewModel;
import com.wallet.crypto.trustapp.ui.transfer.entity.AmountType;
import com.wallet.crypto.trustapp.util.Numbers;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.utils.Numeric;
import trust.blockchain.entity.Asset;
import trust.blockchain.entity.CurrencyValue;
import trust.blockchain.entity.SubunitValue;
import trust.blockchain.entity.Ticker;
import trust.blockchain.entity.TransactionUnsigned;
import trust.blockchain.entity.Value;

public class SendViewModel extends BaseViewModel {
    /* renamed from: d */
    private TransactionUnsigned f21539d;
    /* renamed from: e */
    private final ConfirmationRouter f21540e;
    /* renamed from: f */
    private MutableLiveData<AmountType> f21541f = new MutableLiveData();

    public SendViewModel(ConfirmationRouter confirmationRouter) {
        this.f21540e = confirmationRouter;
    }

    private boolean checkDecimals(String str) {
        try {
            validateFractionLength(Numbers.parse(str), this.f21539d.unit().decimals);
            validateFractionLength(this.f21539d.value(), this.f21539d.unit().decimals);
            return true;
        } catch (Exception unused) {
            this.f19420a.postValue(new ErrorEnvelope((int) R.string.InvalidDecimalPart));
            return false;
        }
    }

    private void checkNumberSize(BigInteger bigInteger) {
        Uint256 uint256 = new Uint256(bigInteger);
    }

    private BigDecimal parseAmount(String str) {
        try {
            return new BigDecimal(str);
        } catch (Exception unused) {
            return null;
        }
    }

    private void validateFractionLength(BigDecimal bigDecimal, int i) {
        bigDecimal = bigDecimal.remainder(BigDecimal.ONE).movePointRight(bigDecimal.scale()).abs();
        int length = bigDecimal.toString().length();
        if (bigDecimal.compareTo(BigDecimal.ZERO) == 0) {
            return;
        }
        if (length > i) {
            throw new IllegalArgumentException("Large fractional part");
        }
    }

    public MutableLiveData<AmountType> amountType() {
        return this.f21541f;
    }

    public void complete(Context context, String str, String str2, String str3, String str4, long j, boolean z) {
        BigDecimal cryptoAmount;
        boolean z4;
        this.f19420a.postValue(null);
        if (this.f21541f.getValue() == AmountType.FIAT) {
            cryptoAmount = getCryptoAmount(str2);
            z4 = false;
        } else {
            z4 = z;
            cryptoAmount = Numbers.parse(str2);
        }

        boolean z2;
        if (this.f21539d.from().coin.isValid(str)) {
            TransactionUnsigned transactionUnsigned = this.f21539d;
            transactionUnsigned.recipientAddress(transactionUnsigned.from().coin.toAddress(str));
            z2 = false;
        } else {
            this.f19420a.postValue(new ErrorEnvelope((int) R.string.send_error_invalidAddress));
            z2 = true;
        }
        try {
            if (cryptoAmount == null) {
                throw new IllegalStateException();
            }
            this.f21539d.value(cryptoAmount);
            checkNumberSize(this.f21539d.weiAmount());
            if (!checkDecimals(str2)) {
                z2 = true;
            }

        } catch (Exception unused) {
            this.f19420a.postValue(new ErrorEnvelope((int) R.string.send_error_invalidAmount));
            z2 = true;
        }
        if (!TextUtils.isEmpty(str3)) {
            try {
                this.f21539d.data(Numeric.hexStringToByteArray(str3));
            } catch (Exception unused2) {
            }
        }
        this.f21539d.tag(j);
        this.f21539d.memo(str4);
        Asset asset = this.f21539d.asset();

        TransactionUnsigned transactionUnsigned2 = this.f21539d;
        boolean z3 = asset != null
                && asset.balance != null
                && transactionUnsigned2.weiAmount().compareTo(asset.balance.bigInteger()) == 0
                && z4;
        transactionUnsigned2.shouldMaxAmount(z3);
        if (!z2) {
            this.f21540e.open(context, this.f21539d);
        }
    }

    public String convert(String str, AmountType amountType) {
        Asset asset = this.f21539d.asset();
        BigDecimal parseAmount = parseAmount(str);
        if (asset != null) {
            Ticker ticker = asset.ticker;
            if (ticker != null) {
                if (parseAmount != null) {
                    Object mediumFormat;
                    if (amountType == AmountType.CRYPTO) {
                        mediumFormat = new CurrencyValue(this.f21539d.unit().toSubunit(parseAmount), asset.ticker).mediumFormat("0", 0);
                    } else {
                        try {
                            parseAmount = parseAmount.divide(new BigDecimal(ticker.getPrice()), MathContext.DECIMAL64).multiply(BigDecimal.TEN.pow(this.f21539d.unit().decimals));
                        } catch (Exception unused) {
                            parseAmount = BigDecimal.ZERO;
                        }
                        mediumFormat = new SubunitValue(parseAmount, this.f21539d.unit()).mediumFormat("0", 0);
                    }
                    if ("0".equals(mediumFormat)) {
                        str = "";
                    } else {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("~");
                        stringBuilder.append(mediumFormat);
                        str = stringBuilder.toString();
                    }
                    return str;
                }
            }
        }
        return "";
    }

    public String defaultFiatValue(Ticker ticker) {
        return ticker == null ? "0" : new CurrencyValue(this.f21539d.unit().toSubunit(BigDecimal.ZERO), ticker).mediumFormat("", 0);
    }

    public BigDecimal getCryptoAmount(String str) {
        BigDecimal parseAmount = parseAmount(str);
        Asset asset = this.f21539d.asset();
        MathContext mathContext = CoinConfig.f16616a.getMathContext(this.f21539d.asset().coin());
        if (asset != null) {
            Ticker ticker = asset.ticker;
            if (ticker != null) {
                if (parseAmount != null) {
                    BigDecimal bigDecimal = new BigDecimal(ticker.getPrice());
                    if (parseAmount.compareTo(BigDecimal.ZERO) != 0) {
                        if (bigDecimal.compareTo(BigDecimal.ZERO) != 0) {
                            parseAmount = parseAmount.divide(bigDecimal, mathContext).setScale(this.f21539d.asset().unit().decimals, 4);
                            return parseAmount;
                        }
                    }
                    parseAmount = BigDecimal.ZERO;
                    return parseAmount;
                }
            }
        }
        return BigDecimal.ZERO;
    }

    public TransactionUnsigned getTransactionUnsigned() {
        return this.f21539d;
    }

    public void init(TransactionUnsigned transactionUnsigned) {
        this.f21539d = transactionUnsigned;
        this.f21541f.postValue(AmountType.CRYPTO);
    }

    public String maxValue() {
        Asset asset = this.f21539d.asset();
        if (asset != null) {
            Value value = asset.balance;
            if (value != null) {
                return new SubunitValue(value, this.f21539d.unit()).convert().toString();
            }
        }
        return "0";
    }

    public void setAmountType(AmountType amountType) {
        this.f21541f.postValue(amountType);
    }

    public void toggleAmountType() {
        AmountType value = amountType().getValue();
        AmountType amountType = AmountType.FIAT;
        if (value == amountType) {
            setAmountType(AmountType.CRYPTO);
        } else {
            setAmountType(amountType);
        }
    }
}
