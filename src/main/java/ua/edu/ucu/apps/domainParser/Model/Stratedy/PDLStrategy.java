package ua.edu.ucu.apps.domainParser.Model.Stratedy;

import lombok.SneakyThrows;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import ua.edu.ucu.apps.domainParser.Classes.PDLLogic.PDLDomainsList;
import ua.edu.ucu.apps.domainParser.Classes.DomainData;


import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class PDLStrategy extends BaseStrategy{
    @Override
    @SneakyThrows
    public DomainData parseInputDomain(DomainData domain, String link) {
        String query = URLEncoder.encode(String.format("SELECT NAME FROM COMPANY WHERE WEBSITE='%s'", link), StandardCharsets.UTF_8);
        System.out.println(query);
        URL url = new URL("https://api.peopledatalabs.com/v5/company/search?sql=" + query);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        String API_KEY = "5258a3c26c6d9d4c50ede99acef2aa1607e897bd33ea68600c6bf552c3fa6752";
        connection.setRequestProperty("X-Api-Key", API_KEY);
        connection.connect();
        String text = new Scanner(connection.getInputStream()).useDelimiter("\\Z").next();
        JSONObject jsonObject = new JSONObject(text);
//        System.out.println(jsonObject);
//        System.out.println(jsonObject.getJSONArray("data").getJSONObject(0).getInt("employee_count"));
        if (domain.getName() == null) {
            domain.setName(String.valueOf(jsonObject.getJSONArray("data").getJSONObject(0).getString("name")));
        }
        if(domain.getTwitter() == null){
            domain.setTwitter(String.valueOf(jsonObject.getJSONArray("data").getJSONObject(0).getString("twitter_url")));
        }
        if(domain.getFacebook() == null){
            domain.setFacebook(String.valueOf(jsonObject.getJSONArray("data").getJSONObject(0).getString("facebook_url")));
        }
        if(domain.getEmployees() == null){
            domain.setEmployees(String.valueOf(jsonObject.getJSONArray("data").getJSONObject(0).getInt("employee_count")));
        }
//        if(domain.getAddress() == null){
//            domain.setAddress(String.valueOf(jsonObject.getJSONArray("data").getJSONObject(0).getString("name")));
//        }
//        if(domain.isFull() || getNext() == null){
        if(getNext() == null){
            return domain;
        }
        else{
            return getNext().parseInputDomain(domain, link);
        }
    }

//    public static void main(String[] args) {
//        DomainData domain = new DomainData();
//        domain.setDomain("epam.com");
//        PDLStrategy pdlStrategy = new PDLStrategy();
//        pdlStrategy.parseInputDomain(domain, "epam.com");
//    }
}
