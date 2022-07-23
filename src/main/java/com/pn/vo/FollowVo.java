package com.pn.vo;

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
public class FollowVo extends Follow {

    @ApiModelProperty(value = "制作人")
    private ProductVo product;
}
