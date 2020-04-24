package com.cxt.work.correction.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *     阿里云短信服务工具类
 * </p>
 *
 * @author xue-sheng-wen
 * @date 2020/3/3 16:11
 */
@Slf4j
public class SmsUtil {

    private static JSONObject result = new JSONObject();
    private static final String RegionId = "cn-hangzhou";

    public static JSONObject sendMessage(String mobileNumber, String accessKeyId, String accessKeySecret, String signName, String templateCode) {
        // 随机生成6位验证码
        String randomCode = String.valueOf((int) ((Math.random() * 9 + 1) * 1000000)).substring(1);
        log.info("随机验证码：{}", randomCode);
        DefaultProfile profile = DefaultProfile.getProfile(RegionId, accessKeyId, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", RegionId);
        request.putQueryParameter("PhoneNumbers", mobileNumber);
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", "{\"code\":\"" + randomCode + "\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            result = JSON.parseObject(response.getData());
            result.put("verificationCode", randomCode);
            log.info("调用成功：{}", result);
            return result;
        } catch (ServerException e) {
            log.error("服务端异常: {}", e);
            e.printStackTrace();
        } catch (ClientException e) {
            log.error("客户端异常: {}", e);
            e.printStackTrace();
        }
        return result;
    }
}
