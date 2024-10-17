package com.porkerspicks.ppbf.file;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("file")
@Getter
@Setter
public class FileStorageConfig {

	private String dateFormat;
	private String timeFormat;
	private String basePath;
}