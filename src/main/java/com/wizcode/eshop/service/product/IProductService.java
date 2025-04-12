package com.wizcode.eshop.service.product;

import com.wizcode.eshop.dto.ProductDTO;
import com.wizcode.eshop.model.Product;
import com.wizcode.eshop.request.AddProductRequest;
import com.wizcode.eshop.request.UpdateProductRequest;

import java.util.List;

public interface IProductService {
    Product addProduct(AddProductRequest product);
    Product getProductById(Long id);
    void deleteProductById(Long id);
    Product updateProduct(UpdateProductRequest request, Long id);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByBrandAndName(String brand, String name);
    Long countProductByBrandAndName(String brand, String name);

    List<ProductDTO> getConvertedProduct(List<Product> products);

    ProductDTO convertToDTO(Product product);
}
