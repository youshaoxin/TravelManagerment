package you.shaoxin.Dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import you.shaoxin.domin.Product;

import java.util.List;

public interface ProductDao {

    /*
     * 功能:查询所有产品
    **/
    @Select("select * from product")
    List<Product> findAll() throws Exception;

    /*
     * 功能:插入产品
    **/
    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);

    /*
     * 功能:根据ID查找某个产品
    **/
    @Select("select * from product where id=#{id}")
    Product findById(String id);
}
