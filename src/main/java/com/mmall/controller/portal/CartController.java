package com.mmall.controller.portal;

import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.ICartService;
import com.mmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart/")
public class CartController {

    @Autowired
    private ICartService iCartService;

    /**
     * 购物车添加商品
     * @param session
     * @param count
     * @param productId
     * @return
     */
    @RequestMapping("add.do")
    @ResponseBody
    public ServerResponse add(HttpSession session,Integer count,Integer productId){
        User user =(User)session.getAttribute(Const.CURRENT_USER);
        if(user==null){
           return ServerResponse.createByErrorMessage("添加失败，用户未登录");
        }
        return iCartService.add(user.getId(),productId,count);
    }

    /**
     * 购物车商品数量更新
     * @param session
     * @param count
     * @param productId
     * @return
     */
    @RequestMapping("update.do")
    @ResponseBody
    public ServerResponse update(HttpSession session,Integer count,Integer productId){
        User user =(User)session.getAttribute(Const.CURRENT_USER);
        if(user==null){
            return ServerResponse.createByErrorMessage("更新失败，用户未登录");
        }
        return iCartService.update(user.getId(),productId,count);
    }


    /**
     * 购物车删除商品
     * @param session
     * @param productIds
     * @return
     */
    @RequestMapping("delete_product.do")
    @ResponseBody
    public ServerResponse deleteProduct(HttpSession session,String productIds){
        User user =(User)session.getAttribute(Const.CURRENT_USER);
        if(user==null){
            return ServerResponse.createByErrorMessage("删除失败，用户未登录");
        }
        return iCartService.delete(user.getId(),productIds);
    }

    /**
     * 获取购物车列表
     * @param session
     * @return
     */
    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse list(HttpSession session){
        User user =(User)session.getAttribute(Const.CURRENT_USER);
        if(user==null){
            return ServerResponse.createByErrorMessage("请求失败，用户未登录");
        }
        return iCartService.list(user.getId());
    }


    /**
     * 购物车全选
     * @param session
     * @return
     */
    @RequestMapping("select_all.do")
    @ResponseBody
    public ServerResponse selectAll(HttpSession session){
        User user =(User)session.getAttribute(Const.CURRENT_USER);
        if(user==null){
            return ServerResponse.createByErrorMessage("操作失败，用户未登录");
        }
        return iCartService.selectOrUnselect(user.getId(),Const.Cart.CHECKED,null);
    }

    /**
     * 购物车取消全选
     * @param session
     * @return
     */
    @RequestMapping("un_select_all.do")
    @ResponseBody
    public ServerResponse unSelectAll(HttpSession session){
        User user =(User)session.getAttribute(Const.CURRENT_USER);
        if(user==null){
            return ServerResponse.createByErrorMessage("操作失败，用户未登录");
        }
        return iCartService.selectOrUnselect(user.getId(),Const.Cart.UNCHECKED,null);
    }

    /**
     * 购物车单选
     * @param session
     * @param productId
     * @return
     */
    @RequestMapping("select.do")
    @ResponseBody
    public ServerResponse select(HttpSession session,Integer productId){
        User user =(User)session.getAttribute(Const.CURRENT_USER);
        if(user==null){
            return ServerResponse.createByErrorMessage("操作失败，用户未登录");
        }
        return iCartService.selectOrUnselect(user.getId(),Const.Cart.CHECKED,productId);
    }

    /**
     * 购物车取消单选
     * @param session
     * @param productId
     * @return
     */
    @RequestMapping("un_select.do")
    @ResponseBody
    public ServerResponse unSelect(HttpSession session,Integer productId){
        User user =(User)session.getAttribute(Const.CURRENT_USER);
        if(user==null){
            return ServerResponse.createByErrorMessage("操作失败，用户未登录");
        }
        return iCartService.selectOrUnselect(user.getId(),Const.Cart.UNCHECKED,productId);
    }

    /**
     * 获取购物车中商品总数
     * @param session
     * @return
     */
    @RequestMapping("get_total_num.do")
    @ResponseBody
    public ServerResponse<Integer> getTotalNum(HttpSession session){
        User user =(User)session.getAttribute(Const.CURRENT_USER);
        if(user==null){
            return ServerResponse.createBySuccess(0);
        }
        return iCartService.getCartProductCount(user.getId());
    }

}
