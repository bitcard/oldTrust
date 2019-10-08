package com.wallet.crypto.trustapp.ui.passcode.viewmodel;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.wallet.crypto.trustapp.repository.PasscodeRepositoryType;
import com.wallet.crypto.trustapp.router.FromPinRouter;
import com.wallet.crypto.trustapp.router.PasscodeRouter;

public class PasscodeViewModel extends ViewModel {
    /* renamed from: a */
    private final Handler f19864a = new Handler(Looper.getMainLooper());
    /* renamed from: b */
    private MutableLiveData<Long> f19865b = new MutableLiveData();
    /* renamed from: c */
    private final PasscodeRepositoryType f19866c;
    /* renamed from: d */
    private final PasscodeRouter f19867d;
    /* renamed from: e */
    private final FromPinRouter f19868e;
    /* renamed from: f */
    private String f19869f;
    /* renamed from: g */
    private int f19870g;
    /* renamed from: h */
    private boolean f19871h;
    /* renamed from: i */
    private String f19872i;
    /* renamed from: j */
    private final Runnable f19873j = new C15451();

    /* renamed from: com.wallet.crypto.trustapp.ui.passcode.viewmodel.PasscodeViewModel$1 */
    class C15451 implements Runnable {
        C15451() {
        }

        public void run() {
            PasscodeViewModel.this.f19864a.removeCallbacks(PasscodeViewModel.this.f19873j);
            long unlockTime = PasscodeViewModel.this.f19866c.unlockTime();
            PasscodeViewModel.this.f19865b.postValue(Long.valueOf(unlockTime));
            if (unlockTime > System.currentTimeMillis()) {
                PasscodeViewModel.this.lockApp(500);
            } else {
                PasscodeViewModel.this.f19865b.postValue(null);
            }
        }
    }

    public PasscodeViewModel(PasscodeRepositoryType passcodeRepositoryType, PasscodeRouter passcodeRouter, FromPinRouter fromPinRouter) {
        this.f19866c = passcodeRepositoryType;
        this.f19867d = passcodeRouter;
        this.f19868e = fromPinRouter;
    }

    private void lockApp(long j) {
        this.f19864a.postDelayed(this.f19873j, j);
    }

    private void route(Activity activity) {
        this.f19868e.route(activity, this.f19870g, this.f19869f);
    }

    public int action() {
        return this.f19870g;
    }

    public boolean check(Activity activity, String str) throws Exception {
        switch (this.f19870g) {
            case 1:
                this.f19867d.openToRepeat(activity, str);
                return true;
            case 2:
            case 3:
            case 5:
                int compare = this.f19866c.compare(str);
                if (compare == 0) {
                    if (this.f19870g == 2) {
                        this.f19866c.delete();
                    }
                    if (this.f19871h) {
                        route(activity);
                    }
                } else if (compare < 0) {
                    lockApp(500);
                }
                if (compare != 0) {
                    return true;
                }
                return false;
            case 4:
                if (!this.f19872i.equals(str)) {
                    return false;
                }
                this.f19866c.set(str);
                this.f19868e.route(activity, this.f19870g, null);
                return true;
            default:
                return false;
        }
    }

    public void fingerprintAuthenticated(Activity activity) throws Exception {
        if (this.f19870g == 2) {
            this.f19866c.delete();
        }
        route(activity);
    }

    public void init(Intent intent) {
        this.f19869f = this.f19867d.tag(intent);
        this.f19870g = this.f19867d.action(intent);
        this.f19872i = this.f19867d.getPinCode(intent);
        this.f19871h = true;
        lockApp(0);
    }

    protected void onCleared() {
        super.onCleared();
        this.f19864a.removeCallbacks(this.f19873j);
    }

    public LiveData<Long> unlockTime() {
        return this.f19865b;
    }

    public void init(int i, boolean z) {
        this.f19870g = i;
        this.f19871h = z;
        lockApp(0);
    }
}
