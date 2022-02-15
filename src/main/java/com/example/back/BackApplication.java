package com.example.back;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackApplication.class, args);
		String[] command = {"curl","-GET","localhost:9200/books/_search?pretty"};
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
				System.out.print(result);
	
		}
		catch (IOException e)
		{   System.out.print("error");
			e.printStackTrace();
		}
	}

}
