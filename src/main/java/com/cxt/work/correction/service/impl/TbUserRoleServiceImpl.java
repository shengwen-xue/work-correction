package com.cxt.work.correction.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cxt.work.correction.mapper.TbUserRoleMapper;
import com.cxt.work.correction.pojo.TbUserRole;
import com.cxt.work.correction.service.TbUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户角色中间表 服务实现类
 * </p>
 *
 * @author xue-sheng-wen
 * @since 2020-02-24
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class TbUserRoleServiceImpl extends ServiceImpl<TbUserRoleMapper, TbUserRole> implements TbUserRoleService {

    @Resource
    private TbUserRoleMapper tbUserRoleMapper;

    @Override
    public List<TbUserRole> selectUserRolesByUserId(Long id) {
        return tbUserRoleMapper.selectUserRolesByUserId(id);
    }
}
