package com.cxt.work.correction.controller;

import com.cxt.work.correction.service.TbUserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

/**
 * <p>
 * 页面跳转 前端控制器
 * </p>
 *
 * @author xue-sheng-wen
 * @date 2020/2/24 11:58
 */
@Slf4j
@Api(value = "页面跳转控制器")
@Controller
public class PageController {

    @Resource
    private TbUserService tbUserService;

    /**
     * 默认访问项目定位到首页
     *
     * @return 跳转到首页
     */
    @GetMapping("/")
    public String toIndex() {
        return "index";//逻辑视图
    }

    /**
     * 跳转到登录页
     *
     * @return 跳转到登录页
     */
    @GetMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    /**
     * 跳转到未授权页
     *
     * @return 跳转到未授权页
     */
    @GetMapping("/toUnauthoriz")
    public String toUnauthorized() {
        return "unauthoriz";
    }

    /**
     * 跳转到注册页
     *
     * @return 跳转到注册页
     */
    @GetMapping("/toRegister")
    public String toRegister() {
        return "register";
    }

    /**
     * 跳转到学生平台
     *
     * @return 跳转到学生平台
     */
    @GetMapping("/toStudentMain")
    public String toStudentMain() {
        return "/user/studentMain";
    }

    /**
     * 跳转到上传页
     *
     * @return 跳转到上传页
     */
    @GetMapping("/toUploadPage")
    public String toUploadPage() {
        return "/user/uploadWork";
    }

    /**
     * 跳转到新增教师页
     *
     * @return 跳转到新增教师页
     */
    @GetMapping("/toAddTeacher")
    public String toAddTeacher() {
        return "/user/addTeacher";
    }

    /**
     * 跳转到新增学生页
     * @return
     */
    @GetMapping("/toAddStudent")
    public String toAddStudent() {
        return "/user/addStudent";
    }

}
