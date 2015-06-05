package sk.stuba.fei.team.local;

public class AlertMessage {
    public static final int WARNING = 0;
    public static final int DANGER = 1;
    public static final int SUCCESS = 2;
    public static final int INFO = 3;
    private int type;
    private String message;

    public AlertMessage(int type, String message) {
        this.type = type;
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
}
