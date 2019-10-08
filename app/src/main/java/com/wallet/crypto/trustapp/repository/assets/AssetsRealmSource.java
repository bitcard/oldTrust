package com.wallet.crypto.trustapp.repository.assets;

import android.text.TextUtils;
import com.wallet.crypto.trustapp.entity.AssetStatus;
import com.wallet.crypto.trustapp.entity.LinkType;
import com.wallet.crypto.trustapp.entity.Session;
import com.wallet.crypto.trustapp.entity.StatusInfo;
import com.wallet.crypto.trustapp.entity.StatusLink;
import com.wallet.crypto.trustapp.entity.TokenTicker;
import com.wallet.crypto.trustapp.repository.entity.RealmAsset;
import com.wallet.crypto.trustapp.repository.entity.RealmCoinStatus;
import com.wallet.crypto.trustapp.repository.entity.RealmStatusInfo;
import com.wallet.crypto.trustapp.repository.entity.RealmStatusLink;
import com.wallet.crypto.trustapp.repository.entity.RealmTokenTicker;
import com.wallet.crypto.trustapp.service.RealmManager;

import org.w3c.dom.Text;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import trust.blockchain.PriceAddress;
import trust.blockchain.Slip;
import trust.blockchain.entity.Account;
import trust.blockchain.entity.Address;
import trust.blockchain.entity.Asset;
import trust.blockchain.entity.Contract;
import trust.blockchain.entity.PlainAddress;
import trust.blockchain.entity.Ticker;
import trust.blockchain.entity.Unit;
import trust.blockchain.entity.Value;
import trust.blockchain.entity.Wallet;

public class AssetsRealmSource implements AssetsLocalSource {
    /* renamed from: a */
    private final RealmManager f19203a;

    public AssetsRealmSource(RealmManager realmManager) {
        this.f19203a = realmManager;
    }

    private AssetStatus convert(RealmCoinStatus realmCoinStatus) {
        StatusLink statusLink;
        RealmStatusLink link = realmCoinStatus.getLink();
        RealmStatusInfo info = realmCoinStatus.getInfo();
        StatusInfo statusInfo = null;
        if (link != null) {
            statusLink = new StatusLink(link.getId(), link.getUrl(), TextUtils.isEmpty(link.getType()) ? LinkType.DAPP.getType() : link.getType(), link.getImageUrl(), link.getTitle(), link.getDescription(), link.getActionTitle(), link.isShowed());
        } else {
            statusLink = null;
        }
        if (info != null) {
            statusInfo = new StatusInfo(info.getType(), info.getStatus(), info.getUrl(), info.getDescription());
        }
        return new AssetStatus(realmCoinStatus.isStatus(), statusInfo, statusLink, realmCoinStatus.isBuyCryptoAvailable());
    }

    private Asset convertAsset(Wallet wallet, Map<String, Ticker> map, RealmAsset realmAsset) {
        Slip find = Slip.find(realmAsset.getCoinType());
        Contract contract = new Contract(find.toAddress(realmAsset.getAddress()), realmAsset.getContract(), realmAsset.getName(), new Unit(realmAsset.getDecimals(), realmAsset.getSymbol()), find, realmAsset.getTokenId());
        Account account = wallet.account(find);
        String balance = realmAsset.getBalance();
        Asset asset = new Asset(realmAsset.getType(), contract, account, !TextUtils.isEmpty(balance) ? new Value(balance) : null, null, realmAsset.getEnabled(), realmAsset.getAddedManually(), realmAsset.getUpdatedTime());
        return new Asset(asset, (Ticker) map.get(PriceAddress.byAsset(asset).toString()));
    }

    private Asset[] convertAssets(Wallet wallet, RealmResults<RealmAsset> realmResults, Map<String, Ticker> map) {
        int size = realmResults.size();
        Asset[] assetArr = new Asset[size];
        for (int i = 0; i < size; i++) {
            RealmAsset realmAsset = (RealmAsset) realmResults.get(i);
            if (realmAsset != null) {
                assetArr[i] = convertAsset(wallet, map, realmAsset);
            }
        }
        return assetArr;
    }

    private Ticker[] convertTickers(RealmResults<RealmTokenTicker> realmResults) {
        int size = realmResults.size();
        Ticker[] tickerArr = new Ticker[size];
        for (int i = 0; i < size; i++) {
            RealmTokenTicker realmTokenTicker = (RealmTokenTicker) realmResults.get(i);
            if (realmTokenTicker != null) {
                tickerArr[i] = new TokenTicker(new PlainAddress(realmTokenTicker.getContract()), realmTokenTicker.getPrice().equalsIgnoreCase("Nan") ? "0" : realmTokenTicker.getPrice(), realmTokenTicker.getPercentChange24h(), realmTokenTicker.getCurrencyCode(), realmTokenTicker.getUpdatedTime());
            }
        }
        return tickerArr;
    }

