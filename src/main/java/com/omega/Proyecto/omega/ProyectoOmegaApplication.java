package com.omega.Proyecto.omega;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;

@SpringBootApplication
public class ProyectoOmegaApplication {

	public static void main(String[] args) throws FileNotFoundException {
		File credentials = new File("./credentials.txt");
		FileReader fr = new FileReader(credentials);
		BufferedReader br = new BufferedReader(fr);
		try {
			String line;

			while ((line = br.readLine()) != null) {
				if (line.contains("key=")) {
					System.out.println(line.substring(line.indexOf("=") + 1));
				}
			}
		}catch (IOException e){
			e.printStackTrace();
		}
		SpringApplication.run(ProyectoOmegaApplication.class, args);
	}

}
