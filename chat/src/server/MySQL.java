package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;

import client.User;

public class MySQL {
	//JDBC 驱动名以及数据库URL
	static final String JDBC_Driver = "com.mysql.jdbc.Driver";
	static final String URL = "jdbc:mysql://localhost:3306/user";
	//数据库用户名和密码
	static final String USER = "root";
	static final String PASS = "123456";
	//存放数据库中数据
	static HashMap<String, String> hm = new HashMap<>();
	
//	public static void main(String[] args) {
//		User u = new User();
//		u.setState("1");u.setUserId("111");u.setPassword("111");
//		addMember(u);;
//	}
	
	public MySQL(){
		try {
			Connection conn = null;
			Statement stmt = null;
			//注册 JDBC 驱动
			Class.forName(JDBC_Driver);
			System.out.println("连接数据库");
			conn = DriverManager.getConnection(URL,USER,PASS);
			stmt = conn.createStatement();
			String sql = "select userid,password from userinfo";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				String userid = rs.getString("userid");
				String pass = rs.getString("password");
				hm.put(userid, pass);
				
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	//检验用户的用户名和密码是否正确，正确时返回true
	public static boolean checkuser(User u) {
		new MySQL();
		Iterator<String> it = hm.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			if(key.equals(u.getUserId())){
				if(hm.get(key).equals(u.getPassword())){
					return true;
				}
			}
		}
		return false;
	}
	//检验用户名是否已注册，已注册时返回false
	public static boolean isrigister(User u) {
		new MySQL();
		Boolean b = true;
		Iterator<String> it = hm.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			if(key.equals(u.getUserId())){
					b = false;
			}
		}
		return b;
	}
	// 向数据库添加成员信息
	public static void addMember(User u) {
		try {
			Connection conn = null;
			Statement stmt = null;
			//注册 JDBC 驱动
			Class.forName(JDBC_Driver);
			System.out.println("连接数据库");
			conn = DriverManager.getConnection(URL,USER,PASS);
			stmt = conn.createStatement();
			
			String userid = u.getUserId();
			String pass = u.getPassword();
			String sql = "insert into userinfo value('"+userid+"','"+pass+"')";
			stmt.executeUpdate(sql);
			
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
