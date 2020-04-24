package com.cxt.work.correction.service;

import com.baomidou.mybatisplus.service.IService;
import com.cxt.work.correction.entity.bo.UserBO;
import com.cxt.work.correction.entity.bo.UserUpdateBO;
import com.cxt.work.correction.pojo.TbUser;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author xue-sheng-wen
 * @since 2020-02-24
 */
public interface TbUserService extends IService<TbUser> {

    /**
     * 根据用户名查询用户
     *
     * @param userName 用户名
     * @return 用户信息
     */
    TbUser selectByUserName(String userName);

    /**
     * 注册用户
     *
     * @param userName     用户名
     * @param password     密码
     * @param mobileNumber 手机号
     * @return 影响行数
     */
    Integer saveUser(String userName, String password, String mobileNumber);

    /**
     * 用户列表、模糊搜索
     *
     * @param name 用户名
     * @return 字段键值对
     */
    List<Map<String, Object>> getUserList(String name);

    /**
     * 根据id查询用户详细信息
     *
     * @param id 用户id
     * @return 用户详细信息
     */
    UserBO selectUserById(Long id);

    /**
     * 教师列表
     *
     * @return 教师列表
     */
    List<TbUser> getTeachers();

    /**
     * 学生列表
     *
     * @return 学生列表
     */
    List<TbUser> getStudents();

    /**
     * 根据用户id查询需要修改的对象
     *
     * @param id 用户id
     * @return 修改的对象
     */
    UserUpdateBO findUserById(Long id);

    /**
     * 修改用户
     *
     * @param userUpdateBO 修改的用户
     * @return 影响行数
     */
    Integer updateUser(UserUpdateBO userUpdateBO);

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return 影响行数
     */
    Integer deleteUser(Long id);

    /**
     * 新增用户
     *
     * @param name        用户名
     * @param password    密码
     * @param phoneNumber 手机号
     * @param eMail       电子邮箱
     * @return 影响行数
     */
    Integer addUser(String name, String password, String phoneNumber, String eMail);

    /**
     * 批量删除
     *
     * @param arrayList 删除的id数组
     * @return 影响行数
     */
    Integer deleteAll(List<Integer> arrayList);

    /**
     * 查询用户
     *
     * @param userName 用户名
     * @return 用户对象
     */
    UserUpdateBO selectUserByUserName(String userName);

    /**
     * 新增学生
     *
     * @param name          用户名
     * @param password      密码
     * @param phoneNumber   手机号
     * @param eMail         电子邮箱
     * @param studentNumber 学号
     * @return 影响行数
     */
    Integer addStudent(String name, String password, String phoneNumber, String eMail, String studentNumber);

    /**
     * 修改权限
     *
     * @param arrayList 权限id集合
     * @param roleId    角色id
     * @return 影响行数
     */
    Integer updateAuthority(List<Long> arrayList, Long roleId);
}
