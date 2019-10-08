package com.wallet.crypto.trustapp.service;

import android.os.StatFs;
import android.text.TextUtils;
import android.util.Log;
import com.wallet.crypto.trustapp.entity.DappLink.Type;
import com.wallet.crypto.trustapp.entity.Session;
import com.wallet.crypto.trustapp.repository.PasswordStore;
import com.wallet.crypto.trustapp.repository.entity.RealmAccount;
import com.wallet.crypto.trustapp.repository.entity.RealmAccountIndex;
import com.wallet.crypto.trustapp.repository.entity.RealmAsset;
import com.wallet.crypto.trustapp.repository.entity.RealmCoinStatus;
import com.wallet.crypto.trustapp.repository.entity.RealmCollectiblesCategory;
import com.wallet.crypto.trustapp.repository.entity.RealmCollectiblesItem;
import com.wallet.crypto.trustapp.repository.entity.RealmCryptoTicker;
import com.wallet.crypto.trustapp.repository.entity.RealmDapp;
import com.wallet.crypto.trustapp.repository.entity.RealmDappCategory;
import com.wallet.crypto.trustapp.repository.entity.RealmDappDoc;
import com.wallet.crypto.trustapp.repository.entity.RealmDappLink;
import com.wallet.crypto.trustapp.repository.entity.RealmStatusInfo;
import com.wallet.crypto.trustapp.repository.entity.RealmStatusLink;
import com.wallet.crypto.trustapp.repository.entity.RealmTokenTicker;
import com.wallet.crypto.trustapp.repository.entity.RealmTransaction;
import com.wallet.crypto.trustapp.repository.entity.RealmTransactionContract;
import com.wallet.crypto.trustapp.repository.entity.RealmTransactionOperation;
import com.wallet.crypto.trustapp.repository.entity.RealmValueCache;
import com.wallet.crypto.trustapp.repository.entity.RealmWallet;
import com.wallet.crypto.trustapp.repository.entity.RealmWalletDescription;
import com.wallet.crypto.trustapp.repository.entity.RealmWalletInfo;
import io.realm.DynamicRealm;
import io.realm.DynamicRealmObject;
import io.realm.FieldAttribute;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmConfiguration.Builder;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmResults;
import io.realm.RealmSchema;
import io.realm.annotations.RealmModule;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import org.bouncycastle.jcajce.provider.digest.Keccak;
import org.web3j.abi.datatypes.Address;

import io.realm.exceptions.RealmError;
import io.realm.exceptions.RealmFileException;
import trust.blockchain.Slip;
import trust.blockchain.blockchain.tron.TronUtils;
import trust.blockchain.entity.Fee;
import trust.blockchain.entity.Wallet;

public class RealmManager {
    private final String dbPrefix;
    private final Gatekeeper gatekeeper;
    private final PasswordStore passwordStore;
    private final Map<String, RealmConfiguration> realmConfigurations;
    private final ReentrantLock walletsLocker;

    public RealmManager(PasswordStore passwordStore) {
        this("", passwordStore, null);
    }

    public RealmManager(PasswordStore passwordStore, Gatekeeper gatekeeper) {
        this("", passwordStore, gatekeeper);
    }

    public RealmManager(String str, PasswordStore passwordStore, Gatekeeper gatekeeper) {
        this.walletsLocker = new ReentrantLock(true);
        this.realmConfigurations = new HashMap();
        this.dbPrefix = str;
        this.passwordStore = passwordStore;
        if (gatekeeper == null) {
            gatekeeper = new GoodGatekeep();
        }
        this.gatekeeper = gatekeeper;
    }
    @RealmModule(classes = {RealmCryptoTicker.class, RealmAsset.class, RealmTokenTicker.class, RealmTransaction.class, RealmTransactionContract.class, RealmTransactionOperation.class, RealmValueCache.class, RealmCollectiblesCategory.class, RealmCollectiblesItem.class, RealmCoinStatus.class, RealmStatusInfo.class, RealmStatusLink.class})
    private static class CacheSchema {
        private CacheSchema() {
        }
    }

