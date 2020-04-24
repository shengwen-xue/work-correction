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
 * 
 * </p>
 *
 * @author xue-sheng-wen
 * @since 2020-03-29
 */
@TableName("tb_mark")
public class TbMark extends Model<TbMark> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 上传者
     */
    private String uploader;
    /**
     * 文件名
     */
    @TableField("file_name")
    private String fileName;

    /**
     * url
     */
    @TableField("check_url")
    private String checkUrl;

    /**
     * 分数
     */
    private Double grade;
    /**
     * 评论
     */
    private String remark;
    /**
     * 批阅时间
     */
    @TableField("mark_time")
    private Date markTime;

    /**
     * 上传时间
     */
    @TableField("upload_time")
    private Date uploadTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getCheckUrl() {
        return checkUrl;
    }

    public void setCheckUrl(String checkUrl) {
        this.checkUrl = checkUrl;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getMarkTime() {
        return markTime;
    }

    public void setMarkTime(Date markTime) {
        this.markTime = markTime;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TbMark{" +
        ", id=" + id +
        ", uploader=" + uploader +
        ", fileName=" + fileName +
        ", checkUrl=" + checkUrl +
        ", grade=" + grade +
        ", remark=" + remark +
        ", markTime=" + markTime +
        ", uploadTime=" + uploadTime +
        "}";
    }
}
