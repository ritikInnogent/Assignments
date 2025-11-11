package com.example.demo.Service;
import com.example.demo.Dto.ProductRequest;
import com.example.demo.Dto.ProductResponse;
import com.example.demo.Model.Products;

import java.util.List;

public interface IProductService {
    List<ProductResponse> addProduct(List<ProductRequest> productRequest);
    List<Products> getProducts();

    ProductResponse getProductById(Long id);

    ProductResponse updateProduct(Long id, ProductRequest productRequest);

    String deleteProduct(Long id);
}

