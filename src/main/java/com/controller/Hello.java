package com.controller;

import com.JFrame.LoginPanel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * create by tanwt
 * 2018/5/16 0016 17:03
 **/
@Controller
public class Hello {

    @RequestMapping("/index")
    @ResponseBody
    public String index(){
        new LoginPanel();
        return "/dist/index.html";
    }
}