    private Map<String, Ticker> getTickerIndex(Realm realm, String str) {
        Ticker[] convertTickers = convertTickers(realm.where(RealmTokenTicker.class).equalTo("currencyCode", str).findAll());
        HashMap hashMap = new HashMap();
        for (Ticker ticker : convertTickers) {
            hashMap.put(ticker.getContract().data(), ticker);
        }
        return hashMap;
    }

    private void saveAsset(Session session, Asset asset, Date date) {
        Realm realm = null;
        try {
            realm = this.f19203a.getCache(session);
            RealmAsset realmAsset = (RealmAsset) realm.where(RealmAsset.class).equalTo("id", asset.id()).findFirst();
            realm.beginTransaction();
            if (realmAsset == null) {
                realmAsset = (RealmAsset) realm.createObject(RealmAsset.class, asset.id());
                realmAsset.setEnabled(asset.isEnabled);
                realmAsset.setAddedTime(date.getTime());
            }
            realmAsset.setAddress(asset.contract.address.toString());
            realmAsset.setContract(asset.contract.contract);
            realmAsset.setType(asset.type);
            realmAsset.setName(asset.contract.name);
            realmAsset.setSymbol(asset.contract.unit.symbol.toUpperCase());
            realmAsset.setDecimals(asset.contract.unit.decimals);
            realmAsset.setAddedManually(asset.isAddedManually);
            realmAsset.setBalance(asset.balance == null ? realmAsset.getBalance() : asset.balance.rawString());
            realmAsset.setAccountAddress(asset.account.address().toString());
            realmAsset.setCoinType(asset.contract.coin.coinType().value());
            realmAsset.setTokenId(asset.contract.tokenId);
            realmAsset.setUpdatedTime(System.currentTimeMillis());
            realm.commitTransaction();
            if (realm != null) {
                realm.close();
            }
        } catch (Exception unused) {
            if (realm != null) {
                realm.cancelTransaction();
            }
            if (realm == null) {
            }
        } catch (Throwable th) {
            if (realm != null) {
                realm.close();
            }
        }
    }

    public void delete(Session r4, Asset r5) {
        try {
            Realm realm = this.f19203a.getCache(r4);
            try {
                realm.beginTransaction();
                RealmAsset realmAsset = realm.where(RealmAsset.class).equalTo("id", r5.id()).findFirst();
                if (realmAsset != null)
                    realmAsset.deleteFromRealm();
                realm.commitTransaction();
            } catch (Exception e) {
                try {
                    realm.cancelTransaction();
                } catch (Throwable t) {
                }
            }
            if (realm != null)
                realm.close();
        } catch (Exception e) {
        }
        return;
    }

    public Ticker[] findTickers(Session session, Asset[] assetArr) {
        int length = assetArr.length;
        Address[] addressArr = new Address[length];
        for (int i = 0; i < length; i++) {
            addressArr[i] = assetArr[i].isCoin() ? new PlainAddress(assetArr[i].coin().coinAddress()) : assetArr[i].contract.address;
        }
        return findTickers(session, addressArr);
    }

    public Asset[] getActive(Session session) {
        Realm cache;
        try {
            cache = this.f19203a.getCache(session);
            try {
                Map tickerIndex = getTickerIndex(cache, session.currencyCode);
                Asset[] convertAssets = convertAssets(session.wallet, cache.where(RealmAsset.class).equalTo("isEnabled", Boolean.valueOf(true)).sort("addedTime", Sort.ASCENDING).findAll(), tickerIndex);

                if (cache != null) {
                    cache.close();
                }
                return convertAssets;
            } catch (Throwable t) {
                if (cache != null) {
                    cache.close();
                }
                throw t;
            }
        } catch (Exception unused) {
            return new Asset[0];
        }
    }

    public Asset[] getAll(Session session) {
        Realm cache;
        try {
            cache = this.f19203a.getCache(session);
            try {
                Map tickerIndex = getTickerIndex(cache, session.currencyCode);
                Asset[] convertAssets = convertAssets(session.wallet, cache.where(RealmAsset.class).sort("addedTime", Sort.ASCENDING).findAll(), tickerIndex);
                if (cache != null) {
                    cache.close();
                }
                return convertAssets;
            } catch (Throwable t) {
                if (cache != null) {
                    cache.close();
                }
                throw t;
            }
        } catch (Exception unused) {
            return new Asset[0];
        }
    }

