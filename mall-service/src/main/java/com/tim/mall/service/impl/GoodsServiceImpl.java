package com.tim.mall.service.impl;

import com.tim.mall.mapper.GoodsMapper;
import com.tim.mall.model.Goods;
import com.tim.mall.service.GoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;


@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsMapper goodsMapper;

    /**
     * 通过名称查询
     * @param query
     * @param start
     * @param limit
     * @return
     */
    @Override
    public List<Goods> selectByName(String query, Integer start, Integer limit) {
        return goodsMapper.selectByName(query,start,limit);
    }


    @Override
    public int selectCount(String query) {
        return goodsMapper.selectCountByName(query);
    }

    /**
     * 查询分类和限制范围所有
     * @param query
     * @param start
     * @param limit
     * @param categoryId
     * @return
     */
    @Override
    public List<Goods> selectCategoryByName(String query, int start, int limit, int categoryId) {
        return goodsMapper.selectCategoryByName(query,start,limit,categoryId);
    }

    @Override
    public int selectCategoryCount(String query, int categoryId) {
        return goodsMapper.selectCategoryCount(query,categoryId);
    }

    @Override
    public Goods selectById(int id) {
        return goodsMapper.selectById(id);
    }

    @Override
    public void delete(Goods goods) {
        goodsMapper.delete(goods);
    }

    @Override
    public boolean updateGood(Goods good) {
        //更新方法错误,要修改
        return goodsMapper.updateByID(good)>0 ? true : false;
    }

    @Override
    public boolean insertUser(Goods good) {
        return goodsMapper.insert(good)>0 ? true :false;
    }

    @Override
    public List<Goods> selectGood_ImgByName(String name, int start, int limit) {
        return goodsMapper.selectGoods_ImgByName(name,start*10,limit);
    }

    /**
     * 利用左外连接查询商品和其图片
     * @param categoryId
     * @param query
     * @param start
     * @param limit
     * @return
     */
    @Override
    public List<Goods> selectCategoryGoods_ImgByName(int categoryId, String query, int start, int limit) {
        return goodsMapper.selectCategoryGoods_ImgByName(categoryId,query,start,limit);
    }

    /**
     * 价格升序排序
     * @param categoryId
     * @param query
     * @param start
     * @param limit
     * @return
     */
    @Override
    public List<Goods> selectCategoryGoods_ImgByName_PriceAsc(int categoryId, String query, int start, int limit) {
        return goodsMapper.selectCategoryGoods_ImgByName_PriceAsc(categoryId,query,start,limit);
    }

    /**
     * 价格降序排序
     * @param categoryId
     * @param query
     * @param start
     * @param limit
     * @return
     */
    @Override
    public List<Goods> selectCategoryGoods_ImgByName_PriceDesc(int categoryId, String query, int start, int limit) {
        return  goodsMapper.selectCategoryGoods_ImgByName_PriceDesc(categoryId,query,start,limit);
    }

    @Override
    public Goods selectByName_NoLimit(String name) {
        return goodsMapper.selectByName_NoLimit(name);
    }

    @Override
    public Goods selectGoods_ImgById(Integer get_id) {
        return goodsMapper.selectGoods_ImgById(get_id);
    }

    @Override
    public Integer selectBasicStockById(Integer productId) {
        return goodsMapper.selectBasicStockById(productId);
    }

    @Override
    public void updateGoodQuantity(Integer productId, int quantity) {
        goodsMapper.updateGoodQuantity(productId,quantity);
    }

}
