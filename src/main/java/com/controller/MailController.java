package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.bean.User;
import com.dao.UserDao;
import com.utils.CheckNull;
import com.utils.CommonUtil;
import com.utils.MailUtil;
import com.utils.Verify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 邮箱验证接口
 * create by tanwt
 * 2018/5/8 0008 9:30
 **/
@Controller
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private UserDao userDao;

    /**
     * 获取验证
     * {"code":1,"data":"643","msg":"成功"}
     * {"code":0,"data":null,"msg":"操作失败"}
     * @param mail
     * @return
     */
    @RequestMapping("/getCode")
    @ResponseBody
    public JSONObject getCode(@RequestParam(value = "mail",required = false) String mail){
        System.out.println("-------获取验证-------");
        System.out.println("请求的邮箱："+mail);
        try{
            if (!Verify.verifyEmailFormat(mail)){
                return CommonUtil.error("邮箱格式有误");
            }
            String code = Verify.getVerify(5);
            new Thread(new MailUtil(mail,code)).start();
            System.out.println("操作返回信息:"+code);
            System.out.println("-------END-------");
            return CommonUtil.success(code);
        }catch (NullPointerException e){
            System.out.println("-------END-------");
            e.printStackTrace();
            return CommonUtil.error();
        }
    }

    /**
     * 修改密码
     * @param mail
     * @return
     * {"code":0,"data":null,"msg":"邮箱格式有误"}
     * {"code":0,"data":null,"msg":"该邮箱未注册"}
     * {"code":1,"data":[{"userDepartment":"行政部","userId":4,"userMail":"1586559635@qq.com","userMarriage":1,"userName":"tanwt","userPassWord":"123456","userPhone":"17783139574","userSex":"W","userState":0},"36853"],"msg":"成功"}
     */
    @RequestMapping("/changePassword")
    @ResponseBody
    public JSONObject changePassword(@RequestParam(value = "mail",required = false) String mail){
        try {
            System.out.println("-------修改密码-------");
            System.out.println("请求的邮箱："+mail);
            if (!Verify.verifyEmailFormat(mail)){
                return CommonUtil.error("邮箱格式有误");
            }
            User user = new User();
            user.setUserMail(mail);
            List<User> users = userDao.selectUserByNameOREmailORId(user);
            if (users.isEmpty()){
                return CommonUtil.error("该邮箱未注册");
            }
            String code = Verify.getVerify(5);
            new Thread(new MailUtil(mail,code)).start();
            List<Object> data = new ArrayList<Object>();
            System.out.println("操作返回信息code:"+code);
            System.out.println("操作返回信息data:"+data);
            System.out.println("-------END-------");
            data.add(users.get(0));
            data.add(code);
            return CommonUtil.success(data);
        }catch (Exception e){
            System.out.println("-------END-------");
            e.printStackTrace();
            return CommonUtil.error(e);
        }
    }

}
