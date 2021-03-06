package life.cch.community.community.exception;

/**
 * Created by codedrinker on 2019-12-23 11:45:08
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND(2001,"你找的问题不在了，要不要换一个试试？"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或评论进行回复"),
    NO_LOGIN(2003, "未登录不能进行评论，请登录重试"),
    SYS_ERROR(2004, "服务器冒烟了，请稍后再试！！！"),
    TYPE_PARAM_WRONG(2005, "评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006, "回复的评论不存在了，要不要换一个试试？"),
    COMMENT_IS_EMPTY(2007, "输入内容不能为空"),
    READ_NOTIFICATION_FAIL(2008, "兄弟，你是想读别人的信息吗？"),
    NOTIFICATION_NOT_FOUND(2009, "消息不翼而飞了！"),
    FILE_UPLOAD_FAIL(2010, "图片上传失败！"),
    ;

    private String message;
    private Integer code;

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
