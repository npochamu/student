package dao;

import bean.School;

public class SchoolDAO extends DAO {

    // 番号に基づいて学校情報を取得するメソッド
    public School get(String no) throws Exception {
        School school = null;
//        String sql = "select * from school where school_cd = ?";
//
//        try (Connection con = getConnection();
//             PreparedStatement pstmt = con.prepareStatement(sql)) {
//            pstmt.setString(1, no);
//            System.out.println(pstmt.toString());
//            ResultSet rs = pstmt.executeQuery();
//
//            if (rs.next()) {
//                school = new School(rs.getString("school_cd"), rs.getString("school_name"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace(); // エラーのスタックトレースを出力
//        }

        // 仮の学校情報を設定
        if (school == null) {
            school = new School();
            school.setSchoolCd("111");
            school.setSchoolName("大原長野");
        }

        return school;
    }
}
