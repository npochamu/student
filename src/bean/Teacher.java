package bean;

import java.io.Serializable;

public class Teacher implements Serializable {

	private String teacherId;
	private String password;
	private String teacherName;
	private School schoolCd;

	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public School getSchoolCd() {
		return schoolCd;
	}
	public void setSchoolCd(School schoolCd) {
		this.schoolCd = schoolCd;
	}
}
