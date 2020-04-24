package com.cxt.work.correction.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cxt.work.correction.mapper.TbUserInfoMapper;
import com.cxt.work.correction.pojo.TbUserInfo;
import com.cxt.work.correction.service.TbUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户基本信息表 服务实现类
 * </p>
 *
 * @author xue-sheng-wen
 * @since 2020-02-24
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class TbUserInfoServiceImpl extends ServiceImpl<TbUserInfoMapper, TbUserInfo> implements TbUserInfoService {

}
