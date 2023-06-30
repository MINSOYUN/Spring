package com.momo.ex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

public class TestOjdbc {
	
	// test 어노테이션 붙인 메서드만 테스트 된다
	@Test
	public void calcTest() {
		Calc calc = new Calc();
		int res = calc.add(1, 2);
		
		// res 와 3이 같은 수인지 확인하는 메서드
		assertEquals(4, res);
	}

	
	
	public void ojdbcTest() {
		try {
			// 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 커넥션 생성
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "library", "1234");
			ResultSet rs = conn.createStatement().executeQuery("select to_char(sysdate, 'yyyy/mm/dd')||입니다");
			
			System.out.println(rs.getString(1));
			System.out.println(conn);
			
			assertNotNull(conn);
			
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
