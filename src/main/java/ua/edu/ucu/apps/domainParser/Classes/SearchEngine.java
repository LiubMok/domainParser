package ua.edu.ucu.apps.domainParser.Classes;

import lombok.Setter;
import org.springframework.stereotype.Component;
import ua.edu.ucu.apps.domainParser.Model.DomainData;

@Setter
@Component
public class SearchEngine {

    public DomainData searchInfoAboutCompany(String inputDomain) {
//        domain = new NameStrategy().getData(new DomainData(), inputDomain);
//        return domain;
        return new DomainData();
    }

}
