package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

//����Ŭ �����ͺ��̽��� �����ϰ� select , insert , update, delete�۾��� �������ִ� Ŭ���� �Դϴ�.
public class MemberDAO {

	String id = "system";
	String pass = "1234";
	String url = "jdbc:oracle:thin:@localhost:1522:orcl";//���� url
	
	Connection con ;//�����ͺ��̽��� ������ �� �ֵ��� ����
	PreparedStatement pstmt;//�����ͺ��̽����� ������ ��������ִ� ��ü
	ResultSet rs;//�����ͺ��̽��� ���̺��� ����� ���Ϲ޾� �ڹٿ� �������ִ� ��ü
	
	//������ ���̽��� ������ �� �ֵ��� �����ִ� �޼ҵ�
	public void getCon() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//jsp�� DB�� �������ִ� class ,Ojdbc6.jar ���� �ȿ� ����.
			//2.�ش� �����ͺ��̽��� ���� 
			con = DriverManager.getConnection(url,id,pass);//iv�� ����ϱ����� Connection ����
		}catch(Exception e) {
			
		}
	}
	
	//�����ͺ��̽��� �ѻ���� ȸ�������� �������ִ� �޼ҵ�
	public void insertMember(MemberBean mbean) {
		try{
			getCon();
			//3.���� �� ���� �غ�.
			String sql = "insert into member values(?,?,?,?,?,?,?,?)";
			//4.������ ����ϵ��� ����.
			PreparedStatement pstmt = con.prepareStatement(sql);
			//5.?�� �°� �����͸� ����.
			pstmt.setString(1,mbean.getId());
			pstmt.setString(2,mbean.getPass1());
			pstmt.setString(3,mbean.getEmail());
			pstmt.setString(4,mbean.getTel());
			pstmt.setString(5,mbean.getHobby());
			pstmt.setString(6,mbean.getJob());
			pstmt.setString(7,mbean.getAge());
			pstmt.setString(8,mbean.getInfo());
			//6.����Ŭ���� ������ �����Ͻÿ�.
			pstmt.executeUpdate();//insert, update, delete �� ����ϴ� �޼���  
			
			//7.�ڿ��ݳ�
			con.close();
		}catch(Exception e){
			e.getStackTrace();
		}
	}
	
	//��� ȸ���� ������ �������ִ� �޼ҵ� 
	public Vector<MemberBean> allSelectMember(){
		//�������̷� �����͸� ����
		Vector<MemberBean> v = new Vector();
		try {
			//Ŀ�ؼ� ���� 
			getCon();
			//�����غ� 
			String sql = "select * from member";
			//������ ��������ִ� ��ü ����
			pstmt = con.prepareStatement(sql);
			//������ �����Ų ����� �����ؼ� �޾���.(����Ŭ ���̺��� �˻��� ����� �ڹٰ�ü�� ����.)
			rs = pstmt.executeQuery();
			
			while(rs.next()) {//����� ������ ��ŭ���� �ݺ����� �����ڴٴ� �ǹ�
				MemberBean bean = new MemberBean();
				bean.setId(rs.getString(1));
				bean.setPass1(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setTel(rs.getString(4));
				bean.setHobby(rs.getString(5));
				bean.setJob(rs.getString(6));
				bean.setAge(rs.getString(7));
				bean.setInfo(rs.getString(8));
				
				//��Ű¡�� MemberBean Ŭ������ ���Ϳ� ����.
				v.add(bean);//0�������� ������� ����
				
			}
			con.close();//�ڿ��ݳ�
		}catch(Exception e) {
			
		}
		//�� ����� ���͸� ����
		return v;
	}
	
	
	
	
}
