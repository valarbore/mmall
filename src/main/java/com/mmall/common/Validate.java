package com.mmall.common;

import com.mmall.pojo.User;
import com.mmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;

public class Validate {
    @Autowired
    private static IUserService iUserService;

    /**
     * 校验用户是否已登录且为管理员
     * @param session
     * @return
     */
    public static ServerResponse checkAdmin(HttpSession session) {
//        User user = (User) session.getAttribute(Const.CURRENT_USER);
//        if (user == null) {
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
//        }
//        // 校验是否为管理员
//        if (iUserService.checkAdminRole(user).isSuccess()) {
//            // 是管理员 执行代码
//            return ServerResponse.createBySuccess();
//        } else {
//            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
//        }
        return null;
    }
}
