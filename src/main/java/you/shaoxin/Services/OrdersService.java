package you.shaoxin.Services;
import you.shaoxin.domin.Orders;

import java.util.List;

/**
 * 功能:
 * 创建时间: 2019-08-13 08:06 --游菜花
 */
public interface OrdersService {

    /*
     * 功能:查找所有订单
    **/
    List<Orders> findAll(int page,int size) throws Exception;
}
