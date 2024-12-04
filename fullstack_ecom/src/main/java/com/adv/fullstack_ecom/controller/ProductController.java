package com.adv.fullstack_ecom.controller;

import com.adv.fullstack_ecom.entity.Product;
import com.adv.fullstack_ecom.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {

    @Autowired
    private final ProductService productService;

    @Value("${upload.dir}")
    private String uploadDir;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{category}")
    public List<Product> getProductsByCategory(@PathVariable    ("category") String category) {
        return productService.getProductsByCategory(category);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Product> addProduct(@RequestParam("product") String productJson,
                                              @RequestParam("image") MultipartFile imageFile) throws IOException {
        Product product = new ObjectMapper().readValue(productJson, Product.class);
        Product createdProduct = productService.addProduct(product, imageFile);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,
                                                 @RequestParam("product") String productJson,
                                                 @RequestParam("image") MultipartFile imageFile) throws IOException {
        Product product = new ObjectMapper().readValue(productJson, Product.class);
        Product updatedProduct = productService.updateProduct(id, product, imageFile);
        return ResponseEntity.ok(updatedProduct);
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Product product = productService.getProduct(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/product/{id}/image")
    public ResponseEntity<byte[]> getProductImage(@PathVariable Long id) throws IOException {
        byte[] imageData = productService.getImageByProductId(id);

        // Return the image as a binary response
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // Or IMAGE_PNG based on the file type
        return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }


}
