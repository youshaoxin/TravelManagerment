package you.shaoxin.Dao;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;
import you.shaoxin.domin.Orders;
import you.shaoxin.domin.Product;

import java.util.List;

/**
 * 游菜花 -
 * 创建时间: 2019-08-13 08:06
 */
public interface OrdersDao {

    /*
     * 功能:查找所有订单
    **/
    @Select("select * from orders")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "you.shaoxin.Dao.ProductDao.findById",fetchType = FetchType.EAGER))
    })
    List<Orders> findAll();
}
