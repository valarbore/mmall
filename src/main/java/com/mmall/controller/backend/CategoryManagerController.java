package com.mmall.controller.backend;


import com.mmall.common.ServerResponse;
import com.mmall.common.Validate;
import com.mmall.service.ICategoryService;
import com.mmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manage/category")
public class CategoryManagerController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private ICategoryService iCategoryService;


    /**
     * 添加商品品类
     *
     * @param session
     * @param categoryName
     * @param parentId
     * @return
     */
    @RequestMapping(value = "add_category.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addCategory(HttpSession session, String categoryName, @RequestParam(value = "parentId", defaultValue = "0") int parentId) {
        ServerResponse response= iUserService.checkAdminRole(session);
        if(response.isSuccess()){
            return iCategoryService.addCategory(categoryName, parentId);
        }
        return  response;
    }

    /**
     * 更新商品品类名称
     * @param session
     * @param categoryId
     * @param categoryName
     * @return
     */
    @RequestMapping(value = "set_category_name.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse setCategoryName(HttpSession session, Integer categoryId, String categoryName) {
        ServerResponse response=iUserService.checkAdminRole(session);
        if(response.isSuccess()){
            return iCategoryService.updateCategoryName(categoryId,categoryName);
        }
        return  response;
    }

    /**
     * 根据父级分类id查找子级分类列表 不递归
     * @param session
     * @param categoryId
     * @return
     */
    @RequestMapping(value = "get_category.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getChildrenParallelCategory(HttpSession session,@RequestParam(value = "categoryId",defaultValue = "0") Integer categoryId){
        ServerResponse response=iUserService.checkAdminRole(session);
        if(response.isSuccess()){
            return iCategoryService.getChildrenParallelCategory(categoryId);
        }
        return  response;
    }

    /**
     *  获取当前类及其所有子分类的id
     * @param session
     * @param categoryId
     * @return
     */
    @RequestMapping(value = "get_category_and_deep_children.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getCategoryAndDeepChildren(HttpSession session,@RequestParam(value = "categoryId",defaultValue = "0") Integer categoryId){
        ServerResponse response=iUserService.checkAdminRole(session);
        if(response.isSuccess()){
            return iCategoryService.selectCategoryAndChildrenById(categoryId);
        }
        return  response;
    }
}
