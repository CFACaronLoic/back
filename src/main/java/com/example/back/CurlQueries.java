package com.example.back;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CurlQueries {

    private String host = "localhost:9200";
    private String indice = "books";
    
    private String Query(String[] command) {
      //  String[] command = {"curl","-GET", host + "/" + indice + "/_search?pretty"};
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

    public String SimpleSearch(String search) {
        String[] command =  {"curl","-GET", host + "/" + indice + "/_search?pretty"};

        return Query(command);
    }
}
