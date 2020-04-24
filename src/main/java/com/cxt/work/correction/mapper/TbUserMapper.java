package com.cxt.work.correction.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cxt.work.correction.entity.bo.UserBO;
import com.cxt.work.correction.entity.bo.UserUpdateBO;
import com.cxt.work.correction.pojo.TbUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author xue-sheng-wen
 * @since 2020-02-24
 */
@Mapper
public interface TbUserMapper extends BaseMapper<TbUser> {

    @Select(value = "select * from tb_user where name = #{userName}")
    TbUser selectByUserName(@Param(value = "userName") String userName);

    List<Map<String, Object>> getUsers(String name);

    UserBO selectUserById(Long id);

    List<TbUser> getTeachers();

    List<TbUser> getStudents();

    UserUpdateBO findUserById(Long id);

    Integer deleteAll(List<Integer> idList);

}
