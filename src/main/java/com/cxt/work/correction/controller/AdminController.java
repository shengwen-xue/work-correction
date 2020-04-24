package com.cxt.work.correction.controller;

import com.cxt.work.correction.entity.bo.UserBO;
import com.cxt.work.correction.entity.bo.UserUpdateBO;
import com.cxt.work.correction.entity.constants.NumberConstants;
import com.cxt.work.correction.entity.dto.ResultDTO;
import com.cxt.work.correction.entity.enums.BusinessEnum;
import com.cxt.work.correction.pojo.TbPermission;
import com.cxt.work.correction.pojo.TbUser;
import com.cxt.work.correction.service.TbPermissionService;
import com.cxt.work.correction.service.TbUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author xue-sheng-wen
 * @date 2020/3/18 9:38
 */
@Controller
public class AdminController {

    @Resource
    private TbUserService tbUserService;

    @Resource
    private TbPermissionService tbPermissionService;

    @RequestMapping("/selectAllUser")
    public String selectAllUser(Map<String, Object> map,
                                @RequestParam(value = "name", defaultValue = "") String name,
                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Map<String, Object>> userList = tbUserService.getUserList(name);
        PageInfo<Map<String, Object>> tbUserPageInfo = new PageInfo<>(userList);
        map.put("userList", tbUserPageInfo);
        return "/user/adminMain";
    }

    @RequestMapping("/checking")
    public String checking(Long id, Model model){
        UserBO userBO = tbUserService.selectUserById(id);
        model.addAttribute("userBO", userBO);
        return "/user/check";
    }

    @RequestMapping("/teacherManagement")
    public String toTeacherManagement(Model model) {
        List<TbUser> userList = tbUserService.getTeachers();
        if(CollectionUtils.isEmpty(userList)) {
            return BusinessEnum.COllECTION_IS_EMPTY.getMessage();
        }
        model.addAttribute("list", userList);
        return "/user/teacherManagement";
    }

    @RequestMapping("/studentManagement")
    public String toStudentManagement(Model model) {
        List<TbUser> userList = tbUserService.getStudents();
        if(CollectionUtils.isEmpty(userList)) {
            return BusinessEnum.COllECTION_IS_EMPTY.getMessage();
        }
        model.addAttribute("list", userList);
        return "/user/studentManagement";
    }

    @RequestMapping("/findUserById")
    public String findUserById(Long id, Model model) {
        UserUpdateBO userUpdateBO = tbUserService.findUserById(id);
        if(Objects.isNull(userUpdateBO)) {
            return BusinessEnum.NOT_FOUND_USER.getMessage();
        }
        model.addAttribute("userUpdateBO", userUpdateBO);
        return "/user/teacherUpdate";
    }

    @RequestMapping("/findUser")
    public String findUser(Long id, Model model) {
        UserUpdateBO userUpdateBO = tbUserService.findUserById(id);
        if(Objects.isNull(userUpdateBO)) {
            return BusinessEnum.NOT_FOUND_USER.getMessage();
        }
        model.addAttribute("userUpdateBO", userUpdateBO);
        return "/user/studentUpdate";
    }

    @RequestMapping("/updateUser")
    @ResponseBody
    public ResultDTO updateUser(UserUpdateBO userUpdateBO){
        Integer result = tbUserService.updateUser(userUpdateBO);
        if(result == NumberConstants.ZERO) {
            return ResultDTO.FAIL(BusinessEnum.USER_UPDATE_ERROR.getMessage());
        }
        return ResultDTO.SUCCESS(result);
    }

    @RequestMapping("/deleteUser")
    @ResponseBody
    public ResultDTO deleteUser(Long id){
        Integer result = tbUserService.deleteUser(id);
        if(result == NumberConstants.ZERO) {
            return ResultDTO.FAIL(BusinessEnum.USER_DELETE_ERROR.getMessage());
        }
        return ResultDTO.SUCCESS(result);
    }

    @RequestMapping("/addUser")
    @ResponseBody
    public ResultDTO addUser(String name, String password, String phoneNumber, String eMail){
        Integer result = tbUserService.addUser(name, password, phoneNumber, eMail);
        if(result == NumberConstants.ZERO) {
            return ResultDTO.FAIL(BusinessEnum.USER_INSER_ERROR.getMessage());
        }
        return ResultDTO.SUCCESS(result);
    }

    @RequestMapping("/deleteAll")
    @ResponseBody
    public ResultDTO deleteAll(@RequestBody List<Integer> arrayList){
        Integer result = tbUserService.deleteAll(arrayList);
        if(result == NumberConstants.ZERO) {
            return ResultDTO.FAIL(BusinessEnum.DELETE_IN_BATCHES_ERROR.getMessage());
        }
        return ResultDTO.SUCCESS(result);
    }

    @RequestMapping("/selectUser")
    public String selectUser(String userName, Model model) {
        UserUpdateBO userUpdateBO = tbUserService.selectUserByUserName(userName);
        if(Objects.isNull(userUpdateBO)) {
            return BusinessEnum.NOT_FOUND_USER.getMessage();
        }
        model.addAttribute("userUpdateBO", userUpdateBO);
        return "/user/adminInfoManagement";
    }

    @PostMapping("/adminInfoUpdate")
    @ResponseBody
    public ResultDTO adminInfoUpdate(UserUpdateBO userUpdateBO){
        Integer result = tbUserService.updateUser(userUpdateBO);
        if(result == NumberConstants.ZERO) {
            return ResultDTO.FAIL(BusinessEnum.USER_UPDATE_ERROR.getMessage());
        }
        return ResultDTO.SUCCESS(result);
    }

    @RequestMapping("/addStudent")
    @ResponseBody
    public ResultDTO addStudent(String name, String password, String phoneNumber, String eMail, String studentNumber){
        Integer result = tbUserService.addStudent(name, password, phoneNumber, eMail, studentNumber);
        if(result == NumberConstants.ZERO) {
            return ResultDTO.FAIL(BusinessEnum.USER_INSER_ERROR.getMessage());
        }
        return ResultDTO.SUCCESS(result);
    }

    @RequestMapping("/assignAuthority")
    public String assignAuthority(Model model) {
        List<TbPermission> permissionList = tbPermissionService.selectList(null);
        String studentId = tbPermissionService.selectStudentPermission();
        String teacherId = tbPermissionService.selectTeacherPermission();
        model.addAttribute("permissionList", permissionList);
        model.addAttribute("studentId", studentId);
        model.addAttribute("teacherId", teacherId);
        return "/user/assignAuthority";
    }

    @RequestMapping("/updateAuthority")
    @ResponseBody
    public ResultDTO updateAuthority(@RequestBody List<Long> arrayList, Long roleId){
        Integer result = tbUserService.updateAuthority(arrayList, roleId);
        if(result == NumberConstants.ZERO) {
            return ResultDTO.FAIL(BusinessEnum.UPDATE_AUTHORITY_ERROR.getMessage());
        }
        return ResultDTO.SUCCESS(result);
    }
}
