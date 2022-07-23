package com.pn.vo;

import com.pn.entry.Star;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 收藏视图类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StarVo extends Star {

    @ApiModelProperty(value = "视频")
    private VideoVo video;
}
