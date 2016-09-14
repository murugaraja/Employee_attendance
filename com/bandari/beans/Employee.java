package com.bandari.beans;

public class Employee {
  String empName;
  String empId;
  String empPhone;
  String empEmail;
  String empSalary;
  String empAddress;
  String doj;
  String dob;
  String sex;
  String designation;
  String allotedleave;
  String present;
  int remainingLeave;
  int totalWorkedHours;
  int monthSalary;
  
  public int getMonthSalary() {
    return this.monthSalary;
  }
  
  public void setMonthSalary(int monthSalary) { this.monthSalary = monthSalary; }
  
  public String getAllotedleave() {
    return this.allotedleave;
  }
  
  public void setAllotedleave(String allotedleave) { this.allotedleave = allotedleave; }
  
  public String getDoj() {
    return this.doj;
  }
  
  public void setDoj(String doj) { this.doj = doj; }
  
  public String getDob() {
    return this.dob;
  }
  
  public void setDob(String dob) { this.dob = dob; }
  
  public String getEmpName() {
    return this.empName;
  }
  
  public void setEmpName(String empName) { this.empName = empName; }
  
  public String getEmpId() {
    return this.empId;
  }
  
  public void setEmpId(String empId) { this.empId = empId; }
  
  public String getEmpPhone() {
    return this.empPhone;
  }
  
  public void setEmpPhone(String empPhone) { this.empPhone = empPhone; }
  
  public String getEmpEmail() {
    return this.empEmail;
  }
  
  public void setEmpEmail(String empEmail) { this.empEmail = empEmail; }
  
  public String getEmpSalary() {
    return this.empSalary;
  }
  
  public void setEmpSalary(String empSalary) { this.empSalary = empSalary; }
  
  public String getEmpAddress() {
    return this.empAddress;
  }
  
  public void setEmpAddress(String empAddress) { this.empAddress = empAddress; }
  
  public String getSex() {
    return this.sex;
  }
  
  public void setSex(String sex) { this.sex = sex; }
  
  public String getDesignation() {
    return this.designation;
  }
  
  public void setDesignation(String designation) { this.designation = designation; }
  
  public String getPresent() {
    return this.present;
  }
  
  public void setPresent(String present) { this.present = present; }
  
  public int getRemainingLeave() {
    return this.remainingLeave;
  }
  
  public void setRemainingLeave(int remainingLeave) { this.remainingLeave = remainingLeave; }
  
  public int getTotalWorkedHours() {
    return this.totalWorkedHours;
  }
  
  public void setTotalWorkedHours(int totalWorkedHours) { this.totalWorkedHours = totalWorkedHours; }
}