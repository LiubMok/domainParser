package ua.edu.ucu.apps.domainParser.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ua.edu.ucu.apps.domainParser.Classes.DomainData;
import ua.edu.ucu.apps.domainParser.Repository.DataFinderRepository;

import java.util.List;

@Component
public class DataService {
    private final DataFinderRepository dataBase;

    @Autowired
    public DataService(DataFinderRepository database) {
        this.dataBase = database;
    }

    public DomainData findOneByDomain(String inputDomain) {
        return dataBase.getDomainDataByDomain(inputDomain);
    }

    public List<DomainData> getAllDomains() {
        return dataBase.findAll();
    }

    public void save(DomainData domain) {
        dataBase.save(domain);
    }
}
