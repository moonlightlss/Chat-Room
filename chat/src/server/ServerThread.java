/**
 * 服务器线程
 */
package server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

import client.Message;
import client.MessageType;

public class ServerThread extends Thread{

	Socket s;
	public ServerThread(Socket s) {
		this.s = s;
	}
	//让该线程通知其他用户
//	public void notifyOther(String name) {
//		//得到所有在线成员的线程
//		HashMap<String, ServerThread> hm = ManageServerThread.hm;
//		Iterator<String> it = hm.keySet().iterator();
//		while(it.hasNext()){
//			Message m = new Message();
//			m.setComm(name);
//			m.setMessageType(MessageType.message_ret_member);
//			//取出在线成员id
//			String onlineMember = it.next().toString();
//			try {
//				ObjectOutputStream oos = new ObjectOutputStream(ManageServerThread.getServerThread(onlineMember).s.getOutputStream());
//				m.setSender(onlineMember);
//				oos.writeObject(m);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}
	public void notifyOther(String name) {
		//得到所有在线成员的线程
		HashMap<String, ServerThread> hm = ManageServerThread.hm;
		Iterator<String> it = hm.keySet().iterator();
		while(it.hasNext()){
			Message m = new Message();
			m.setComm(name);
			m.setMessageType(MessageType.message_ret_member);
			//取出在线成员id
			String onlineMember = it.next().toString();
			try {
				ObjectOutputStream oos = new ObjectOutputStream(ManageServerThread.getServerThread(onlineMember).s.getOutputStream());
				m.setSender(onlineMember);
				oos.writeObject(m);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void run() {
		while(true){
			//接受客户端的消息
			try {
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message m = (Message)ois.readObject();
				//对从客户端取得的消息进行判断
				if(m.getMessageType().equals(MessageType.message_comm)){
					//取得所有人的通讯线程
					HashMap<String, ServerThread> hm = ManageServerThread.hm;
					Iterator<String> it = hm.keySet().iterator();
					while(it.hasNext()){
						//取出在线成员id
						String onlineMember = it.next().toString();
						try {
							ObjectOutputStream oos = new ObjectOutputStream(ManageServerThread.getServerThread(onlineMember).s.getOutputStream());
							m.setSender(onlineMember);
							oos.writeObject(m);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}else if(m.getMessageType().equals(MessageType.message_get_member)){
					String res = ManageServerThread.getAllOnlineMember();
					Message m2 = new Message();
					m2.setMessageType(MessageType.message_ret_member);
					m2.setComm(res);
					m2.setSender(m.getSender());
					ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
					oos.writeObject(m2);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
