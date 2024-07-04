package dao;

/*==========================================================*
 *「try-with-resources 文」を使用しているため、データベースは自動的にクローズ処理される。
 *※一つ以上のリソースを宣言するtry文。文の終わりで各リソースを確実に閉じることができる。
 *PreparedStatement用に、「?」の入ったSQLを定義しておく。
 *ConnectionとPreparedStatementはtryの後ろの括弧に入っていて閉じられる。
 *ResultSetオブジェクトは、このオブジェクトを生成したStatementオブジェクトが閉じられるとき、
 *再実行されるとき、あるいは一連の複数の結果から次の結果を取り出すために使われるときに、自動的に閉じられる。
 *Statementがtry-with-resouces構文で閉じられているので、結果的にResultSetも閉じられる。
 *従ってこのDAOでは以下の記述は不要。
 *} finally {
 *	  if (rs != null) {
 *	    rs.close();
 *	  }
 *	  if (st != null) {
 *	    st.close();
 *	  }
 *	  if (con != null) {
 *	    con.close();
 *	  }
 *	}
 *==========================================================*/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import bean.School;
import bean.Subject;


public class SubjectDAO extends DAO {

	//科目を削除
	public void deleteSubject(Subject subjectBean) throws Exception {
//		public void deleteSubject(String subCd) throws Exception {
		//科目を削除するSQL
//		String sql = "delete  from subject where SUB_CD=?";
		String sql = "delete  from subject where sub_cd=? and school_cd=?";

		//SQL実行の準備
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(sql);) {

			//パラメータをSQLにセット
			st.setString(1, subjectBean.getSubCd());
			st.setString(2, subjectBean.getSchoolCd());
			//SQL文を表示
			System.out.println(st.toString());

			//SQL実行
			st.executeUpdate();

		}
	}

	//科目情報を更新
	public int updateSubject(Subject subjectBean) throws Exception {
		//科目を更新するSQL
//		String sql = "update subject set SCHOOL_CD=?, SUB_CD=?, SUB_NAME=? where SUB_CD=?";
		String sql = "update subject set SCHOOL_CD=?, SUB_CD=?, SUB_NAME=? where SUB_CD=? and school_cd=?";
		int result = 0;

		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(sql);) {
			//パラメータをSQLにセット
			st.setString(1, subjectBean.getSchoolCd());
			st.setString(2, subjectBean.getSubCd());
			st.setString(3, subjectBean.getSubName());
			st.setString(4, subjectBean.getSubCd());
			st.setString(5, subjectBean.getSchoolCd());
			//SQL文を表示
			System.out.println(st.toString());

			//SQL実行（変更した行数が戻る）
			result = st.executeUpdate();
		}
		System.out.println(result);
		return result;
	}

	//科目IDから科目情報を取得
	public Subject getSubjectBean(String subCd, School school) throws Exception {
		//科目情報を初期化
		Subject subjectBean = new Subject();
		//科目を取得するSQL
		String sql = "select SCHOOL_CD, SUB_CD, SUB_NAME from subject where SUB_CD=? and school_cd=?";

		//SQL実行の準備
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(sql);) {
			//パラメータをSQLにセット
			st.setString(1, subCd);
			st.setString(2, school.getSchoolCd());
			//SQL文を表示
			System.out.println(st.toString());

			//SQL実行
			ResultSet rs = st.executeQuery();
			//取得した行数を繰り返す
			while (rs.next()) {
				//取得した値を科目Beanにセット
				subjectBean.setSchoolCd("SCHOOL_CD");
				subjectBean.setSubCd("sub_cd");
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
				PreparedStatement st = con.prepareStatement(sql);) {
			//パラメータをSQLにセット
			st.setString(1, subjectBean.getSchoolCd());
			st.setString(2, subjectBean.getSubCd());
			st.setString(3, subjectBean.getSubName());
			//SQL文を表示
			System.out.println(st.toString());

			//SQL実行
			st.executeUpdate();

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
	public ArrayList<Subject> getAllSubject(School school) throws Exception {

		//ArrayListの初期化
		ArrayList<Subject> subjectList = new ArrayList<Subject>();

		//科目を取得するSQL
		String sql = "select SCHOOL_CD, SUB_CD, SUB_NAME from subject where school_cd=? order by sub_cd";

		//SQL実行の準備
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(sql);) {
			//パラメータをSQLにセット
			st.setString(1, school.getSchoolCd());
			//SQL文を表示
			System.out.println(st.toString());

			//SQL実行
			ResultSet rs = st.executeQuery();

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
