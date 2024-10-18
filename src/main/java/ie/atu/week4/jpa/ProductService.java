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


    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    public List<Product> addProduct(Product product){
        productRepository.save(product);
        return productRepository.findAll();
    }

    private Product findProductById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find product."));
    }

    public Product updateProduct(Long id, Product updatedProduct){
        Product existingProduct = findProductById(id);

            existingProduct.setProductName(updatedProduct.getProductName());
            existingProduct.setProductDescription(updatedProduct.getProductDescription());
            existingProduct.setProductPrice(updatedProduct.getProductPrice());
            return productRepository.save(existingProduct);

    }

    public List<Product> deleteProduct(Long id) {
        Product existingProduct = findProductById(id);

        productList.remove(existingProduct);
        return productList;


    }
}
