package ua.edu.ucu.apps.domainParser.Model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table
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

}