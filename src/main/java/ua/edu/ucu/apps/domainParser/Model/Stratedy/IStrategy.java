package ua.edu.ucu.apps.domainParser.Model.Stratedy;

import ua.edu.ucu.apps.domainParser.Classes.DomainData;

public interface IStrategy {
    DomainData parseInputDomain(DomainData domain, String link);
}
