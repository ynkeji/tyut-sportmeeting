package com.tyut.sport.controller;

import com.tyut.sport.config.TokenUtil;
import com.tyut.sport.entity.DataResult;
import com.tyut.sport.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class adminController {


    @Autowired
    private AdminService adminService;

    @Autowired
    private com.tyut.sport.service.TokenService TokenService;
    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/admin",method = RequestMethod.GET)
    public DataResult admin(@RequestHeader Map<String, String> headers){
        DataResult dataResult = new DataResult();
        String token = headers.get("token");
        System.out.println("admin:" + token);

        if (check(token)){
            String username =  TokenUtil.getId(headers.get("token"));
            com.tyut.sport.pojo.admin admin = adminService.QueryAdminInfor(username);
            dataResult.setData(admin);
            dataResult.setStatus(1);
            dataResult.setMsg("获取成功");
            return dataResult;
        }
        dataResult.setMsg("身份验证失败，请重新登录");
        dataResult.setStatus(-2);
        return dataResult;
    }

    private Boolean check(String token){
        if (token == null) return false;
        if(TokenUtil.verify(token)) {
            if (TokenService.SelectToken(token) == null) {
                return false;
            } else {
                System.out.println("身份验证成功");
                return true;
            }
        }
        return false;
    }

}
