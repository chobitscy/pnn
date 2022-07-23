package com.pn.entry;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.pn.support.validate.AddValidationGroup;
import com.sun.xml.internal.ws.developer.Serialization;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 视频实体类
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Video extends BaseEntity {

    @ApiModelProperty(value = "视频 id")
    private String vid;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(
            pattern = "yyyy-MM-dd"
    )
    private Date createDate;

    @ApiModelProperty(value = "hash")
    private String infoHash;

    @ApiModelProperty(value = "大小")
    private Float size;

    @ApiModelProperty(value = "速度")
    private Integer speeders;

    @ApiModelProperty(value = "下载数")
    private Integer downloads;

    @ApiModelProperty(value = "完成数")
    private Integer completed;

    @ApiModelProperty(value = "分数")
    private Float rate;

    @ApiModelProperty(value = "官方截图")
    private String screenshot;

    @ApiModelProperty(value = "发行时间")
    @JsonFormat(
            pattern = "yyyy-MM-dd"
    )
    private Date pubDate;

    @ApiModelProperty(value = "封面")
    private String thumb;

    @ApiModelProperty(value = "第三方截图")
    private String printScreen;

    @ApiModelProperty(value = "标签 id")
    private String tid;

    @ApiModelProperty(value = "产品 id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long pid;

    @ApiModelProperty(value = "评论数")
    private Integer comments;

    @ApiModelProperty(value = "喜欢数")
    private Integer likes;

    @ApiModelProperty(value = "回复数")
    private Integer reply;

    @ApiModelProperty(value = "是否审查")
    @NotNull(message = "审查不存在", groups = {AddValidationGroup.class})
    private Boolean uncensored;

    @ApiModelProperty(value = "nyaa id")
    private String cid;
}