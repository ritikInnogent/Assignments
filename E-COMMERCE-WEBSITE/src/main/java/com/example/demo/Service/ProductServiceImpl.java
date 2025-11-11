package com.example.demo.Service;

import com.example.demo.Dto.ProductRequest;
import com.example.demo.Dto.ProductResponse;
import com.example.demo.Model.Products;
import com.example.demo.Repository.ProductsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Provider;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService{

    @Autowired
    private ProductsRepo repo;

    @Override
    public List<ProductResponse> addProduct(List<ProductRequest> productRequests) {
        List<ProductResponse> responses = new ArrayList<>();

        for (ProductRequest productRequest : productRequests) {
            // Create entity object
            Products products = new Products();
            products.setTitle(productRequest.getTitle());
            products.setPrice(productRequest.getPrice());
            products.setCategory(productRequest.getCategory());
            products.setDescription(productRequest.getDescription());
            products.setImage(productRequest.getImage());
            products.setRate(productRequest.getRate());
            products.setCount(productRequest.getCount());

            // Save to database
            repo.save(products);

            // Create response object
            ProductResponse productResponse = new ProductResponse();// if ID is generated
            productResponse.setTitle(products.getTitle());
            productResponse.setPrice(products.getPrice());
            productResponse.setCategory(products.getCategory());
            productResponse.setDescription(products.getDescription());
            productResponse.setImage(products.getImage());
            productResponse.setRate(products.getRate());
            productResponse.setCount(products.getCount());

            responses.add(productResponse);
        }

        return responses;
    }



    @Override
    public ProductResponse updateProduct(Long id, ProductRequest productRequest) {
        Products products = repo.findById(id).orElseThrow(()-> new RuntimeException("product not exist"));

        products.setTitle(productRequest.getTitle());
        products.setPrice(productRequest.getPrice());
        products.setCategory(productRequest.getCategory());
        products.setDescription(productRequest.getDescription());
        products.setImage(productRequest.getImage());
        products.setRate(productRequest.getRate());
        products.setCount(productRequest.getCount());
        repo.save(products);

        ProductResponse  productResponse = new ProductResponse();
        productResponse.setCategory(products.getCategory());
        productResponse.setDescription(products.getDescription());
        productResponse.setImage(products.getImage());
        productResponse.setPrice(products.getPrice());
        productResponse.setRate(products.getRate());
        productResponse.setTitle(products.getTitle());
        productResponse.setCount(products.getCount());
        return productResponse;
    }



    @Override
    public String deleteProduct(Long id) {
        Products products = repo.findById(id).orElseThrow(() -> new RuntimeException("product not exist"));
        repo.delete(products);
        return "Product deleted successfully!";
    }



    @Override
    public List<Products> getProducts() {
        return repo.findAll();
    }

    @Override
    public ProductResponse getProductById(Long id) {
        Products product = repo.findById(id).orElseThrow(()-> new RuntimeException("product not exist"));

        ProductResponse response = new ProductResponse();
        response.setTitle(product.getTitle());
        response.setDescription(product.getDescription());
        response.setCategory(product.getCategory());
        response.setPrice(product.getPrice());
        response.setImage(product.getImage());
        response.setRate(product.getRate());
        response.setCount(product.getCount());
        return response;
    }
}
