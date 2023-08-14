package com.knife.controller;

import com.knife.mybaties.entity.CollectHead;
import com.mybatisflex.core.BaseMapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO
 * @Author geey
 * @Date 2023/8/2 16:05
 * @Version 1.0
 */
@RestController
@RequestMapping("/multiPhone/business/collectHead")
@Api(tags = "collectHead")
public class CollectHeadController extends BaseController<CollectHead> {

    @Autowired
    public CollectHeadController(BaseMapper<CollectHead> baseMapper) {
        super(baseMapper);
    }
}
