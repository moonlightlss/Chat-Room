/**
 * 用户信息
 */
package client;

import java.io.Serializable;

public class User implements Serializable{
	String userId;
	String password;
	String state ="0";//state = '0'时为验证账号，state = '1'时为注册账号 
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String cs) {
		this.password = cs;
	}
	
	public boolean checkUser(){
		return new ClientConnServer(this).b;
	}
	
	public boolean addUser() {
		return new ClientConnServer(this).c;
	}
	
	public boolean isregister(){
		return new ClientConnServer(this).d;
	}
}
