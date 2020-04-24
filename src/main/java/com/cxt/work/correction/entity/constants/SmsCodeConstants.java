package com.cxt.work.correction.entity.constants;

/**
 * <p>
 *     短信服务返回值常量类
 * </p>
 *
 * @author xue-sheng-wen
 * @date 2020/3/5 20:08
 */
public final class SmsCodeConstants {

    private SmsCodeConstants() {

    }

    /**
     * 请求成功
     */
    public static final String OK = "OK";

    /**
     * 短信模板不合法
     */
    public static final String SMS_TEMPLATE_ILLEGAL = "isv.SMS_TEMPLATE_ILLEGAL";

    /**
     * 短信签名不合法
     */
    public static final String SMS_SIGNATURE_ILLEGAL = "isv.SMS_SIGNATURE_ILLEGAL";

    /**
     * 非法手机号
     */
    public static final String MOBILE_NUMBER_ILLEGAL = "isv.MOBILE_NUMBER_ILLEGAL";

    /**
     * 手机号码数量超过限制
     */
    public static final String MOBILE_COUNT_OVER_LIMIT = "isv.MOBILE_COUNT_OVER_LIMIT";

    /**
     * 账户余额不足
     */
    public static final String AMOUNT_NOT_ENOUGH = "isv.AMOUNT_NOT_ENOUGH";

    /**
     * 参数异常
     */
    public static final String INVALID_PARAMETERS = "isv.INVALID_PARAMETERS";

    /**
     * 系统错误
     */
    public static final String SYSTEM_ERROR = "isp.SYSTEM_ERROR";

    /**
     * 	不支持URL
     */
    public static final String	PARAM_NOT_SUPPORT_URL = "isv.PARAM_NOT_SUPPORT_URL";
}
