package com.tim.mall.service;

import com.tim.mall.model.Goods;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface GoodsService {


    List<Goods> selectByName(String query, Integer start, Integer limit);

    int selectCount(String query);

    List<Goods> selectCategoryByName(String query, int start, int limit, int categoryId);

    int selectCategoryCount(String query, int categoryId);

    Goods selectById(int id);

    void delete(Goods goods);

    boolean updateGood(Goods good);

    boolean insertUser(Goods good);

    List<Goods> selectGood_ImgByName(String name, int start, int limit);

    List<Goods> selectCategoryGoods_ImgByName(int categoryId, String query, int start, int limit);

    List<Goods> selectCategoryGoods_ImgByName_PriceAsc(int categoryId,String query, int start, int limit);

    List<Goods> selectCategoryGoods_ImgByName_PriceDesc(int categoryId,String query, int start, int limit);

    Goods selectByName_NoLimit(String name);

    Goods selectGoods_ImgById(Integer get_id);

    Integer selectBasicStockById(Integer productId);

    void updateGoodQuantity(Integer productId, int quantity);
}
