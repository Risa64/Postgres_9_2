package postgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// PostgreSQLのResultSetの実装クラスでサポートされてない者たち
// Java 的には便利だろうと思って作ったけど、サポートされてないので結局使えない子たち以下三つ
// resultSet.absolute(5);
// resultSet.first();
// resultSet.deleteRow();

class DatabaseSample_01_Products_01 {
	public static void main(String[] args) {
		// JDBC ドライバでPosteSQLサーバーに接続
		
		String url = "jdbc:postgresql://localhost:5432/shop";
		
		String user = "postgres";
		
		String password = "root";
		try {
		Connection connection = DriverManager.getConnection(url, user, password);
		// 接続が成功すると、connectionのインスタンスを返してくれる。ことにより、SQL
		
		Statement statement = connection.createStatement();
		// Postgres用のインスタンスを返してくれる←？
		// SQLを実行するためのstatementインスタンス←？
		
	
//		String sql = "select * from products order by product_id asc;";
		String sql = "select * from products order by product_id asc;";
		ResultSet resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
		    // 各カラムを取り出す
//			System.out.println(resultSet); // like hash values
			String productId = resultSet.getString("product_id");
			System.out.println("Product ID : " + productId);
			
			System.out.println(resultSet.getString("product_name"));
			System.out.println(resultSet.getString("product_type"));
			System.out.println(resultSet.getString("price"));
			System.out.println(resultSet.getString("cost"));
		}
		
		// Do not forget close!
		resultSet.close();
		statement.close();
		// cutout connection
		connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
