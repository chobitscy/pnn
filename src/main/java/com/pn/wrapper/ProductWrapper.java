package com.pn.wrapper;

import com.pn.entry.Product;
import com.pn.support.BaseEntityWrapper;
import com.pn.vo.ProductVo;
import org.springframework.beans.BeanUtils;

/**
 * 制作人转化类
 */
public class ProductWrapper extends BaseEntityWrapper<Product, ProductVo> {

    public static ProductWrapper build() {
        return new ProductWrapper();
    }

    @Override
    public ProductVo entityVO(Product entity) {
        if (entity == null) {
            return null;
        }
        ProductVo vo = new ProductVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }
}
