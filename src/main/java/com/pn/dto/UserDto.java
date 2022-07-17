package com.pn.dto;

import com.pn.entry.User;
import com.pn.support.validate.ExpireValidationGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用户传输类
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "过期时间（单位分钟）")
    @NotNull(message = "过期时间不能为空", groups = {ExpireValidationGroup.class})
    private Integer expire;
}