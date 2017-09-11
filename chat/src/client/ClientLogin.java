/**
 * 聊天室登录界面
 */
package client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ClientLogin extends JFrame implements ActionListener,MouseListener{
	//定义登陆界面上边的组件
	JLabel jl1;
	//定义中间的组件
	JPanel jp1,jp2,jp3,jp4;
	JLabel jp1_jl1,jp1_jl2,jp1_jl3,jp1_jl4;
	JTextField jp1_jt1;
	JPasswordField jp1_jpw;
	
	JCheckBox jp2_jc1,jp2_jc2;
	
	JButton jp3_jb1;
	public static void main(String[] args) {
		new ClientLogin();

	}
	
	public ClientLogin(){
		//实现北部
		jl1 = new JLabel(new ImageIcon("image/1.jpg"));
		jl1.setPreferredSize(new Dimension(450,160));
		//实现中部
		jp1 = new JPanel(new GridLayout(2,3));
		jp1_jl1 = new JLabel("账号    :      ",JLabel.RIGHT);
		jp1_jl1.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		jp1_jl2 = new JLabel("密码    :      ",JLabel.RIGHT);
		jp1_jl2.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		jp1_jl3 = new JLabel("注册账号",JLabel.CENTER);
		jp1_jl3.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		jp1_jl3.addMouseListener(this);
		jp1_jl4 = new JLabel("忘记密码",JLabel.CENTER);
		jp1_jl4.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		jp1_jl4.addMouseListener(this);
		jp1_jt1 = new JTextField();
		jp1_jpw = new JPasswordField();
		jp1.add(jp1_jl1);
		jp1.add(jp1_jt1);
		jp1.add(jp1_jl3);
		jp1.add(jp1_jl2);
		jp1.add(jp1_jpw);
		jp1.add(jp1_jl4);
		
		jp2 = new JPanel();
		jp2_jc1 = new JCheckBox("记住密码");
		jp2_jc1.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
		jp2_jc2 = new JCheckBox("自动登陆");
		jp2_jc2.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		jp2.add(jp2_jc1);
		jp2.add(jp2_jc2);
		
		jp3 = new JPanel();
		jp3_jb1 = new JButton("登陆");
		//响应用户点击登陆
		jp3_jb1.addActionListener(this);
		
		jp3_jb1.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		jp3_jb1.setPreferredSize(new Dimension(150,30));
		jp3.add(jp3_jb1);
		
		jp4 = new JPanel(new GridLayout(3, 1));
		jp4.add(jp1);
		jp4.add(jp2);
		jp4.add(jp3);
		
		this.add(jl1,"North");
		this.add(jp4,"Center");
		this.setTitle("Chat Room");
		this.setSize(450,350);
		this.setLocation(500, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//如果点击了登陆
		if(e.getSource() == jp3_jb1){
			User u = new User();
			u.setUserId(jp1_jt1.getText());
			u.setPassword(new String(jp1_jpw.getPassword()));
			//检查登陆信息
			if(u.checkUser()){
				try {
					ChatView cView= new ChatView(u.getUserId());
					ManageChatView.addChatView(u.getUserId(), cView);
					ObjectOutputStream oos = new ObjectOutputStream(ManagerClientThread.getClientThread(u.getUserId()).getS().getOutputStream());
					Message m = new Message();
					//向服务器发送一个请求在线成员的message
					m.setMessageType(MessageType.message_get_member);
					m.setSender(u.getUserId());
					oos.writeObject(m);
					//关闭登陆界面
					this.dispose();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}else{
				JOptionPane.showMessageDialog(this, "账号或密码错误！");
			}
			
		}else{
			JOptionPane.showMessageDialog(this, "账号或密码错误！");
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource() == jp1_jl3){
			JLabel jl3_event = (JLabel)e.getSource();
			jl3_event.setForeground(Color.red);
		}
		if(e.getSource() == jp1_jl4){
			JLabel jl4_event = (JLabel)e.getSource();
			jl4_event.setForeground(Color.red);
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource() == jp1_jl3){
			JLabel jl3_event = (JLabel)e.getSource();
			jl3_event.setForeground(Color.black);
		}
		if(e.getSource() == jp1_jl4){
			JLabel jl4_event = (JLabel)e.getSource();
			jl4_event.setForeground(Color.black);
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource() == jp1_jl3){
			new Register();
		}
		if(e.getSource() == jp1_jl4){
			System.out.println("点击了忘记密码按钮");
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
