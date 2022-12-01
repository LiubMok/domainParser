package ua.edu.ucu.apps.domainParser.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.ucu.apps.domainParser.Model.DomainData;
import ua.edu.ucu.apps.domainParser.Repository.DataFinderRepository;

@Service
public class DataService {
    DataFinderRepository dataBase;
    @Autowired
    public  DataService(DataFinderRepository database){
        this.dataBase = database;
    }
    public DomainData findOneByName(String inputDomain) {
//        TODO find an domain using domain string
        return new DomainData();
    }

    public void save(DomainData domain) {
//TODO add domain to database
    }
}
