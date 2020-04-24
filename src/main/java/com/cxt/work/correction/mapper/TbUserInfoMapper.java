package com.cxt.work.correction.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cxt.work.correction.pojo.TbUserInfo;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <p>
 * 用户基本信息表 Mapper 接口
 * </p>
 *
 * @author xue-sheng-wen
 * @since 2020-02-24
 */
@Mapper
public interface TbUserInfoMapper extends BaseMapper<TbUserInfo> {

    TbUserInfo findUserInfoById(Long id);

    Integer deleteAll(List<Integer> idList);

    TbUserInfo selectUserInfoByUserId(Long id);
}
