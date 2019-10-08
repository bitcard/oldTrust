package trust.blockchain;

/* compiled from: Bech32Exception.kt */
public enum ErrorCode {
    MISMATCH_HRP("Mismatching bech32 human readable part"),
    INVALID_SEGWIT_HRP("Invalid segwit human readable part"),
    INVALID_CODEDATA_LENGTH("Invalid decoded data length: Decoded Length"),
    INVALID_WITNESSVERSION("Invalid decoded witness version:"),
    DECODED_UNKNOWN_LENGTH("Decoded witness version 0 with unknown length"),
    WRONG_INPUT("Wrong input in bits conversion"),
    INVALID_CHECKSUM("Invalid bech32 checksum"),
    BECH32_MIXING_CASES("Bech32 cannot mix upper and lower case"),
    CHARACTERS_OUTOF_RANGE("Bech32 characters  out of range"),
    BECH_SEPARATOR_ERROR("Bech32 separator misplaced");
    
    private final String message;

    private ErrorCode(String str) {
        this.message = str;
    }

    public final String getMessage() {
        return this.message;
    }
}
