/**
 * 注册用户
 */
package client;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Register extends JFrame implements ActionListener{
	//定义注册界面的组件
	JLabel jl1,jp1_jl1,jp1_jl2,jp1_jl3;
	JPanel jp1,jp2;
	JButton jb;
	JTextField jt;
	JPasswordField jpass1,jpass2;
	
	
	public Register(){
		jl1 = new JLabel("注册账号:");
		jl1.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		
		jp1 = new JPanel(new GridLayout(5, 2));
		jp1_jl1 = new JLabel("账号:",JLabel.CENTER);
		jp1_jl1.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		jp1_jl2 = new JLabel("密码:",JLabel.CENTER);
		jp1_jl2.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		jp1_jl3 = new JLabel("确认密码:",JLabel.CENTER);
		jp1_jl3.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		jt = new JTextField();
		jt.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		jpass1 = new JPasswordField();
		jpass2 = new JPasswordField();
		jp1.add(jp1_jl1);
		jp1.add(jt);
		jp1.add(jp1_jl2);
		jp1.add(jpass1);
		jp1.add(jp1_jl3);
		jp1.add(jpass2);
		
		jb = new JButton("立即注册");
		jb.addActionListener(this); // 监听用户点击注册按钮
		jb.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		jb.setPreferredSize(new Dimension(150, 30));
		jp2= new JPanel();
		jp2.add(jb);
		
		this.add(jl1,"North");
		this.add(jp1, "Center");
		this.add(jp2, "South");
		this.setSize(350,250);
		this.setLocation(650, 300);
		this.setResizable(false);
		this.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jb){
			if(!jt.getText().isEmpty()){
				if(!(new String(jpass1.getPassword()).isEmpty()||new String(jpass2.getPassword()).isEmpty())){
					if(new String(jpass1.getPassword()).equals(new String(jpass2.getPassword()))){
						User u = new User();
						u.setUserId(jt.getText());
						u.setPassword(new String(jpass1.getPassword()));
						u.setState("1");
						if(!u.isregister()){
							if(!u.addUser()){
								JOptionPane.showMessageDialog(this, "注册成功!");
								this.dispose();
							}
							else 
								JOptionPane.showMessageDialog(this, "注册失败!");
						}
						else{
							JOptionPane.showMessageDialog(this, "当前账号已注册");
							}
					}
					else JOptionPane.showMessageDialog(this, "输入的密码不一致!");
				}
				else JOptionPane.showMessageDialog(this, "密码不能为空!");
			}
			else JOptionPane.showMessageDialog(this, "账号不能为空!");
		}
		
	}
}
