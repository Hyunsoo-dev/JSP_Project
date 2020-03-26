<%@page import="model.MemberBean"%>
<%@page import="java.util.Vector"%>
<%@page import="model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<!--1.데이터베이스에서 모든 회원의 정보를 가져옴. 2.table태그를 이용하여 화면의 회원들에 정보를 출력-->
	<%
	MemberDAO mdao = new MemberDAO();
	
	//회원들의 정보가 얼마나 저장되어 있는지 모르기에 가변길이인 Vector를 이용하여 데이터를 저장.
	Vector<MemberBean> vec = mdao.allSelectMember();
	%>
	<h2>모든 회원 정보 보기</h2>
	<table width = "800" border = "1">
		<tr height = "50">
			<td align = "center" width = "150">아이디</td>
			<td align = "center" width = "250">이메일</td>
			<td align = "center" width = "200">전화번호</td>
			<td align = "center" width = "200">취미</td>
		</tr>
	<%
		for(int i = 0; i < vec.size(); i++){
			MemberBean bean = vec.get(i);
	%>
		<tr height = "50">
			<td align = "center" width = "150"><%=bean.getId() %></td>
			<td align = "center" width = "150"><%=bean.getEmail() %></td>
			<td align = "center" width = "150"><%=bean.getTel() %></td>
			<td align = "center" width = "150"><%=bean.getHobby() %></td>
		</tr>		
	<% 
		}
	%>	
	
	</table>
</body>
</html>