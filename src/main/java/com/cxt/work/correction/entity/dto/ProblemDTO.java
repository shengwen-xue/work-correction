package com.cxt.work.correction.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author xue-sheng-wen
 * @date 2020/4/12 14:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProblemDTO {

    private Long id;
    /**
     * 问题标题
     */
    private String problemTitle;
    /**
     * 标题id
     */
    private Long labelId;
    /**
     * 创建时间
     */
    private Date creatTime;
    /**
     * 创建人
     */
    private String creatBy;
    /**
     * 回复数
     */
    private Integer replyCount;
}
