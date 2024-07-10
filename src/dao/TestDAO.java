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

	//学生番号から学生情報を取得
	public Test getTestBean(String stuNo) throws Exception {
		//試験情報を初期化
		Test testBean = new Test();
		//学生を取得するSQL
		String sql = "SELECT student_no, student_name FROM STUDENT where student_no=? ";

		//SQL実行の準備
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(sql);) {
			//パラメータをSQLにセット
			st.setString(1, stuNo);
			//SQL文を表示
			System.out.println(st.toString());

			//SQL実行
			ResultSet rs = st.executeQuery();
			//取得した行数を繰り返す
			while (rs.next()) {
				//取得した値を科目Beanにセット
				testBean.setStudent_No(rs.getString("student_no"));
				testBean.setStudent_Name(rs.getString("student_name"));
			}
		}
		return testBean;
	}

}


