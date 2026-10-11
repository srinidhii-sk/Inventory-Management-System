package dao;

import model.Product;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

//    CREATE
        public boolean addProduct(Product product){

            String sql = """
                    INSERT INTO products (product_name, category, price, quantity) 
                    VALUES (?, ?, ?, ?)
                    """;

            try(Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)){

                statement.setString(1, product.getProductName());
                statement.setString(2, product.getCategory());
                statement.setDouble(3, product.getPrice());
                statement.setInt(4,product.getQuantity());

                return statement.executeUpdate() > 0;
            }catch (SQLException e){
                System.out.println("Error adding Product : " + e.getMessage());
            }

            return false;
        }

//        READ - Get Product By ID
        public Product getProductById(int id){

            String sql = """
                    SELECT * FROM products 
                    WHERE product_id = ?
                    """;

            try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){

                statement.setInt(1, id);
                try(ResultSet resultSet = statement.executeQuery()){

                    if(resultSet.next()){

                        return new Product(
                                resultSet.getInt("product_id"),
                                resultSet.getString("product_name"),
                                resultSet.getString("category"),
                                resultSet.getDouble("price"),
                                resultSet.getInt("quantity")
                        );
                    }
                }
            }catch (SQLException e){
                System.out.println("Error fetching product by ID : " + e.getMessage());
            }

            return null;
        }

//        READ - Get All Products
    public List<Product> getAllProducts(){

        List<Product> productList = new ArrayList<>();

        String sql = """
                   SELECT * FROM products
                    """;

        try(Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){

            try(ResultSet resultSet = statement.executeQuery()){

                while(resultSet.next()) {
                    Product product = new Product(
                            resultSet.getInt("product_id"),
                            resultSet.getString("product_name"),
                            resultSet.getString("category"),
                            resultSet.getDouble("price"),
                            resultSet.getInt("quantity")
                    );

                    productList.add(product);
                }
            }
        }catch (SQLException e){
            System.out.println("Error fetching product : " + e.getMessage());
        }

        return productList;
    }

//    UPDATE
    public boolean updateProduct(Product product) {

        String sql = """
                    
                UPDATE products
                    SET product_name = ?,
                        category = ?,
                        price = ?,
                        quantity = ?
                    WHERE product_id = ?
                    """;

            try(Connection connection = DBConnection.
             getConnection();
            PreparedStatement statement = connection.

            prepareStatement(sql)){

                statement.setString(1, product.getProductName());
                statement.setString(2, product.getCategory());
                statement.setDouble(3, product.getPrice());
                statement.setInt(4, product.getQuantity ());
                statement.setInt(5, product.getProductId());
        ;

                return
            statement.executeUpdate() > 0;
            }catch (SQLException e){
                System.out.println("Error updating product : " + e.getMessage());
            }

            return false;
    }

//    DELETE
    public boolean deleteProduct(int id){

            String sql = """
                    DELETE FROM products 
                    WHERE product_id = ?
                    """;

            try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){

                statement.setInt(1, id);

                return statement.executeUpdate() > 0;
            }catch (SQLException e){
                System.out.println("Error deleting product : " + e.getMessage());
            }

            return false;
    }

    // SEARCH
    public List<Product> searchProductsByName(String name) {

        List<Product> productList = new ArrayList<>();

        String sql = """
            SELECT * FROM products
            WHERE product_name LIKE ?
            """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, "%" + name + "%");

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {

                    Product product = new Product(
                            resultSet.getInt("product_id"),
                            resultSet.getString("product_name"),
                            resultSet.getString("category"),
                            resultSet.getDouble("price"),
                            resultSet.getInt("quantity")
                    );

                    productList.add(product);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error searching product : " + e.getMessage());
        }

        return productList;
    }


    // UPDATE STOCK
    public boolean updateStock(int productId, int quantity) {

        String sql = """
            UPDATE products
            SET quantity = quantity + ?
            WHERE product_id = ?
            """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, quantity);
            statement.setInt(2, productId);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error updating stock : " + e.getMessage());
        }

        return false;
    }

    // REDUCE STOCK
    public boolean reduceStock(int productId, int quantity) {

        String sql = """
            UPDATE products
            SET quantity = quantity - ?
            WHERE product_id = ?
            """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, quantity);
            statement.setInt(2, productId);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error reducing stock : " + e.getMessage());
        }

        return false;
    }

    // LOW STOCK
    public List<Product> getLowStockProducts() {

        List<Product> productList = new ArrayList<>();

        String sql = """
            SELECT * FROM products
            WHERE quantity <= 5
            """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Product product = new Product(
                        resultSet.getInt("product_id"),
                        resultSet.getString("product_name"),
                        resultSet.getString("category"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("quantity")
                );

                productList.add(product);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching low stock products : " + e.getMessage());
        }

        return productList;
    }
}
