package com.cxt.work.correction.controller;

import com.cxt.work.correction.entity.constants.NumberConstants;
import com.cxt.work.correction.entity.dto.ProblemDTO;
import com.cxt.work.correction.entity.dto.ResultDTO;
import com.cxt.work.correction.entity.enums.BusinessEnum;
import com.cxt.work.correction.pojo.TbLabel;
import com.cxt.work.correction.pojo.TbProblem;
import com.cxt.work.correction.pojo.TbReply;
import com.cxt.work.correction.pojo.TbUser;
import com.cxt.work.correction.service.TbLabelService;
import com.cxt.work.correction.service.TbProblemService;
import com.cxt.work.correction.service.TbReplyService;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 论坛前端控制器
 *
 * @author xue-sheng-wen
 * @date 2020/4/12 9:47
 */
@Controller
public class ForumController {

    @Resource
    private TbLabelService tbLabelService;

    @Resource
    private TbProblemService tbProblemService;

    @Resource
    private TbReplyService tbReplyService;

    @RequestMapping("/toForum")
    public String toForum(Model model) {
        List<TbLabel> tbLabels = tbLabelService.selectList(null);
        List<ProblemDTO> problemDTOList = tbProblemService.defaultProblemList();
        List<ProblemDTO> problemDTOListByJava = tbProblemService.getProblemList(tbLabels.get(0).getId());
        List<ProblemDTO> problemDTOListByPhp = tbProblemService.getProblemList(tbLabels.get(1).getId());
        List<ProblemDTO> problemDTOListByPython = tbProblemService.getProblemList(tbLabels.get(2).getId());
        List<ProblemDTO> problemDTOListByJavaScript = tbProblemService.getProblemList(tbLabels.get(3).getId());
        List<ProblemDTO> problemDTOListByVue = tbProblemService.getProblemList(tbLabels.get(4).getId());

        model.addAttribute("tbLabels", tbLabels);
        model.addAttribute("problemDTOList", problemDTOList);
        model.addAttribute("problemDTOListByJava", problemDTOListByJava);
        model.addAttribute("problemDTOListByPhp", problemDTOListByPhp);
        model.addAttribute("problemDTOListByPython", problemDTOListByPython);
        model.addAttribute("problemDTOListByJavaScript", problemDTOListByJavaScript);
        model.addAttribute("problemDTOListByVue", problemDTOListByVue);
        return "/forum/forum";
    }

    @RequestMapping("/toDetails")
    public String toDetails(Long id, Model model) {
        TbUser user = (TbUser) SecurityUtils.getSubject().getPrincipal();
        TbProblem tbProblem = tbProblemService.selectById(id);
        List<TbReply> replyList = tbReplyService.selectAllReplysByProblemId(id);
        model.addAttribute("tbProblem", tbProblem);
        model.addAttribute("replyList", replyList);
        model.addAttribute("problemId", id);
        model.addAttribute("userName", user.getName());
        return "/forum/details";
    }

    @RequestMapping("/saveReply")
    @ResponseBody
    public ResultDTO saveReply(String replyContent, Long problemId, String reolyName) {
        Integer result = tbReplyService.saveReply(replyContent, problemId, reolyName);
        if (result == NumberConstants.ZERO) {
            return ResultDTO.FAIL(BusinessEnum.REPLY_ERROR.getMessage());
        }
        return ResultDTO.SUCCESS(result);
    }

    @RequestMapping("/toQuestions")
    public String toQuestions(Model model) {
        TbUser user = (TbUser) SecurityUtils.getSubject().getPrincipal();
        List<TbLabel> tbLabels = tbLabelService.selectList(null);
        model.addAttribute("tbLabels", tbLabels);
        model.addAttribute("name", user.getName());
        return "/forum/questions";
    }
}
