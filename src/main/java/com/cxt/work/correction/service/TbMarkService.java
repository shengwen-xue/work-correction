package com.cxt.work.correction.service;

import com.baomidou.mybatisplus.service.IService;
import com.cxt.work.correction.pojo.TbMark;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xue-sheng-wen
 * @since 2020-03-29
 */
public interface TbMarkService extends IService<TbMark> {

    /**
     * 批阅作业
     *
     * @param id       编号
     * @param grade    分数
     * @param remark   评语
     * @return 影响行数
     */
    Integer reviewWork(Long id, Double grade, String remark);
}
