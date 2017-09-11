/**
 * 聊天界面
 */
package client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ChatView extends JFrame implements ActionListener{
	//定义聊天界面所需组件
	JTextArea jta_rec,jta_send,jta_info;
	JPanel jp_message,jp_send,jp1,jp2,jp_button,jp_info;
	JButton jb_close,jb_send;
	JLabel jl1,jl2;
	String userId;
	ArrayList<String> aList;
	
	public ChatView(String u){
		this.userId = u;
		//显示接收到的信息
		jta_rec = new JTextArea(15,40);
		jta_rec.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		jta_rec.setLineWrap(true);//激活自动换行
		jta_rec.setWrapStyleWord(true);// 激活断行不断字功能
		jta_rec.setMargin(new Insets(5, 5, 5, 5));
		jta_rec.setEditable(false);
		jp1 = new JPanel();
		jp1.add(new JScrollPane(jta_rec));
		
		jl1 = new JLabel(new ImageIcon("image/2.jpg"));
		
		//显示发送的信息
		jta_send = new JTextArea(6,40);
		jta_send.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		jta_send.setLineWrap(true);
		jta_send.setMargin(new Insets(5, 5, 5, 5));
		jp2 = new JPanel(new BorderLayout());
		jp2.add(jl1,"North");
		jp2.add(new JScrollPane(jta_send),"Center");
		
		jp_button = new JPanel();
		jb_close = new JButton("关闭");
		jb_close.addActionListener(this);//响应点击了关闭
		jb_send = new JButton("发送");
		jb_send.addActionListener(this);//响应点击了发送
		jp_button.setLayout(new FlowLayout(FlowLayout.RIGHT));
		jp_button.add(jb_close);
		jp_button.add(jb_send);
		
		jp_message = new JPanel(new BorderLayout());
		jp_message.add(jp1,"North");
		jp_message.add(jp2,"Center");
		jp_message.add(jp_button,"South");
		
		//实现聊天室在线成员信息
		jp_info = new JPanel(new BorderLayout());
		jl2 = new JLabel("    聊天成员:");
		jl2.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		jta_info = new JTextArea(10,14);
		jta_info.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		jta_info.setLineWrap(true);
		jta_info.setMargin(new Insets(5, 5, 5, 5));
		jta_info.setEditable(false);
		jp_info.add(jl2,"North");
		jp_info.add(new JScrollPane(jta_info),"Center");
		
		this.add(jp_message,"West");
		this.add(jp_info,"East");
		this.setSize(810, 600);
		this.setLocation(500,200);
		this.setTitle("Chat Room --"+userId);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public void showMessages(String s) {
		String info = s;
		this.jta_rec.append(info);
	}
	public void updateMember(String s){
		String info = new String("      "+s+" 进入了聊天室 "+"\r\n");
		String info_member = new String(s+"\r\n");
		this.jta_rec.append(info);
		this.jta_info.append(info_member);
	}
	public void showMessage(Message m) {
		String info = m.getComm();
		this.jta_rec.append(info);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//如果用户点击了发送按钮
		if(e.getSource() == jb_send){
			if (!jta_send.getText().equals("")) {
				Date date = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");
				String dd = formatter.format(date);
				String ower = new String(dd+" @"+userId+" : "+jta_send.getText()+"\r\n");
				this.jta_send.setText("");
				Message m = new Message();
				m.setMessageType(MessageType.message_comm);
				m.setComm(ower);
				//发送给服务器
				try {
					ObjectOutputStream oos = new ObjectOutputStream(ManagerClientThread.getClientThread(userId).getS().getOutputStream());
					oos.writeObject(m);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			
		}else if (e.getSource() == jb_close) {
			System.exit(0);
		}
		
	}

}
