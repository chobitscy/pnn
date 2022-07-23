package com.pn.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pn.support.Query;
import com.pn.vo.ProductVo;

import javax.servlet.http.HttpServletRequest;

public interface ProductService {

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

    /**
     * 关注
     *
     * @param id      制作人 id
     * @param request 请求
     * @return 是否成功
     */
    Boolean follow(Long id, HttpServletRequest request);

    /**
     * 取关
     *
     * @param id      制作人 id
     * @param request 请求
     * @return 是否成功
     */
    Boolean unFollow(Long id, HttpServletRequest request);


}
