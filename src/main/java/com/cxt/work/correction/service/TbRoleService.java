package com.cxt.work.correction.service;

import com.baomidou.mybatisplus.service.IService;
import com.cxt.work.correction.pojo.TbRole;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author xue-sheng-wen
 * @since 2020-02-24
 */
public interface TbRoleService extends IService<TbRole> {

    /**
     * 根据角色id查询角色名称集合
     *
     * @param roleIds 角色Id数组
     * @return 角色名称集合
     */
    List<TbRole> selectRoleNameByRoleIds(Long[] roleIds);

    /**
     * 根据用户名查询角色
     *
     * @param userName 用户名
     * @return 角色
     */
    TbRole selectRoleByUserName(String userName);
}
