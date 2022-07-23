package com.pn.dto;

import com.pn.entry.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 制作人传输类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class ProductDto extends Product {
}
