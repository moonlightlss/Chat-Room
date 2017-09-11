/**
 * 服务器主体
 */
package server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import client.Message;
import client.MessageType;
import client.User;

public class MyServer {
	
	public static void main(String[] args){
		new MyServer();
	}
	public MyServer(){
		try {
			//在端口8888监听
			ServerSocket ss = new ServerSocket(8888);
			System.out.println("服务器在端口8888监听");
			//阻塞，等待连接
			while (true){
				Socket s = ss.accept();
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				User u = (User)ois.readObject();
				//当验证用户信息时
				if(u.getState().equals("0")){
					System.out.println("用户  "+u.getUserId()+"  密码 ： "+u.getPassword());
					Message m = new Message();
					m.setSender(u.getUserId());
					ObjectOutputStream oss = new ObjectOutputStream(s.getOutputStream());
					//验证用户名和密码
					if(MySQL.checkuser(u)){
						m.setMessageType("1");
						oss.writeObject(m);
						
						//保持通信的线程
						ServerThread st = new ServerThread(s);
						ManageServerThread.addServerThread(u.getUserId(), st);
						st.start();
						//通知其他在线用户
						st.notifyOther(u.getUserId());
					}else{
						m.setMessageType("2");
						oss.writeObject(m);
						s.close();
					}
				}
				//当注册用户时
				else if(u.getState().equals("1")){
					Message m = new Message();
					ObjectOutputStream oss = new ObjectOutputStream(s.getOutputStream());
					//注册前检测账号是否已注册
					if(MySQL.isrigister(u)){
						MySQL.addMember(u);
						//检测是否注册成功
						if(MySQL.checkuser(u)){
							m.setMessageType(MessageType.message_register_succeed);
							oss.writeObject(m);
							s.close();
						}
						else {
							m.setMessageType(MessageType.message_register_fail);
							oss.writeObject(m);
							s.close();
						}
					}
					else{
						m.setMessageType(MessageType.message_registered);
						oss.writeObject(m);
						s.close();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
