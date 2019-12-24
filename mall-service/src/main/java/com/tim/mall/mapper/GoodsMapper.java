package com.tim.mall.mapper;

import com.tim.mall.model.Goods;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface GoodsMapper extends Mapper<Goods> {

    /**
     * 查询某一个搜索的内容
     * @param query
     * @param start
     * @param limit
     * @return
     */
    @Select({"select id,name,short_name,category_id,length,width,high,weight,cost_price,selling_price,original_price," +
            "detail,basic_stock,upper_shelf," +
            "examine,deleted,create_time,update_time from goods where name like \"%\""+"#{query} "+"\"%\""+"limit #{start},#{limit}"})
    @Results({
            @Result(column="id",property = "id",jdbcType = JdbcType.INTEGER,id=true),
            @Result(column="name", property="name", jdbcType= JdbcType.VARCHAR),
            @Result(column="short_name", property="shortName", jdbcType= JdbcType.DATE),
            @Result(column="category_id", property="categoryId", jdbcType= JdbcType.INTEGER),
            @Result(column="length", property="length", jdbcType= JdbcType.DOUBLE),
            @Result(column="width", property="width", jdbcType= JdbcType.DOUBLE),
            @Result(column="high", property="high", jdbcType= JdbcType.DOUBLE),
            @Result(column="weight", property="weight", jdbcType= JdbcType.DOUBLE),
            @Result(column="cost_price", property="costPrice", jdbcType= JdbcType.DOUBLE),
            @Result(column="selling_price", property="sellingPrice", jdbcType= JdbcType.DOUBLE),
            @Result(column="original_price", property="originalPrice", jdbcType= JdbcType.DOUBLE),
            @Result(column="detail", property="detail", jdbcType= JdbcType.VARCHAR),
            @Result(column="basic_stock", property="basicStock", jdbcType= JdbcType.INTEGER),
            @Result(column="upper_shelf", property="upperShelf", jdbcType= JdbcType.CHAR),
            @Result(column="examine", property="examine", jdbcType= JdbcType.CHAR),
            @Result(column="deleted", property="deleted", jdbcType= JdbcType.CHAR),
            @Result(column="create_time", property="createTime", jdbcType= JdbcType.DATE),
            @Result(column="update_time", property="updateTime", jdbcType= JdbcType.DATE),
    })
    List<Goods> selectByName(@Param("query")String query, @Param("start")int start, @Param("limit")int limit);




    @Select({"select count(id) from goods  where name like \"%\""+"#{query}"+"\"%\""})
    Integer selectCountByName(@Param("query")String query);


    /**
     * 查询某一个分类的所有
     * @param query
     * @param start
     * @param limit
     * @param categoryId
     * @return
     */
    @Select({"select id,name,short_name,category_id,length,width,high,weight,cost_price,selling_price,original_price," +
            "detail,basic_stock,upper_shelf," +
            "examine,deleted,create_time,update_time from goods where name like \"%\""+"#{query} "+"\"%\""+" and category_id=#{categoryId} limit #{start},#{limit}"})
    @Results({
            @Result(column="id",property = "id",jdbcType = JdbcType.INTEGER,id=true),
            @Result(column="name", property="name", jdbcType= JdbcType.VARCHAR),
            @Result(column="short_name", property="shortName", jdbcType= JdbcType.DATE),
            @Result(column="category_id", property="categoryId", jdbcType= JdbcType.INTEGER),
            @Result(column="length", property="length", jdbcType= JdbcType.DOUBLE),
            @Result(column="width", property="width", jdbcType= JdbcType.DOUBLE),
            @Result(column="high", property="high", jdbcType= JdbcType.DOUBLE),
            @Result(column="weight", property="weight", jdbcType= JdbcType.DOUBLE),
            @Result(column="cost_price", property="costPrice", jdbcType= JdbcType.DOUBLE),
            @Result(column="selling_price", property="sellingPrice", jdbcType= JdbcType.DOUBLE),
            @Result(column="original_price", property="originalPrice", jdbcType= JdbcType.DOUBLE),
            @Result(column="detail", property="detail", jdbcType= JdbcType.VARCHAR),
            @Result(column="basic_stock", property="basicStock", jdbcType= JdbcType.INTEGER),
            @Result(column="upper_shelf", property="upperShelf", jdbcType= JdbcType.CHAR),
            @Result(column="examine", property="examine", jdbcType= JdbcType.CHAR),
            @Result(column="deleted", property="deleted", jdbcType= JdbcType.CHAR),
            @Result(column="create_time", property="createTime", jdbcType= JdbcType.DATE),
            @Result(column="update_time", property="updateTime", jdbcType= JdbcType.DATE),
    })
    List<Goods> selectCategoryByName(@Param("query")String query, @Param("start")int start, @Param("limit")int limit,@Param("categoryId") int categoryId);

    @Select({"select count(id) from goods  where name like \"%\""+"#{query}"+"\"%\" and category_id=#{categoryId}"})
    Integer selectCategoryCount(@Param("query") String query, @Param("categoryId") int categoryId);

    @Select({"select id,name,short_name,category_id,length,width,high,weight,cost_price,selling_price,original_price,detail,basic_stock,upper_shelf, examine,deleted,create_time,update_time from goods where id=#{id}"})
    @Results({
            @Result(column="id",property = "id",jdbcType = JdbcType.INTEGER,id=true),
            @Result(column="name", property="name", jdbcType= JdbcType.VARCHAR),
            @Result(column="short_name", property="shortName", jdbcType= JdbcType.DATE),
            @Result(column="category_id", property="categoryId", jdbcType= JdbcType.INTEGER),
            @Result(column="length", property="length", jdbcType= JdbcType.DOUBLE),
            @Result(column="width", property="width", jdbcType= JdbcType.DOUBLE),
            @Result(column="high", property="high", jdbcType= JdbcType.DOUBLE),
            @Result(column="weight", property="weight", jdbcType= JdbcType.DOUBLE),
            @Result(column="cost_price", property="costPrice", jdbcType= JdbcType.DOUBLE),
            @Result(column="selling_price", property="sellingPrice", jdbcType= JdbcType.DOUBLE),
            @Result(column="original_price", property="originalPrice", jdbcType= JdbcType.DOUBLE),
            @Result(column="detail", property="detail", jdbcType= JdbcType.VARCHAR),
            @Result(column="basic_stock", property="basicStock", jdbcType= JdbcType.INTEGER),
            @Result(column="upper_shelf", property="upperShelf", jdbcType= JdbcType.CHAR),
            @Result(column="examine", property="examine", jdbcType= JdbcType.CHAR),
            @Result(column="deleted", property="deleted", jdbcType= JdbcType.CHAR),
            @Result(column="create_time", property="createTime", jdbcType= JdbcType.DATE),
            @Result(column="update_time", property="updateTime", jdbcType= JdbcType.DATE),
    })
    Goods selectById(int id);

    @Update({"UPDATE goods SET name = #{good.name},short_name = #{good.shortName},category_id = #{good.categoryId},length = #{good.length},width = #{good.width},high = #{good.high},weight = #{good.weight},cost_price = #{good.costPrice},selling_price = #{good.sellingPrice},original_price = #{good.originalPrice},basic_stock = #{good.basicStock},upper_shelf = #{good.upperShelf},examine = #{good.examine},deleted = #{good.deleted},detail = #{good.detail} WHERE id = #{good.id}"})
    Integer updateByID(@Param("good") Goods good);

    @Select({"SELECT g.id,g.name,g.short_name,g.category_id,g.length,g.width,g.high,g.weight,g.cost_price,g.selling_price,g.original_price,g.detail,g.basic_stock,g.upper_shelf,\n" +
            " g.examine,g.deleted,g.create_time,g.update_time,gi.image_url FROM goods g LEFT JOIN goods_image  gi ON (g.id=gi.id) where name like \"%\""+"#{query} "+"\"%\""+" limit #{start},#{limit}"})
    @Results({
            @Result(column="id",property = "id",jdbcType = JdbcType.INTEGER,id=true),
            @Result(column="name", property="name", jdbcType= JdbcType.VARCHAR),
            @Result(column="short_name", property="shortName", jdbcType= JdbcType.DATE),
            @Result(column="category_id", property="categoryId", jdbcType= JdbcType.INTEGER),
            @Result(column="length", property="length", jdbcType= JdbcType.DOUBLE),
            @Result(column="width", property="width", jdbcType= JdbcType.DOUBLE),
            @Result(column="high", property="high", jdbcType= JdbcType.DOUBLE),
            @Result(column="weight", property="weight", jdbcType= JdbcType.DOUBLE),
            @Result(column="cost_price", property="costPrice", jdbcType= JdbcType.DOUBLE),
            @Result(column="selling_price", property="sellingPrice", jdbcType= JdbcType.DOUBLE),
            @Result(column="original_price", property="originalPrice", jdbcType= JdbcType.DOUBLE),
            @Result(column="detail", property="detail", jdbcType= JdbcType.VARCHAR),
            @Result(column="basic_stock", property="basicStock", jdbcType= JdbcType.INTEGER),
            @Result(column="upper_shelf", property="upperShelf", jdbcType= JdbcType.CHAR),
            @Result(column="examine", property="examine", jdbcType= JdbcType.CHAR),
            @Result(column="deleted", property="deleted", jdbcType= JdbcType.CHAR),
            @Result(column="create_time", property="createTime", jdbcType= JdbcType.DATE),
            @Result(column="update_time", property="updateTime", jdbcType= JdbcType.DATE),
            @Result(column="image_url", property="imageUrl", jdbcType= JdbcType.VARCHAR),
    })
    List<Goods> selectGoods_ImgByName(@Param("query")String query, @Param("start")int start, @Param("limit")int limit);

    @Select({"SELECT g.id,g.name,g.short_name,g.category_id,g.length,g.width,g.high,g.weight,g.cost_price,g.selling_price,g.original_price,g.detail,g.basic_stock,g.upper_shelf,\n" +
            " g.examine,g.deleted,g.create_time,g.update_time,gi.image_url FROM goods g LEFT JOIN goods_image  gi ON (g.id=gi.id) where name like \"%\""+"#{query} "+"\"%\""+" and category_id=#{categoryId} and upper_shelf=\"Y\"limit #{start},#{limit}"})
    @Results({
            @Result(column="id",property = "id",jdbcType = JdbcType.INTEGER,id=true),
            @Result(column="name", property="name", jdbcType= JdbcType.VARCHAR),
            @Result(column="short_name", property="shortName", jdbcType= JdbcType.DATE),
            @Result(column="category_id", property="categoryId", jdbcType= JdbcType.INTEGER),
            @Result(column="length", property="length", jdbcType= JdbcType.DOUBLE),
            @Result(column="width", property="width", jdbcType= JdbcType.DOUBLE),
            @Result(column="high", property="high", jdbcType= JdbcType.DOUBLE),
            @Result(column="weight", property="weight", jdbcType= JdbcType.DOUBLE),
            @Result(column="cost_price", property="costPrice", jdbcType= JdbcType.DOUBLE),
            @Result(column="selling_price", property="sellingPrice", jdbcType= JdbcType.DOUBLE),
            @Result(column="original_price", property="originalPrice", jdbcType= JdbcType.DOUBLE),
            @Result(column="detail", property="detail", jdbcType= JdbcType.VARCHAR),
            @Result(column="basic_stock", property="basicStock", jdbcType= JdbcType.INTEGER),
            @Result(column="upper_shelf", property="upperShelf", jdbcType= JdbcType.CHAR),
            @Result(column="examine", property="examine", jdbcType= JdbcType.CHAR),
            @Result(column="deleted", property="deleted", jdbcType= JdbcType.CHAR),
            @Result(column="create_time", property="createTime", jdbcType= JdbcType.DATE),
            @Result(column="update_time", property="updateTime", jdbcType= JdbcType.DATE),
            @Result(column="image_url", property="imageUrl", jdbcType= JdbcType.VARCHAR),
    })
    List<Goods> selectCategoryGoods_ImgByName(@Param("categoryId") int categoryId,@Param("query")String query, @Param("start")int start, @Param("limit")int limit);

    /**
     * 按价格升序排列
     * @param categoryId
     * @param query
     * @param start
     * @param limit
     * @return
     */
    @Select({"SELECT g.id,g.name,g.short_name,g.category_id,g.length,g.width,g.high,g.weight,g.cost_price,g.selling_price,g.original_price,g.detail,g.basic_stock,g.upper_shelf,\n" +
            " g.examine,g.deleted,g.create_time,g.update_time,gi.image_url FROM goods g LEFT JOIN goods_image  gi ON (g.id=gi.id) where name like \"%\""+"#{query} "+"\"%\""+" and category_id=#{categoryId} AND upper_shelf=\"Y\"ORDER BY g.selling_price ASC limit #{start},#{limit}"})
    @Results({
            @Result(column="id",property = "id",jdbcType = JdbcType.INTEGER,id=true),
            @Result(column="name", property="name", jdbcType= JdbcType.VARCHAR),
            @Result(column="short_name", property="shortName", jdbcType= JdbcType.DATE),
            @Result(column="category_id", property="categoryId", jdbcType= JdbcType.INTEGER),
            @Result(column="length", property="length", jdbcType= JdbcType.DOUBLE),
            @Result(column="width", property="width", jdbcType= JdbcType.DOUBLE),
            @Result(column="high", property="high", jdbcType= JdbcType.DOUBLE),
            @Result(column="weight", property="weight", jdbcType= JdbcType.DOUBLE),
            @Result(column="cost_price", property="costPrice", jdbcType= JdbcType.DOUBLE),
            @Result(column="selling_price", property="sellingPrice", jdbcType= JdbcType.DOUBLE),
            @Result(column="original_price", property="originalPrice", jdbcType= JdbcType.DOUBLE),
            @Result(column="detail", property="detail", jdbcType= JdbcType.VARCHAR),
            @Result(column="basic_stock", property="basicStock", jdbcType= JdbcType.INTEGER),
            @Result(column="upper_shelf", property="upperShelf", jdbcType= JdbcType.CHAR),
            @Result(column="examine", property="examine", jdbcType= JdbcType.CHAR),
            @Result(column="deleted", property="deleted", jdbcType= JdbcType.CHAR),
            @Result(column="create_time", property="createTime", jdbcType= JdbcType.DATE),
            @Result(column="update_time", property="updateTime", jdbcType= JdbcType.DATE),
            @Result(column="image_url", property="imageUrl", jdbcType= JdbcType.VARCHAR),
    })
    List<Goods> selectCategoryGoods_ImgByName_PriceAsc(@Param("categoryId") int categoryId,@Param("query")String query, @Param("start")int start, @Param("limit")int limit);

    /**
     * 按价格降序排序
     * @param categoryId
     * @param query
     * @param start
     * @param limit
     * @return
     */
    @Select({"SELECT g.id,g.name,g.short_name,g.category_id,g.length,g.width,g.high,g.weight,g.cost_price,g.selling_price,g.original_price,g.detail,g.basic_stock,g.upper_shelf,\n" +
            " g.examine,g.deleted,g.create_time,g.update_time,gi.image_url FROM goods g LEFT JOIN goods_image  gi ON (g.id=gi.id) where name like \"%\""+"#{query} "+"\"%\""+" and category_id=#{categoryId} AND upper_shelf=\"Y\"ORDER BY g.selling_price desc limit #{start},#{limit}"})
    @Results({
            @Result(column="id",property = "id",jdbcType = JdbcType.INTEGER,id=true),
            @Result(column="name", property="name", jdbcType= JdbcType.VARCHAR),
            @Result(column="short_name", property="shortName", jdbcType= JdbcType.DATE),
            @Result(column="category_id", property="categoryId", jdbcType= JdbcType.INTEGER),
            @Result(column="length", property="length", jdbcType= JdbcType.DOUBLE),
            @Result(column="width", property="width", jdbcType= JdbcType.DOUBLE),
            @Result(column="high", property="high", jdbcType= JdbcType.DOUBLE),
            @Result(column="weight", property="weight", jdbcType= JdbcType.DOUBLE),
            @Result(column="cost_price", property="costPrice", jdbcType= JdbcType.DOUBLE),
            @Result(column="selling_price", property="sellingPrice", jdbcType= JdbcType.DOUBLE),
            @Result(column="original_price", property="originalPrice", jdbcType= JdbcType.DOUBLE),
            @Result(column="detail", property="detail", jdbcType= JdbcType.VARCHAR),
            @Result(column="basic_stock", property="basicStock", jdbcType= JdbcType.INTEGER),
            @Result(column="upper_shelf", property="upperShelf", jdbcType= JdbcType.CHAR),
            @Result(column="examine", property="examine", jdbcType= JdbcType.CHAR),
            @Result(column="deleted", property="deleted", jdbcType= JdbcType.CHAR),
            @Result(column="create_time", property="createTime", jdbcType= JdbcType.DATE),
            @Result(column="update_time", property="updateTime", jdbcType= JdbcType.DATE),
            @Result(column="image_url", property="imageUrl", jdbcType= JdbcType.VARCHAR),
    })
    List<Goods> selectCategoryGoods_ImgByName_PriceDesc(@Param("categoryId") int categoryId,@Param("query")String query, @Param("start")int start, @Param("limit")int limit);

    @Select({"SELECT g.id,g.name,g.short_name,g.category_id,g.length,g.width,g.high,g.weight,g.cost_price,g.selling_price,g.original_price,g.detail,g.basic_stock,g.upper_shelf,\n" +
            " g.examine,g.deleted,g.create_time,g.update_time,gi.image_url FROM goods g LEFT JOIN goods_image  gi ON (g.id=gi.id) where g.name=#{name}"})
    @Results({
            @Result(column="id",property = "id",jdbcType = JdbcType.INTEGER,id=true),
            @Result(column="name", property="name", jdbcType= JdbcType.VARCHAR),
            @Result(column="short_name", property="shortName", jdbcType= JdbcType.DATE),
            @Result(column="category_id", property="categoryId", jdbcType= JdbcType.INTEGER),
            @Result(column="length", property="length", jdbcType= JdbcType.DOUBLE),
            @Result(column="width", property="width", jdbcType= JdbcType.DOUBLE),
            @Result(column="high", property="high", jdbcType= JdbcType.DOUBLE),
            @Result(column="weight", property="weight", jdbcType= JdbcType.DOUBLE),
            @Result(column="cost_price", property="costPrice", jdbcType= JdbcType.DOUBLE),
            @Result(column="selling_price", property="sellingPrice", jdbcType= JdbcType.DOUBLE),
            @Result(column="original_price", property="originalPrice", jdbcType= JdbcType.DOUBLE),
            @Result(column="detail", property="detail", jdbcType= JdbcType.VARCHAR),
            @Result(column="basic_stock", property="basicStock", jdbcType= JdbcType.INTEGER),
            @Result(column="upper_shelf", property="upperShelf", jdbcType= JdbcType.CHAR),
            @Result(column="examine", property="examine", jdbcType= JdbcType.CHAR),
            @Result(column="deleted", property="deleted", jdbcType= JdbcType.CHAR),
            @Result(column="create_time", property="createTime", jdbcType= JdbcType.DATE),
            @Result(column="update_time", property="updateTime", jdbcType= JdbcType.DATE),
            @Result(column="image_url", property="imageUrl", jdbcType= JdbcType.VARCHAR),
    })
    Goods selectByName_NoLimit(@Param("name") String name);

    @Select({"SELECT g.id,g.name,g.short_name,g.category_id,g.length,g.width,g.high,g.weight,g.cost_price,g.selling_price,g.original_price,g.detail,g.basic_stock,g.upper_shelf,\n" +
            " g.examine,g.deleted,g.create_time,g.update_time,gi.image_url FROM goods g LEFT JOIN goods_image  gi ON (g.id=gi.id) where g.id=#{get_id}"})
    @Results({
            @Result(column="id",property = "id",jdbcType = JdbcType.INTEGER,id=true),
            @Result(column="name", property="name", jdbcType= JdbcType.VARCHAR),
            @Result(column="short_name", property="shortName", jdbcType= JdbcType.DATE),
            @Result(column="category_id", property="categoryId", jdbcType= JdbcType.INTEGER),
            @Result(column="length", property="length", jdbcType= JdbcType.DOUBLE),
            @Result(column="width", property="width", jdbcType= JdbcType.DOUBLE),
            @Result(column="high", property="high", jdbcType= JdbcType.DOUBLE),
            @Result(column="weight", property="weight", jdbcType= JdbcType.DOUBLE),
            @Result(column="cost_price", property="costPrice", jdbcType= JdbcType.DOUBLE),
            @Result(column="selling_price", property="sellingPrice", jdbcType= JdbcType.DOUBLE),
            @Result(column="original_price", property="originalPrice", jdbcType= JdbcType.DOUBLE),
            @Result(column="detail", property="detail", jdbcType= JdbcType.VARCHAR),
            @Result(column="basic_stock", property="basicStock", jdbcType= JdbcType.INTEGER),
            @Result(column="upper_shelf", property="upperShelf", jdbcType= JdbcType.CHAR),
            @Result(column="examine", property="examine", jdbcType= JdbcType.CHAR),
            @Result(column="deleted", property="deleted", jdbcType= JdbcType.CHAR),
            @Result(column="create_time", property="createTime", jdbcType= JdbcType.DATE),
            @Result(column="update_time", property="updateTime", jdbcType= JdbcType.DATE),
            @Result(column="image_url", property="imageUrl", jdbcType= JdbcType.VARCHAR),
    })
    Goods selectGoods_ImgById(Integer get_id);

    @Select({"SELECT basic_stock from goods where id=#{productId}"})
    Integer selectBasicStockById(Integer productId);

    @Update({"update goods set basic_stock=#{quantity} where id=#{productId}"})
    void updateGoodQuantity(@Param("productId") Integer productId, @Param("quantity") int quantity);
}
