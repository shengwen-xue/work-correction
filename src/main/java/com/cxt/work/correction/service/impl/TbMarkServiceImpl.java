package com.cxt.work.correction.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cxt.work.correction.mapper.TbMarkMapper;
import com.cxt.work.correction.pojo.TbMark;
import com.cxt.work.correction.service.TbMarkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xue-sheng-wen
 * @since 2020-03-29
 */
@Service
public class TbMarkServiceImpl extends ServiceImpl<TbMarkMapper, TbMark> implements TbMarkService {

    @Resource
    private TbMarkMapper tbMarkMapper;

    @Override
    public Integer reviewWork(Long id, Double grade, String remark) {
        TbMark dbMark = tbMarkMapper.selectById(id);
        Integer result = null;
        if(Objects.nonNull(dbMark)) {
            dbMark.setId(id);
            dbMark.setGrade(grade);
            dbMark.setRemark(remark);
            dbMark.setMarkTime(new Date());
            result = tbMarkMapper.updateById(dbMark);
        }
        return result;
    }
}
