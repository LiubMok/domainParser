package ua.edu.ucu.apps.domainParser.Model.Stratedy;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.PlaceDetailsRequest;
import com.google.maps.TextSearchRequest;
import com.google.maps.model.PlaceDetails;
import com.google.maps.model.PlacesSearchResponse;
import lombok.SneakyThrows;
import ua.edu.ucu.apps.domainParser.Classes.DomainData;

public class GooglePlacesStrategy extends BaseStrategy{
    @SneakyThrows
    public static void main(String[] args) {
//        TODO api keys can not be used, why? how to deal error?
        String API_KEY = "AIzaSyAGehSSsIW2AC7n0t89BPEsoS727lf3Vpg";
        String query = "ucu.edu.ua";
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(API_KEY)
                .build();
        PlacesSearchResponse placesRespose = new TextSearchRequest(context).query(query).await();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String address = placesRespose.results[0].formattedAddress;
        String placeId = placesRespose.results[0].placeId;

        PlaceDetails placeDetails = new PlaceDetailsRequest(context).placeId(placeId).await();
        //System.out.println(gson.toJson(placeDetails));
        System.out.format("Place ID %s\n",  placeId);
        System.out.format("Address %s\n",  address);

        context.shutdown();

    }

    @Override
    @SneakyThrows
    public DomainData parseInputDomain(DomainData domain, String link) {
        String API_KEY = "AIzaSyAGehSSsIW2AC7n0t89BPEsoS727lf3Vpg";
        String query = link;
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(API_KEY)
                .build();
        PlacesSearchResponse placesRespose = new TextSearchRequest(context).query(query).await();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String address = placesRespose.results[0].formattedAddress;
        String placeId = placesRespose.results[0].placeId;

        PlaceDetails placeDetails = new PlaceDetailsRequest(context).placeId(placeId).await();
        //System.out.println(gson.toJson(placeDetails));
        System.out.format("Place ID %s\n",  placeId);
        System.out.format("Address %s\n",  address);

        context.shutdown();
        if(getNext() == null){
            return domain;
        }
        else{
            return getNext().parseInputDomain(domain, link);
        }
    }
}