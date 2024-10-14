package ie.atu.week4.jpa;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private List<Product> productList = new ArrayList<>();
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> addProduct(Product product){
        productRepository.save(product);
        return productRepository.findAll();
    }

    private Product findProductById(int id) {
        for (Product product : productList) {
            if (product.getProductCode() == id) {
                return product;
            }
        }
        return null;
    }

    public Product updateProduct(int id, Product updatedProduct){
        Product existingProduct = findProductById(id);

        if (existingProduct != null) {
            existingProduct.setProductName(updatedProduct.getProductName());
            existingProduct.setProductDescription(updatedProduct.getProductDescription());
            existingProduct.setProductPrice(updatedProduct.getProductPrice());
            return existingProduct;
        } else {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }

    public List<Product> deleteProduct(int id) {
        Product existingProduct = findProductById(id);

        if (existingProduct != null) {
            productList.remove(existingProduct);
            return productList;
        } else {
            return null;
        }

    }
}
