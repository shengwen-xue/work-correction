package com.cxt.work.correction.controller;


import com.cxt.work.correction.entity.constants.NumberConstants;
import com.cxt.work.correction.entity.dto.ResultDTO;
import com.cxt.work.correction.entity.enums.BusinessEnum;
import com.cxt.work.correction.service.TbProblemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 问题表 前端控制器
 * </p>
 *
 * @author xue-sheng-wen
 * @since 2020-03-31
 */
@Controller
public class TbProblemController {

    @Resource
    private TbProblemService tbProblemService;

    @PostMapping("/saveProblem")
    @ResponseBody
    public ResultDTO saveProblem(@RequestParam("arrayList[]") List<Long> arrayList, String problemTitle, String name) {
        Integer result = tbProblemService.saveProblem(arrayList, problemTitle, name);
        if(result == NumberConstants.ZERO) {
            return ResultDTO.FAIL(BusinessEnum.ISSUE_ERROR.getMessage());
        }
        return ResultDTO.SUCCESS(result);
    }
}

