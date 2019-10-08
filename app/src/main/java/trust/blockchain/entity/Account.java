package trust.blockchain.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import trust.blockchain.Slip;
import trust.blockchain.util.HDWalletExtensions;

public class Account implements Parcelable {
    public static final Creator<Account> CREATOR = new Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel parcel) {
            return new Account(parcel);
        }

        @Override
        public Account[] newArray(int n) {
            return new Account[n];
        }
    };
    private static final ReentrantReadWriteLock locker = new ReentrantReadWriteLock();
    private Bip32Path[] bip32PathList;
    public final Slip coin;
    public final String extendedPublicKey;
    private final Address zeroAddress;

    protected Account(final Parcel parcel) {
        super();
        this.bip32PathList = new Bip32Path[0];
        this.coin = Slip.valueOf(parcel.readString());
        this.extendedPublicKey = parcel.readString();
        this.zeroAddress = this.coin.toAddress(parcel.readString());
    }
    
    public Account(final Slip coin, final String extendedPublicKey, final String s) {
        super();
        this.bip32PathList = new Bip32Path[0];
        this.coin = coin;
        this.extendedPublicKey = extendedPublicKey;
        this.zeroAddress = coin.toAddress(s);
    }
    
    private Bip32Path maxIndex(final int n) {
        final Bip32Path[] bip32PathList = this.bip32PathList;
        final int length = bip32PathList.length;
        Bip32Path bip32Path = null;
        Bip32Path bip32Path3;
        for (int i = 0; i < length; ++i, bip32Path = bip32Path3) {
            final Bip32Path bip32Path2 = bip32PathList[i];
            bip32Path3 = bip32Path;
            if (bip32Path2.getDerivationPath().getChangeIndex() == n) {
                if (bip32Path != null) {
                    bip32Path3 = bip32Path;
                    if (bip32Path.getDerivationPath().getAddressIndex() >= bip32Path2.getDerivationPath().getAddressIndex()) {
                        continue;
                    }
                }
                bip32Path3 = bip32Path2;
            }
        }
        return bip32Path;
    }
    
    public Address address() {
        return this.address(0);
    }
    
    public Address address(final int n) {
        try {
            Account.locker.readLock().lock();
            if (this.bip32PathList == null || this.bip32PathList.length == 0) {
                return this.zeroAddress;
            }
            final Bip32Path maxIndex = this.maxIndex(n);
            if (maxIndex == null || TextUtils.isEmpty((CharSequence) maxIndex.getPath())) {
                return this.zeroAddress;
            }
            final DerivationPath derivationPath = DerivationPath.toDerivationPath(maxIndex.getPath());
            derivationPath.incrementAddressIndexBy(1);
            final String deriveAddress = HDWalletExtensions.deriveAddress(this.coin, this.extendedPublicKey, derivationPath);
            if (deriveAddress != null) {
                return this.coin.toAddress(deriveAddress);
            }
            return this.zeroAddress;
        } finally {
            Account.locker.readLock().unlock();
        }
    }
    
    public boolean containsAddress(final String s) {
        final Bip32Path[] bip32PathList = this.bip32PathList;
        for (int length = bip32PathList.length, i = 0; i < length; ++i) {
            if (bip32PathList[i].getAddress().equals(s)) {
                return true;
            }
        }
        return this.zeroAddress.toString().equals(s);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public Bip32Path[] getAllIndices() {
        final Bip32Path[] bip32PathList = this.bip32PathList;
        return Arrays.<Bip32Path>copyOf(bip32PathList, bip32PathList.length);
    }
    
    public Bip32Path[] getIndices(final int n) {
        try {
            Account.locker.readLock().lock();
            final ArrayList<Bip32Path> list = new ArrayList<Bip32Path>();
            for (final Bip32Path bip32Path : this.bip32PathList) {
                if (bip32Path.getDerivationPath().getChangeIndex() == n) {
                    list.add(bip32Path);
                }
            }
            return list.<Bip32Path>toArray(new Bip32Path[0]);
        } finally {
            Account.locker.readLock().unlock();
        }
    }
    
    public void setIndices(final Bip32Path[] bip32PathList) {
        try {
            Account.locker.writeLock().lock();
            this.bip32PathList = bip32PathList;
        } finally {
            Account.locker.writeLock().unlock();
        }
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeString(((Enum) this.coin).name());
        parcel.writeString(this.extendedPublicKey);
        parcel.writeString(this.zeroAddress.data());
    }
    
    public Address zeroAddress() {
        return this.zeroAddress;
    }
}