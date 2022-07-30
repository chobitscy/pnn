package com.pn.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pn.annotation.BaseResponse;
import com.pn.annotation.LoginUser;
import com.pn.service.ProductService;
import com.pn.support.Query;
import com.pn.vo.ProductVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 制作人接口控制器
 **/
@RestController
@RequestMapping("/product")
@Api(value = "制作人管理", tags = "制作人接口")
@BaseResponse
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @LoginUser
    @GetMapping("/page")
    @ApiOperation("分页")
    public IPage<ProductVo> selectByPage(Query query) {
        return productService.selectByPage(query);
    }

    @LoginUser
    @GetMapping("/{id}")
    @ApiOperation("详情")
    public ProductVo detail(@PathVariable Long id) {
        return productService.detail(id);
    }

    @LoginUser
    @GetMapping("/search/{keyword}")
    @ApiOperation("搜索")
    public IPage<ProductVo> search(Query query, @PathVariable String keyword) {
        return productService.search(query, keyword);
    }
}
