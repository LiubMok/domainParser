package ua.edu.ucu.apps.domainParser.WebScraping;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebCrawlerUCU {

    private Queue<String> queueURLS;
    private List<String> visitedURLS;

    private static HashMap<String, String> foundData;

    public WebCrawlerUCU() {
        queueURLS = new LinkedList<>();
        visitedURLS = new ArrayList<>();
    }

    public static boolean checkAndAdd(String feature, String url){
        if (url.contains(feature) && foundData.get(feature) == null){
            foundData.put(feature, url);
            return true;
        }
        return false;
    }

    private int addFeature(int breakpoint, Matcher matcher) {
        while(matcher.find()){
            String currentURL = matcher.group();

            try {
                if (!visitedURLS.contains(currentURL)) {
                    visitedURLS.add(currentURL);

                    ArrayList<String> features = new ArrayList<>(List.of("facebook", "twitter",
                            "logo", "icon", "linkedin", "youtube", "instagram", "wiki"));

                    for (String feature: features) {
                        if (checkAndAdd(feature, currentURL))
                            break;
                    }

                    queueURLS.add(currentURL);

                }
            } catch (Exception ignored){

            }

            if(breakpoint == 0){
                break;
            }
            breakpoint--;
        }
        return breakpoint;
    }

    public void crawl(String rootURL, int breakpoint) {
        queueURLS.add(rootURL);
        visitedURLS.add(rootURL);

        while(!queueURLS.isEmpty()){

            String s = queueURLS.remove();
            StringBuilder basicHTML = new StringBuilder();
            try{

                URL url = new URL(s);
                BufferedReader scanner = new BufferedReader(new InputStreamReader(url.openStream()));
                String inputLine = scanner.readLine();

                while(inputLine  != null){
                    basicHTML.append(inputLine);

                    inputLine = scanner.readLine();
                }
                scanner.close();
            } catch (Exception ignored){

            }

            String regexPattern = "(www|http:|https:)+[^\s]+[\\w]";
            Pattern pattern = Pattern.compile(regexPattern);
            Matcher matcher = pattern.matcher(basicHTML.toString());

            breakpoint = addFeature(breakpoint, matcher);

            if(breakpoint == 0){
                break;
            }
        }
    }

    public static void main(String[] args) {
        WebCrawlerUCU crawler = new WebCrawlerUCU();

        foundData = new HashMap<>();
        String root = "https://github.com";
        foundData.put("facebook", null);
        foundData.put("twitter", null);
        foundData.put("logo", null);
        foundData.put("icon", null);
        foundData.put("linkedin", null);
        foundData.put("youtube", null);
        foundData.put("instagram", null);

        crawler.crawl(root, 1000);


        System.out.println(foundData);
    }
}
