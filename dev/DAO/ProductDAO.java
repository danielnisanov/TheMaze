package DAO;

import Domain.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IDAO<Product> {
    private final String url = "jdbc:sqlite:path_to_your_database.db";

    @Override
    public void add(Product product) throws Exception {
        String sql = "INSERT INTO products(name, category, subCategory, subSubCategory, area, manufacturer, minQuantity, costPrice, sellingPrice, discount, sale) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getCat());
            pstmt.setString(3, product.getSubCat());
            pstmt.setString(4, product.getSubSubCat());
            pstmt.setString(5, product.getArea());
            pstmt.setString(6, product.getManufacturer());
            pstmt.setInt(7, product.getMinQuantity());
            pstmt.setDouble(8, product.getCostPrice());
            pstmt.setDouble(9, product.getSellingPrice());
            pstmt.setDouble(10, product.getDiscount());
            pstmt.setDouble(11, product.getSale());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void remove(String name) throws Exception {
        String sql = "DELETE FROM products WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        }
    }

    @Override
    public Product get(String name) throws Exception {
        String sql = "SELECT * FROM products WHERE name = ?";
        Product product = null;

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                product = new Product(
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getString("subCategory"),
                        rs.getString("subSubCategory"),
                        rs.getString("area"),
                        rs.getString("manufacturer"),
                        rs.getInt("minQuantity"),
                        rs.getDouble("costPrice"),
                        rs.getDouble("sellingPrice"),
                        rs.getDouble("discount"),
                        rs.getDouble("sale")
                );
            }
        }

        return product;
    }

//    @Override
//    public List<Product> findAll() throws Exception {
//        String sql = "SELECT * FROM products";
//        List<Product> products = new ArrayList<>();
//
//        try (Connection conn = DriverManager.getConnection(url);
//             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery(sql)) {
//
//            while (rs.next()) {
//                Product product = new Product(
//                        rs.getString("name"),
//                        rs.getString("category"),
//                        rs.getString("subCategory"),
//                        rs.getString("subSubCategory"),
//                        rs.getString("area"),
//                        rs.getString("manufacturer"),
//                        rs.getInt("minQuantity"),
//                        rs.getDouble("costPrice"),
//                        rs.getDouble("sellingPrice"),
//                        rs.getDouble("discount"),
//                        rs.getDouble("sale")
//                );
//                products.add(product);
//            }
//        }
//
//        return products;
//    }
}
