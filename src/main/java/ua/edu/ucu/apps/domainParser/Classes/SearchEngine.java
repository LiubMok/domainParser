package ua.edu.ucu.apps.domainParser.Classes;

import lombok.Setter;
import org.springframework.stereotype.Component;
import ua.edu.ucu.apps.domainParser.Model.Stratedy.*;

@Setter
@Component
public class SearchEngine {

    public DomainData searchInfoAboutCompany(String inputDomain) {
        DomainData domainData = new DomainData();
        domainData.setDomain(inputDomain);

        BaseStrategy pdlStrategy= new PDLStrategy();
        BaseStrategy regexStrategy = new RegexStrategy();
        BaseStrategy brandfetchStrategy = new BrandfetchStrategy();
        BaseStrategy googlePlacesStrategy = new GooglePlacesStrategy();

//        googlePlacesStrategy.setNext(brandfetchStrategy);
        brandfetchStrategy.setNext(pdlStrategy);
//        regexStrategy.setNext(pdlStrategy);

//        googlePlacesStrategy.parseInputDomain(domainData, inputDomain);
        brandfetchStrategy.parseInputDomain(domainData, inputDomain);
        return domainData;
    }

}
