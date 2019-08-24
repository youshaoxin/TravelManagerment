package you.shaoxin.Services;
import you.shaoxin.domin.Product;

import java.util.List;

/**
 * 功能:
**/
public interface ProductService{

    public List<Product> findAll() throws Exception;

    void save(Product product) throws Exception;
}
