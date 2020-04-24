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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author xue-sheng-wen
 * @date 2020/3/22 12:14
 */
@Controller
public class TeacherController {

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

    @Value(value = "${qiniu.bucket}")
    private String bucket;

    @Value(value = "${qiniu.path}")
    private String path;

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @PostMapping("/uploadWork")
    public String uploadWork(@RequestParam("file") MultipartFile file, Model model, String workName, String startDate, String endDate) throws IOException, ParseException {
        String originalFilename = file.getOriginalFilename();
        FileInputStream inputStream = (FileInputStream) file.getInputStream();
        String result = QiNiuUtil.uploadFile(inputStream, originalFilename, accessKey, secretKey, bucket, path);
        if (result != null || "".equals(result)) {
            Date start = simpleDateFormat.parse(startDate);
            Date end = simpleDateFormat.parse(endDate);
            TbWork tbWork = new TbWork();
            tbWork.setWorkName(workName + "-" + originalFilename);
            tbWork.setCheckUrl(result);
            tbWork.setStartDate(start);
            tbWork.setEndDate(end);
            Integer flag = tbWorkService.save(tbWork);
        }
        model.addAttribute("msg", "上传成功");
        return "/user/teacherSuccess";
    }

    @GetMapping("/selectAllWork")
    public String selectWorks(Model model) {
        List<TbWork> workList = tbWorkService.selectList(null);
        if (CollectionUtils.isEmpty(workList)) {
            return "toUploadPage";
        }
        model.addAttribute("list", workList);
        return "/user/teacherMain";
    }

    @PostMapping("/deleteFile")
    @ResponseBody
    public ResultDTO deleteFile(Long id) {
        TbWork tbWork = tbWorkService.selectById(id);
        String key = tbWork.getWorkName().split("-")[1];
        String result = QiNiuUtil.deleteQiNiuYunFile(accessKey, secretKey, bucket, key);
        if(Objects.isNull(result)) {
            return ResultDTO.FAIL(BusinessEnum.QINIUYUN_DELETE_FILE_ERROR.getMessage());
        }
        return ResultDTO.SUCCESS(tbWorkService.deleteFile(id));
    }

    @RequestMapping("/selectUserByUserName")
    public String selectUserByUserName(String userName, Model model) {
        UserUpdateBO userUpdateBO = tbUserService.selectUserByUserName(userName);
        if(Objects.isNull(userUpdateBO)) {
            return BusinessEnum.NOT_FOUND_USER.getMessage();
        }
        model.addAttribute("userUpdateBO", userUpdateBO);
        return "/user/teacherInfoManagement";
    }

    @PostMapping("/teacherInfoUpdate")
    @ResponseBody
    public ResultDTO teacherInfoUpdate(UserUpdateBO userUpdateBO){
        Integer result = tbUserService.updateUser(userUpdateBO);
        if(result == NumberConstants.ZERO) {
            return ResultDTO.FAIL(BusinessEnum.USER_UPDATE_ERROR.getMessage());
        }
        return ResultDTO.SUCCESS(result);
    }

    @RequestMapping("/reviewHomework")
    public String reviewHomework(Model model) {
        List<TbMark> tbMarks = tbMarkService.selectList(null);
        model.addAttribute("tbMarks", tbMarks);
        return "/user/reviewHomework";
    }

    @RequestMapping("/review")
    public String review(Long id, Model model) {
        TbMark tbMark = tbMarkService.selectById(id);
        model.addAttribute("tbMark", tbMark);
        return "/user/review";
    }

    @PostMapping("/reviewWork")
    @ResponseBody
    public ResultDTO reviewWork(Long id, Double grade, String remark) throws ParseException {
        Integer result = tbMarkService.reviewWork(id, grade, remark);
        if(result == NumberConstants.ZERO) {
            return ResultDTO.FAIL(BusinessEnum.REVIEW_WORK_ERROR.getMessage());
        }
        return ResultDTO.SUCCESS(result);
    }

}
