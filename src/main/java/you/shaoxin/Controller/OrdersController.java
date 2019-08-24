package you.shaoxin.Controller;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import you.shaoxin.Services.OrdersService;
import you.shaoxin.domin.Orders;

import java.util.List;

/**
 * 功能:
 * 创建时间: 2019-08-13 08:05 --游菜花
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    /*
     * 功能:查询所有订单，未分页
    **/
    /*@RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {

        ModelAndView modelAndView = new ModelAndView();
        List<Orders> ordersList = ordersService.findAll();
        modelAndView.addObject("ordersList",ordersList);
        modelAndView.setViewName("orders-list");

        return modelAndView;
    }*/


    /**
     * 功能:查询所有订单，并且分页显示
    **/
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page,@RequestParam(name = "size",required = true,defaultValue = "4") Integer size) throws Exception {

        ModelAndView modelAndView = new ModelAndView();
        //查找，传入分页的页码和每页的数据条数
        List<Orders> ordersList = ordersService.findAll(page,size);

        //使用PageInfo把分页出来的数据封装起来，PageInfo：一个分页的Bean,PageHeaper提供的
        PageInfo pageInfo = new PageInfo(ordersList);

        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("orders-list");

        return modelAndView;
    }


}
