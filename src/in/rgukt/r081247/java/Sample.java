package in.rgukt.r081247.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class Sample {
    public static class CountryPopulaion {
        public String country;
        public long population;
        public CountryPopulaion(String country, long population) {
            this.country = country;
            this.population = population;
        }
        public String toString() {
            return country + "," + population;
        }
        public String getCountry() {
            return country;
        }
        public void setCountry(String country) {
            this.country = country;
        }
        public long getPopulation() {
            return population;
        }
        public void setPopulation(long population) {
            this.population = population;
        }
    }
    public static void main(String[] args) throws Exception {
        List<CountryPopulaion> list = new ArrayList<>();
        String region = "Europe";
        String keyword = "d";
        String url = "https://jsonmock.hackerrank.com/api/countries/search?region=" + region + "&name=" + keyword;

        String response = sendGET(url);
        System.out.println("Response: " + response);
        Pattern pattern = Pattern.compile("\"total_pages\":\\d+");
        Matcher matcher = pattern.matcher(response);
        int totalPages = 0;
        if(matcher.find()) {
            String totpages = matcher.group();
            System.out.println(totpages);
            totalPages = Integer.parseInt(totpages.split(":")[1]);
        }
        System.out.println("Pages: " + totalPages);

        for(int i = 1; i <= totalPages; i++) {
            String fullUrl = url + "&page=" + i;
            response = sendGET(fullUrl);
            System.out.println("Page: " + i + ", Response: " + response);
            Pattern cpattern = Pattern.compile("\"name\":\"");
            Matcher cmatcher = cpattern.matcher(response);
            Pattern ppattern = Pattern.compile("\"population\":\\d+");
            Matcher pmatcher = ppattern.matcher(response);
            while (cmatcher.find() && pmatcher.find()) {
                int start  = cmatcher.end();
                //System.out.println(start);
                //System.out.println(cmatcher.group());
                int end = response.indexOf(",", start) - 1;
                String countryName = response.substring(start, end);
                System.out.println(countryName);

                String spopulation = pmatcher.group();
                long population = Integer.parseInt(spopulation.split(":")[1]);
                System.out.println(population);
                list.add(new CountryPopulaion(countryName, population));
            }
        }

        List<CountryPopulaion> sortedList = list.stream().sorted(Comparator.comparingLong(CountryPopulaion::getPopulation).thenComparing(CountryPopulaion::getCountry))
                .collect(Collectors.toList());
        System.out.println(sortedList);
    }

    private static String sendGET(String url) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        //con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
       // System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            return response.toString();
        } else {
           return null;
        }

    }
}
