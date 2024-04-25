package hellojpa;

import javax.persistence.*;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Member{
   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }


   public Period getWorkperiod() {
      return workperiod;
   }

   public void setWorkperiod(Period workperiod) {
      this.workperiod = workperiod;
   }

   public Address getHomeAddress() {
      return homeAddress;
   }

   public void setHomeAddress(Address homeAddress) {
      this.homeAddress = homeAddress;
   }

   @Id
   @GeneratedValue
   private Long id;

   private String name;

   @Embedded
   private Period workperiod;
   @Embedded
   private Address homeAddress;


    public Member(){

   }
}
