package service;

import dao.ProductDAO;
import exception.ProductException;
import model.Product;

import java.util.List;

public class ProductService {

    private final ProductDAO productDAO = new ProductDAO();

//    Add Product
    public boolean addProduct(Product product){

        validateProduct(product);

        validateProductName(product.getProductName());

        validateCategory(product.getCategory());

        validatePrice(product.getPrice());

        validateQuantity(product.getQuantity());

        return productDAO.addProduct(product);
    }

//    Get Product By Id
    public Product getProductById(int productId){

        validateProductId(productId);

        return productDAO.getProductById(productId);
    }

//    Get All Products
    public List<Product> getAllProducts(){

        return productDAO.getAllProducts();
    }

//    Update Product
    public boolean updateProduct(Product product) {

        validateProduct(product);

        validateProductId(product.getProductId());

        validateProductName(product.getProductName());

        validateCategory(product.getCategory());

        validatePrice(product.getPrice());

        validateQuantity(product.getQuantity());

        return productDAO.updateProduct(product);
    }

//    Delete Product
    public boolean deleteProduct(int productId) {

        validateProductId(productId);

        return productDAO.deleteProduct(productId);
    }

//  Search Product
    public List<Product> searchProductsByName(String productName) {

        validateProductName(productName);

        return productDAO.searchProductsByName(productName.trim());
    }

//  Update Stock
    public boolean updateStock(int productId, int quantity) {

        validateProductId(productId);

        validateQuantity(quantity);

        return productDAO.updateStock(
                productId,
                quantity
        );
    }

    // Reduce Stock
    public boolean reduceStock(int productId, int quantity) {

        validateProductId(productId);
        validateQuantity(quantity);

        Product product = productDAO.getProductById(productId);

        validateProduct(product);
        
        if (quantity > product.getQuantity()) {
            throw new ProductException(
                    "Insufficient stock. Available stock: " + product.getQuantity()
            );
        }

        return productDAO.reduceStock(productId, quantity);
    }

//    Low Stock Products
    public List<Product> getLowStockProducts() {

        return productDAO.getLowStockProducts();
    }

    //    VALIDATION - Product
    private void validateProduct(Product product){

        if(product == null){
            throw new ProductException(
                    "Product cannot be null."
            );
        }
    }

//    VALIDATION - Product Id
    private void validateProductId(int id){

        if (id <= 0) {

            throw new ProductException(
                    "Product ID must be greater than 0."
            );
        }
    }

//    VALIDATION - Product Name
    private void validateProductName(String name){

        if(name == null ||
        name.trim().isEmpty()){
            throw new ProductException(
                    "Name cannot be empty."
            );
        }
    }

//    VALIDATION - Category
    private void validateCategory(String category){

        if(category == null ||
        category.trim().isEmpty()){
            throw new ProductException(
                    "Category cannot be empty."
            );
        }
    }

//    VALIDATION - Price
    private void validatePrice(double price){

        if(price <= 0){
            throw new ProductException(
                    "Price should be greater than '0'"
            );
        }
    }

//    VALIDATION - Quantity
    private void validateQuantity(int quantity){

        if(quantity < 0){
            throw new ProductException(
                    "Quantity should be greater than or equal to '0'"
            );
        }
    }

}
