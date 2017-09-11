/**
 * 管理客户端线程
 */
package client;

import java.util.HashMap;

public class ManagerClientThread {

	private static HashMap<String, ClientThread> hm = new HashMap<>();
	
	public static void addClientThread(String userId, ClientThread ct) {
		hm.put(userId, ct);
	}
	public static ClientThread getClientThread(String userId) {
		return (ClientThread)hm.get(userId);
	}
	
	
}