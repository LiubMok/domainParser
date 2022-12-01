package ua.edu.ucu.apps.domainParser.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.edu.ucu.apps.domainParser.Classes.SearchEngine;
import ua.edu.ucu.apps.domainParser.Model.DomainData;
import ua.edu.ucu.apps.domainParser.Services.DataService;

@RestController
public class DataController {
    private final DataService dataBase;
    private final SearchEngine searchEngine;

    @Autowired
    public DataController(DataService database, SearchEngine searchEngine) {
        this.dataBase = database;
        this.searchEngine = searchEngine;
    }

    @GetMapping
    public DomainData getRequest(String inputDomain) {
        DomainData domain = dataBase.findOneByName(inputDomain);
        if (domain != null){
            return domain;
        }

        ;
        domain = searchEngine.searchInfoAboutCompany(inputDomain);
        addDomainData(domain);
        return domain;
    }

    @PostMapping
    public void addDomainData(@RequestBody DomainData domain){
        dataBase.save(domain);
    }
}
