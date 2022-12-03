package ua.edu.ucu.apps.domainParser.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.edu.ucu.apps.domainParser.Classes.DomainData;
import ua.edu.ucu.apps.domainParser.Classes.DomainInput;
import ua.edu.ucu.apps.domainParser.Classes.SearchEngine;
import ua.edu.ucu.apps.domainParser.Services.DataService;

import java.util.List;

@Controller
@RequestMapping("/api")
public class HomeController {

    private final DataService dataService;
    private final SearchEngine searchEngine;

    public HomeController(@Autowired DataService database, @Autowired SearchEngine searchEngine) {
        this.dataService = database;
        this.searchEngine = searchEngine;
    }

    @GetMapping("/")
    public String welcome() {
        return "index.html";
    }

    @GetMapping("/find")
    @ResponseBody
    public DomainData getData(@RequestParam String domainString) {
        System.out.println(domainString);
        DomainInput inputDomain = new DomainInput(domainString);
        Logger logger = LoggerFactory.getLogger(DataController.class);
        DomainData domain = dataService.findOneByDomain(inputDomain.getName());
        if (domain != null) {
            return domain;
        }
        domain = searchEngine.searchInfoAboutCompany(inputDomain.getName());
        domain.changeNull();
        dataService.save(domain);
        return domain;
    }

}