package ua.edu.ucu.apps.domainParser.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.edu.ucu.apps.domainParser.Classes.DomainData;

import java.util.List;

@Repository
public interface DataFinderRepository extends JpaRepository<DomainData, Integer> {
    DomainData getDomainDataByDomain(String domain);

}
