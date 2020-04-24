package com.cxt.work.correction.entity.enums;

/**
 * @author xue-sheng-wen
 * @date 2020/2/24 12:17
 */
public enum BusinessEnum implements IExceptionEnum{

    NOT_FOUND_USER(10001, "未查询到该用户"),

    NOT_FOUND_DRUID_DATA_SOURCE(10002, "不能找到druid数据源"),

    PASSWORD_ERROR(10003,"密码错误"),

    VERIFICATION_CODE_ERROR(10004, "验证码错误"),

    USER_LOGOUT_SUCCESS(10005, "用户退出成功"),

    USER_LOGOUT_ERROR(10006, "用户退出失败"),

    FAIL_TO_REGISTER(10007,"注册失败"),

    NOT_FOUND_ROLE(10008, "未查询到角色"),

    COllECTION_IS_EMPTY(10009, "集合为空"),

    USER_UPDATE_ERROR(10010, "用户修改失败"),

    USER_DELETE_ERROR(10011, "用户删除失败"),

    USER_INSER_ERROR(10012, "用户新增失败"),

    DELETE_IN_BATCHES_ERROR(10013, "批量删除失败"),

    QINIUYUN_DELETE_FILE_ERROR(10014, "七牛云删除文件失败"),

    REVIEW_WORK_ERROR(10015, "批阅作业失败"),

    UPDATE_AUTHORITY_ERROR(10016, "分配权限失败"),

    REPLY_ERROR(10017, "回复失败"),

    ISSUE_ERROR(10018, "发布失败");

    private final int code;
    private final String message;

    BusinessEnum(int code, String message){
        this.code = code;
        this.message = message;
    }
    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }}
