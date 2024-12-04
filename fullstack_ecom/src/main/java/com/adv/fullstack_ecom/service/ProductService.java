package com.adv.fullstack_ecom.service;

import com.adv.fullstack_ecom.repository.ProductRepository;
import com.adv.fullstack_ecom.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    // Directory path for saving images
    @Value("${upload.dir}")
    private String uploadDir;

    @Autowired
    ProductRepository productRepository;



    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public byte[] getImageByProductId(Long productId) throws IOException {
        // Retrieve the product using the ID
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));

        // Check if the product has an associated image
        String imageName = product.getImageName();
        if (imageName == null) {
            throw new RuntimeException("No image associated with this product.");
        }

        // Read the image file as bytes
        File imageFile = new File("uploads/" + imageName);
        if (!imageFile.exists()) {
            throw new RuntimeException("Image file not found: " + imageName);
        }
        return Files.readAllBytes(imageFile.toPath());
    }


    private static final String UPLOAD_DIR = "uploads/";

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        // If image is provided, save it to the file system
        if (imageFile != null && !imageFile.isEmpty()) {
            String imageName = imageFile.getOriginalFilename();
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            imageFile.transferTo(new File(UPLOAD_DIR + imageName));
            product.setImageName(imageName);  // Save image path in database
        }

        return productRepository.save(product);
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findAllByCategory(category);
    }



    public Product updateProduct(Long id, Product product, MultipartFile imageFile) throws IOException {
        Optional<Product> existingProductOpt = productRepository.findById(id);
        if (existingProductOpt.isPresent()) {
            Product existingProduct = existingProductOpt.get();
            existingProduct.setName(product.getName());
            existingProduct.setCategory(product.getCategory());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());

            // If image is provided, save it to the file system and update image path
            if (imageFile != null && !imageFile.isEmpty()) {
                String imageName = imageFile.getOriginalFilename();
                File uploadDir = new File(UPLOAD_DIR);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }
                imageFile.transferTo(new File(UPLOAD_DIR + imageName));
                existingProduct.setImageName(imageName);
            }

            return productRepository.save(existingProduct);
        } else {
            throw new RuntimeException("Product not found");
        }
    }

    public void deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new RuntimeException("Product not found");
        }
    }
}
