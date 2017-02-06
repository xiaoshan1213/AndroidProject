<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%

out.clear();

String action=request.getParameter("action");

if(action!=null){
	if(action.equals("send_pass")){
		out.print("{\"status\":1}");
	}else if(action.equals("login")){
		out.print("{\"status\":1,\"token\":\"testtokenmessage\"}");
	}else if(action.equals("upload_contacts")){
		out.print("{\"status\":1}");
	}else if(action.equals("timeline")){
		out.print("{\"status\":1,\"page\":1,\"perpage\":20,\"timeline\":["+
	"{\"message\":\"hello1\",\"phone_md5\":\"ejglshakshd\",\"msg_id\":\"123\"},"+
	"{\"message\":\"hello2\",\"phone_md5\":\"ejglshakshd\",\"msg_id\":\"123\"},"+
	"{\"message\":\"hello3\",\"phone_md5\":\"ejglshakshd\",\"msg_id\":\"123\"}"+
				"]}");
	}else if(action.equals("get_comment")){
		out.print("{\"status\":1,\"page\":1,\"perpage\":20,\"msg_id\":\"12321\",\"comments\":["+
	"{\"content\":\"hey1\",\"phone_md5\":\"ejglshakshd\"},"+
	"{\"content\":\"hey2\",\"phone_md5\":\"ejglshakshd\"},"+
	"{\"content\":\"hey3\",\"phone_md5\":\"ejglshakshd\"}"+
				"]}");
	}else if(action.equals("pub_comment")){
		out.print("{\"status\":1}");
	} else if(action.equals("publish")){
		out.print("{\"status\":1}");
	}
}else{
	out.print("please specify action");
}

 %>
