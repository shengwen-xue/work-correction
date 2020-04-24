package com.cxt.work.correction.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cxt.work.correction.mapper.TbWorkMapper;
import com.cxt.work.correction.pojo.TbWork;
import com.cxt.work.correction.service.TbWorkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 作业表 服务实现类
 * </p>
 *
 * @author xue-sheng-wen
 * @since 2020-03-22
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class TbWorkServiceImpl extends ServiceImpl<TbWorkMapper, TbWork> implements TbWorkService {

    @Resource
    private TbWorkMapper tbWorkMapper;

    @Override
    public Integer save(TbWork tbWork) {
        return tbWorkMapper.insert(tbWork);
    }

    @Override
    public Integer deleteFile(Long id) {
        return tbWorkMapper.deleteById(id);
    }
}
