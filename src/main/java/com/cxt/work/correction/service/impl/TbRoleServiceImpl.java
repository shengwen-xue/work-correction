package com.cxt.work.correction.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cxt.work.correction.mapper.TbRoleMapper;
import com.cxt.work.correction.pojo.TbRole;
import com.cxt.work.correction.service.TbRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author xue-sheng-wen
 * @since 2020-02-24
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class TbRoleServiceImpl extends ServiceImpl<TbRoleMapper, TbRole> implements TbRoleService {

    @Resource
    private TbRoleMapper tbRoleMapper;

    @Override
    public List<TbRole> selectRoleNameByRoleIds(Long[] roleIds) {
        return tbRoleMapper.selectRoleNameByRoleIds(roleIds);
    }

    @Override
    public TbRole selectRoleByUserName(String userName) {
        return tbRoleMapper.selectRoleByUserName(userName);
    }
}
