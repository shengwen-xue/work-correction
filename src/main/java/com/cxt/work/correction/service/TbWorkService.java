package com.cxt.work.correction.service;

import com.baomidou.mybatisplus.service.IService;
import com.cxt.work.correction.pojo.TbWork;

/**
 * <p>
 * 作业表 服务类
 * </p>
 *
 * @author xue-sheng-wen
 * @since 2020-03-22
 */
public interface TbWorkService extends IService<TbWork> {

    Integer save(TbWork tbWork);

    Integer deleteFile(Long id);
}
