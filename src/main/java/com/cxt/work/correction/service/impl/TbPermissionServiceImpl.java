package com.cxt.work.correction.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cxt.work.correction.entity.dto.AssignAuthorityDTO;
import com.cxt.work.correction.mapper.TbPermissionMapper;
import com.cxt.work.correction.pojo.TbPermission;
import com.cxt.work.correction.service.TbPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author xue-sheng-wen
 * @since 2020-02-24
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class TbPermissionServiceImpl extends ServiceImpl<TbPermissionMapper, TbPermission> implements TbPermissionService {

    @Resource
    private TbPermissionMapper tbPermissionMapper;

    @Override
    public String selectStudentPermission() {
        List<AssignAuthorityDTO> studentPermissionList = tbPermissionMapper.selectStudentPermission();
        Long[] studentPermissionIdList = studentPermissionList.stream().map(AssignAuthorityDTO::getId).toArray(Long[]::new);
        StringBuffer studentStr = new StringBuffer();
        for (Long studentPermissionId : studentPermissionIdList) {
            studentStr.append(studentPermissionId + ",");
        }
        return studentStr.substring(0, studentStr.lastIndexOf(","));
    }

    @Override
    public String selectTeacherPermission() {
        List<AssignAuthorityDTO> studentPermissionList = tbPermissionMapper.selectTeacherPermission();
        Long[] teacherPermissionIdList = studentPermissionList.stream().map(AssignAuthorityDTO::getId).toArray(Long[]::new);
        StringBuffer teacherStr = new StringBuffer();
        for (Long teacherPermissionId : teacherPermissionIdList) {
            teacherStr.append(teacherPermissionId + ",");
        }
        return teacherStr.substring(0, teacherStr.lastIndexOf(","));
    }
}
