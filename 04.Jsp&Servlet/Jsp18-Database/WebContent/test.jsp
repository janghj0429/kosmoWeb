<%@page import="java.sql.DriverManager" %>
<%@page import="java.sql.ResultSet" %>
<%@page import="java.sql.Statement" %>
<%@page import="java.sql.PreparedStatement" %>
<%@page import="java.sql.Connection" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%!
    	Connection connection;
    	Statement statement;
    	PreparedStatement pstmt;
    	ResultSet resultSet;
    	
    	String driver = "oracle.jdbc.driver.OracleDriver";
    	String url = "jdbc:oracle:thin:@localhost:1521:xe";
    	String uid = "scott";
    	String upw = "tiger";
   
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		try{
			Class.forName(driver);
			connection = DriverManager.getConnection(url, uid, upw);		
			
			String sql = "insert into member values(?,?,?,?)";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, "eee");
			pstmt.setString(2, "555");
			pstmt.setString(3, "홍길동5");
			pstmt.setString(4, "010-5555-5555");
			int updateCount = pstmt.executeUpdate();
			out.println("insertCount : " + updateCount + "<br>");
			
			sql = "delete member where id='aaa'";
			pstmt = connection.prepareStatement(sql);
			updateCount = pstmt.executeUpdate();
			out.println("deleteCount : " + updateCount + "<br>");
			
			sql = "select * from member";
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next()){
				String id = resultSet.getString("id");
				String pw = resultSet.getString("pw");
				String name = resultSet.getString("name");
				String phone = resultSet.getString("phone");
				
				out.println("아이디 : " + id +
							", 비밀번호 : " + pw +
							", 이름 : " + name + 
							", 전화번호 : " + phone + "<br>");
			}			
		}catch(Exception e){
		}finally{
			try{
				if(resultSet != null) resultSet.close();
				if(statement != null) statement.close();
				if(connection != null) connection.close();
			}catch(Exception e){}
		}
	
	
	%>
		
</body>
</html>