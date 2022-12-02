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
        BaseStrategy jsoupStrategy = new JsoupStrategy();
        BaseStrategy brandfetchStrategy = new BrandfetchStrategy();

//        pdlStrategy.setNext(jsoupStrategy);
//        jsoupStrategy.setNext(brandfetchStrategy);

        pdlStrategy.parseInputDomain(domainData, inputDomain);
        return domainData;
    }

}
