<%@page import="model.MemberDAO"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="org.apache.tomcat.dbcp.dbcp2.DriverManagerConnectionFactory"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%
		request.setCharacterEncoding("euc-kr");//한글처리
		
		//hobby부분은 별도로 읽어드려 다시 빈 클래스에 저장
		String [] hobby = request.getParameterValues("hobby");
		
		//배열의 있는 내용을 하나의 스트링으로 저장
		String texthobby ="";
		for(int i = 0; i < hobby.length; i++){
			texthobby += hobby[i] + " ";
		}
		
		
	%>
		<!-- useBean을 이용하여 한꺼번에 데이터를 받아옴. -->
		<!-- request로 넘어온 데이터를 자바 빈즈랑 맵핑 시켜주는 useBean -->
		<jsp:useBean id="mbean" class="model.MemberBean">
		<!-- 객체 생성 MemberBean mbean = new MemberBean(); 과 같음. -->
			<jsp:setProperty name = "mbean" property
			 = "*"/> <!-- 맵핑 시키시오. -->
		</jsp:useBean>
		
	<%
		mbean.setHobby(texthobby);//기존 hobby는 주소번지가 저장되기에 위에 배열의 내용을 하나의 스트링으로 저장한 변수를 다시 입력.
		
		//데이터 베이스 클래스 객체 생성 
		MemberDAO mdao = new MemberDAO();
		mdao.insertMember(mbean);
		
		
		//회원가입이 되었다면 회원정보를 보여주는 페이지로 이동시킴.
		response.sendRedirect("MemberList.jsp");
	%>
	오라클완료 
	
	
</body>
</html>