package com.wizcode.eshop.contoller;

import com.wizcode.eshop.dto.ProductDTO;
import com.wizcode.eshop.exception.ResourceNotFoundException;
import com.wizcode.eshop.model.Product;
import com.wizcode.eshop.request.AddProductRequest;
import com.wizcode.eshop.request.UpdateProductRequest;
import com.wizcode.eshop.response.APIResponse;
import com.wizcode.eshop.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("${api.prefix}/products")
@RequiredArgsConstructor
public class ProductController {
    private final IProductService productService;

    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductDTO> convertedProducts = productService.getConvertedProduct(products);
        return  ResponseEntity.ok(new APIResponse("success", convertedProducts));
    }

    @GetMapping("product/{productId}/product")
    public ResponseEntity<APIResponse> getProductById(@PathVariable Long productId) {
        try {
            Product product = productService.getProductById(productId);
            ProductDTO productDTO = productService.convertToDTO(product);
            return  ResponseEntity.ok(new APIResponse("success", productDTO));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new APIResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<APIResponse> addProduct(@RequestBody AddProductRequest product) {
        try {
            Product theProduct = productService.addProduct(product);
            ProductDTO productDTO = productService.convertToDTO(theProduct);
            return ResponseEntity.ok(new APIResponse("Add product success!", productDTO));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new APIResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/product/{productId}/update")
    public  ResponseEntity<APIResponse> updateProduct(@RequestBody UpdateProductRequest request, @PathVariable Long productId) {
        try {
            Product theProduct = productService.updateProduct(request, productId);
            ProductDTO productDTO = productService.convertToDTO(theProduct);
            return ResponseEntity.ok(new APIResponse("Update product success!", productDTO));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new APIResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/product/{productId}/delete")
    public ResponseEntity<APIResponse> deleteProduct(@PathVariable Long productId) {
        try {
            productService.deleteProductById(productId);
            return ResponseEntity.ok(new APIResponse("Delete product success!", productId));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new com.wizcode.eshop.response.APIResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/products/by/brand-and-name")
    public ResponseEntity<APIResponse> getProductByBrandAndName(@RequestParam String brandName, @RequestParam String productName) {
        try {
            List<Product> products = productService.getProductsByBrandAndName(brandName, productName);
            if (products.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND).body(new APIResponse("No products found ", null));
            }
            List<ProductDTO> convertedProducts = productService.getConvertedProduct(products);
            return  ResponseEntity.ok(new APIResponse("success", convertedProducts));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new APIResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/products/by/category-and-brand")
    public ResponseEntity<APIResponse> getProductByCategoryAndBrand(@RequestParam String category, @RequestParam String brand){
        try {
            List<Product> products = productService.getProductsByCategoryAndBrand(category, brand);
            if (products.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND).body(new APIResponse("No products found ", null));
            }
            List<ProductDTO> convertedProducts = productService.getConvertedProduct(products);
            return  ResponseEntity.ok(new APIResponse("success", convertedProducts));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new APIResponse("error", e.getMessage()));
        }
    }

    @GetMapping("/products/{name}/products")
    public ResponseEntity<APIResponse> getProductByName(@PathVariable String name){
        try {
            List<Product> products = productService.getProductsByName(name);
            if (products.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND).body(new APIResponse("No products found ", null));
            }
            return  ResponseEntity.ok(new APIResponse("success", products));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new APIResponse("error", e.getMessage()));
        }
    }

    @GetMapping("/product/by-brand")
    public ResponseEntity<APIResponse> findProductByBrand(@RequestParam String brand) {
        try {
            List<Product> products = productService.getProductsByBrand(brand);
            if (products.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND).body(new APIResponse("No products found ", null));
            }
            List<ProductDTO> convertedProducts = productService.getConvertedProduct(products);
            return  ResponseEntity.ok(new APIResponse("success", convertedProducts));
        } catch (Exception e) {
            return ResponseEntity.ok(new APIResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/product/{category}/all/products")
    public ResponseEntity<APIResponse> findProductByCategory(@PathVariable String category) {
        try {
            List<Product> products = productService.getProductsByCategory(category);
            if (products.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND).body(new APIResponse("No products found ", null));
            }
            List<ProductDTO> convertedProducts = productService.getConvertedProduct(products);
            return  ResponseEntity.ok(new APIResponse("success", convertedProducts));
        } catch (Exception e) {
            return ResponseEntity.ok(new APIResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/product/count/by-brand/and-name")
    public ResponseEntity<APIResponse> countProductsByBrandAndName(@RequestParam String brand, @RequestParam String name) {
        try {
            var productCount = productService.countProductByBrandAndName(brand, name);
            return ResponseEntity.ok(new APIResponse("Product count!", productCount));
        } catch (Exception e) {
            return ResponseEntity.ok(new APIResponse(e.getMessage(), null));
        }
    }
}
