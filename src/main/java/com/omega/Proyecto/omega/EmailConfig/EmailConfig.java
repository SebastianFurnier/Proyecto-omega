package com.omega.Proyecto.omega.EmailConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.io.*;
import java.util.Properties;

@Configuration
@PropertySource("classpath:email.properties")
public class EmailConfig {

    private Properties getMailProperties(){
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.put("mail.smtp.ssl.trust", "*");
        return properties;
    }

    private String getString(String text) throws FileNotFoundException {
        File credentials = new File("./credentials.txt");
        FileReader fr = new FileReader(credentials);
        BufferedReader br = new BufferedReader(fr);
        try {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.contains(text))
                    return line.substring(line.indexOf("=") + 1);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Bean
    public JavaMailSender javaMailSender() throws FileNotFoundException {
        JavaMailSenderImpl mailSender =
                new JavaMailSenderImpl();
        mailSender.setJavaMailProperties(getMailProperties());
        mailSender.setUsername(getString("email="));
        mailSender.setPassword(getString("email_password="));
        return mailSender;
    }

    @Bean
    public ResourceLoader resourceLoader(){
        return new DefaultResourceLoader();
    }
}
