package you.shaoxin.Services.Impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import you.shaoxin.Dao.OrdersDao;
import you.shaoxin.Services.OrdersService;
import you.shaoxin.domin.Orders;

import java.util.List;

/**
 * 功能:
 * 创建时间: 2019-08-13 08:07 --游菜花
 */
@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersDao ordersDao;

    @Override
    public List<Orders> findAll(int page,int size) {

        //参数：pageNum是页码值，参数pageSize代表每页显示条数
        //注意，这句话必须写在执行分页语句的前面
        PageHelper.startPage(page,size);
        List<Orders> orders = ordersDao.findAll();
        return orders;
    }
}
