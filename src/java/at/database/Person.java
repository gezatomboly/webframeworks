package at.database;
// Generated 09.01.2018 14:02:06 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Person generated by hbm2java
 */
public class Person  implements java.io.Serializable {


     private Integer personPk;
     private String username;
     private String password;
     private String role;
     private String firstname;
     private String lastname;
     private Set personCourseMemberships = new HashSet(0);

    public Person() {
    }

	
    public Person(String username, String password, String role, String firstname, String lastname) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.firstname = firstname;
        this.lastname = lastname;
    }
    public Person(String username, String password, String role, String firstname, String lastname, Set personCourseMemberships) {
       this.username = username;
       this.password = password;
       this.role = role;
       this.firstname = firstname;
       this.lastname = lastname;
       this.personCourseMemberships = personCourseMemberships;
    }
   
    public Integer getPersonPk() {
        return this.personPk;
    }
    
    public void setPersonPk(Integer personPk) {
        this.personPk = personPk;
    }
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRole() {
        return this.role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    public String getFirstname() {
        return this.firstname;
    }
    
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return this.lastname;
    }
    
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public Set getPersonCourseMemberships() {
        return this.personCourseMemberships;
    }
    
    public void setPersonCourseMemberships(Set personCourseMemberships) {
        this.personCourseMemberships = personCourseMemberships;
    }




}

