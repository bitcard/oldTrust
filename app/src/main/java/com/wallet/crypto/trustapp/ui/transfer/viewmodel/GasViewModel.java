package com.wallet.crypto.trustapp.ui.transfer.viewmodel;

import android.text.TextUtils;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.ui.BaseViewModel;
import com.wallet.crypto.trustapp.ui.transfer.fragment.GasSettingsFragment.EventListener;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.web3j.utils.Numeric;
import trust.blockchain.FeeCalculator;
import trust.blockchain.entity.Fee;
import trust.blockchain.entity.SubunitValue;
import trust.blockchain.entity.TransactionUnsigned;

public class GasViewModel extends BaseViewModel {
    /* renamed from: d */
    private TransactionUnsigned f21535d;
    /* renamed from: e */
    private final MutableLiveData<Integer> f21536e = new MutableLiveData();
    /* renamed from: f */
    private final MutableLiveData<Integer> f21537f = new MutableLiveData();
    /* renamed from: g */
    private final MutableLiveData<Integer> f21538g = new MutableLiveData();

    private boolean networkFeeValidate(FeeCalculator feeCalculator, CharSequence charSequence, CharSequence charSequence2) {
        int validateLimit = validateLimit(charSequence);
        int validatePrice = validatePrice(charSequence2);
        if (validateLimit == 0) {
            if (validatePrice == 0) {
                int compareTo = new BigDecimal(feeCalculator.feeMax()).compareTo(new BigDecimal(feeCalculator.priceToWei(charSequence.toString())).multiply(new BigDecimal(charSequence2.toString())));
                if (compareTo >= 0) {
                    return true;
                }
                this.f21538g.setValue(Integer.valueOf(compareTo));
                return false;
            }
        }
        this.f21536e.postValue(Integer.valueOf(validateLimit));
        this.f21537f.postValue(Integer.valueOf(validatePrice));
        return false;
    }

    public LiveData<Integer> getFeeError() {
        return this.f21538g;
    }

    public LiveData<Integer> getLimitError() {
        return this.f21536e;
    }

    public LiveData<Integer> getPriceError() {
        return this.f21537f;
    }

    public void init(TransactionUnsigned transactionUnsigned) {
        this.f21535d = transactionUnsigned;
    }

    public String maxFee() {
        return new SubunitValue(this.f21535d.asset().coin().feeCalculator().feeMax(), this.f21535d.asset().coin().unit()).shortFormat("", -1);
    }

    public void save(CharSequence charSequence, CharSequence charSequence2, long j, String str, EventListener eventListener) {
        this.f21538g.setValue(Integer.valueOf(0));
        this.f21537f.setValue(Integer.valueOf(0));
        this.f21536e.setValue(Integer.valueOf(0));
        if (networkFeeValidate(this.f21535d.asset().coin().feeCalculator(), charSequence2, charSequence)) {
            Fee fee = new Fee(this.f21535d.fee(), charSequence.toString(), charSequence2.toString());
            byte[] bArr = null;
            if (this.f21535d.canAttachData() && !TextUtils.isEmpty(str) && str.length() > 2) {
                bArr = Numeric.hexStringToByteArray(str);
            }
            this.f21535d.fee(fee);
            this.f21535d.data(bArr);
            this.f21535d.nonce(j);
            eventListener.onComplete(this.f21535d);
        }
    }

    public TransactionUnsigned transaction() {
        return this.f21535d;
    }

    public int validateLimit(CharSequence charSequence) {
        try {
            return validateLimit(Long.valueOf(charSequence.toString()).longValue());
        } catch (NumberFormatException unused) {
            return R.string.MustNumericValue;
        }
    }

    public int validatePrice(CharSequence charSequence) {
        try {
            return validatePrice(this.f21535d.fee().energy().coin().feeCalculator().priceToWei(charSequence.toString()));
        } catch (NumberFormatException unused) {
            return R.string.MustNumericValue;
        }
    }

    public int validateLimit(long j) {
        int isValidLimit = this.f21535d.fee().energy().coin().feeCalculator().isValidLimit(j);
        if (isValidLimit < 0) {
            return R.string.configureTransaction_tooLow_textField_message;
        }
        return isValidLimit > 0 ? R.string.configureTransaction_tooHigh_textField_message : 0;
    }

    public int validatePrice(BigInteger bigInteger) {
        int isValidPrice = this.f21535d.fee().energy().coin().feeCalculator().isValidPrice(bigInteger);
        if (isValidPrice < 0) {
            return R.string.configureTransaction_tooLow_textField_message;
        }
        return isValidPrice > 0 ? R.string.configureTransaction_tooHigh_textField_message : 0;
    }
}
