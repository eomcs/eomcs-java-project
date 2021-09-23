package com.eomcs.pms.domain;

import java.sql.Date;
import com.eomcs.csv.CsvValue;

public class Member implements CsvValue {
  private int no;
  private String name;
  private String email;
  private String password;
  private String photo;
  private String tel;
  private Date registeredDate;

  @Override
  public String toString() {
    return "Member [no=" + no + ", name=" + name + ", email=" + email + ", password=" + password
        + ", photo=" + photo + ", tel=" + tel + ", registeredDate=" + registeredDate + "]";
  }

  // 다음 메서드는 CsvValue 규칙에 따라 정의한 메서드다.
  @Override
  public String toCsvString() {
    return String.format("%d,%s,%s,%s,%s,%s,%s",
        this.getNo(),
        this.getName(),
        this.getEmail(),
        this.getPassword(),
        this.getPhoto(),
        this.getTel(),
        this.getRegisteredDate());
  }

  // 다음 메서드는 파라미터로 받은 CSV 문자열에서 값을 추출하여 
  // Board 객체의 각 필드에 저장한다.
  @Override
  public void loadCsv(String csv) {
    String[] values = csv.split(",");

    // CSV 문자열에서 추출한 값을 객체의 필드에 저장한다.
    this.setNo(Integer.valueOf(values[0]));
    this.setName(values[1]);
    this.setEmail(values[2]);
    this.setPassword(values[3]);
    this.setPhoto(values[4]);
    this.setTel(values[5]);
    this.setRegisteredDate(Date.valueOf(values[6]));
  }

  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getPhoto() {
    return photo;
  }
  public void setPhoto(String photo) {
    this.photo = photo;
  }
  public String getTel() {
    return tel;
  }
  public void setTel(String tel) {
    this.tel = tel;
  }
  public Date getRegisteredDate() {
    return registeredDate;
  }
  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }
}