package ua.edu.ucu.apps.domainParser.Controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.edu.ucu.apps.domainParser.Classes.DomainInput;
import ua.edu.ucu.apps.domainParser.Classes.SearchEngine;
import ua.edu.ucu.apps.domainParser.Classes.DomainData;
import ua.edu.ucu.apps.domainParser.Services.DataService;

import java.util.List;

@RestController
@RequestMapping(path = "/domainParser")
public class DataController {
    private final DataService dataService;
    private final SearchEngine searchEngine;


    public DataController(@Autowired DataService database, @Autowired SearchEngine searchEngine) {
        this.dataService = database;
        this.searchEngine = searchEngine;
    }

    @PostMapping(path = "/requestInfo")
    public DomainData getRequest(@RequestBody DomainInput  inputDomain) {
        Logger logger = LoggerFactory.getLogger(DataController.class);
        DomainData domain = dataService.findOneByDomain(inputDomain.getName());
        if (domain != null){
            return domain;
        }
        System.out.println(domain);
        domain = searchEngine.searchInfoAboutCompany(inputDomain.getName());
        domain.changeNull();
        dataService.save(domain);
        return domain;
    }

    @PostMapping(path = "/edit")
    public void editDomainData(@RequestBody DomainData domain){
        if(dataService.findOneByDomain(domain.getDomain()).getDomain().equals(domain.getName())){
            System.out.println("Hello world");
        }
        else {
            dataService.save(domain);
        }
    }

    @GetMapping(path = "/all")
//    @GetMapping
    public List<DomainData> getData() {
        List<DomainData> allDomains = dataService.getAllDomains();
        System.out.println(allDomains);
        return allDomains;
    }

    @GetMapping(path = "/hello")
    public String getHello(){return "Hello bitch"; }

}