package com.cxt.work.correction.service;

import com.baomidou.mybatisplus.service.IService;
import com.cxt.work.correction.pojo.TbPermission;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author xue-sheng-wen
 * @since 2020-02-24
 */
public interface TbPermissionService extends IService<TbPermission> {

    /**
     * 查询学生权限
     *
     * @return 权限id字符串
     */
    String selectStudentPermission();

    /**
     * 查询教师权限
     *
     * @return 权限id字符串
     */
    String selectTeacherPermission();
}
