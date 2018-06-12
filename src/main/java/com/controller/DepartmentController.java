package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.service.DepartmentService;
import com.utils.CommonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * create by tanwt
 * 2018/5/8 0008 16:37
 **/
@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Resource
    private DepartmentService departmentService;

    @RequestMapping("/getDepartmentList")
    @ResponseBody
    public JSONObject getDepartmentList(){
        List list = null;
        try {
            list = departmentService.getAllDepartment();
        } catch (Exception e) {
            return CommonUtil.error(e);
        }
        return CommonUtil.success(list);
    }
}
