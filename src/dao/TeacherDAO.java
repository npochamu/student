package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School;
import bean.Teacher;

public class TeacherDAO extends DAO {

    public Teacher get(String id) throws Exception {
    	Teacher teacher = null;
        String sql = "select * from teacher where teacher_id = ?";

        try (Connection con = getConnection();
            PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, id);

            System.out.println(st.toString());
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                teacher = new Teacher();
                teacher.setTeacherId(rs.getString("teacher_id"));
                teacher.setTeacherName(rs.getString("teacher_name"));

                // Schoolオブジェクトを設定
                String schoolCd = rs.getString("school_cd");
                School school = new School();
                school.setSchoolCd(schoolCd);
                teacher.setSchoolCd(school);
            }
        }
        return teacher;
    }



		public Teacher login(String id, String password) throws Exception {
			Teacher teacher = null;
		    String sql = "select * from teacher where teacher_id = ? and password = ?";

		    try (Connection con = getConnection();
		        PreparedStatement st = con.prepareStatement(sql)) {
		        st.setString(1, id);
		        st.setString(2, password);

		        // デバッグ用のSQL出力
		        System.out.println(st.toString());

		        ResultSet rs = st.executeQuery();

	            if (rs.next()) {
	                teacher = new Teacher();
	                teacher.setTeacherId(rs.getString("teacher_id"));
	                teacher.setTeacherName(rs.getString("teacher_name"));
	                teacher.setPassword(rs.getString("password"));

	                // Schoolオブジェクトを設定
	                String schoolCd = rs.getString("school_cd");
	                School school = new School();
	                school.setSchoolCd(schoolCd);
	                teacher.setSchoolCd(school);
	            }
		    }
		    return teacher;
		}
}
