package com.kh.product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
	private static final String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String jdbcUsername = "khcafe";
	private static final String jdbcPassword = "kh1234";
	
	//Class.forName("oracle.jdbc.OracleDriver"); 실행해주는게 없어서 생성자 만들어줘야함
	public ProductDAO() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<Product> getAllProducts() { //getAllProducts() : 전부다 가지고 오는 것
		List<Product> products = new ArrayList<>();
		try {
			Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			String sql = "SELECT * FROM products";
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ResultSet resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
				int productId = resultSet.getInt("product_id");
				String productName = resultSet.getString("product_name");
				String category = resultSet.getString("category");
				double price = resultSet.getDouble("price");
				int stockQuantity = resultSet.getInt("stock_quantity");
				
				Product product = new Product(productId, productName, category, price, stockQuantity);
				products.add(product);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
		
	}
	
	public Product getProductId(int ProductId) {
		
		Product product = null;
		//select해서 한 개만 볼 수 있는 쿼리 작성하고 new product 이용해서 값 가져오기
		try {
			Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			String sql = "SELECT * FROM products WHERE = product_id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, ProductId);
			
			ResultSet resultSet = ps.executeQuery();
			
			if(resultSet.next()) {
				ProductId = resultSet.getInt("product_id");
				String productName = resultSet.getString("product_name");
				String category = resultSet.getString("category");
				double price = resultSet.getDouble("price");
				int stockQuantity = resultSet.getInt("stock_quantity");
				
				product = new Product(ProductId, productName, category, price, stockQuantity);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return product;
	}
	private int getProductId() {
		// TODO Auto-generated method stub
		return 0;
	}
}
