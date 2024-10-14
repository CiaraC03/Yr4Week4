package ie.atu.week4.jpa;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private List<Product> productList = new ArrayList<>();
    private ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/getProducts")
    public List<Product> getProducts() {
        return productList;
    }

    @PostMapping("/addProduct")
    public ResponseEntity<List> addProduct(@RequestBody Product product) {
        productList = productService.addProduct(product);
        return ResponseEntity.ok(productList);
    }


    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product updatedProduct) {
        Product update = productService.updateProduct(id, updatedProduct);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<List<Product>> deleteProduct(@PathVariable int id) {
       List<Product> productList = productService.deleteProduct(id);
       return ResponseEntity.ok(productList);
    }
}
