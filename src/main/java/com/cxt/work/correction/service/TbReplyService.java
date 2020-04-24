package com.cxt.work.correction.service;

import com.baomidou.mybatisplus.service.IService;
import com.cxt.work.correction.pojo.TbReply;

import java.util.List;

/**
 * <p>
 * 回复表 服务类
 * </p>
 *
 * @author xue-sheng-wen
 * @since 2020-03-31
 */
public interface TbReplyService extends IService<TbReply> {

    /**
     * 根据问题id查询所有的回复
     *
     * @param id 问题id
     * @return 回复集合
     */
    List<TbReply> selectAllReplysByProblemId(Long id);

    /**
     * 新增回复
     *
     * @param replyContent 回复内容
     * @param problemId    问题id
     * @param reolyName    回复者
     * @return 影响行数
     */
    Integer saveReply(String replyContent, Long problemId, String reolyName);

}
