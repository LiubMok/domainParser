package ua.edu.ucu.apps.domainParser.Model.Stratedy;

import lombok.Getter;
import lombok.Setter;

public abstract class BaseStrategy implements IStrategy{
    @Getter
    @Setter
    protected IStrategy next;

}
