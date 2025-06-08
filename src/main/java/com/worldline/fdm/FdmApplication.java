package com.worldline.fdm;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.worldline.fdm.api.CrazySupplierApi;
import okhttp3.OkHttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Configuration
@EnableScheduling
public class FdmApplication {

	public static void main(String[] args) {
		SpringApplication.run(FdmApplication.class, args);
	}


	@Bean
	CrazySupplierApi crazySupplierApi(){
		return new CrazySupplierApi();
	}

	@Bean
	ObjectMapper getObjectMapperBean() {
		return JsonMapper
				.builder()
				.disable(MapperFeature.REQUIRE_HANDLERS_FOR_JAVA8_TIMES)
				.build();
	}

	@Bean
	OkHttpClient getHttpClient() {
		return new OkHttpClient()
				.newBuilder()
				.build();
	}
}
