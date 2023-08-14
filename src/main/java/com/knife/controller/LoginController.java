package com.knife.controller;
import com.knife.config.LoginUser;
import com.knife.core.R;
import com.knife.core.jwt.Jwt;
import com.knife.config.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @Description: TODO
 * @Author geey
 * @Date 2023/8/1 11:17
 * @Version 1.0
 */
@Api(tags = "登录")
@RestController
@RequestMapping("/multiPhone/login")
public class LoginController {

    @Autowired
    private TokenService tokenService;

    @ApiOperation(value = "使用密码登录")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "password",value = "密码",required = true),
            @ApiImplicitParam(name = "userName",value = "用户名",required = true)
    })
    @GetMapping("/loginByPwd")
    public R loginByPwd(
            String userName,
            String password
    ){
        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(userName);
        Jwt jwt = tokenService.encrypt(loginUser);
        return R.ok(jwt);
    }
}
