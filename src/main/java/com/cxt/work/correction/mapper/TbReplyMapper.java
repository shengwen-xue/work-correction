package com.cxt.work.correction.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cxt.work.correction.pojo.TbReply;

import java.util.List;

/**
 * <p>
 * 回复表 Mapper 接口
 * </p>
 *
 * @author xue-sheng-wen
 * @since 2020-03-31
 */
public interface TbReplyMapper extends BaseMapper<TbReply> {

    List<TbReply> selectAllReplysByProblemId(Long id);
}
