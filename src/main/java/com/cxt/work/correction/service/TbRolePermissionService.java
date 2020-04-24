package com.cxt.work.correction.service;

import com.baomidou.mybatisplus.service.IService;
import com.cxt.work.correction.pojo.TbPermission;
import com.cxt.work.correction.pojo.TbRolePermission;

import java.util.List;

/**
 * <p>
 * 角色权限中间表 服务类
 * </p>
 *
 * @author xue-sheng-wen
 * @since 2020-02-24
 */
public interface TbRolePermissionService extends IService<TbRolePermission> {

    /**
     * 根据角色id查询权限集合
     *
     * @param roleIds 角色id数组
     * @return 权限集合
     */
    List<TbPermission> selectPermissionNameByRoleIds(Long... roleIds);
}
