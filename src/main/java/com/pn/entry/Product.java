package com.pn.entry;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity {

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "主页")
    private String home;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "作品量")
    private Integer works;

    @ApiModelProperty(value = "粉丝数")
    private Integer fans;

    @ApiModelProperty(value = "追随数")
    private Integer follow;
}
