/**
 * 管理聊天界面
 */
package client;

import java.util.HashMap;

public class ManageChatView {
	private static HashMap<String, ChatView> hm = new HashMap<>();
	
	public static void addChatView(String uid,ChatView cView){
		hm.put(uid, cView);
	}
	public static ChatView getChatView(String uid) {
		return (ChatView)hm.get(uid);
	}
}
