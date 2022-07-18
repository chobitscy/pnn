package com.pn.dto;

import com.pn.entry.Video;
import com.pn.support.validate.EditValidationGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * 视频传输类
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoDto extends Video {

    @ApiModelProperty(value = "分数")
    @NotNull(message = "分数不存在", groups = {EditValidationGroup.class})
    private Float score;
}