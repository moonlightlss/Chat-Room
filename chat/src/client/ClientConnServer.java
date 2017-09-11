/**
 * 客户端连接服务器
 */
package client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket; 

public class ClientConnServer {
	public Socket s;
	boolean b= false,c= false,d= false;
	public ClientConnServer(Object o){
//		b = false;
		try {
			//在端口8888进行监听
			s = new Socket("127.0.0.1", 8888);
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Message m = (Message)ois.readObject();
			//验证用户登陆
			if(m.getMessageType().equals("1")){
				ClientThread ct = new ClientThread(s);
				ct.start();
				ManagerClientThread.addClientThread(((User)o).getUserId(), ct);
				b = true;
			}else if(m.getMessageType().equals("6")){
				c = true;
			}else if(m.getMessageType().equals("8")){
				d = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
