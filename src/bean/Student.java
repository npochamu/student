package bean;

import java.io.Serializable;

public class Student implements Serializable{
	private String student_no;
	private String student_name;
	private String ent_year;
	private boolean is_attend;
	private String school_cd;
	private String stu_seibetu;
	private String student_kana;
	private String class_no;

	public String getStudent_No(){
		return student_no;
	}
	public void setStudent_No(String student_no){
		this.student_no = student_no;
	}

	public String getStudent_Name(){
		return student_name;
	}
	public void setStudent_Name(String student_name){
		this.student_name = student_name;
	}

	public String getEnt_Year(){
		return ent_year;
	}
	public void setEnt_Year(String ent_year){
		this.ent_year = ent_year;
	}

	public boolean getIs_Attend(){
		return is_attend;
	}
	public void setIs_Attend(boolean is_attend){
		this.is_attend = is_attend;
	}

	public String getSchool_Cd(){
		return school_cd;
	}
	public void setSchool_Cd(String school_cd){
		this.school_cd = school_cd;
	}

	public String getStu_Seibetu(){
		return stu_seibetu;
	}
	public void setStu_Seibetu(String stu_seibetu){
		this.stu_seibetu = stu_seibetu;
	}

	public String getStudent_Kana(){
		return student_kana;
	}
	public void setStudent_Kana(String student_kana){
		this.student_kana = student_kana;
	}

	public String getClass_No(){
		return class_no;
	}
	public void setClass_No(String class_no){
		this.class_no = class_no;
	}
}