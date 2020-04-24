package com.cxt.work.correction.service;

import com.baomidou.mybatisplus.service.IService;
import com.cxt.work.correction.entity.dto.ProblemDTO;
import com.cxt.work.correction.pojo.TbProblem;

import java.util.List;

/**
 * <p>
 * 问题表 服务类
 * </p>
 *
 * @author xue-sheng-wen
 * @since 2020-03-31
 */
public interface TbProblemService extends IService<TbProblem> {

    /**
     * 推荐列表
     *
     * @return 推荐列表
     */
    List<ProblemDTO> defaultProblemList();

    /**
     * 根据标签id获取问题列表
     *
     * @param id 标签id
     * @return 问题列表
     */
    List<ProblemDTO> getProblemList(Long id);

    /**
     * 新增问题
     *
     * @param arrayList    标签列表
     * @param problemTitle 问题标题
     * @param name         发布者
     * @return
     */
    Integer saveProblem(List<Long> arrayList, String problemTitle, String name);
}
