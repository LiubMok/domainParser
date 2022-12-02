package ua.edu.ucu.apps.domainParser.Model.Stratedy;

import ua.edu.ucu.apps.domainParser.Classes.DomainData;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;


//public class BrandfetchStrategy extends BaseStrategy{
//
//    @Override
//    public DomainData parseInputDomain(DomainData domain, String link) {
//        return null;
//    }
//}

public class BrandfetchStrategy extends BaseStrategy {

    private JSONObject json;
    private JSONArray links;
    private JSONArray logos;


    public void getInfo(String link) {
        try {
            Unirest.setTimeouts(0, 0);
            HttpResponse<String> response = Unirest.get("https://api.brandfetch.io/v2/brands/" + link).header("Authorization", "Bearer E9wSIqq25UAZNmQA4KAwH8O6EIzIU6o9ILES1Pu1TW4=")
                    .asString();
            json = new JSONObject(response.getBody());
            links = json.getJSONArray("links");
            logos = json.getJSONArray("logos");
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public DomainData parseInputDomain(DomainData domain, String link) {
        getInfo(link);
        if (domain.getName() == null) {
            domain.setName(String.valueOf(getName()));
            System.out.println(true);
        }
        if (domain.getTwitter() == null) {
            domain.setTwitter(getTwitter());
            System.out.println(true);
        }
        if (domain.getFacebook() == null) {
            domain.setFacebook(getFacebook());
            System.out.println(true);
        }
        if (domain.getLogo() == null) {
            domain.setLogo(getLogo());
            System.out.println(true);
        }
        if (domain.getIcon() == null) {
            domain.setIcon(getIcon());
            System.out.println(true);
        }
        if (domain.isFull() || getNext() == null) {
            return domain;
        } else {
            return getNext().parseInputDomain(domain, link);
        }
    }

    public String getName() {
        try {
            return json.getString("name");
        } catch (JSONException ignored) {
        }
        return null;
    }

    public String getTwitter() {
        for (int i = 0; i < links.length(); i++) {
            try {
                JSONObject curLink = links.getJSONObject(i);
                if (Objects.equals(curLink.getString("name"), "twitter")) {
                    return curLink.getString("url");
                }
            } catch (JSONException ignored) {
            }
        }
        return null;
    }

    public String getFacebook() {
        for (int i = 0; i < links.length(); i++) {
            try {
                JSONObject curLink = links.getJSONObject(i);
                if (Objects.equals(curLink.getString("name"), "facebook")) {
                    return curLink.getString("url");
                }
            } catch (JSONException ignored) {
            }
        }
        return null;
    }

    public String getLogo() {
        for (int i = 0; i < logos.length(); i++) {
            JSONObject current = logos.getJSONObject(i);
            try {
                if (Objects.equals(current.getString("type"), "logo")) {
                    return current.getJSONArray("formats").getJSONObject(0).getString("src");
                }

            } catch (JSONException ignored) {
            }
        }
        return null;
    }

    public String getIcon() {
        for (int i = 0; i < logos.length(); i++) {
            JSONObject current = logos.getJSONObject(i);
            try {
                if (Objects.equals(current.getString("type"), "icon")) {
                    return current.getJSONArray("formats").getJSONObject(0).getString("src");
                }

            } catch (JSONException ignored) {
            }
        }
        return null;
    }


}
