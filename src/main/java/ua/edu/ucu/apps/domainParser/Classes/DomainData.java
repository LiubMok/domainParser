package ua.edu.ucu.apps.domainParser.Classes;

import lombok.*;
import lombok.Getter;
import lombok.SneakyThrows;

import javax.persistence.*;
import java.lang.reflect.Method;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "domains")
@Entity
@Getter
@Setter
public class DomainData {
    @Id
    @GeneratedValue
    private int id;
//    @Column(name = "domain")
    private String domain;
    private String name;
    private String twitter;
    private String facebook;
    private String logo;
    private String icon;
    private String employees;
    private String address;

    @SneakyThrows
    public boolean isFull() {
        Method[] allMethods = DomainData.class.getDeclaredMethods();
        for (Method m : allMethods) {
            System.out.println(m.getName());
            if (m.invoke(this) == null || (boolean) m.invoke(this)) {
                return false;
            }
        }
        return true;
    }

    public void changeNull(){
        if(this.getIcon() == null){
            this.setIcon("No information about this field");
        }
        if(this.getLogo() == null){
            this.setLogo("No information about this field");
        }
        if(this.getAddress() == null){
            this.setAddress("No information about this field");
        }
    }

}