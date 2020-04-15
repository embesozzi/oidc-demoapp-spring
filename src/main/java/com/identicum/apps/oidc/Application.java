package com.identicum.apps.oidc;

import javax.annotation.PostConstruct;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	@PostConstruct
	public void skipSSLValidation(){	
		javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier(){
			@Override
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		});
	}
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
