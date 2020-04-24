package com.cxt.work.correction.controller;


import com.alibaba.fastjson.JSONObject;
import com.cxt.work.correction.entity.constants.NumberConstants;
import com.cxt.work.correction.entity.constants.SmsCodeConstants;
import com.cxt.work.correction.entity.dto.ResultDTO;
import com.cxt.work.correction.entity.enums.BusinessEnum;
import com.cxt.work.correction.pojo.TbUser;
import com.cxt.work.correction.service.TbUserService;
import com.cxt.work.correction.utils.SmsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author xue-sheng-wen
 * @since 2020-02-24
 */
@Slf4j
@Api(value = "用户前端控制器")
@RestController(value = "/user")
public class TbUserController {

    private static final String CODEKEY = "codeKey";

    private static final long data = 5L;

    @Autowired
    private TbUserService tbUserService;

    @Resource
    private RedisTemplate<String, String> stringRedisTemplate;

    @Value(value = "${aliyun.sms.accessKeyId}")
    private String accessKeyId;

    @Value(value = "${aliyun.sms.accessKeySecret}")
    private String accessKeySecret;

    @Value(value = "${aliyun.sms.sign_name}")
    private String sign_name;

    @Value(value = "${aliyun.sms.template_code}")
    private String template_code;

    @GetMapping("/selectByUserName")
    @ApiOperation(value = "根据用户名查询用户")
    public ResultDTO selectByUserName(
            @ApiParam(name = "userName", value = "用户名", required = true)
            @RequestParam(value = "userName") String userName) {
        TbUser user = tbUserService.selectByUserName(userName);
        if (Objects.isNull(user)) {
            return ResultDTO.FAIL(BusinessEnum.NOT_FOUND_USER.getMessage());
        }
        return ResultDTO.SUCCESS(user);
    }

    @PostMapping("/userLogin")
    @ApiOperation(value = "用户登录")
    public ResultDTO toLogin(
            @ApiParam(name = "userName", value = "用户名", required = true)
            @RequestParam(value = "userName") String userName,
            @ApiParam(name = "password", value = "密码", required = true)
            @RequestParam(value = "password") String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        try {
            // 登录成功
            subject.login(token);
            return ResultDTO.SUCCESS(token.getUsername());
        } catch (UnknownAccountException e) {
            log.info("UnknownAccountException:用户名不存在");
            return ResultDTO.FAIL(BusinessEnum.NOT_FOUND_USER.getMessage());
        } catch (IncorrectCredentialsException e) {
            log.info("IncorrectCredentialsException:密码错误");
            return ResultDTO.FAIL(BusinessEnum.PASSWORD_ERROR.getMessage());
        }
    }

    @GetMapping("/logout")
    @ApiOperation(value = "用户退出")
    public ResultDTO logout(){
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()) {
            subject.logout();
            return ResultDTO.SUCCESS(BusinessEnum.USER_LOGOUT_SUCCESS.getMessage());
        }
        return ResultDTO.FAIL(BusinessEnum.USER_LOGOUT_ERROR.getMessage());
    }

    @GetMapping("/selectMobileNumber")
    @ApiOperation(value = "查询手机号")
    public ResultDTO selectMobileNumber(
            @ApiParam(name = "mobileNumber", value = "手机号", required = true)
            @RequestParam(value = "mobileNumber") String mobileNumber) {
        JSONObject jsonObject = SmsUtil.sendMessage(mobileNumber, accessKeyId, accessKeySecret, sign_name, template_code);
        String resultCode = (String) jsonObject.get("Code");
        if (resultCode.equals(SmsCodeConstants.OK)) {
            String verificationCode = (String) jsonObject.get("verificationCode");
            // 把获取的随机验证码保存到redis中设置5分钟过期时间
            stringRedisTemplate.opsForValue().set(CODEKEY, verificationCode, data, TimeUnit.MINUTES);
            log.info("短信服务调用成功");
            return ResultDTO.SUCCESS(jsonObject);
        }
        if (resultCode.equals(SmsCodeConstants.SMS_TEMPLATE_ILLEGAL)) {
            log.info("短信模板不合法");
            return ResultDTO.FAIL("短信模板不合法");
        }
        if (resultCode.equals(SmsCodeConstants.SMS_SIGNATURE_ILLEGAL)) {
            log.info("短信签名不合法");
            return ResultDTO.FAIL("短信签名不合法");
        }
        if (resultCode.equals(SmsCodeConstants.MOBILE_NUMBER_ILLEGAL)) {
            log.info("非法手机号");
            return ResultDTO.FAIL("非法手机号");
        }
        if (resultCode.equals(SmsCodeConstants.MOBILE_COUNT_OVER_LIMIT)) {
            log.info("手机号码数量超过限制");
            return ResultDTO.FAIL("手机号码数量超过限制");
        }
        return new ResultDTO();
    }

    @GetMapping("/verifyCode")
    @ApiOperation(value = "查询验证码")
    public ResultDTO selectCode(
            @ApiParam(name = "verificationCode", value = "验证码", required = true)
            @RequestParam(value = "verificationCode") String verificationCode) {
        String value = stringRedisTemplate.opsForValue().get(CODEKEY);
        if (value.equals(verificationCode)) {
            // 表示redis的验证码未失效
            return ResultDTO.SUCCESS(value);
        }
        // 表示redis不存在该验证码
        return ResultDTO.FAIL(BusinessEnum.VERIFICATION_CODE_ERROR.getMessage());
    }

    @PostMapping("/saveUser")
    @ApiOperation(value = "注册用户")
    public ResultDTO saveUser(
            @ApiParam(name = "userName", value = "用户名", required = true)
            @RequestParam(value = "userName") String userName,
            @ApiParam(name = "password", value = "密码", required = true)
            @RequestParam(value = "password") String password,
            @ApiParam(name = "mobileNumber", value = "手机号", required = true)
            @RequestParam(value = "mobileNumber") String mobileNumber) {
        Integer resule = tbUserService.saveUser(userName, password, mobileNumber);
        if (resule == NumberConstants.ZERO) {
            return ResultDTO.FAIL(BusinessEnum.FAIL_TO_REGISTER.getMessage());
        }
        return ResultDTO.SUCCESS(resule);
    }
}

