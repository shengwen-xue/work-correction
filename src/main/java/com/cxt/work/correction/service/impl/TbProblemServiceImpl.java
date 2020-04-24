package com.cxt.work.correction.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cxt.work.correction.entity.dto.ProblemDTO;
import com.cxt.work.correction.mapper.TbProblemMapper;
import com.cxt.work.correction.pojo.TbProblem;
import com.cxt.work.correction.service.TbProblemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 问题表 服务实现类
 * </p>
 *
 * @author xue-sheng-wen
 * @since 2020-03-31
 */
@Service
public class TbProblemServiceImpl extends ServiceImpl<TbProblemMapper, TbProblem> implements TbProblemService {

    @Resource
    private TbProblemMapper tbProblemMapper;

    @Override
    public List<ProblemDTO> defaultProblemList() {
        return tbProblemMapper.defaultProblemList();
    }

    @Override
    public List<ProblemDTO> getProblemList(Long id) {
        return tbProblemMapper.getProblemList(id);
    }

    @Override
    public Integer saveProblem(List<Long> arrayList, String problemTitle, String name) {
        List<TbProblem> problemLinkedList = new LinkedList<>();
        Integer result = null;
        for (Long labelId : arrayList) {
            TbProblem problem = new TbProblem();
            problem.setProblemTitle(problemTitle);
            problem.setLabelId(labelId);
            problem.setCreatTime(new Date());
            problem.setCreatBy(name);
            problemLinkedList.add(problem);
        }
        for (TbProblem problem : problemLinkedList) {
            result = tbProblemMapper.insert(problem);
        }
        return result;
    }
}
