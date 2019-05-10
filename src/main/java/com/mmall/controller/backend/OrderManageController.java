package com.mmall.controller.backend;

import com.mmall.common.ServerResponse;
import com.mmall.service.IOrderService;
import com.mmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller("/manage/order")
public class OrderManageController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private IOrderService iOrderService;

    /**
     * 管理端获取订单列表
     *
     * @param session
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse orderList(HttpSession session, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                    @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        ServerResponse response = iUserService.checkAdminRole(session);
        if (response.isSuccess()) {
            return iOrderService.manageList(pageNum, pageSize);
        }
        return response;
    }

    /**
     * 根据订单号获取订单详情
     * @param session
     * @param orderNo
     * @return
     */
    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse orderDetail(HttpSession session, Long orderNo) {
        ServerResponse response = iUserService.checkAdminRole(session);
        if (response.isSuccess()) {
            return iOrderService.manageDetail(orderNo);
        }
        return response;
    }

    /**
     * 搜索订单
     * @param session
     * @param orderNo
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("search.do")
    @ResponseBody
    public ServerResponse orderSearch(HttpSession session, Long orderNo, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                      @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        ServerResponse response = iUserService.checkAdminRole(session);
        if (response.isSuccess()) {
            return iOrderService.manageSearch(orderNo,pageNum,pageSize);
        }
        return response;
    }

    @RequestMapping("send_goods.do")
    @ResponseBody
    public ServerResponse orderSendGoods(HttpSession session, Long orderNo) {
        ServerResponse response = iUserService.checkAdminRole(session);
        if (response.isSuccess()) {
            return iOrderService.manageSendGoods(orderNo);
        }
        return response;
    }
}
