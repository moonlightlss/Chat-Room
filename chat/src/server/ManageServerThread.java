/**
 * 管理服务器线程
 */
package server;

import java.util.HashMap;
import java.util.Iterator;

public class ManageServerThread {
	public static HashMap<String, ServerThread> hm = new HashMap<>();
	
	public static void addServerThread(String uid,ServerThread st) {
		hm.put(uid,st);
	}

	public static ServerThread getServerThread(String uid) {
		return (ServerThread)hm.get(uid);
	}
	public static String getAllOnlineMember() {
		//使用迭代器完成
		Iterator<String> it = hm.keySet().iterator();
		String res = "";
		while(it.hasNext()){
			res += it.next().toString()+" ";
		}
		return res;
	}
}
