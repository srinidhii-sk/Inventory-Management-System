import controller.ProductController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ProductController productController =
                new ProductController(scanner);

        boolean running = true;

        while (running) {

            System.out.println("\n------ INVENTORY MANAGEMENT SYSTEM ------1");
            System.out.println("1. Add Product");
            System.out.println("2. View Product");
            System.out.println("3. View All Products");
            System.out.println("4. Update Product");
            System.out.println("5. Delete Product");
            System.out.println("6. Search Product");
            System.out.println("7. Update Stock");
            System.out.println("8. Reduce Stock");
            System.out.println("9. View Low Stock Products");
            System.out.println("10. Exit");

            System.out.print("Enter your choice: ");

            try {

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {

                    case 1 -> productController.addProduct();

                    case 2 -> productController.getProductById();

                    case 3 -> productController.getAllProducts();

                    case 4 -> productController.updateProduct();

                    case 5 -> productController.deleteProduct();

                    case 6 -> productController.searchProductsByName();

                    case 7 -> productController.updateStock();

                    case 8 -> productController.reduceStock();

                    case 9 -> productController.getLowStockProducts();

                    case 10 -> {
                        running = false;
                        System.out.println("Thank you for using Inventory Management System!");
                    }

                    default ->
                            System.out.println("Invalid choice. Please enter 1-9.");
                }

            } catch (InputMismatchException e) {

                System.out.println("ERROR: Please enter a valid number.");
                scanner.nextLine();

            } catch (Exception e) {

                System.out.println("ERROR: " + e.getMessage());
            }
        }

        scanner.close();
    }
}