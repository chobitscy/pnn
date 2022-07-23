package com.pn.entry;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 收藏实体类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Star extends BaseEntity {

    @ApiModelProperty(value = "视频 id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long vid;

    @ApiModelProperty(value = "用户 id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long uid;
}
