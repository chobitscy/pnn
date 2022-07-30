package com.pn.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.pn.entry.Follow;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 关注视图类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"id", "pid", "uid", "createTime", "updateTime", "state"})
public class FollowVo extends Follow {

    @JsonUnwrapped
    @ApiModelProperty(value = "视频")
    private VideoVo video;
}
