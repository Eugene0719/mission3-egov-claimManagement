package sample;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class MySqlTest {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/test?serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PW = "1111";

	@Test
	public void testConnect() throws Exception {

		Class.forName(DRIVER);

		try (Connection con = DriverManager.getConnection(URL, USER, PW)) {
			System.out.println(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
