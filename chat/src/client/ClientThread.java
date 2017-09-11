/**
 * 客户端线程
 */
package client;

import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientThread extends Thread{

	public Socket s;
	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}

	public ClientThread(Socket s){
		this.s = s;
	}

	@Override
	public void run() {
		//不停读取服务器发来的message
		while(true){
			try {
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message m = (Message)ois.readObject();
				if(m.getMessageType().equals(MessageType.message_comm)){
					ChatView cView = ManageChatView.getChatView(m.getSender());
					cView.showMessage(m);
				}
				else if(m.getMessageType().equals(MessageType.message_ret_member)){
					String comm = m.getComm();
					String[] member = comm.split(" ");
					String sender = m.getSender();
					ChatView cView = ManageChatView.getChatView(sender);
					if(cView != null){
						if(member != null){
							for(int i = 0;i<member.length;i++){
								cView.updateMember(member[i]);
							}
							
						}
					}
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}

