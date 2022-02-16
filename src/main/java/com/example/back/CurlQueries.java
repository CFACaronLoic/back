package com.example.back;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CurlQueries {

    private static String host = "localhost:9200";
    private static String indice = "books";
    
    private static String Query(String[] command) {
		ProcessBuilder process = new ProcessBuilder(command); 
		Process p;
		try
		{
			p = process.start();
			BufferedReader reader =  new BufferedReader(new InputStreamReader(p.getInputStream()));
			StringBuilder builder = new StringBuilder();
			String line = null;
			while ( (line = reader.readLine()) != null) {
					builder.append(line);
					builder.append(System.getProperty("line.separator"));
                    builder.append("\n");
				}
			String result = builder.toString();
			return result;
		}
		catch (IOException e)
		{   
            System.out.print("error");
			e.printStackTrace();
            return null;
		}
    }

    public static String SimpleSearch(String search) {
        //-d' {  "from": 5,  "size": 20,  "query": { "match": { "user.id": "kimchy" }  }}
        String[] command =  {"curl","-GET", host + "/" + indice + "/_search?pretty"};
        return Query(command);
    }

    public static String GetAll() {
        String[] command =  {"curl","-X GET", host + "/_search?pretty", "-H", "\"Content-Type:application/json\"", "-d", "\"{\"\"sort\"\":[{\"\"download_count\"\":{\"\"order\"\":\"\"desc\"\"}}],\"\"size\"\":10}"};
        return Query(command);
    }


    public static String RegexSearch(String regex) {
        return "pl";
    }
}
