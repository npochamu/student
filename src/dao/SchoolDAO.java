package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.School;

public class SchoolDAO extends DAO {

    // 番号に基づいて学校情報を取得するメソッド
    public School get(String cd) throws Exception {

        School school = null;
        String sql = "select * from school where school_cd = ?";

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, cd);
            System.out.println(st.toString());
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                school = new School();
                school.setSchoolCd(rs.getString("school_cd"));
                school.setSchoolName(rs.getString("school_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // エラーのスタックトレースを出力
        }

    	        // 仮の学校情報を設定
//        if (school == null) {
//            school = new School();
//            school.setSchoolCd("111");
//            school.setSchoolName("大原長野");
//        }

        return school;
    }
}
