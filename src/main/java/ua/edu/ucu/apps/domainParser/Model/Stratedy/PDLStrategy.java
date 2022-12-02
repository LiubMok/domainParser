package ua.edu.ucu.apps.domainParser.Model.Stratedy;

import lombok.*;
import ua.edu.ucu.apps.domainParser.Classes.DomainData;


import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class PDLStrategy extends BaseStrategy {
    private JSONObject jsonObject;
    DomainData domain;
    private String link;

    @Override
    @SneakyThrows
    public DomainData parseInputDomain(DomainData domain_, String link) {
        this.link = link;
        this.domain = domain_;
        String query = URLEncoder.encode(String.format("SELECT NAME FROM COMPANY WHERE WEBSITE='%s'", link), StandardCharsets.UTF_8);
        URL url = new URL("https://api.peopledatalabs.com/v5/company/search?sql=" + query);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        String API_KEY = "5258a3c26c6d9d4c50ede99acef2aa1607e897bd33ea68600c6bf552c3fa6752";
        connection.setRequestProperty("X-Api-Key", API_KEY);
        connection.connect();
        String text = null;
        try {
            text = new Scanner(connection.getInputStream()).useDelimiter("\\Z").next();
        } catch (IOException e) {
            if (domain.isFull() || getNext() == null) {
                return domain;
            } else {
                return getNext().parseInputDomain(domain, link);
            }
        }
        jsonObject = new JSONObject(text);
        if (domain.getName() == null) {
            domain.setName(getNameToDomain());
            System.out.println(true);
        }
        if (domain.getTwitter() == null) {
            domain.setTwitter(getTwitterToDomain());
            System.out.println(true);
        }
        if (domain.getFacebook() == null) {
            domain.setFacebook(getFacebookToDomain());
            System.out.println(true);
        }
        if (domain.getEmployees() == null) {
            domain.setEmployees(getEmployeesNumberToDomain());
            System.out.println(true);
        }
//        if(domain.getAddress() == null){
//            domain.setAddress(String.valueOf(jsonObject.getJSONArray("data").getJSONObject(0).getString("name")));
//        }
        if (domain.isFull() || getNext() == null) {
            return domain;
        } else {
            return getNext().parseInputDomain(domain, link);
        }
    }

    private String getNameToDomain() {
        try {
            return jsonObject.getJSONArray("data").getJSONObject(0).getString("name");
        } catch (Exception ignored) {
            return null;
        }
    }

    private String getTwitterToDomain() {
        try {
            return jsonObject.getJSONArray("data").getJSONObject(0).getString("twitter_url");
        } catch (Exception ignored) {
            return null;
        }
    }

    private String getFacebookToDomain() {
        try {
            return jsonObject.getJSONArray("data").getJSONObject(0).getString("facebook_url");
        } catch (Exception ignored) {
            return null;
        }

    }

    private String getEmployeesNumberToDomain() {
        try {
            return String.valueOf(jsonObject.getJSONArray("data").getJSONObject(0).getInt("employee_count"));
        } catch (Exception ignored) {
            return null;
        }
    }

}

