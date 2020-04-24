package com.cxt.work.correction.controller;

import com.cxt.work.correction.entity.bo.UserUpdateBO;
import com.cxt.work.correction.entity.constants.NumberConstants;
import com.cxt.work.correction.entity.dto.ResultDTO;
import com.cxt.work.correction.entity.enums.BusinessEnum;
import com.cxt.work.correction.pojo.TbMark;
import com.cxt.work.correction.pojo.TbWork;
import com.cxt.work.correction.service.TbMarkService;
import com.cxt.work.correction.service.TbUserService;
import com.cxt.work.correction.service.TbWorkService;
import com.cxt.work.correction.utils.QiNiuUtil;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author xue-sheng-wen
 * @date 2020/3/22 12:14
 */
@Controller
public class StudentController {

    @Resource
    private TbWorkService tbWorkService;

    @Resource
    private TbUserService tbUserService;

    @Resource
    private TbMarkService tbMarkService;

    @Value(value = "${qiniu.accessKey}")
    private String accessKey;

    @Value(value = "${qiniu.secretKey}")
    private String secretKey;

    @Value(value = "${qiniu.bucket.answer}")
    private String bucket;

    @Value(value = "${qiniu.path.answer}")
    private String path;

    @GetMapping("/selectAllStudent")
    public String selectWorks(Model model) {
        List<TbWork> workList = tbWorkService.selectList(null);
        if (CollectionUtils.isEmpty(workList)) {
            return "/toUploadAnswer";
        }
        model.addAttribute("list", workList);
        return "/user/studentMain";
    }

    @RequestMapping("/findUserByuserName")
    public String selectUserByUserName(String userName, Model model) {
        UserUpdateBO userUpdateBO = tbUserService.selectUserByUserName(userName);
        if(Objects.isNull(userUpdateBO)) {
            return BusinessEnum.NOT_FOUND_USER.getMessage();
        }
        model.addAttribute("userUpdateBO", userUpdateBO);
        return "/user/studentInfoManagement";
    }

    @PostMapping("/studentInfoUpdate")
    @ResponseBody
    public ResultDTO teacherInfoUpdate(UserUpdateBO userUpdateBO){
        Integer result = tbUserService.updateUser(userUpdateBO);
        if(result == NumberConstants.ZERO) {
            return ResultDTO.FAIL(BusinessEnum.USER_UPDATE_ERROR.getMessage());
        }
        return ResultDTO.SUCCESS(result);
    }

    @GetMapping("/toUploadAnswer")
    public String toUploadAnswer(String name, Model model) {
        model.addAttribute("userName", name);
        return "/user/uploadAnswer";
    }


    @PostMapping("/uploadFile")
    public String uploadWork(@RequestParam("file_name") MultipartFile file, String userName) throws IOException, ParseException {
        String originalFilename = file.getOriginalFilename();
        FileInputStream inputStream = (FileInputStream) file.getInputStream();
        String result = QiNiuUtil.uploadFile(inputStream, originalFilename, accessKey, secretKey, bucket, path);
        if (result != null || "".equals(result)) {
            TbMark tbMark = new TbMark();
            tbMark.setUploader(userName);
            tbMark.setFileName(originalFilename);
            tbMark.setCheckUrl(result);
            tbMark.setUploadTime(new Date());
            tbMarkService.insert(tbMark);
        }
        return "/user/studentSuccess";
    }

    @RequestMapping("/selectAllGrade")
    public String toCheckGrade(Model model) {
        List<TbMark> tbMarks = tbMarkService.selectList(null);
        model.addAttribute("tbMarks", tbMarks);
        return "/user/checkGrade";
    }

    @RequestMapping("/checkDetails")
    public String checkDetails(Long id, Model model) {
        TbMark tbMark = tbMarkService.selectById(id);
        model.addAttribute("mark", tbMark);
        return "/user/checkDetails";
    }

}
