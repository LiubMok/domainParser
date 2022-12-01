package ua.edu.ucu.apps.domainParser.Model.Stratedy;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import ua.edu.ucu.apps.domainParser.Model.DomainData;

public class PDLStrategy implements IStrategy{

    private final String PDL_URL = "https://api.peopledatalabs.com/v5/company/search";
    private final String KEY_HEADER_NAME = "X-API-Key";
    private final String KEY_HEADER_VALUE = "5258a3c26c6d9d4c50ede99acef2aa1607e897bd33ea68600c6bf552c3fa6752";
    private final String SQL_TEMPLATE = "SELECT NAME FROM COMPANY WHERE WEBSITE='%s'";
    private final RestTemplate restTemplate = new RestTemplate();


    @Override
    public DomainData parseInputDomain(DomainData domain, String link) {
        String url = PDL_URL + "?sql=" + String.format(SQL_TEMPLATE, domain);

        HttpHeaders headers = new HttpHeaders();
        headers.set(KEY_HEADER_NAME, KEY_HEADER_VALUE);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        return domain;
    }
}
