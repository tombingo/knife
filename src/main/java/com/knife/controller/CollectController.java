package com.knife.controller;

import com.knife.core.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO
 * @Author geey
 * @Date 2023/8/2 14:56
 * @Version 1.0
 */
@RestController
@RequestMapping("/multiPhone/business/collect")
@Api(tags = "数据")
public class CollectController {

    @ApiImplicitParam(name = "auth",value = "身份凭证",paramType = "header")
    @ApiOperation(value = "添加一个数据")
    @PostMapping("add")
    public R add(){
        return R.ok();
    }

}
