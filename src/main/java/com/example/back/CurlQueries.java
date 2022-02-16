package com.example.back;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;

public class CurlQueries {
    
    private static String Query(String command) {
        try {
            Request request = Request.Get("http://localhost:9200/_search?pretty");
            request.bodyString(command,ContentType.APPLICATION_JSON);
            request.setHeader("Content-Type", "application/json");
            HttpResponse httpResponse = request.execute().returnResponse();
            System.out.println(httpResponse.getStatusLine());
            if (httpResponse.getEntity() != null) {
	            String html;
                html = EntityUtils.toString(httpResponse.getEntity());
	            return(html);
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        } 
        return null;
    }

    public static String SimpleSearch(String search) {
        return Query(" {  \"size\": 1000 }");
    }

    public static String GetAll() {
        return Query(" {  \"size\": 1000 }");
    }

    public static String RegexSearch(String regex) {
        return "pl";
    }
}
