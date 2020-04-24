package com.cxt.work.correction.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cxt.work.correction.entity.dto.ProblemDTO;
import com.cxt.work.correction.pojo.TbProblem;

import java.util.List;

/**
 * <p>
 * 问题表 Mapper 接口
 * </p>
 *
 * @author xue-sheng-wen
 * @since 2020-03-31
 */
public interface TbProblemMapper extends BaseMapper<TbProblem> {

    List<ProblemDTO> defaultProblemList();

    List<ProblemDTO> getProblemList(Long id);
}
