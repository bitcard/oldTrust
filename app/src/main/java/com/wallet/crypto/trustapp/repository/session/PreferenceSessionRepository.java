package com.wallet.crypto.trustapp.repository.session;

import com.wallet.crypto.trustapp.entity.Session;
import com.wallet.crypto.trustapp.repository.PreferenceRepositoryType;
import com.wallet.crypto.trustapp.repository.wallet.WalletsRepository;
import io.reactivex.Completable;
import io.reactivex.Single;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;
import trust.blockchain.entity.Wallet;

public class PreferenceSessionRepository implements SessionRepository {
    /* renamed from: a */
    private final PreferenceRepositoryType f19248a;
    /* renamed from: b */
    private final WalletsRepository f19249b;
    /* renamed from: c */
    private final Object f19250c = new Object();
    /* renamed from: d */
    private final Set<WeakReference<OnSessionChangeListener>> f19251d = new HashSet();

    public PreferenceSessionRepository(PreferenceRepositoryType preferenceRepositoryType, WalletsRepository walletsRepository) {
        this.f19248a = preferenceRepositoryType;
        this.f19249b = walletsRepository;
    }

    private Single<Wallet> findDefaultWallet() {
        return this.f19249b.findMainWallet().onErrorResumeNext(this.f19249b.fetch().map(C2252g.f19260a)).flatMapCompletable(new C2254i(this)).andThen(getWalletFromPreferences());
    }

    private Single<Wallet> getWalletFromPreferences() {
        PreferenceRepositoryType preferenceRepositoryType = this.f19248a;
        preferenceRepositoryType.getClass();
        Single fromCallable = Single.fromCallable(new C1451b(preferenceRepositoryType));
        WalletsRepository walletsRepository = this.f19249b;
        walletsRepository.getClass();
        return fromCallable.flatMap(new C2253h(walletsRepository));
    }

    public void addOnSessionChangeListener(OnSessionChangeListener onSessionChangeListener) {
        this.f19251d.add(new WeakReference(onSessionChangeListener));
    }

    public Session getSession() {
        Wallet walletById = this.f19249b.getWalletById(this.f19248a.getWalletId());
        if (walletById == null) {
            for (Wallet wallet : this.f19249b.getAllWallet()) {
                if (wallet.type == 3 || walletById == null) {
                    walletById = wallet;
                }
            }
        }
        if (walletById != null) {
            return new Session(walletById, this.f19248a.getCurrencyCode());
        }
        throw new IllegalStateException("Main wallet is not found");
    }

    public Single<Session> getSessionOperator() {
        return getWalletFromPreferences().onErrorResumeNext(findDefaultWallet())
                .map(wallet -> new Session(wallet, f19248a.getCurrencyCode()));
    }

    public Completable notifySessionChanged(Session session) {
        return Completable.fromAction(()->PreferenceSessionRepository.m70a(this, session));
    }

    public Completable setCurrencyCode(String str) {
        return Completable.fromAction(()->f19248a.setCurrencyCode(str))
                .andThen(getSessionOperator()).flatMapCompletable(new C2247a(this));
    }

    public Completable setWallet(Wallet wallet) {
        return Completable.fromAction(()->f19248a.setWalletId(wallet.id))
                .andThen(getSessionOperator()).flatMapCompletable(new C2247a(this));
    }

    public static void m70a(PreferenceSessionRepository r5, Session r6) throws Exception {
        synchronized (r5.f19250c) {
            HashSet<WeakReference<OnSessionChangeListener>> r1=  new HashSet<>();
            for (WeakReference<OnSessionChangeListener> listenerWeakReference : r5.f19251d) {
                try {
                    OnSessionChangeListener listener = listenerWeakReference.get();
                    if (listener == null) {
                        r1.add(listenerWeakReference);
                    } else {
                        listener.onSessionChanged(r6);
                    }
                } catch (Exception e) {

                }
            }
            r5.f19251d.removeAll(r1);
        }
    }
}
