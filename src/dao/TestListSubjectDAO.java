package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.TestListSubject;

public class TestListSubjectDAO extends DAO {

	//全科目×全学生（退学含む）
	public List<TestListSubject> selectSubjectScore() throws Exception {

		List<TestListSubject> list = new ArrayList<>();
		Connection con=getConnection();

		PreparedStatement st = con.prepareStatement(
				"select s.is_attend, su.sub_cd as subject_cd, su.sub_name, s.ent_year, s.class_no, s.student_no, s.student_name, s.student_kana, s.stu_seibetu, coalesce(cast(subt.test_1 as char),'-')as test_1, coalesce(cast(subt.test_2 as char),'-') as test_2 from student as s"
				+ " cross join subject as su"
				+ " left join (select student_no, subject_cd, max(case when test_no='1' then point end) as test_1, max(case when test_no='2' then point end) as test_2 from test"
				+ " group by student_no, subject_cd)  as subt on s.student_no=subt.student_no"
				+ " and su.sub_cd=subt.subject_cd"
				+ " order by s.student_no, su.sub_cd");
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			TestListSubject tls=new TestListSubject();
				tls.setAttend(rs.getBoolean("is_attend"));
				tls.setSubCd(rs.getString("subject_cd"));
				tls.setSubName(rs.getString("sub_name"));
				tls.setEntYear(rs.getString("ent_year"));
				tls.setClassNo(rs.getString("class_no"));
				tls.setStudentNo(rs.getString("student_no"));
				tls.setStudentName(rs.getString("student_name"));
				tls.setKana(rs.getString("student_kana"));
				tls.setSeibetu(rs.getString("stu_seibetu"));
				tls.setTest_1(rs.getString("test_1"));
				tls.setTest_2(rs.getString("test_2"));
			list.add(tls);
		}

		st.close();
		con.close();

		return list;
	}


	//指定クラス×指定科目×全学生（在籍に限る）
    public List<TestListSubject> selectClassSubjectScore(String classNo, String subCd, String entYear) throws Exception {
        List<TestListSubject> list = new ArrayList<>();
        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
                "select s.is_attend, su.sub_cd as subject_cd, su.sub_name, s.ent_year, s.class_no, s.student_no, s.student_name, s.student_kana, s.stu_seibetu,"
				+ " coalesce(cast(subt.test_1 as char), '-') as test_1, coalesce(cast(subt.test_2 as char), '-') as test_2"
				+ " from student as s"
				+ " cross join (select * from subject where sub_cd = ?) as su"  // 科目フィルタリング
				+ " left join ("
				+ " select student_no, subject_cd, max(case when test_no = '1' then point end) as test_1, max(case when test_no = '2' then point end) as test_2"
				+ " from test where subject_cd = ? "  // 科目フィルタリング
				+ " group by student_no, subject_cd"
				+ ") as subt on s.student_no = subt.student_no and su.sub_cd = subt.subject_cd"
				+ " where s.ent_year = ?"  // 入学年度フィルタリング
				+ " and s.class_no = ?"  // クラス番号フィルタリング
				+ " and s.is_attend = true"  // 在籍者に限定
				+ " order by s.student_no, su.sub_cd");

        st.setString(1, subCd);
        st.setString(2, subCd);
        st.setString(3, entYear);
        st.setString(4, classNo);

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            TestListSubject tls = new TestListSubject();
            tls.setAttend(rs.getBoolean("is_attend"));
            tls.setSubCd(rs.getString("subject_cd"));
            tls.setSubName(rs.getString("sub_name"));
            tls.setEntYear(rs.getString("ent_year"));
            tls.setClassNo(rs.getString("class_no"));
            tls.setStudentNo(rs.getString("student_no"));
            tls.setStudentName(rs.getString("student_name"));
            tls.setKana(rs.getString("student_kana"));
            tls.setSeibetu(rs.getString("stu_seibetu"));
            tls.setTest_1(rs.getString("test_1"));
            tls.setTest_2(rs.getString("test_2"));
            list.add(tls);
        }

        st.close();
        con.close();

        return list;
    }
}
