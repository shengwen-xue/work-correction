package com.cxt.work.correction.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cxt.work.correction.mapper.TbRolePermissionMapper;
import com.cxt.work.correction.pojo.TbPermission;
import com.cxt.work.correction.pojo.TbRolePermission;
import com.cxt.work.correction.service.TbRolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 角色权限中间表 服务实现类
 * </p>
 *
 * @author xue-sheng-wen
 * @since 2020-02-24
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class TbRolePermissionServiceImpl extends ServiceImpl<TbRolePermissionMapper, TbRolePermission> implements TbRolePermissionService {

    @Resource
    private TbRolePermissionMapper tbRolePermissionMapper;

    @Override
    public List<TbPermission> selectPermissionNameByRoleIds(Long... roleIds) {
        return tbRolePermissionMapper.selectPermissionNameByRoleIds(roleIds);
    }
}
