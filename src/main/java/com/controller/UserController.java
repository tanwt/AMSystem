package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.bean.User;
import com.service.UserService;
import com.utils.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param loginName
     * @param password
     * @param request
     * @return  {"code":0,"data":null,"msg":"登录失败"}
     *          {"code":1,"data":{"userDepartment":"行政部","userId":2,"userMail":"799957684@qq.com","userMarriage":0,"userName":"admin","userPassWord":"admin","userPhone":"13648404959","userSex":"M","userState":0},"msg":"成功"}
     */
    @RequestMapping("/userLogin")
    @ResponseBody
    public JSONObject userLogin(@RequestParam(value = "loginName",required = false) String loginName,
                                @RequestParam(value = "password",required = false) String password,
                                HttpServletRequest request){
        System.out.println("-------用户登录操作-------");
        System.out.println("用户登录:" + loginName );
        HashMap<Integer ,User> users = new HashMap<Integer, User>();
        User user = null;
        HttpSession session = request.getSession();
        if (session.getAttribute("users") != null){
            users = (HashMap<Integer, User>) session.getAttribute("users");
        }
        try {
            user = userService.userLogin(loginName,password);
            System.out.println("返回的登录用户数据：" + user);
            users.put(user.getUserId(),user);
            session.setAttribute("users",users);
            System.out.println("用户列表：" + session.getAttribute("users"));
            System.out.println("-------END-------");
            return CommonUtil.success(user);
        }
        catch (Exception e) {
            System.out.println("-------END-------");
            e.printStackTrace();
            return CommonUtil.error(e);
        }
    }

    /**
     * 用户注册
     * @param user
     * @return
     * {"code":0,"data":null,"msg":"错误的注册信息"}
     * {"code":0,"data":null,"msg":"重复注册"}
     * {"code":1,"data":null,"msg":"成功"}
     */
    @RequestMapping("/userRegister")
    @ResponseBody
    public JSONObject userRegister(User user){
        System.out.println("-------用户注册操作-------");
        System.out.println("请求注册的用户信息：" + user);
        logger.info("注册信息:" + user );
        try {
            int result = userService.userRegister(user);
            System.out.println("操作返回码：" + result);
            System.out.println("-------END-------");
            return CommonUtil.success(null);
        } catch (Exception e) {
            System.out.println("-------END-------");
            e.printStackTrace();
            return CommonUtil.error(e);
        }
    }

    /**
     * 用户更新
     * @param user
     * @return
     * {"code":0,"data":null,"msg":"更新失败"}
     * {"code":0,"data":null,"msg":"更新信息已存在"}
     * {"code":1,"data":{"userDepartment":"行政部","userId":11,"userMail":"799857684@qq.com","userMarriage":0,"userName":"text1","userPassWord":"text1","userPhone":"13548404959","userSex":"M","userState":0},"msg":"成功"}
     */
    @RequestMapping("/userUpdate")
    @ResponseBody
    public JSONObject userUpdate(User user,HttpServletRequest request){
        System.out.println("-------用户更新操作-------");
        System.out.println("用户更新数据:"+user );
        HttpSession session = request.getSession();
        HashMap<Integer,User> users = new HashMap<Integer, User>();
        if (request.getSession().getAttribute("users") != null)
            users = (HashMap<Integer, User>) request.getSession().getAttribute("users");
        try {
            user = userService.updateUserById(user);
            System.out.println("更新后："+user);
            users.put(user.getUserId(),user);
            session.setAttribute("users",users);
            System.out.println("用户列表：" + session.getAttribute("users"));
            System.out.println("-------END-------");
            return CommonUtil.success(user);
        } catch (Exception e) {
            System.out.println("-------END-------");
            e.printStackTrace();
            return CommonUtil.error(e);
        }
    }

    /**
     * 获取某部门用户信息
     * @param session
     * @return
     */
   /* @RequestMapping("/getUserByDepartment")
    @ResponseBody
    public JSONObject getUserByDepartment(HttpServletRequest request , HttpSession session){
        Map<Integer , User> usersLogin = null;
        usersLogin = (Map) session.getAttribute("users");
        User user = null;
        try {
            Integer userId = Integer.valueOf(request.getParameter("userId"));
            user = (User) usersLogin.get(userId);
        }catch (NullPointerException e){
            e.printStackTrace();
            return CommonUtil.constructResponse(0,"用户未登录",null);
        };
        if (user != null){
            List users = new ArrayList();
            try {
                users = userService.getUserByDepartment(user);
                return CommonUtil.success(users);
            } catch (Exception e) {
                return CommonUtil.error(e);
            }
        }
        return CommonUtil.constructResponse(0,"用户未登录",null);
    }
*/
}
