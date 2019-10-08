package trust.blockchain.blockchain.tron;

import android.text.TextUtils;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import org.web3j.abi.datatypes.Address;
import org.web3j.utils.Numeric;
import trust.blockchain.entity.Asset;

// recheck kkk
/* compiled from: TronUtils.kt */
public final class TronUtils {
    public static final TronUtils INSTANCE = new TronUtils();

    private TronUtils() {
    }

    public static final String getTronTokenId(Asset asset) {
        String str;
        Intrinsics.checkParameterIsNotNull(asset, "asset");
        String str2 = asset.contract.contract;
        Intrinsics.checkExpressionValueIsNotNull(str2, "asset.contract.contract");
        if ((((CharSequence) str2).length() > 0 ? 1 : null) != null) {
            str = asset.contract.contract;
        } else {
            str = asset.contract.address.toString();
        }
        Intrinsics.checkExpressionValueIsNotNull(str, "if (asset.contract.contr…ress.toString()\n        }");
        return getTronTokenId(str);
    }

    public static final String getTronTokenId(String str) {
//        Intrinsics.checkParameterIsNotNull(str, Address.TYPE_NAME);
//        if (TextUtils.isEmpty(str)) {
//            throw new IllegalStateException("Bad tokenId");
//        }
//        byte[] hexStringToByteArray = Numeric.hexStringToByteArray(str);
//        Intrinsics.checkExpressionValueIsNotNull(hexStringToByteArray, "Numeric.hexStringToByteArray(address)");
//        List split = new Regex("-").split(new String(hexStringToByteArray, Charsets.f17588a), 0);
//        if (!split.isEmpty()) {
//            ListIterator listIterator = split.listIterator(split.size());
//            while (listIterator.hasPrevious()) {
//                int i;
//                if (((String) listIterator.previous()).length() == 0) {
//                    i = 1;
//                    continue;
//                } else {
//                    i = 0;
//                    continue;
//                }
//                if (i == 0) {
//                    split = CollectionsKt___CollectionsKt.take(split, listIterator.nextIndex() + 1);
//                    break;
//                }
//            }
//        }
//        split = CollectionsKt__CollectionsKt.emptyList();
//        Collection collection = split;
//        if (collection != null) {
//            Object[] toArray = collection.toArray(new String[0]);
//            if (toArray != null) {
//                String[] strArr = (String[]) toArray;
//                str = strArr[strArr.length - 1];
//                if (!TextUtils.isEmpty(str)) {
//                    return str;
//                }
//                throw new IllegalStateException("Bad tokenId");
//            }
//            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
//        }
//        throw new TypeCastException("null cannot be cast to non-null type java.util.Collection<T>");
        return "";
    }
}
