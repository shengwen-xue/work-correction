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
 * 回复表
 * </p>
 *
 * @author xue-sheng-wen
 * @since 2020-03-31
 */
@TableName("tb_reply")
public class TbReply extends Model<TbReply> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 回复人名称
     */
    @TableField("reply_name")
    private String replyName;
    /**
     * 回复内容
     */
    @TableField("reply_content")
    private String replyContent;
    /**
     * 回复时间
     */
    @TableField("reply_time")
    private Date replyTime;
    /**
     * 回复数
     */
    @TableField("reply_count")
    private Integer replyCount;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 问题id
     */
    @TableField("problem_id")
    private Long problemId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReplyName() {
        return replyName;
    }

    public void setReplyName(String replyName) {
        this.replyName = replyName;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public Integer getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getProblemId() {
        return problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TbReply{" +
        ", id=" + id +
        ", replyName=" + replyName +
        ", replyContent=" + replyContent +
        ", replyTime=" + replyTime +
        ", replyCount=" + replyCount +
        ", createTime=" + createTime +
        ", problemId=" + problemId +
        "}";
    }
}
