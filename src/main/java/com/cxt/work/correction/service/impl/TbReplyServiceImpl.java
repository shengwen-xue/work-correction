package com.cxt.work.correction.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cxt.work.correction.entity.constants.NumberConstants;
import com.cxt.work.correction.mapper.TbReplyMapper;
import com.cxt.work.correction.pojo.TbReply;
import com.cxt.work.correction.service.TbReplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 回复表 服务实现类
 * </p>
 *
 * @author xue-sheng-wen
 * @since 2020-03-31
 */
@Service
public class TbReplyServiceImpl extends ServiceImpl<TbReplyMapper, TbReply> implements TbReplyService {

    @Resource
    private TbReplyMapper tbReplyMapper;

    public List<TbReply> selectAllReplysByProblemId(Long id) {
        return tbReplyMapper.selectAllReplysByProblemId(id);
    }

    @Override
    public Integer saveReply(String replyContent, Long problemId, String reolyName) {
        TbReply tbreply = new TbReply();
        tbreply.setReplyName(reolyName);
        tbreply.setReplyContent(replyContent);
        tbreply.setReplyTime(new Date());
        tbreply.setReplyCount(NumberConstants.ONE);
        tbreply.setCreateTime(new Date());
        tbreply.setProblemId(problemId);
        return tbReplyMapper.insert(tbreply);
    }
}
