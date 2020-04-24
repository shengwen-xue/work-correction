package com.cxt.work.correction.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cxt.work.correction.entity.dto.AssignAuthorityDTO;
import com.cxt.work.correction.pojo.TbPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author xue-sheng-wen
 * @since 2020-02-24
 */
@Mapper
public interface TbPermissionMapper extends BaseMapper<TbPermission> {

    List<AssignAuthorityDTO> selectStudentPermission();

    List<AssignAuthorityDTO> selectTeacherPermission();
}
