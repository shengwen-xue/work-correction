package com.cxt.work.correction.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cxt.work.correction.pojo.TbRole;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author xue-sheng-wen
 * @since 2020-02-24
 */
@Mapper
public interface TbRoleMapper extends BaseMapper<TbRole> {

    List<TbRole> selectRoleNameByRoleIds(Long[] roleIds);

    TbRole selectRoleByUserName(@Param(value = "userName") String userName);
}
