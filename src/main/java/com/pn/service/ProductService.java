package com.pn.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pn.entry.Product;
import com.pn.support.Query;
import com.pn.vo.ProductVo;

public interface ProductService extends IService<Product> {

    /**
     * 分页
     *
     * @param query 分页参数
     * @return 分页结果
     */
    IPage<ProductVo> selectByPage(Query query);

    /**
     * 详情
     *
     * @param id 制作人 id
     * @return 制作人视图类
     */
    ProductVo detail(Long id);

    /**
     * 搜索
     *
     * @param query   分页参数
     * @param keyword 关键词
     * @return 搜索结果
     */
    IPage<ProductVo> search(Query query, String keyword);
}
