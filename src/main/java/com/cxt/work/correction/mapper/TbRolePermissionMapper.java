package com.cxt.work.correction.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cxt.work.correction.pojo.TbPermission;
import com.cxt.work.correction.pojo.TbRolePermission;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <p>
 * 角色权限中间表 Mapper 接口
 * </p>
 *
 * @author xue-sheng-wen
 * @since 2020-02-24
 */
@Mapper
public interface TbRolePermissionMapper extends BaseMapper<TbRolePermission> {

    List<TbPermission> selectPermissionNameByRoleIds(Long[] roleIds);

    Integer deleteByRoleId(Long roleId);
}
