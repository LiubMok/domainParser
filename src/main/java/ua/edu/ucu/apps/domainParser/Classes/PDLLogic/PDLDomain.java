package ua.edu.ucu.apps.domainParser.Classes.PDLLogic;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PDLDomain {

    private String name;
    private String size;
    private String employee_count;
    private String facebook_url;
    private String twitter_url;
    private Location location;

    }
