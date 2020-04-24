package com.cxt.work.correction.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cxt.work.correction.mapper.TbUploadLogMapper;
import com.cxt.work.correction.pojo.TbUploadLog;
import com.cxt.work.correction.service.TbUploadLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 上传日志 服务实现类
 * </p>
 *
 * @author xue-sheng-wen
 * @since 2020-03-22
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class TbUploadLogServiceImpl extends ServiceImpl<TbUploadLogMapper, TbUploadLog> implements TbUploadLogService {

}
