package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.ClassNum;
import bean.School;

public class ClassNumDAO extends DAO {

    // 学校に関連するクラス番号を取得するメソッド
    public List<ClassNum> filter(School school) throws Exception {
        List<ClassNum> classNumList = new ArrayList<>();
        String sql = "select SCHOOL_CD, CLASS_NO, CLASS_NAME from CLASS_NUM where SCHOOL_CD = ?";

        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, school.getSchoolCd());

            System.out.println(pstmt.toString());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                ClassNum classNum = new ClassNum();
                classNum.setSchoolCd(rs.getString("SCHOOL_CD"));
                classNum.setClassNo(rs.getString("CLASS_NO"));
                classNum.setClassName(rs.getString("CLASS_NAME"));
                classNumList.add(classNum);
            }
        }
        return classNumList;
    }
}

//public class ClassNumDAO extends DAO {
//
//	// 学校に関連するクラス番号を取得するメソッド
//	public List<String> filter(School school) throws Exception {
//	    List<String> classNumList = new ArrayList<>();
//	    String sql = "select class_no from CLASS_NUM where SCHOOL_CD = ?";
//
//	    try (Connection con = getConnection();
//	        PreparedStatement pstmt = con.prepareStatement(sql)) {
//	        pstmt.setString(1, school.getSchoolCd());
//
//	        System.out.println(pstmt.toString());
//	        ResultSet rs = pstmt.executeQuery();
//
//	        while (rs.next()) {
//	            classNumList.add(rs.getString("CLASS_NUM"));
//	        }
//	    }
//	    return classNumList;
//	}
//}
