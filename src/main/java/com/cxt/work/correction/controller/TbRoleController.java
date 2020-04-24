package com.cxt.work.correction.controller;


import com.cxt.work.correction.entity.dto.ResultDTO;
import com.cxt.work.correction.entity.enums.BusinessEnum;
import com.cxt.work.correction.pojo.TbRole;
import com.cxt.work.correction.service.TbRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author xue-sheng-wen
 * @since 2020-02-24
 */
@Slf4j
@Api(value = "角色控制器")
@RestController("/role")
public class TbRoleController {

    @Resource
    private TbRoleService tbRoleService;

    @GetMapping("/selectRoleByUserName")
    @ApiOperation(value = "根据用户名查询角色")
    public ResultDTO selectRoleByUserName(
            @ApiParam(name = "userName", value = "用户名", required = true)
            @RequestParam(value = "userName") String userName) {
        TbRole role = tbRoleService.selectRoleByUserName(userName);
        if(Objects.isNull(role)) {
            return ResultDTO.FAIL(BusinessEnum.NOT_FOUND_ROLE.getMessage());
        }
        return ResultDTO.SUCCESS(role);
    }
}

