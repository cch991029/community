package life.cch.community.community.enums;

/**
 * Created by codedrinker on 2020-01-05 16:05:21
 */
public enum NotificationStatusEnum {
    UNREAD(0),
    READ(1)
    ;
    private int status;

    public int getStatus() {
        return status;
    }

    NotificationStatusEnum(int status) {
        this.status = status;
    }
}
