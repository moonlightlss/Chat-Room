/**
 * 客户端和服务器之间的通信包
 */
package client;

import java.io.Serializable;

public class Message implements Serializable{
	

	String messageType;
	String sender;
	String comm;

	public String getComm() {
		return comm;
	}

	public void setComm(String comm) {
		this.comm = comm;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
}