    public Asset getAssetById(Session session, String str) {
        try {
            Realm cache = this.f19203a.getCache(session);
            try {
                Map tickerIndex = getTickerIndex(cache, session.currencyCode);
                RealmAsset realmAsset = (RealmAsset) cache.where(RealmAsset.class).sort("addedTime", Sort.ASCENDING).equalTo("id", str).findFirst();
                if (realmAsset != null) {
                    Asset convertAsset = convertAsset(session.wallet, tickerIndex, realmAsset);
                    if (cache != null) {
                        cache.close();
                    }
                    return convertAsset;
                }
            } catch (Throwable th22) {
                if (cache != null) {
                    cache.close();
                }
                throw th22;
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public AssetStatus getCoinStatus(Session session, Asset asset) {
        try {
            Realm cache = this.f19203a.getCache(session);
            try {
                AssetStatus convert = convert((RealmCoinStatus) cache.where(RealmCoinStatus.class).equalTo("contract", asset.contract.address.toString()).and().equalTo("coinId", Integer.valueOf(asset.coin().coinType().value())).findFirst());
                if (cache != null) {
                    cache.close();
                }
                return convert;
            } catch (Throwable th22) {
                if (cache != null) {
                    cache.close();
                }
                throw th22;
            }
        } catch (Exception unused) {
            return new AssetStatus(true, null, null, false);
        }
    }

    public void markPopupAsShowed(Session r5, Asset r6) {
        Realm realm = this.f19203a.getCache(r5);
        try {
            RealmCoinStatus realmCoinStatus = realm.where(RealmCoinStatus.class)
                                                    .equalTo("contract", r6.contract.address.toString())
                                                    .and()
                                                    .equalTo("coinId", r6.coin().coinType().value())
                                                    .findFirst();
            if (realmCoinStatus != null) {
                try {
                    realm.beginTransaction();
                    RealmStatusLink link = realmCoinStatus.getLink();
                    if (link != null) {
                        link.setShowed(true);
                    }
                    realm.commitTransaction();
                } catch (Exception e) {
                    realm.cancelTransaction();
                }
            }
        } catch (Throwable t) {
            throw t;
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

    public void saveAssets(Session session, Asset[] assetArr) {
        Date date = new Date();
        for (Asset saveAsset : assetArr) {
            saveAsset(session, saveAsset, date);
        }
    }

    public void saveCoinStatus(Session r8, Asset r9, AssetStatus r10) {
        Realm realm = this.f19203a.getCache(r8);
        try {
            realm.beginTransaction();
            RealmCoinStatus r1 = realm.where(RealmCoinStatus.class)
                    .equalTo("contract", r9.contract.address.toString())
                    .and()
                    .equalTo("coinId", r9.coin().coinType().value())
                    .findFirst();
            if (r1 == null) {
                r1 = realm.createObject(RealmCoinStatus.class);
            }

            StatusLink r2 = r10.getLink();
            StatusInfo r3 = r10.getInfo();
            r1.setStatus(r10.getStatus());
            r1.setBuyCryptoAvailable(r10.isBuyCryptoAvailable());
            r1.setContract(r9.contract.address.toString());
            RealmStatusInfo info = r1.getInfo();
            RealmStatusLink r4 = r1.getLink();
            if (r3 == null) {
                if (info != null) {
                    info.deleteFromRealm();
                }
            } else {
                if (info == null) {
                    info = realm.createObject(RealmStatusInfo.class);
                    r1.setInfo(info);
                }
                info.setType(r3.getType());
                info.setStatus(r3.getStatus());
                info.setDescription(r3.getDescription());
                info.setUrl(r3.getUrl());
            }

            if (r2 == null) {
                if (r4 != null) {
                    r4.deleteFromRealm();
                }
            } else {
                if (r4 == null) {
                    r4 = realm.createObject(RealmStatusLink.class);
                    r1.setLink(r4);
                }
                String id = r4.getId();
                r4.setShowed((!TextUtils.isEmpty(id) & id.equals(r2.getId())) && r4.isShowed());
                r4.setId(r2.getId());
                r4.setUrl(r2.getUrl());
                r4.setType(r2.getType());
                r4.setImageUrl(r2.getImageUrl());
                r4.setTitle(r4.getTitle());
                r4.setActionTitle(r2.getActionTitle());
                r4.setDescription(r2.getDescription());
            }
            r1.setCoinId(r9.coin().coinType().value());
            realm.commitTransaction();
        } catch (Exception e) {
            try {
                realm.cancelTransaction();
            } catch (Throwable t) {
                throw t;
            }
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

    public void saveTickers(Session session, Ticker[] tickerArr) {
        Realm realm = null;
        try {
            realm = this.f19203a.getCache(session);
            realm.beginTransaction();
            long currentTimeMillis = System.currentTimeMillis();
            for (Ticker ticker : tickerArr) {
                RealmTokenTicker realmTokenTicker = (RealmTokenTicker) realm.where(RealmTokenTicker.class).equalTo("contract", ticker.getContract().toString()).equalTo("currencyCode", ticker.getCurrencyCode()).findFirst();
                if (realmTokenTicker == null) {
                    realmTokenTicker = (RealmTokenTicker) realm.createObject(RealmTokenTicker.class);
                    realmTokenTicker.setContract(ticker.getContract().toString());
                    realmTokenTicker.setCreatedTime(currentTimeMillis);
                    realmTokenTicker.setCurrencyCode(ticker.getCurrencyCode());
                }
                Double percentChange24h = ticker.percentChange24h();
                realmTokenTicker.setPercentChange24h(percentChange24h.equals(Double.valueOf(Double.NaN)) ? "0" : percentChange24h.toString());
                realmTokenTicker.setPrice(ticker.getPrice());
                realmTokenTicker.setUpdatedTime(currentTimeMillis);
            }
            realm.commitTransaction();
        } catch (Exception unused) {
            if (realm != null) {
                realm.cancelTransaction();
            }
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

    public void setEnable(Session r4, Asset r5, boolean r6) {
        try {
            Realm realm = this.f19203a.getCache(r4);
            try {
                RealmAsset realmAsset = realm.where(RealmAsset.class).equalTo("address", r5.contract.address.toString()).findFirst();
                try {
                    realm.beginTransaction();
                    if (realmAsset != null)
                        realmAsset.setEnabled(r6);
                    realm.commitTransaction();
                } catch (Exception e) {
                    realm.cancelTransaction();
                }
            } catch (Throwable t) {
                throw t;
            } finally {
                if (realm != null)
                    realm.close();
            }
        } catch (Exception e) {

        }
    }

    public void update(Session r21, Asset[] r22) {
        Map<String, Asset> r3 = new HashMap<>();
        for (Asset asset : r22) {
            r3.put(asset.id(), asset);
        }

        Realm realm = this.f19203a.getCache(r21);
        try {
            for (RealmAsset realmAsset : realm.where(RealmAsset.class).findAll()) {
                String r8 = realmAsset.getId();
                Asset r9 = r3.get(r8);
                if (r9 != null) {
                    r3.put(r8, new Asset(realmAsset.getType(),
                                            r9.contract,
                                            new Account(Slip.find(realmAsset.getCoinType()), "", realmAsset.getAccountAddress()),
                                            new Value(realmAsset.getBalance()),
                                            null,
                                            realmAsset.getEnabled(),
                                            false,
                                            realmAsset.getUpdatedTime()));
                }
            }
        } catch (Throwable t) {
            throw t;
        } finally {
            if (realm != null)
                realm.close();
        }
        saveAssets(r21, r3.values().toArray(new Asset[0]));
    }

    public void updateAssetBalance(Session r8, Asset[] r9) {
        Realm realm = this.f19203a.getCache(r8);
        try {
            Map<String, Asset> r1 = new HashMap<>();
            for (Asset asset : r9) {
                r1.put(asset.id(), asset);
            }

            RealmResults<RealmAsset> results = realm.where(RealmAsset.class).in("id", (String[])r1.keySet().toArray(new String[0])).findAll();
            try {
                realm.beginTransaction();
                for (RealmAsset result : results) {
                    if (result != null) {
                        Asset r3 = r1.get(result.getId());
                        if (r3 != null && r3.balance != null) {
                            result.setBalance(r3.balance.rawString());
                        }
                    }
                }
                realm.commitTransaction();
            } catch (Throwable t) {
                realm.cancelTransaction();
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (realm != null)
                realm.close();
        }
    }

    public Ticker[] findTickers(Session session, Address[] addressArr) {
        Throwable th;
        Realm cache = this.f19203a.getCache(session);
        try {
            RealmQuery equalTo = cache.where(RealmTokenTicker.class).equalTo("currencyCode", session.currencyCode);
            if (addressArr != null && addressArr.length > 0) {
                int length = addressArr.length;
                String[] strArr = new String[length];
                for (int i = 0; i < length; i++) {
                    strArr[i] = addressArr[i].toString();
                }
                equalTo.in("contract", strArr);
            }
            Ticker[] convertTickers = convertTickers(equalTo.findAll());
            if (cache != null) {
                cache.close();
            }
            return convertTickers;
        } catch (Throwable th2) {
            if (cache != null) {
                cache.close();
            }
            throw th2;
        }
    }
}
