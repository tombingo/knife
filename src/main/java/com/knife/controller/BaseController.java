package com.knife.controller;

import com.knife.config.Operate;
import com.knife.core.R;
import com.knife.core.validator.group.SaveOrUpdate;
import com.mybatisflex.core.BaseMapper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author geey
 * @date 2022/9/27 18:46
 */
@Validated
public abstract class BaseController<T> {


    private BaseMapper<T> baseMapper;

    public BaseController(BaseMapper<T> baseMapper) {
        this.baseMapper = baseMapper;
    }

    @ApiOperation(value = "通过主键查询单条原始数据")
    @ApiImplicitParam(name = "id", value = "主键")
    @GetMapping("getById")
    @Operate(logResultData = true, logMethodArgs = true)
    public R<T> getById(@NotNull(message = "id不能为空") @RequestParam("id") Serializable id) {
        T t = baseMapper.selectOneById(id);
        return R.ok(t);
    }

    @ApiOperation(value = "保存或者更新一条数据")
    @Validated(SaveOrUpdate.class)
    @PostMapping("saveOrUpdateOne")
    @Operate(logResultData = true, logMethodArgs = true)
    public R<T> saveOrUpdateOne(@RequestBody @Valid T t) {
        baseMapper.insert(t);
        return R.ok(t);
    }

    @Data
    class Del {
        @NotNull
        private Serializable id;
    }

    @ApiOperation(value = "通过主键删除一条数据")
    @Validated
    @PostMapping("delById")
    public R delById(@RequestBody @Valid Del del) {
        baseMapper.deleteById(del.getId());
        return R.ok();
    }

}
