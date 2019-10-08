package trust.blockchain.entity;

public class ServiceErrorException extends Exception {
    public static final int CREATE_WALLET_ERROR = 1008;
    public static final int FAIL_TO_SAVE_IV_FILE = 1002;
    public static final int INVALID_DATA = 1;
    public static final int INVALID_KEY = 1007;
    public static final int IV_OR_ALIAS_NO_ON_DISK = 1006;
    public static final int KEY_IS_GONE = 1005;
    public static final int KEY_STORE_ERROR = 1001;
    public static final int KEY_STORE_SECRET = 1003;
    public static final int UNKNOWN_ERROR = -1;
    public static final int USER_NOT_AUTHENTICATED = 1004;
    public final int code;

    public ServiceErrorException(int i, String message, Throwable th) {
        super(message, th);
        this.code = i;
    }

    public ServiceErrorException(int code, String message) {
        this(code, message, null);
    }

    public ServiceErrorException(int i) {
        this(i, null);
    }
}
