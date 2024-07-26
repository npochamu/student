package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Student;

public class StudentsDAO extends DAO {

	private String baseSql = "select * from student where ";

	/**
	 * Product表からキーワードを検索し、該当する商品のListを返却する。
	 * @param keyword
	 * @return list<Product>
	 * @throws Exception
	 */

	/**
	 * Product表へデータを挿入する。
	 * @param product
	 * @return
	 * @throws Exception
	 */
	public int insert(Student product) throws Exception {
		// ここから
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"insert into student values(?, ?, ?, ?, ?, ?, ?, ?)");
		st.setString(3, product.getEnt_Year());
		st.setString(1, product.getStudent_No());
		st.setString(2, product.getStudent_Name());
		st.setString(7, product.getStu_Seibetu());
		st.setString(4, product.getClass_No());
		st.setString(8, product.getStudent_Kana());
		st.setString(6, product.getSchool_Cd());
		st.setBoolean(5, product.getIs_Attend());
		int line=st.executeUpdate();

		st.close();
		con.close();
		// ここまで
		return line;
	}

	public List<Student> all() throws Exception {
		// ここから

		List<Student> list=new ArrayList<>();

		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
				"select * from student order by ENT_YEAR desc, is_attend desc");
		ResultSet rs=st.executeQuery();

		while (rs.next()){
			Student p=new Student();
			p.setEnt_Year(rs.getString("ent_year"));
			p.setStudent_No(rs.getString("student_no"));
			p.setSchool_Cd(rs.getString("school_cd"));
			p.setStudent_Name(rs.getString("student_name"));
			p.setStudent_Kana(rs.getString("student_kana"));
			p.setClass_No(rs.getString("class_no"));
			p.setStu_Seibetu(rs.getString("stu_seibetu"));
			p.setIs_Attend(rs.getBoolean("is_attend"));
			list.add(p);
		}

		st.close();
		con.close();

		return list;
		// ここまで
	}

	public int UpdateInsert(Student product) throws Exception {
		// ここから
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			    "UPDATE student SET student_name = ?, ent_year = ?, is_attend = ?, stu_seibetu = ?, student_kana = ?, class_no = ? WHERE student_no = ?");
		st.setString(2, product.getEnt_Year());
		st.setString(7, product.getStudent_No());
		st.setString(1, product.getStudent_Name());
		st.setString(4, product.getStu_Seibetu());
		st.setString(6, product.getClass_No());
		st.setString(5, product.getStudent_Kana());
		st.setBoolean(3, product.getIs_Attend());
		int line=st.executeUpdate();

		st.close();
		con.close();
		// ここまで
		return line;
	}
	public int delete(Student id) throws Exception {

		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
				"DELETE FROM student WHERE student_no = ?");
		st.setString(1, id.getStudent_No());
		int count=st.executeUpdate();

		st.close();
		con.close();

		return count;
	}



	 // 学生番号が既に存在するかどうかを確認するメソッド
	 public boolean isStudentNumberExists(String studentNo) throws Exception {
       Connection con = getConnection();
       PreparedStatement st = con.prepareStatement("SELECT COUNT(*) FROM student WHERE student_no = ?");
       st.setString(1, studentNo);
       ResultSet rs = st.executeQuery();

       boolean exists = false;
       if (rs.next()) {
           exists = rs.getInt(1) > 0;
       }

       rs.close();
       st.close();
       con.close();

       return exists;
   }

	 public List<Student> search(String ent_year, String class_no, boolean isAttend) throws Exception {
			// ここから
			List<Student> list=new ArrayList<>();

			String condition = "ENT_YEAR = ? AND class_no = ?";
			String order = " order by ENT_YEAR desc";
			String conditionis = "";
			String ordee2 =", is_attend desc";

			if(isAttend){
				conditionis = "AND is_attend = true";
				ordee2 = "";
			}else{
				conditionis = "AND is_attend = false";
				ordee2 = "";
			}

			Connection con=getConnection();
			PreparedStatement st=con.prepareStatement(
					baseSql + condition + conditionis + order + ordee2);
			st.setString(1,  ent_year);
			st.setString(2,  class_no);
			ResultSet rs=st.executeQuery();
			while (rs.next()){
				Student p=new Student();
				p.setStudent_No(rs.getString("student_no"));
				p.setStudent_Kana(rs.getString("student_kana"));
				p.setStudent_Name(rs.getString("student_name"));
				p.setEnt_Year(rs.getString("ent_year"));
				p.setClass_No(rs.getString("class_no"));
				p.setStudent_No(rs.getString("student_no"));
				p.setIs_Attend(rs.getBoolean("is_attend"));
				p.setStu_Seibetu(rs.getString("stu_seibetu"));
				p.setSchool_Cd(rs.getString("school_cd"));
				list.add(p);


			}

			st.close();
			con.close();
			return list;
			// ここまで
		}
	 public List<Student> search(String ent_year, boolean isAttend) throws Exception {
			// ここから
			List<Student> list=new ArrayList<>();

			String condition = "ENT_YEAR = ?";
			String order = " order by ENT_YEAR desc";
			String conditionis = "";
			String ordee2 =", is_attend desc";



			if(isAttend){
				conditionis = "AND is_attend = true";
				ordee2 = "";
			}else{
				conditionis = "AND is_attend = false";
				ordee2 = "";
			}

			Connection con=getConnection();
			PreparedStatement st=con.prepareStatement(
					baseSql + condition + conditionis + order + ordee2);
			st.setString(1, ent_year);
			ResultSet rs=st.executeQuery();
			while (rs.next()){
				Student p=new Student();
				p.setStudent_No(rs.getString("student_no"));
				p.setStudent_Kana(rs.getString("student_kana"));
				p.setStudent_Name(rs.getString("student_name"));
				p.setEnt_Year(rs.getString("ent_year"));
				p.setClass_No(rs.getString("class_no"));
				p.setStudent_No(rs.getString("student_no"));
				p.setIs_Attend(rs.getBoolean("is_attend"));
				p.setStu_Seibetu(rs.getString("stu_seibetu"));
				p.setSchool_Cd(rs.getString("school_cd"));
				list.add(p);


			}

			st.close();
			con.close();
			return list;
			// ここまで
		}
	 public List<Student> search(boolean isAttend) throws Exception {
			// ここから
			List<Student> list=new ArrayList<>();

			String conditionis = "";
			String ordee2 =", is_attend desc";
			String order = " order by ENT_YEAR desc";

			if(isAttend){
				conditionis = "is_attend = true";
				ordee2 = "";
			}else{
				conditionis = "is_attend = false";
				ordee2 = "";
			}

			Connection con=getConnection();
			PreparedStatement st=con.prepareStatement(
					baseSql + conditionis + order + ordee2);
			ResultSet rs=st.executeQuery();
			while (rs.next()){
				Student p=new Student();
				p.setStudent_No(rs.getString("student_no"));
				p.setStudent_Kana(rs.getString("student_kana"));
				p.setStudent_Name(rs.getString("student_name"));
				p.setEnt_Year(rs.getString("ent_year"));
				p.setClass_No(rs.getString("class_no"));
				p.setStudent_No(rs.getString("student_no"));
				p.setIs_Attend(rs.getBoolean("is_attend"));
				p.setStu_Seibetu(rs.getString("stu_seibetu"));
				p.setSchool_Cd(rs.getString("school_cd"));
				list.add(p);


			}

			st.close();
			con.close();
			return list;
			// ここまで
		}

}