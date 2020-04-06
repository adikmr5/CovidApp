package com.example.android.covidapp;

  public class Patient {
      private String name;
      private String illness;
      private String travel;
      private String phycontact;
      private Integer age;
      private Long phone;
      private String gender;
      private String Hospitalname;

      public Patient() {
      }


      public String getName() {

          return name;
      }



      public void setName(String name) {

          this.name = name;
      }

      public String getIllness() {

          return illness;
      }

      public void setIllness(String illness)
      {
          this.illness = illness;
      }

      public String getTravel()
      {
          return travel;
      }

      public void setTravel(String travel) {
          this.travel = travel;
      }

      public String getPhycontact()
      {
          return phycontact;
      }

      public void setPhycontact(String phycontact) {

          this.phycontact = phycontact;
      }

      public Integer getAge() {

          return age;
      }

      public void setAge(Integer age)
      {
          this.age = age;
      }

      public Long getPhone() {

          return phone;
      }

      public void setPhone(Long phone) {
          this.phone = phone;
      }

      public String getGender() {
          return gender;
      }

      public void setGender(String gender) {
          this.gender = gender;
      }

      public String getHospitalname() {
          return Hospitalname;
      }

      public void setHospitalname(String hospitalname) {
          Hospitalname = hospitalname;
      }
  }
