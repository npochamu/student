package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Test;

public class TestDAO extends DAO{

	public List<Test> searchin(Test keyword) throws Exception {
		// ここから

		List<Test> list=new ArrayList<>();

		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
				"select a.SUBJECT_CD, a.STUDENT_NO, a.TEST_NO, a.POINT, b.SUB_NAME, c.STUDENT_NAME from TEST as a join SUBJECT as b on a.SUBJECT_CD = b.SUB_CD join STUDENT as c on a.STUDENT_NO = c.STUDENT_NO where a.STUDENT_NO = ? ");
		st.setString(1,  keyword.getStudent_No());
		ResultSet rs=st.executeQuery();

		while (rs.next()){
			Test p=new Test();
			p.setSubject_Cd(rs.getString("subject_cd"));
			p.setStudent_No(rs.getString("student_no"));
			p.setTest_No(rs.getInt("test_no"));
			p.setPoint(rs.getInt("point"));
			p.setSub_Name(rs.getString("sub_name"));
			p.setStudent_Name(rs.getString("student_name"));
			list.add(p);


		}

		st.close();
		con.close();

		return list;
		// ここまで
	}
}


