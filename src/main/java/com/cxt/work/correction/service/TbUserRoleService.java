package com.cxt.work.correction.service;

import com.baomidou.mybatisplus.service.IService;
import com.cxt.work.correction.pojo.TbUserRole;

import java.util.List;

/**
 * <p>
 * 用户角色中间表 服务类
 * </p>
 *
 * @author xue-sheng-wen
 * @since 2020-02-24
 */
public interface TbUserRoleService extends IService<TbUserRole> {

    /**
     * 根据用户id查询用户角色中间表集合
     *
     * @param id 用户id
     * @return 用户角色中间表集合
     */
    List<TbUserRole> selectUserRolesByUserId(Long id);
}
