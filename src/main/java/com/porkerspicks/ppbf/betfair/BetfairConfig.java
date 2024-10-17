package com.porkerspicks.ppbf.betfair;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:config/prod/betfairApi-prod.yml")
@ConfigurationProperties("betfair")
@Getter
@Setter
public class BetfairConfig {

	private String sessionId;
	private String applicationKey;
	private String authUrl;
	private String apiUrl = null;
	private String contentType = null;
	private String encoding = null;
	private int timeOut = 10000;
	private boolean debug = false;
	private String username;
	private String password;
	private KeyStore keyStore;

	@Getter
	@Setter
	public static class KeyStore {
		private String path;
		private String password;
	}

	public String getKeyStorePath() {
		return keyStore.getPath();
	}

	public String getKeyStorePassword() {
		return keyStore.getPassword();
	}
}