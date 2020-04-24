package com.cxt.work.correction.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cxt.work.correction.entity.bo.UserBO;
import com.cxt.work.correction.entity.bo.UserUpdateBO;
import com.cxt.work.correction.entity.constants.NumberConstants;
import com.cxt.work.correction.mapper.TbRolePermissionMapper;
import com.cxt.work.correction.mapper.TbUserInfoMapper;
import com.cxt.work.correction.mapper.TbUserMapper;
import com.cxt.work.correction.mapper.TbUserRoleMapper;
import com.cxt.work.correction.pojo.TbRolePermission;
import com.cxt.work.correction.pojo.TbUser;
import com.cxt.work.correction.pojo.TbUserInfo;
import com.cxt.work.correction.pojo.TbUserRole;
import com.cxt.work.correction.service.TbUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author xue-sheng-wen
 * @since 2020-02-24
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser> implements TbUserService {

    @Resource
    private TbUserMapper tbUserMapper;

    @Resource
    private TbUserInfoMapper tbUserInfoMapper;

    @Resource
    private TbUserRoleMapper tbUserRoleMapper;

    @Resource
    private TbRolePermissionMapper tbRolePermissionMapper;

    private static final BeanCopier userBoCopyTbUser = BeanCopier.create(UserBO.class, TbUser.class, false);

    @Override
    public TbUser selectByUserName(String userName) {
        log.info("user name : {}", userName);
        return tbUserMapper.selectByUserName(userName);
    }

    @Override
    public Integer saveUser(String userName, String password, String mobileNumber) {
        TbUser user = selectByUserName(userName);
        if(Objects.isNull(user)) {
            TbUser dbUser = new TbUser();
            dbUser.setName(userName);
            dbUser.setPassword(password);
            dbUser.setCreateTime(new Date());
            dbUser.setUpdateTime(new Date());
            Integer saveUserResult = tbUserMapper.insert(dbUser);
            log.info("User 影响行数：{}", saveUserResult);
            if(saveUserResult == NumberConstants.ONE) {
                TbUserInfo userInfo = new TbUserInfo();
                userInfo.setUserId(dbUser.getId());
                userInfo.setPhoneNumber(mobileNumber);
                Integer result = tbUserInfoMapper.insert(userInfo);
                log.info("UserInfo 影响行数：{}", result);
                TbUserRole tbUserRole = new TbUserRole();
                tbUserRole.setUserId(dbUser.getId());
                tbUserRole.setRoleId((long) NumberConstants.THREE);
                tbUserRole.setCreateTime(new Date());
                tbUserRole.setUpdateTime(new Date());
                tbUserRoleMapper.insert(tbUserRole);
                log.info("User Role 影响行数：{}", result);
            }
            return saveUserResult;
        }
        return NumberConstants.ZERO;
    }

    @Override
    public List<Map<String, Object>> getUserList(String name) {
        return tbUserMapper.getUsers(name);
    }

    @Override
    public UserBO selectUserById(Long id) {
        return tbUserMapper.selectUserById(id);
    }

    @Override
    public List<TbUser> getTeachers() {
        return tbUserMapper.getTeachers();
    }

    @Override
    public List<TbUser> getStudents() {
        return tbUserMapper.getStudents();
    }

    @Override
    public UserUpdateBO findUserById(Long id) {
        return tbUserMapper.findUserById(id);
    }

    @Override
    public Integer updateUser(UserUpdateBO userUpdateBO) {
        TbUser user = new TbUser();
        user.setId(userUpdateBO.getId());
        user.setName(userUpdateBO.getName());
        user.setPassword(userUpdateBO.getPassword());
        user.setUpdateTime(new Date());
        Integer result = tbUserMapper.updateById(user);
        Integer dbUserInfo = null;
        if(result == NumberConstants.ONE) {
            TbUserInfo userInfo = tbUserInfoMapper.findUserInfoById(userUpdateBO.getId());
            userInfo.setUserId(userUpdateBO.getId());
            userInfo.setPhoneNumber(userUpdateBO.getPhoneNumber());
            userInfo.seteMail(userUpdateBO.getEMail());
            userInfo.setStudentNumber(userUpdateBO.getStudentNumber());
            userInfo.setUpdateTime(new Date());
            dbUserInfo = tbUserInfoMapper.updateById(userInfo);
        }
        if(result == NumberConstants.ZERO || dbUserInfo == NumberConstants.ZERO) {
            return null;
        }
        return result;
    }

    @Override
    public Integer deleteUser(Long id){
        Integer result = tbUserMapper.deleteById(id);
        TbUserInfo userInfo = tbUserInfoMapper.findUserInfoById(id);
        if(Objects.isNull(userInfo)) {
            return 0;
        }
        Integer flag = tbUserInfoMapper.deleteById(userInfo.getId());
        TbUserRole tbUserRole = tbUserRoleMapper.findUserRoleById(id);
        if(Objects.isNull(tbUserRole)) {
            return 0;
        }
        return tbUserRoleMapper.deleteById(tbUserRole.getId());
    }

    @Override
    public Integer addUser(String name, String password, String phoneNumber, String eMail) {
        Integer flag = null;
        TbUser user = new TbUser();
        user.setName(name);
        user.setPassword(password);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        Integer result = tbUserMapper.insert(user);
        log.info("TbUser 影响行数: " + result);
        if(result == NumberConstants.ONE) {
            Long userId = user.getId();
            TbUserInfo userInfo = new TbUserInfo();
            userInfo.setUserId(userId);
            userInfo.setPhoneNumber(phoneNumber);
            userInfo.seteMail(eMail);
            userInfo.setCreateTime(new Date());
            userInfo.setUpdateTime(new Date());
            Integer dbRulest = tbUserInfoMapper.insert(userInfo);
            log.info("TbUserInfo 影响行数: " + dbRulest);
            TbUserRole tbUserRole = new TbUserRole();
            tbUserRole.setUserId(userId);
            tbUserRole.setRoleId((long) NumberConstants.TWO);
            tbUserRole.setCreateTime(new Date());
            tbUserRole.setUpdateTime(new Date());
            flag = tbUserRoleMapper.insert(tbUserRole);
            log.info("TbUserRole 影响行数：{}", flag);
        }
        return flag;
    }

    @Override
    public Integer deleteAll(List<Integer> arrayList) {
        Integer result = tbUserMapper.deleteAll(arrayList);
        log.info("TbUser 影响行数 : {}", result);
        Integer dbResult = tbUserInfoMapper.deleteAll(arrayList);
        log.info("TbUserInfo 影响行数 : {}", dbResult);
        Integer flag = tbUserRoleMapper.deleteAll(arrayList);
        log.info("TbUserRole 影响行数 : {}", flag);
        return result + dbResult + flag;
    }

    @Override
    public UserUpdateBO selectUserByUserName(String userName) {
        TbUser tbUser = tbUserMapper.selectByUserName(userName);
        UserUpdateBO userUpdateBO = new UserUpdateBO();
        if(Objects.nonNull(tbUser)) {
            TbUserInfo tbUserInfo = tbUserInfoMapper.selectUserInfoByUserId(tbUser.getId());
            userUpdateBO.setId(tbUser.getId());
            userUpdateBO.setName(tbUser.getName());
            userUpdateBO.setPassword(tbUser.getPassword());
            userUpdateBO.setEMail(tbUserInfo.geteMail());
            userUpdateBO.setPhoneNumber(tbUserInfo.getPhoneNumber());
            userUpdateBO.setStudentNumber(tbUserInfo.getStudentNumber());
        }
        return userUpdateBO;
    }

    @Override
    public Integer addStudent(String name, String password, String phoneNumber, String eMail, String studentNumber) {
        Integer dbResult = null;
        TbUser tbUser = new TbUser();
        tbUser.setName(name);
        tbUser.setPassword(password);
        tbUser.setCreateTime(new Date());
        tbUser.setUpdateTime(new Date());
        Integer result = tbUserMapper.insert(tbUser);
        log.info("TbUser 影响行数：{}", result);
        if(result == NumberConstants.ONE) {
            TbUserInfo tbUserInfo = new TbUserInfo();
            tbUserInfo.setUserId(tbUser.getId());
            tbUserInfo.seteMail(eMail);
            tbUserInfo.setPhoneNumber(phoneNumber);
            tbUserInfo.setStudentNumber(Long.valueOf(studentNumber));
            tbUserInfo.setCreateTime(new Date());
            tbUserInfo.setUpdateTime(new Date());
            Integer flag = tbUserInfoMapper.insert(tbUserInfo);
            log.info("TbUserInfo 影响行数：{}", flag);
            TbUserRole tbUserRole = new TbUserRole();
            tbUserRole.setUserId(tbUser.getId());
            tbUserRole.setRoleId((long)NumberConstants.THREE);
            tbUserRole.setCreateTime(new Date());
            tbUserRole.setUpdateTime(new Date());
            dbResult = tbUserRoleMapper.insert(tbUserRole);
        }
        return dbResult;
    }

    @Override
    public Integer updateAuthority(List<Long> arrayList, Long roleId) {
        if(CollectionUtils.isEmpty(arrayList)) {
            return 0;
        }
        Integer dbFlag = null;
        Integer result = tbRolePermissionMapper.deleteByRoleId(roleId);
        if(result > 0) {
            TbRolePermission tbRolePermission = new TbRolePermission();
            for(Long permissionId : arrayList) {
                tbRolePermission.setRoleId(roleId);
                tbRolePermission.setPermissionId(Long.valueOf(permissionId));
                tbRolePermission.setCreateTime(new Date());
                tbRolePermission.setUpdateTime(new Date());
                dbFlag = tbRolePermissionMapper.insert(tbRolePermission);
            }
        }
        return dbFlag;
    }
}
