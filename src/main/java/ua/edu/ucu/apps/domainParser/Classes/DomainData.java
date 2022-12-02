package ua.edu.ucu.apps.domainParser.Classes;

import lombok.*;
import lombok.Getter;
import lombok.SneakyThrows;

import javax.persistence.*;

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
        if (this.getName() == null) {
            return false;
        }
        if (this.getTwitter() == null) {
            return false;
        }
        if (this.getFacebook() == null) {
            return false;
        }
        if (this.getIcon() == null) {
            return false;
        }
        if (this.getLogo() == null) {
            return false;
        }
        if (this.getEmployees() == null) {
            return false;
        }
        if (this.getAddress() == null) {
            return false;
        }
        return true;
    }

    public void changeNull() {
        if (this.getName() == null) {
            this.setName("No information about this field");
        }
        if (this.getTwitter() == null) {
            this.setTwitter("No information about this field");
        }
        if (this.getFacebook() == null) {
            this.setFacebook("No information about this field");
        }
        if (this.getIcon() == null) {
            this.setIcon("No information about this field");
        }
        if (this.getLogo() == null) {
            this.setLogo("No information about this field");
        }
        if (this.getEmployees() == null) {
            this.setEmployees("No information about this field");
        }
        if (this.getAddress() == null) {
            this.setAddress("No information about this field");
        }
    }

}