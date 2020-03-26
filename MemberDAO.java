package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

//오라클 데이터베이스에 연결하고 select , insert , update, delete작업을 실행해주는 클래스 입니다.
public class MemberDAO {

	String id = "system";
	String pass = "1234";
	String url = "jdbc:oracle:thin:@localhost:1522:orcl";//접속 url
	
	Connection con ;//데이터베이스에 접근할 수 있도록 설정
	PreparedStatement pstmt;//데이터베이스에서 쿼리를 실헹시켜주는 객체
	ResultSet rs;//데이터베이스의 테이블의 결과를 리턴받아 자바에 저장해주는 객체
	
	//데이터 베이스에 접근할 수 있도록 도와주는 메소드
	public void getCon() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//jsp와 DB를 연결해주는 class ,Ojdbc6.jar 파일 안에 존재.
			//2.해당 데이터베이스에 접속 
			con = DriverManager.getConnection(url,id,pass);//iv로 사용하기위해 Connection 삭제
		}catch(Exception e) {
			
		}
	}
	
	//데이터베이스에 한사람의 회원정보를 저장해주는 메소드
	public void insertMember(MemberBean mbean) {
		try{
			getCon();
			//3.접속 후 쿼리 준비.
			String sql = "insert into member values(?,?,?,?,?,?,?,?)";
			//4.쿼리를 사용하도록 설정.
			PreparedStatement pstmt = con.prepareStatement(sql);
			//5.?에 맞게 데이터를 맵핑.
			pstmt.setString(1,mbean.getId());
			pstmt.setString(2,mbean.getPass1());
			pstmt.setString(3,mbean.getEmail());
			pstmt.setString(4,mbean.getTel());
			pstmt.setString(5,mbean.getHobby());
			pstmt.setString(6,mbean.getJob());
			pstmt.setString(7,mbean.getAge());
			pstmt.setString(8,mbean.getInfo());
			//6.오라클에서 쿼리를 실행하시오.
			pstmt.executeUpdate();//insert, update, delete 시 사용하는 메서드  
			
			//7.자원반납
			con.close();
		}catch(Exception e){
			e.getStackTrace();
		}
	}
	
	//모든 회원의 정보를 리턴해주는 메소드 
	public Vector<MemberBean> allSelectMember(){
		//가변길이로 데이터를 저장
		Vector<MemberBean> v = new Vector();
		try {
			//커넥션 연결 
			getCon();
			//쿼리준비 
			String sql = "select * from member";
			//쿼리를 실행시켜주는 객체 선언
			pstmt = con.prepareStatement(sql);
			//쿼리를 실행시킨 결과를 리턴해서 받아줌.(오라클 테이블의 검색된 결과를 자바객체에 저장.)
			rs = pstmt.executeQuery();
			
			while(rs.next()) {//저장된 데이터 만큼까지 반복문을 돌리겠다는 의미
				MemberBean bean = new MemberBean();
				bean.setId(rs.getString(1));
				bean.setPass1(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setTel(rs.getString(4));
				bean.setHobby(rs.getString(5));
				bean.setJob(rs.getString(6));
				bean.setAge(rs.getString(7));
				bean.setInfo(rs.getString(8));
				
				//패키징된 MemberBean 클래스를 백터에 저장.
				v.add(bean);//0번지부터 순서대로 저장
				
			}
			con.close();//자원반납
		}catch(Exception e) {
			
		}
		//다 저장된 벡터를 리턴
		return v;
	}
	
	
	
	
}
