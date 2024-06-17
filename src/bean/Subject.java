package bean;

import java.io.Serializable;

public class Subject implements Serializable {

    private String schoolCd;
    private String subCd;
    private String subName;

    public String getSchoolCd() {
        return schoolCd;
    }
    public void setSchoolCd(String schoolCd) {
        this.schoolCd = schoolCd;
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
}
