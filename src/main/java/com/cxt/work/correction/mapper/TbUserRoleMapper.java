package com.cxt.work.correction.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cxt.work.correction.pojo.TbUserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 用户角色中间表 Mapper 接口
 * </p>
 *
 * @author xue-sheng-wen
 * @since 2020-02-24
 */
@Mapper
public interface TbUserRoleMapper extends BaseMapper<TbUserRole> {

    List<TbUserRole> selectUserRolesByUserId(Long id);

    TbUserRole findUserRoleById(Long id);

    Integer deleteAll(List<Integer> idList);
}
