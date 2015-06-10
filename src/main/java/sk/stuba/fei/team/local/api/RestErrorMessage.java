package sk.stuba.fei.team.local.api;

public class RestErrorMessage extends RestMessage {

    public static final int EXCEPTION = 0;
    public static final int NO_MATCH = 1;

    private int errorCode;
    private String description;

    public RestErrorMessage(int errorCode) {
        this.errorCode = errorCode;
    }

    public RestErrorMessage(int errorCode, String description) {
        this.errorCode = errorCode;
        this.description = description;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getDescription() {
        return description;
    }
}
