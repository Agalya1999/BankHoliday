package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.databind.ObjectMapper;


public class BankHolidaysAPI {
    private Root allHols = null;

    public String getBankHolidaysData() throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet("https://www.gov.uk/bank-holidays.json");
            HttpResponse response = httpClient.execute(httpGet);


            BufferedReader in = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            ObjectMapper om = new ObjectMapper();
            allHols = om.readValue(content.toString(), Root.class);

            // Set region for events based on division
            setRegionForEvents(allHols.ew.events, Region.ENGLAND_AND_WALES);
            setRegionForEvents(allHols.scotland.events, Region.SCOTLAND);
            setRegionForEvents(allHols.ni.events, Region.NORTHERN_IRELAND);


            return content.toString();
        }
    }

    private void setRegionForEvents(Events events, Region region) {
        for (Event event : events) {
            event.region = region;
        }
    }
    public Root getAllHolidays() {
        return allHols;
    }
}
