package controller;

import model.Product;
import service.ProductService;

import java.util.List;
import java.util.Scanner;

public class ProductController {

    private final ProductService productService;
    private final Scanner scanner;

    public ProductController(Scanner scanner) {

        productService = new ProductService();
        this.scanner = scanner;
    }

    public void addProduct() {

        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();

        System.out.print("Enter category: ");
        String category = scanner.nextLine();

        System.out.print("Enter price: ");
        double price = scanner.nextDouble();

        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        Product product = new Product(
                productName,
                category,
                price,
                quantity
        );

        boolean result = productService.addProduct(product);

        if (result) {
            System.out.println("Product added successfully.");
        } else {
            System.out.println("Failed to add product.");
        }
    }

    public void getProductById() {

        System.out.print("Enter product ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Product product = productService.getProductById(id);

        if (product != null) {
            printProduct(product);
        } else {
            System.out.println("Product not found.");
        }
    }

    public void getAllProducts() {

        List<Product> products = productService.getAllProducts();

        if (products.isEmpty()) {
            System.out.println("No products found.");
            return;
        }

        for (Product product : products) {
            printProduct(product);
        }
    }

    public void updateProduct() {

        System.out.print("Enter product ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Product existingProduct = productService.getProductById(id);

        if (existingProduct == null) {
            System.out.println("Product not found.");
            return;
        }

        System.out.print("Enter new product name: ");
        String productName = scanner.nextLine();

        System.out.print("Enter new category: ");
        String category = scanner.nextLine();

        System.out.print("Enter new price: ");
        double price = scanner.nextDouble();

        System.out.print("Enter new quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        Product product = new Product(
                id,
                productName,
                category,
                price,
                quantity
        );

        boolean result = productService.updateProduct(product);

        if (result) {
            System.out.println("Product updated successfully.");
        } else {
            System.out.println("Failed to update product.");
        }
    }

    public void deleteProduct() {

        System.out.print("Enter product ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean result = productService.deleteProduct(id);

        if (result) {
            System.out.println("Product deleted successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    public void searchProductsByName() {

        System.out.print("Enter product name to search: ");
        String name = scanner.nextLine();

        List<Product> products =
                productService.searchProductsByName(name);

        if (products.isEmpty()) {
            System.out.println("No products found.");
            return;
        }

        for (Product product : products) {
            printProduct(product);
        }
    }

    public void updateStock() {

        System.out.print("Enter product ID: ");
        int id = scanner.nextInt();

        System.out.print("Enter new quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        boolean result =
                productService.updateStock(id, quantity);

        if (result) {
            System.out.println("Stock updated successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    public void reduceStock() {

        System.out.print("Enter product ID: ");
        int id = scanner.nextInt();

        System.out.print("Enter quantity to reduce: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        boolean result =
                productService.reduceStock(id, quantity);

        if (result) {
            System.out.println("Stock Reduced successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    public void getLowStockProducts() {

        List<Product> products =
                productService.getLowStockProducts();

        if (products.isEmpty()) {
            System.out.println("No low-stock products.");
            return;
        }

        System.out.println("\n===== LOW STOCK PRODUCTS =====");

        for (Product product : products) {
            printProduct(product);
        }
    }

    private void printProduct(Product product) {

        System.out.println("----------------------------");
        System.out.println("Product ID : " + product.getProductId());
        System.out.println("Name       : " + product.getProductName());
        System.out.println("Category   : " + product.getCategory());
        System.out.println("Price      : " + product.getPrice());
        System.out.println("Quantity   : " + product.getQuantity());
        System.out.println("----------------------------");
    }
}