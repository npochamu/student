package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import bean.Subject;

public class SubjectDAO extends DAO {

	//科目を削除
	public void deleteSubject(String subCd) throws Exception {
		//科目を削除するSQL
		String sql = "delete  from subject where SUB_CD=?";

		//SQL実行の準備
		try (Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			//パラメータをSQLにセット
			pstmt.setString(1, subCd);
			//SQL文を表示
			System.out.println(pstmt.toString());

			//SQL実行
			pstmt.executeUpdate();

		}
	}

	//科目情報を更新
	public int updateSubject(Subject subjectBean) throws Exception {
		//科目を更新するSQL
		String sql = "update subject set SCHOOL_CD=?, SUB_CD=?, SUB_NAME=? where SUB_CD=?";
		int result = 0;

		try (Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			//パラメータをSQLにセット
			pstmt.setString(1, subjectBean.getSchoolCd());
			pstmt.setString(2, subjectBean.getSubCd());
			pstmt.setString(3, subjectBean.getSubName());
			pstmt.setString(4, subjectBean.getSubCd());
			//SQL文を表示
			System.out.println(pstmt.toString());

			//SQL実行（変更した行数が戻る）
			result = pstmt.executeUpdate();
		}
		System.out.println(result);
		return result;
	}

	//科目IDから科目情報を取得
	public Subject getSubjectBean(String subCd) throws Exception {
		//科目情報を初期化
		Subject subjectBean = new Subject();
		//科目を取得するSQL
		String sql = "select SCHOOL_CD, SUB_CD, SUB_NAME from subject where SUB_CD=?";

		//SQL実行の準備
		try (Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			//パラメータをSQLにセット
			pstmt.setString(1, subCd);
			//SQL文を表示
			System.out.println(pstmt.toString());

			//SQL実行
			ResultSet rs = pstmt.executeQuery();
			//取得した行数を繰り返す
			while (rs.next()) {
				//取得した値を科目Beanにセット
				subjectBean.setSchoolCd("111");
//				subjectBean.setSchoolCd("SCHOOL_CD");
				subjectBean.setSubCd(subCd);
				subjectBean.setSubName(rs.getString("SUB_NAME"));
			}
		}
		return subjectBean;
	}

	//リクエストから科目Beanの作成
	public Subject getSubjectBean(HttpServletRequest request) {

		//科目Beanの初期化
		Subject subjectBean = new Subject();

		//リクエストから科目Beanの作成
		subjectBean.setSchoolCd(request.getParameter("schoolCd"));
		subjectBean.setSubCd(request.getParameter("subCd"));
		subjectBean.setSubName(request.getParameter("subName"));

		//作成した科目Beanを戻す
		return subjectBean;
	}

	//科目を登録
	public void insertSubject(Subject subjectBean) throws Exception {

		//科目を登録するSQL
		String sql = "insert into subject(SCHOOL_CD, SUB_CD, SUB_NAME) values(?,?,?);";

		try (Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			//パラメータをSQLにセット
			pstmt.setString(1, subjectBean.getSchoolCd());
			pstmt.setString(2, subjectBean.getSubCd());
			pstmt.setString(3, subjectBean.getSubName());
			//SQL文を表示
			System.out.println(pstmt.toString());

			//SQL実行
			pstmt.executeUpdate();

//			★データベースでエラー発生
		} catch (SQLException e) {

//			科目コード重複エラー
				if (e.getSQLState().equals("23505")) {
					throw new SQLException("duplicateError", e);
				} else {
					throw new SQLException("dbError", e);
				}
		}
	}

	//全科目を取得
	public ArrayList<Subject> getAllSubject() throws Exception {

		//ArrayListの初期化
		ArrayList<Subject> subjectList = new ArrayList<Subject>();

		//科目を取得するSQL
		String sql = "select SCHOOL_CD, SUB_CD, SUB_NAME from subject order by sub_cd";

		//SQL実行の準備
		try (Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			//SQL文を表示
			System.out.println(pstmt.toString());

			//SQL実行
			ResultSet rs = pstmt.executeQuery();

			//取得した行数を繰り返す
			while (rs.next()) {
				//科目Beanの初期化
				Subject subjectBean = new Subject();
				//取得した値を科目Beanにセット
				subjectBean.setSchoolCd(rs.getString("SCHOOL_CD"));
				subjectBean.setSubCd(rs.getString("SUB_CD"));
				subjectBean.setSubName(rs.getString("SUB_NAME"));
				//リストに科目Beanの追加
				subjectList.add(subjectBean);
			}
		}
		return subjectList;
	}

}
