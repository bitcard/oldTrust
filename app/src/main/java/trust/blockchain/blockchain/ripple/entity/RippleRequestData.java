package trust.blockchain.blockchain.ripple.entity;

import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RippleModels.kt */
public final class RippleRequestData {
    private final String method;
    private final List<Map<String, Object>> params;

    public RippleRequestData(String method, List<? extends Map<String, ? extends Object>> params) {
        Intrinsics.checkParameterIsNotNull(method, "method");
        Intrinsics.checkParameterIsNotNull(params, "params");
        this.method = method;
        this.params = (List<Map<String, Object>>)params;
    }

    public static /* synthetic */ RippleRequestData copy$default(RippleRequestData rippleRequestData, String str, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = rippleRequestData.method;
        }
        if ((i & 2) != 0) {
            list = rippleRequestData.params;
        }
        return rippleRequestData.copy(str, list);
    }

    public final String component1() {
        return this.method;
    }

    public final List<Map<String, Object>> component2() {
        return this.params;
    }

    public final RippleRequestData copy(String str, List<? extends Map<String, ? extends Object>> list) {
        Intrinsics.checkParameterIsNotNull(str, "method");
        Intrinsics.checkParameterIsNotNull(list, "params");
        return new RippleRequestData(str, list);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof RippleRequestData) {
                RippleRequestData rippleRequestData = (RippleRequestData) obj;
                if (Intrinsics.areEqual(this.method, rippleRequestData.method) && Intrinsics.areEqual(this.params, rippleRequestData.params)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final String getMethod() {
        return this.method;
    }

    public final List<Map<String, Object>> getParams() {
        return this.params;
    }

    public int hashCode() {
        String str = this.method;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        List list = this.params;
        if (list != null) {
            i = list.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("RippleRequestData(method=");
        stringBuilder.append(this.method);
        stringBuilder.append(", params=");
        stringBuilder.append(this.params);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
