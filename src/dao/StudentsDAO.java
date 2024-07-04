package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Student;

public class StudentsDAO extends DAO {

	/**
	 * Product表からキーワードを検索し、該当する商品のListを返却する。
	 * @param keyword
	 * @return list<Product>
	 * @throws Exception
	 */
	public List<Student> search(Student keyword) throws Exception {
		// ここから

		List<Student> list=new ArrayList<>();

		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
				"select * from student where ENT_YEAR = ? AND class_no = ? AND is_attend = ?");
		st.setString(1,  keyword.getEnt_Year());
		st.setString(2,  keyword.getClass_No());
		st.setBoolean(3, keyword.getIs_Attend());
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
				"select * from student ");
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

}