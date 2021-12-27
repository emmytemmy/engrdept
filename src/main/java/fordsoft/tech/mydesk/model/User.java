/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fordsoft.tech.mydesk.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
     
    private Long id;
     
    private String fullname;
    
    private LocalDate dob;
    
    private LocalDate dofa;
    
    private LocalDate doc;
    
    private LocalDate dopa;
    
    private String ranks;
    
    private String gradelevel;
    
    private String qualification;
    
    private String lga;
    
    private String psn;
    
    private String gender;
    
    private String cadre;
    
    private String location;
    
    private String email;
    
    private String password;
    
    private LocalDateTime datecreated;

    

    @Override
    public String toString() {
        return "fordsoft.tech.mydesk.model.User[ id=" + id + " ]";
    }
    
}
