package you.shaoxin.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import you.shaoxin.Services.ProductService;
import you.shaoxin.domin.Product;

import javax.annotation.security.RolesAllowed;
import java.util.List;


@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 功能: 产品添加
    **/
    @RequestMapping("save.do")
    public String save(Product product) throws Exception {
        productService.save(product);
        //保存完之后跳转到查询所有产品的方法
        return "redirect:findAll.do";

    }

    /**
     * 功能: 查找所有产品的方法
    **/
    @RequestMapping("/findAll.do")
    @RolesAllowed("GUEST")
    public ModelAndView findAll() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        List<Product> productList = productService.findAll();
        modelAndView.addObject("productList",productList);
        modelAndView.setViewName("product-list");

        return modelAndView;

    }

}
