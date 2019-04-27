package com.mmall.dao;

import com.mmall.pojo.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);

    Cart selectCartByUserIdProductId(@Param(value = "userId") Integer userId, @Param(value = "productId") Integer productId);

    List<Cart> selectCartByUserId(Integer userId);

    int selectProductCheckedStatusByUserID(Integer userId);

    int deleteProductByUserIdProductIds(@Param(value = "userId") Integer userId, @Param(value = "productIds") List<String> productIds);

    int checkedOrUncheckedProduct(@Param("userId") Integer userId,@Param("checked") Integer checked,@Param("productId") Integer productId);

    int selectCartProductCount(Integer userId);
}