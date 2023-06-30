package com.momo.ex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestOjdbc {

	public void ojdbcTest() {
		try {
			// 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 커넥션 생성
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "library", "1234");
			ResultSet rs = conn.createStatement().executeQuery("select to_char(sysdate, 'yyyy/mm/dd')||입니다");
			
			System.out.println(rs.getString(1));
			System.out.println(conn);
			
		} catch (ClassNotFoundException e) {
			System.err.println("라이브러리를 확인해주세요!");
			System.err.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
}
