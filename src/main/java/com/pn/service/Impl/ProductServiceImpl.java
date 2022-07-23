package com.pn.service.Impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pn.entry.Product;
import com.pn.enums.ResponseCode;
import com.pn.mapper.ProductMapper;
import com.pn.service.ProductService;
import com.pn.support.Condition;
import com.pn.support.Query;
import com.pn.support.exception.BaseException;
import com.pn.utils.UserUtil;
import com.pn.vo.ProductVo;
import com.pn.wrapper.ProductWrapper;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Override
    @Cacheable(value = "product", keyGenerator = "md5KeyGenerator")
    public IPage<ProductVo> selectByPage(Query query) {
        return ProductWrapper.build().pageVO(this.page(Condition.getPage(query), this.buildOrderByFansWrapper()));
    }

    @Override
    @Cacheable(value = "product", keyGenerator = "md5KeyGenerator")
    public ProductVo detail(Long id) {
        Product product = Optional.ofNullable(this.getById(id))
                .orElseThrow(() -> new BaseException(ResponseCode.SERVICE_ERROR, "id 不存在"));
        return ProductWrapper.build().entityVO(product);
    }

    @Override
    @Cacheable(value = "product", keyGenerator = "md5KeyGenerator")
    public IPage<ProductVo> search(Query query, String keyword) {
        Wrapper<Product> wrapper = this.buildOrderByFansWrapper()
                .like(Product::getName, keyword);
        return ProductWrapper.build().pageVO(this.page(Condition.getPage(query), wrapper));
    }

    @Override
    public Boolean follow(Long id, HttpServletRequest request) {
        Long userId = UserUtil.getUserIdByRequest(request);
        return null;
    }

    @Override
    public Boolean unFollow(Long id, HttpServletRequest request) {
        return null;
    }

    /**
     * 构建按 fans 倒序的 wrapper
     *
     * @return wrapper
     */
    private LambdaQueryWrapper<Product> buildOrderByFansWrapper() {
        return new LambdaQueryWrapper<Product>()
                .orderByDesc(Product::getFans);
    }
}
