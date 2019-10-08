package trust.blockchain.entity;

import android.os.Parcelable;

public interface Ticker extends Parcelable {
    boolean compare(Ticker ticker);

    Address getContract();

    String getCurrencyCode();

    String getPrice();

    String getSymbol();

    long getUpdateTime();

    Double percentChange24h();
}
