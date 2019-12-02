package com.drato.graduationthesis;

import com.drato.graduationthesis.utils.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class GraduationThesisApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraduationThesisApplication.class, args);
	}

}