    @RealmModule(classes = {RealmDappLink.class, RealmDappDoc.class, RealmDappCategory.class, RealmDapp.class})
    private static class DappLinksSchema {
        private DappLinksSchema() {
        }
    }

    public interface Gatekeeper {
        boolean impulse(String str);

        boolean rancor(File file);
    }

    public interface WalletsOperation<T> {
        T execute(Realm realm);
    }

    @RealmModule(classes = {RealmWalletInfo.class, RealmWalletDescription.class, RealmWallet.class, RealmAccount.class, RealmAccountIndex.class})
    private static class WalletsSchema {
        private WalletsSchema() {
        }
    }

    private static class CacheMigration implements RealmMigration {
        private CacheMigration() {
        }

        public void migrate(DynamicRealm dynamicRealm, long j, long j2) {
            RealmObjectSchema realmObjectSchema;
            Iterator it;
            DynamicRealmObject dynamicRealmObject;
            RealmObjectSchema realmObjectSchema2;
            RealmSchema schema = dynamicRealm.getSchema();
            if (j <= 7) {
                schema.remove("RealmPendingTransaction");
                realmObjectSchema = schema.get("RealmTransaction");
                if (realmObjectSchema != null) {
                    realmObjectSchema.addField("isPending", Boolean.TYPE, new FieldAttribute[0]).addField("createdTime", Long.TYPE, new FieldAttribute[0]);
                }
                schema.remove("RealmBookmark");
                j++;
            }
            if (j <= 8) {
                schema.remove("RealmWalletBalance");
                schema.create("RealmValueCache").addField("updateTime", Long.TYPE, new FieldAttribute[0]).addField("name", String.class, FieldAttribute.PRIMARY_KEY).addField("value", String.class, new FieldAttribute[0]);
            }
            if (j <= 9) {
                realmObjectSchema = schema.get("RealmTransaction");
                if (realmObjectSchema != null) {
                    realmObjectSchema.removePrimaryKey().addPrimaryKey("nonce");
                }
            }
            if (j <= 11) {
                realmObjectSchema = schema.get("RealmTransaction");
                if (realmObjectSchema != null) {
                    realmObjectSchema.removePrimaryKey().addField("uniqueID", String.class, new FieldAttribute[0])
                            .transform(obj -> {

                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append(obj.getString("from"));
                                stringBuilder.append("-");
                                stringBuilder.append(obj.getInt("nonce"));
                                obj.setString("uniqueID", stringBuilder.toString());
                            }).addPrimaryKey("uniqueID");
                }
            }
            if (j <= 12) {
                realmObjectSchema = schema.get("RealmTransactionContract");
                if (realmObjectSchema != null) {
                    realmObjectSchema.removeField("totalSupply");
                }
                schema.create("RealmAsset").addField("id", String.class, FieldAttribute.PRIMARY_KEY).addField(Address.TYPE_NAME, String.class, new FieldAttribute[0]).addField("name", String.class, new FieldAttribute[0]).addField("symbol", String.class, new FieldAttribute[0]).addField("decimals", Integer.TYPE, new FieldAttribute[0]).addField("addedTime", Long.TYPE, new FieldAttribute[0]).addField("updatedTime", Long.TYPE, new FieldAttribute[0]).addField("balance", String.class, new FieldAttribute[0]).addField("isEnabled", Boolean.TYPE, new FieldAttribute[0]).addField("isAddedManually", Boolean.TYPE, new FieldAttribute[0]).addField("type", Integer.TYPE, new FieldAttribute[0]).addField("annotation", String.class, new FieldAttribute[0]).addField("category", String.class, new FieldAttribute[0]).addField("coverUri", String.class, new FieldAttribute[0]).addField("externalUri", String.class, new FieldAttribute[0]).addField("itemId", String.class, new FieldAttribute[0]);
                RealmResults findAll = dynamicRealm.where("RealmToken").findAll();
                if (findAll.size() > 0) {
                    it = findAll.iterator();
                    while (it.hasNext()) {
                        dynamicRealmObject = (DynamicRealmObject) it.next();
                        DynamicRealmObject createObject = dynamicRealm.createObject("RealmAsset", dynamicRealmObject.getString(Address.TYPE_NAME));
                        createObject.setString("name", dynamicRealmObject.getString("name"));
                        createObject.setString("symbol", dynamicRealmObject.getString("symbol"));
                        createObject.setInt("decimals", dynamicRealmObject.getInt("decimals"));
                        createObject.setLong("addedTime", dynamicRealmObject.getLong("addedTime"));
                        createObject.setLong("updatedTime", dynamicRealmObject.getLong("updatedTime"));
                        createObject.setString("balance", dynamicRealmObject.getString("balance"));
                        createObject.setBoolean("isEnabled", dynamicRealmObject.getBoolean("isEnabled"));
                        createObject.setBoolean("isAddedManually", dynamicRealmObject.getBoolean("isAddedManually"));
                        createObject.setInt("type", 2);
                    }
                }
                schema.remove("RealmTransactionContract");
            }
            if (j <= 17) {
                schema.get("RealmAsset").removePrimaryKey()
                        .transform(obj -> {
                            String string = obj.getString(Address.TYPE_NAME);
                            int i = obj.getInt("coinType");
                            int i2 = obj.getInt("type");
                            String string2 = obj.getString("itemId");
                            String string3 = obj.getString("category");
                            Object[] objArr = new Object[]{string, Integer.valueOf(i), Integer.valueOf(i2), string3, string2};
                            obj.setString("id", String.format("addr%s-node%s-type%s-cat%s-item_id%s", objArr));
                        }).addPrimaryKey("id");
            }
            if (j < 19) {
                schema.get("RealmTokenTicker").removePrimaryKey();
            }
            if (j < 20) {
                schema.get("RealmAsset")
                        .transform(obj -> {
                            if (obj.getInt("type") == 1) {
                                int i = obj.getInt("coinType");
                                if (i == 61) {
                                    obj.setString("name", Slip.ETC.coinName());
                                    obj.setString("symbol", Slip.ETC.unit().symbol);
                                } else if (i == 820) {
                                    obj.setString("name", Slip.CLO.coinName());
                                    obj.setString("symbol", Slip.CLO.unit().symbol);
                                } else if (i == 6060) {
                                    obj.setString("name", Slip.GO.coinName());
                                    obj.setString("symbol", Slip.GO.unit().symbol);
                                }
                            }
                        });
            }
            if (j < 21) {
                dynamicRealm.where("RealmTransaction").findAll().deleteAllFromRealm();
            }
            if (j < 22) {
                schema.get("RealmAsset").addField("total", Integer.TYPE, new FieldAttribute[0]);
            }
            if (j < 23) {
                schema.get("RealmAsset")
                        .transform(obj -> {
                            if ("0x0000000000000000000000000000456e65726779".equals(obj.getString(Address.TYPE_NAME))) {
                                obj.set("type", Integer.valueOf(4));
                            }
                        });
            }
            if (j < 24) {
                dynamicRealm.where("RealmTransaction").findAll().deleteAllFromRealm();
            }
            if (j < 26) {
                schema.create("RealmCollectiblesCategory").addField("name", String.class, FieldAttribute.PRIMARY_KEY).addField("symbol", String.class, new FieldAttribute[0]).addField("imageUrl", String.class, new FieldAttribute[0]).addField("description", String.class, new FieldAttribute[0]).addField("externalLink", String.class, new FieldAttribute[0]).addField("total", Integer.TYPE, new FieldAttribute[0]).addField("contractAddress", String.class, new FieldAttribute[0]).addField(Address.TYPE_NAME, String.class, new FieldAttribute[0]).addField("nftVersion", String.class, new FieldAttribute[0]).addField("coin", Integer.TYPE, new FieldAttribute[0]).addField("type", String.class, new FieldAttribute[0]);
            }
            if (j < 27) {
                dynamicRealm.where("RealmTransaction").findAll().deleteAllFromRealm();
            }
            if (j < 28) {
                schema.create("RealmCollectiblesItem").addField("id", String.class, new FieldAttribute[0]).addField("category", String.class, new FieldAttribute[0]).addField("imageUrl", String.class, new FieldAttribute[0]).addField("name", String.class, new FieldAttribute[0]).addField("externalLink", String.class, new FieldAttribute[0]).addField("description", String.class, new FieldAttribute[0]).addField("contractAddress", String.class, new FieldAttribute[0]).addField("coin", Integer.TYPE, new FieldAttribute[0]).addField("type", String.class, new FieldAttribute[0]);
            }
            if (j < 29) {
                schema.create("RealmCoinStatus").addField("status", Boolean.TYPE, new FieldAttribute[0]).addField("message", String.class, new FieldAttribute[0]).addField("type", String.class, new FieldAttribute[0]).addField("contract", String.class, new FieldAttribute[0]).addField("coinId", Integer.TYPE, new FieldAttribute[0]);
            }
            if (j < 33) {
                it = dynamicRealm.where("RealmAsset").findAll().iterator();
                while (it.hasNext()) {
                    dynamicRealmObject = (DynamicRealmObject) it.next();
                    if (dynamicRealmObject.getInt("type") == 1) {
                        dynamicRealmObject.deleteFromRealm();
                    }
                }
                schema.get("RealmTransaction").addIndex("from").addIndex("to");
                schema.get("RealmTransactionOperation").addIndex("from").addIndex("to");
            }
            if (j < 34) {
                schema.get("RealmAsset").addField("contract", String.class, new FieldAttribute[0])
                        .removeField("itemId")
                        .removeField("annotation")
                        .removeField("category")
                        .removeField("coverUri")
                        .removeField("externalUri")
                        .removeField("total")
                        .removePrimaryKey()
                        .transform(obj -> obj.setString("id", String.format("addr%s-coin%s-type%s", new Object[]{obj.getString(Address.TYPE_NAME), Integer.valueOf(obj.getInt("coinType")), Integer.valueOf(obj.getInt("type"))}))).addPrimaryKey("id");
            }
            if (j < 36) {
                schema.get("RealmCoinStatus").addField("isBuyCryptoAvailable", Boolean.TYPE, new FieldAttribute[0]);
            }
            if (j < 37) {
                schema.get("RealmCoinStatus").addField("url", String.class, new FieldAttribute[0]);
            }
            if (j < 38) {
                schema.get("RealmTransaction")
                        .addField("fee", String.class, new FieldAttribute[0])
                        .transform(obj -> {
                            BigInteger bigInteger;
                            long parseLong;
                            String string = obj.getString("gasPrice");
                            String string2 = obj.getString("gasUsed");
                            Slip find = Slip.find(obj.getInt("coinType"));
                            try {
                                if (TextUtils.isEmpty(string)) {
                                    string = "1";
                                }
                                bigInteger = new BigInteger(string);
                                parseLong = Long.parseLong(string2);
                            } catch (NumberFormatException unused) {
                                bigInteger = BigInteger.ONE;
                                parseLong = 1;
                            }
                            obj.setString("fee", new Fee(bigInteger, parseLong, find).calculateNetworkFee().toString());
                        }).removeField("gas").removeField("gasUsed").removeField("gasPrice");
            }
            if (j < 39) {
                schema.get("RealmCoinStatus").addField("urlType", String.class, new FieldAttribute[0]);
            }
            if (j < 41 && schema.get("RealmStatusInfo") == null) {
                realmObjectSchema = schema.create("RealmStatusLink").addField("id", String.class, new FieldAttribute[0]).addField("url", String.class, new FieldAttribute[0]).addField("type", String.class, new FieldAttribute[0]).addField("imageUrl", String.class, new FieldAttribute[0]).addField("title", String.class, new FieldAttribute[0]).addField("description", String.class, new FieldAttribute[0]).addField("actionTitle", String.class, new FieldAttribute[0]).addField("isShowed", Boolean.TYPE, new FieldAttribute[0]);
                schema.get("RealmCoinStatus").removeField("message").removeField("type").removeField("urlType").removeField("url").addRealmObjectField("link", realmObjectSchema).addRealmObjectField("info", schema.create("RealmStatusInfo").addField("status", String.class, new FieldAttribute[0]).addField("type", String.class, new FieldAttribute[0]).addField("url", String.class, new FieldAttribute[0]).addField("description", String.class, new FieldAttribute[0]));
            }
            if (j < 43) {
                dynamicRealm.where("RealmTransaction").findAll().deleteAllFromRealm();
                schema.get("RealmTransaction").removeField("operations").removeField("isPending").addField("tokenName", String.class, new FieldAttribute[0]).addField("tokenId", String.class, new FieldAttribute[0]).addField("type", String.class, new FieldAttribute[0]).addField("status", String.class, new FieldAttribute[0]).addField("direction", String.class, new FieldAttribute[0]);
            }
            if (j < 44) {
                realmObjectSchema = schema.get("RealmAsset");
                if (realmObjectSchema != null) {
                    realmObjectSchema
                            .addField("tokenId", String.class, new FieldAttribute[0])
                            .transform(obj -> {
                                int i = obj.getInt("coinType");
                                int i2 = obj.getInt("type");
                                if (i2 != 1) {
                                    if (i == Slip.TRX.coinType().value()) {
                                        try {
                                            obj.setString("tokenId", TronUtils.getTronTokenId(obj.getString("contract")));
                                        } catch (Exception unused) {
                                        }
                                    } else if (i == Slip.THETA.coinType().value() && i2 == 4) {
                                        obj.setString("tokenId", "tfuel");
                                    } else {
                                        obj.setString("tokenId", obj.getString(Address.TYPE_NAME));
                                    }
                                }
                            });
                }
            }
            if (j < 45) {
                dynamicRealm.where("RealmTransaction").findAll().deleteAllFromRealm();
                schema.get("RealmTransaction").addField("owner", String.class, new FieldAttribute[0]);
            }
            if (j < 46) {
                dynamicRealm.where("RealmTransaction").findAll().deleteAllFromRealm();
                realmObjectSchema = schema.get("RealmTransaction");
                if (realmObjectSchema != null) {
                    realmObjectSchema.addIndex("hash");
                }
            }
            if (j < 47) {
                dynamicRealm.where("RealmTransaction").findAll().deleteAllFromRealm();
            }
            if (j < 48) {
                dynamicRealm.where("RealmTransaction").findAll().deleteAllFromRealm();
                realmObjectSchema2 = schema.get("RealmTransaction");
                if (realmObjectSchema2 != null) {
                    realmObjectSchema2.addField("memo", String.class, new FieldAttribute[0]);
                }
            }
            if (j < 49) {
                realmObjectSchema2 = schema.get("RealmTransaction");
                if (realmObjectSchema2 != null) {
                    realmObjectSchema2.addField("title", String.class, new FieldAttribute[0]).addField("outCoin", Integer.TYPE, new FieldAttribute[0]).addField("outTokenId", String.class, new FieldAttribute[0]).addField("outSymbol", String.class, new FieldAttribute[0]).addField("outDecimals", Integer.TYPE, new FieldAttribute[0]).addField("outPrice", String.class, new FieldAttribute[0]).addField("outValue", String.class, new FieldAttribute[0]).addField("swapDirection", String.class, new FieldAttribute[0]);
                }
            }
        }
    }

