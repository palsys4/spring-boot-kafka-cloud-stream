package com.example.cloudstream.spring.boot.cloud.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@SpringBootApplication
public class Application {

	Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	class User {
		private String userName;

		public User(String userName) {
			this.userName = userName;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}
	}

	@Bean
	public Supplier<Flux<Long>> producer(){
		return () -> Flux.interval(Duration.ofSeconds(1)).log();
	}

	@Bean
	public Function<Flux<Long>,Flux<Long>> processor(){
		return (lNumb)-> lNumb.map(n -> n*n) ;
	}

	@Bean
	public Consumer<Long> consumer(){
		return (l)-> logger.info("squared number = " + l);
	}

}
