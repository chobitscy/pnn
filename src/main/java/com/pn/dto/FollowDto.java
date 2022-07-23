package com.pn.dto;

import com.pn.entry.Follow;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 关注传输类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class FollowDto extends Follow {
}