    private static class DappLinksMigration implements RealmMigration {
        private DappLinksMigration() {
        }

        public void migrate(DynamicRealm dynamicRealm, long j, long j2) {
            if (j <= 9) {
                RealmSchema schema = dynamicRealm.getSchema();
                schema.create("RealmDappLink").addField("id", String.class, FieldAttribute.PRIMARY_KEY).addField("url", String.class, new FieldAttribute[0]).addField("name", String.class, new FieldAttribute[0]).addField("addTime", Long.TYPE, new FieldAttribute[0]).addField("type", Integer.TYPE, new FieldAttribute[0]);
                RealmResults findAll = dynamicRealm.where("RealmBookmark").findAll();
                if (!findAll.isEmpty()) {
                    Iterator it = findAll.iterator();
                    while (it.hasNext()) {
                        DynamicRealmObject dynamicRealmObject = (DynamicRealmObject) it.next();
                        String valueOf = String.valueOf(dynamicRealmObject.getLong("addTime"));
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(valueOf);
                        stringBuilder.append(dynamicRealmObject.getString("url"));
                        DynamicRealmObject createObject = dynamicRealm.createObject("RealmDappLink", stringBuilder.toString());
                        createObject.setString("url", dynamicRealmObject.getString("url"));
                        createObject.setString("name", dynamicRealmObject.getString("name"));
                        createObject.setLong("addTime", dynamicRealmObject.getLong("addTime"));
                        createObject.setInt("type", Type.bookmark.getValue());
                    }
                }
                schema.remove("RealmBookmark");
            }
            if (j < 11) {
                RealmSchema schema2 = dynamicRealm.getSchema();
                schema2.create("RealmDapp").addField("id", String.class, new FieldAttribute[0]).addField("name", String.class, new FieldAttribute[0]).addField("url", String.class, new FieldAttribute[0]).addField("description", String.class, new FieldAttribute[0]).addField("coin", Integer.TYPE, new FieldAttribute[0]).addField("image", String.class, new FieldAttribute[0]).addField("pageImage", String.class, new FieldAttribute[0]);
                schema2.create("RealmDappCategory").addField("id", String.class, new FieldAttribute[0]).addField("name", String.class, new FieldAttribute[0]).addField("order", Integer.TYPE, new FieldAttribute[0]).addField("limit", Integer.TYPE, new FieldAttribute[0]).addField("slug", String.class, new FieldAttribute[0]).addRealmListField("items", schema2.get("RealmDapp"));
                schema2.create("RealmDappDoc").addField("tag", String.class, new FieldAttribute[0]).addRealmListField("categories", schema2.get("RealmDappCategory"));
                schema2.get("RealmDappLink")
                        .addField("coin", Integer.TYPE, new FieldAttribute[0])
                        .transform(obj -> {
                            obj.setString("url", obj.getString("url").split("\\?")[0]);
                            obj.setInt("coin", Slip.ETH.coinType().value());
                        });
            }
        }
    }

