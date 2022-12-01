package ua.edu.ucu.apps.domainParser.Model.Stratedy;

import lombok.Getter;
import lombok.Setter;
import ua.edu.ucu.apps.domainParser.Model.DomainData;

public abstract class BaseStrategy implements IStrategy{
    @Getter
    @Setter
    protected IStrategy next;

}
