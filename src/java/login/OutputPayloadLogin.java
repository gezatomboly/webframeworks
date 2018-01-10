/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;



import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gezatomboly
 */
@XmlRootElement
public class OutputPayloadLogin {
    
    private Integer userid;
    private String role;
    private String fehlerbeschreibung;
      

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFehlerbeschreibung() {
        return fehlerbeschreibung;
    }

    public void setFehlerbeschreibung(String fehlerbeschreibung) {
        this.fehlerbeschreibung = fehlerbeschreibung;
    }
    
    
    
   
    
   
    
}