    private static class GoodGatekeep implements Gatekeeper {
        private GoodGatekeep() {
        }

        public boolean impulse(String str) {
            return false;
        }

        public boolean rancor(File file) {
            return file.length() * 5 > new StatFs(file.getAbsolutePath()).getAvailableBytes();
        }
    }

    private static class WalletsMigration implements RealmMigration {
        private WalletsMigration() {
        }

        public void migrate(DynamicRealm dynamicRealm, long j, long j2) {
            RealmObjectSchema realmObjectSchema;
            RealmSchema schema = dynamicRealm.getSchema();
            if (j <= 10) {
                if (schema.get("RealmWalletName") != null) {
                    schema.remove("RealmWalletName");
                }
                schema.create("RealmAccount").addField("walletId", String.class, FieldAttribute.PRIMARY_KEY).addField("walletName", String.class, new FieldAttribute[0]).addField("userBackedUp", Boolean.TYPE, new FieldAttribute[0]);
            }
            if (j <= 11) {
                schema.create("RealmWallet")
                        .addField("id", String.class, FieldAttribute.PRIMARY_KEY)
                        .addField("name", String.class, new FieldAttribute[0])
                        .addField("type", Integer.TYPE, new FieldAttribute[0])
                        .addField("data", byte[].class, new FieldAttribute[0])
                        .addField("isMainWallet", Boolean.TYPE, new FieldAttribute[0])
                        .addField("isSkipBackup", Boolean.TYPE, new FieldAttribute[0])
                        .addRealmListField("accounts", schema.create("RealmAccount")
                                .addField("address", String.class, new FieldAttribute[0])
                                .addField("name", String.class, new FieldAttribute[0])
                                .addField("coinType", Integer.TYPE, new FieldAttribute[0]));
            }
            if (j <= 12) {
                schema.get("RealmWallet").transform(dynamicRealmObject -> {
                    if (dynamicRealmObject.getBoolean("isMainWallet")) {
                        dynamicRealmObject.setInt("type", 3);
                    }
                }).removeField("isMainWallet");
            }
            if (j <= 14) {
                realmObjectSchema = schema.get("RealmAccount");
                if (realmObjectSchema != null) {
                    realmObjectSchema.addField("publicKey", String.class, new FieldAttribute[0]);
                }
            }
            if (j <= 15) {
                realmObjectSchema = schema.get("RealmAccount");
                if (realmObjectSchema != null) {
                    realmObjectSchema.addField("extendedPublicKey", String.class, new FieldAttribute[0]);
                    realmObjectSchema.removeField("publicKey");
                }
            }
            if (j < 17) {
                schema.create("RealmAccountIndex").addField("publicKey", String.class, new FieldAttribute[0]).addField(Address.TYPE_NAME, String.class, new FieldAttribute[0]).addField("change", Integer.TYPE, new FieldAttribute[0]).addField("index", Integer.TYPE, new FieldAttribute[0]).addIndex("publicKey");
            }
            if (j < 18) {
                RealmObjectSchema realmObjectSchema2 = schema.get("RealmAccountIndex");
                if (realmObjectSchema2 != null) {
                    dynamicRealm.where("RealmAccountIndex").findAll().deleteAllFromRealm();
                    realmObjectSchema2.addField("path", String.class, new FieldAttribute[0]).removeField("change").removeField("index");
                }
            }
        }
    }

