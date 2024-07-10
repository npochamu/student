package bean;

import java.io.Serializable;

public class TestListSubject implements Serializable {

    private String schoolCd;
	private boolean isAttend;
	private String subCd;
	private String subName;
	private String entYear;
	private String classNo;
    private String studentNo;
    private String studentName;
    private String kana;
    private String seibetu;
    private String test_1;
    private String test_2;

    public String getSchoolCd() {
    	return schoolCd;
    }
    public void setSchoolCd(String schoolCd) {
    	this.schoolCd = schoolCd;
	}

    public boolean isAttend() {
    	return isAttend;
    }
    public void setAttend(boolean isAttend) {
    	this.isAttend = isAttend;
    }

    public String getSubCd() {
    	return subCd;
    }
    public void setSubCd(String subCd) {
    	this.subCd = subCd;
    }
    public String getSubName() {
    	return subName;
    }
    public void setSubName(String subName) {
    	this.subName = subName;
    }
    public String getEntYear() {
    	return entYear;
    }
    public void setEntYear(String entYear) {
    	this.entYear = entYear;
    }
    public String getClassNo() {
    	return classNo;
    }
    public void setClassNo(String classNo) {
    	this.classNo = classNo;
    }
    public String getStudentNo() {
    	return studentNo;
    }
    public void setStudentNo(String studentNo) {
    	this.studentNo = studentNo;
    }
    public String getStudentName() {
    	return studentName;
    }
    public void setStudentName(String studentName) {
    	this.studentName = studentName;
    }
    public String getKana() {
    	return kana;
    }
    public void setKana(String kana) {
    	this.kana = kana;
    }
    public String getSeibetu() {
    	return seibetu;
    }
    public void setSeibetu(String seibetu) {
    	this.seibetu = seibetu;
    }
    public String getTest_1() {
        return test_1;
    }
    public void setTest_1(String test_1) {
        this.test_1 = test_1;
    }
    public String getTest_2() {
        return test_2;
    }
    public void setTest_2(String test_2) {
        this.test_2 = test_2;
    }
}
