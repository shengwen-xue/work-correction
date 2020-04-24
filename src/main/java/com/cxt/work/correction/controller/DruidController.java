package com.cxt.work.correction.controller;

import com.alibaba.fastjson.JSON;
import com.cxt.work.correction.entity.dto.ResultDTO;
import com.cxt.work.correction.entity.enums.BusinessEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * <p>
 * 测试druid是否成功
 * </p>
 *
 * @author xue-sheng-wen
 * @date 2020/2/21 18:42
 */
@Slf4j
@Api(value = "测试druid")
@RestController(value = "/druid")
public class DruidController {

    @Autowired
    private DataSource dataSource;

    /**
     * 测试druid数据源是否配置成功
     *
     * @return 是否成功
     */
    @GetMapping("/dataSource")
    @ApiOperation(value = "测试druid数据源")
    public ResultDTO dataSource() {
        try {
            log.info("dataSource : {}", dataSource);
            Connection connection = dataSource.getConnection();
            log.info("connection : {}", connection);
            String dataSourceStr = JSON.toJSONString(connection);
            return ResultDTO.SUCCESS(dataSourceStr);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultDTO.FAIL(BusinessEnum.NOT_FOUND_DRUID_DATA_SOURCE.getMessage());
        }
    }
}
