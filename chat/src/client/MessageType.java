/**
 * 通信包的种类
 */
package client;

public interface MessageType {
	String message_login_succeed = "1";//表明登陆成功
	String message_login_fail = "2";//表明登陆失败
	String message_comm = "3";//普通信息包
	String message_get_member = "4";//要求在线成员信息包
	String message_ret_member = "5";//返回在线成员信息包
	String message_register_succeed = "6"; //表明注册成功
	String message_register_fail = "7"; //表明注册失败
	String message_registered = "8"; //表明当前用户已注册
}
