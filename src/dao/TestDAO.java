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

	public List<Test> search2(Test keyword) throws Exception {
		// ここから

		List<Test> list=new ArrayList<>();

		Connection con=getConnection();

		System.out.println(keyword.getEnt_Year() + keyword.getClass_Num() + keyword.getSubject_Cd() + keyword.getTest_No());

		PreparedStatement st=con.prepareStatement(
				"select s.is_attend, sub.sub_cd as subject_cd, sub.sub_name, s.school_cd, "
				+ "s.ent_year, s.class_no, s.student_no, s.student_name, t.test_no, t.point from student as s "
				+ "left join test as t on s.student_no = t.student_no and t.subject_cd = ? and t.test_no = ? "
				+ "left join subject sub on sub.sub_cd = ? "
				+ "where s.ent_year = ? and s.class_no = ? and s.school_cd = ? "
				+ "order by s.student_no");
		st.setString(1, keyword.getSubject_Cd());
		st.setInt(2, keyword.getTest_No());
		st.setString(3, keyword.getSubject_Cd());
		st.setString(4,  keyword.getEnt_Year());
		st.setString(5,  keyword.getClass_Num());
		st.setString(6, "111");
		System.out.println("最初"+st);
		ResultSet rs=st.executeQuery();
//		SQL表示
		System.out.println(st);
		while (rs.next()){
		    Test p=new Test();
		    p.setEnt_Year(rs.getString("ent_year"));
		    p.setClass_Num(rs.getString("class_no"));
		    p.setStudent_No(rs.getString("student_no"));
		    p.setStudent_Name(rs.getString("student_name"));
			p.setPoint(rs.getInt("point"));
			p.setSubject_Name(rs.getString("sub_name"));
			list.add(p);


		}
		System.out.print(list);

		st.close();
		con.close();

		return list;
		// ここまで
	}


	public int Insert2(List<Test> points) throws Exception {

		List<Test> list = new ArrayList<>();
		Connection con=getConnection();

//    テスト情報を登録
		PreparedStatement st = con.prepareStatement(
				"MERGE INTO TEST KEY(STUDENT_NO, SUBJECT_CD, SCHOOL_CD, TEST_NO)"
				+ "VALUES (?, ?, ?, ?, ?, ?)");

	//	受け取ったリストの要素数分、テスト登録を繰り返す
		int line = 0;
	    for (int i = 0; i < points.size(); i++) {
	        Test test = points.get(i);
	        st.setString(1, test.getStudent_No());
	        st.setString(2, test.getSubject_Cd());
	        st.setString(3, test.getSchool_Cd());
	        st.setInt(4, test.getTest_No());
	        st.setInt(5, test.getPoint());
	        st.setString(6, test.getClass_Num());

	//   	SQL繰り返した結果を成功行数に追加
	        line+=st.executeUpdate();
	    }

	        st.close();
	        con.close();
	        return line;
	    }


	public List<Test> all2() throws Exception {
		// ここから

		List<Test> list=new ArrayList<>();

		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
				"select * from subject ");
		ResultSet rs=st.executeQuery();

		while (rs.next()){
			Test p=new Test();
			p.setSubject_Name(rs.getString("sub_name"));
			p.setSubject_Cd(rs.getString("sub_cd"));
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