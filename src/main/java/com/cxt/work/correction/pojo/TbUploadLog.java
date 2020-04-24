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
 * 上传日志
 * </p>
 *
 * @author xue-sheng-wen
 * @since 2020-03-22
 */
@TableName("tb_upload_log")
public class TbUploadLog extends Model<TbUploadLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 操作人
     */
    private String operator;
    /**
     * 上传日期
     */
    @TableField("upload_date")
    private Date uploadDate;
    /**
     * 上传内容
     */
    @TableField("upload_content")
    private String uploadContent;
    /**
     * 状态(1：成功 0：失败)
     */
    private Integer status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getUploadContent() {
        return uploadContent;
    }

    public void setUploadContent(String uploadContent) {
        this.uploadContent = uploadContent;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TbUploadLog{" +
        ", id=" + id +
        ", operator=" + operator +
        ", uploadDate=" + uploadDate +
        ", uploadContent=" + uploadContent +
        ", status=" + status +
        "}";
    }
}
