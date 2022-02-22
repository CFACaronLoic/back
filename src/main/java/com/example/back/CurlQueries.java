package com.example.back;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.json.JSONObject;
import org.json.JSONException;

public class CurlQueries {

    private static String host = "localhost:9200";
    
    private static String Query(String[] command) {
		ProcessBuilder process = new ProcessBuilder(command); 
		Process p;
        String result;
		try
		{
			p = process.start();
			BufferedReader reader =  new BufferedReader(new InputStreamReader(p.getInputStream()));
			StringBuilder builder = new StringBuilder();
			String line = null;
			while ( (line = reader.readLine()) != null) {
					builder.append(line);
					builder.append(System.getProperty("line.separator"));
				}
			return result = builder.toString();
		}
		catch (IOException e)
		{   
            System.out.print("error");
			e.printStackTrace();
            return null;
		}
    }

    public static String SimpleSearch(String search) {
        String[] command =  {"curl","-X GET", host + "/_search?pretty", "-H", "\"Content-Type:application/json\"", "-d", "\"{\"\"query\"\":{\"\"query_string\"\":{\"\"query\"\":\"\"" + search + "\"\"}}}"};
        return Query(command);
    }

    public static String FieldSearch(String field, String search) {
        String[] command =  {"curl","-X GET", host + "/_search?pretty", "-H", "\"Content-Type:application/json\"", "-d", "\"{\"\"query\"\":{\"\"match\"\":{\"\""+ field + "\"\":\"\"" + search + "\"\"}}}"};
        return Query(command);
    }

    public static String GetAll(int size) {
        String[] command =  {"curl","-X GET", host + "/_search?pretty", "-H", "\"Content-Type:application/json\"", "-d", "\"{\"\"sort\"\":[{\"\"download_count\"\":{\"\"order\"\":\"\"desc\"\"}}],\"\"size\"\":" + size + "}"};
        return Query(command);
    }

    public static String GetRegex(String field, String regex) {
        String[] command =  {"curl","-X GET", host + "/_search?pretty", "-H", "\"Content-Type:application/json\"", "-d", "\"{\"\"query\"\":{\"\"regexp\"\":{\"\""+ field + "\"\":{\"\"value\"\":\"\"" + regex + "\"\",\"\"flags\"\":\"\"ALL\"\"}}}}"};
        return Query(command);
    }
}