    private byte[] getDbPass() {
        Builder builder = new Builder();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.dbPrefix);
        stringBuilder.append("wallets_info");
        try {
            if (!new File(builder.name(stringBuilder.toString()).build().getPath()).exists()) {
                this.passwordStore.setPassword("dbkey", this.passwordStore.getPassword("salt"));
            }
            return hash(this.passwordStore.getPassword("dbkey"));
        } catch (Throwable unused) {
            return null;
        }
    }

    private String getName(Wallet wallet) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.dbPrefix);
        stringBuilder.append(wallet.id);
        stringBuilder.append("-db.realm");
        return stringBuilder.toString();
    }

    private RealmConfiguration getRealmConfiguration(String str, int i, byte[] bArr, RealmMigration realmMigration, Object obj) {
        RealmConfiguration realmConfiguration = (RealmConfiguration) this.realmConfigurations.get(str);
        if (realmConfiguration != null) {
            return realmConfiguration;
        }
        Builder migration = new Builder().name(str).schemaVersion((long) i).modules(obj, new Object[0]).migration(realmMigration);
        if (bArr != null && bArr.length > 0) {
            migration.encryptionKey(bArr);
        }
        realmConfiguration = migration.build();
        this.realmConfigurations.put(str, realmConfiguration);
        return realmConfiguration;
    }

    private RealmConfiguration getWalletsConfig() {
        byte[] dbPass = getDbPass();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.dbPrefix);
        stringBuilder.append("wallets_info");
        return getRealmConfiguration(stringBuilder.toString(), 18, dbPass, new WalletsMigration(), new WalletsSchema());
    }

    private byte[] hash(String str) {
        return TextUtils.isEmpty(str) ? new byte[0] : hash(str.getBytes());
    }

    public synchronized void deleteCache(Session session) {
        try {
            Realm.deleteRealm(getRealmConfiguration(getName(session.wallet), 49, null, new CacheMigration(), new CacheSchema()));
        } catch (Exception e) {
            Log.d("TAG", "", e);
        }
        return;
    }

    public void deleteDapp() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.dbPrefix);
        stringBuilder.append("bookmarks");
        try {
            Realm.deleteRealm(getRealmConfiguration(stringBuilder.toString(), 11, null, new DappLinksMigration(), new DappLinksSchema()));
        } catch (Exception e) {
            Log.d("TAG", "", e);
        }
    }

    public void deleteWallets() {
        try {
            this.walletsLocker.lock();
            Realm.deleteRealm(getWalletsConfig());
        } finally {
            this.walletsLocker.unlock();
        }
    }

    public void encrypt(byte[] r10) throws java.lang.Exception {
        try {
            this.walletsLocker.lock();
            RealmConfiguration r0 = this.getWalletsConfig();
            File r1 = r0.getRealmDirectory();
            File r2 = new File(r0.getPath());
            File r3 = new File(r1, this.dbPrefix + "temp_encrypted");
            if (!this.gatekeeper.rancor(r1)) {
                Realm r4 = Realm.getInstance(r0);
                Throwable ex = null;
                try {
                    try {
                        this.gatekeeper.impulse("encrypt");
                        byte[] hash = this.hash(r10);
                        if (r3.exists())
                            r3.delete();

                        r4.writeEncryptedCopyTo(r3, hash);
                        this.passwordStore.setPassword("dbkey", new String(r10));
                        if (!new String(r10).equals(this.passwordStore.getPassword("dbkey"))
                                || this.gatekeeper.impulse("set-pass"))
                            throw new Exception();
                    } catch (Throwable e) {
                        ex = e;
                        throw e;
                    }
                } finally {
                    if (r4 != null) {
                        if (ex != null) {
                            try {
                                r4.close();
                            } catch (Throwable e) {
                                ex.addSuppressed(e);
                                throw ex;
                            }
                        } else
                            r4.close();
                    }

                }
                File[] files = r1.listFiles();
                int len = files.length;
                for (int i = 0; i < len; i++) {
                    if (files[i].getName().contains("wallets_info")) {
                        try {
                            files[i].delete();
                        } catch (Throwable e) {

                        }
                    }
                }
                r3.renameTo(r2);
                this.realmConfigurations.remove(r0.getRealmFileName());

            } else
                throw new IOException();
        } catch (Throwable e) {
        }
        this.walletsLocker.unlock();
    }

    public synchronized Realm getCache(Session session) {
        RealmConfiguration realmConfiguration;
        realmConfiguration = getRealmConfiguration(getName(session.wallet), 49, null, new CacheMigration(), new CacheSchema());
        try {
            return Realm.getInstance(realmConfiguration);
        } catch (Exception e) {
            Log.d("REALM_EX", "", e);
            Realm.deleteRealm(realmConfiguration);
            return Realm.getInstance(realmConfiguration);
        }
    }

    public synchronized Realm getDappLinks() {
        RealmConfiguration r0 = this.getRealmConfiguration(this.dbPrefix + "bookmarks", 11, null, new DappLinksMigration(), new DappLinksSchema());
        try {
            return Realm.getInstance(r0);
        } catch (Exception e) {
            Realm.deleteRealm(r0);
            return Realm.getInstance(r0);
        }
    }

    public synchronized <T> T getWallets(RealmManager.WalletsOperation<T> r7) {
        RealmConfiguration r1 = null;
        T ret;
        try {
            this.walletsLocker.lock();
            r1 = this.getWalletsConfig();
            try {
                Realm r2 = Realm.getInstance(r1);
                try {
                    ret = r7.execute(r2);
                } catch (Throwable e) {
                    throw e;
                } finally {
                    if (r2 != null)
                        r2.close();
                }
            } catch (RealmFileException | RealmError e) {
                if (e instanceof RealmFileException && ((RealmFileException) e).getKind() == RealmFileException.Kind.ACCESS_ERROR || e.getMessage().contains("Unrecoverable error")) {
                    this.deleteWallets();
                    ret = this.getWallets(r7);
                } else
                    ret = null;
            }
        }finally {
            if (r1 != null) {
                this.realmConfigurations.remove(r1.getRealmFileName());
            }
            this.walletsLocker.unlock();
        }
        return ret;
    }

    private byte[] hash(byte[] bArr) {
        Keccak.Digest512 keccak$Digest512 = new Keccak.Digest512();
        keccak$Digest512.update(bArr, 0, bArr.length);
        return keccak$Digest512.digest();
    }

}
