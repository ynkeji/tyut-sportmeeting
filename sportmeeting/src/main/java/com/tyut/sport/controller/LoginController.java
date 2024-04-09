package com.tyut.sport.controller;

import com.tyut.sport.config.TokenUtil;
import com.tyut.sport.entity.DataResult;
import com.tyut.sport.pojo.admin;
import com.tyut.sport.pojo.login;
import com.tyut.sport.service.AdminService;
import com.tyut.sport.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private TokenService tokenService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public DataResult Login(@RequestBody login login, @RequestHeader Map<String, String> headers) {
        DataResult dataResult = new DataResult();
        admin admin = adminService.QueryAdmin(login.getUsername(), login.getPassword());
        String token = headers.get("token");
        if (token != null)tokenService.DelToken(token);
        if (admin != null) {
            token = TokenUtil.sign(login.getUsername(), login.getPassword());
            tokenService.SetToken(token);
            System.out.println(token);
            dataResult.setStatus(1);
            dataResult.setMsg("登录成功");
            dataResult.setData(token);
        } else {
            dataResult.setStatus(0);
            dataResult.setMsg("登录失败");
        }
        return dataResult;
    }

    @CrossOrigin
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public DataResult Logout(@RequestHeader Map<String, String> headers){
        DataResult dataResult = new DataResult();
        String token = headers.get("token");
        if (token != null) tokenService.DelToken(token);
        System.out.println("删除token" + token);
        dataResult.setStatus(1);
        dataResult.setMsg("登出成功！");
        return dataResult;
    }
}