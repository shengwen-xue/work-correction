package com.cxt.work.correction.pojo;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 问题表
 * </p>
 *
 * @author xue-sheng-wen
 * @since 2020-03-31
 */
@TableName("tb_problem")
public class TbProblem extends Model<TbProblem> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 问题标题
     */
    @TableField("problem_title")
    private String problemTitle;
    /**
     * 标题id
     */
    @TableField("label_id")
    private Long labelId;
    /**
     * 创建时间
     */
    @TableField("creat_time")
    private Date creatTime;
    /**
     * 创建人
     */
    @TableField("creat_by")
    private String creatBy;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProblemTitle() {
        return problemTitle;
    }

    public void setProblemTitle(String problemTitle) {
        this.problemTitle = problemTitle;
    }

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public String getCreatBy() {
        return creatBy;
    }

    public void setCreatBy(String creatBy) {
        this.creatBy = creatBy;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TbProblem{" +
        ", id=" + id +
        ", problemTitle=" + problemTitle +
        ", labelId=" + labelId +
        ", creatTime=" + creatTime +
        ", creatBy=" + creatBy +
        "}";
    }
}
