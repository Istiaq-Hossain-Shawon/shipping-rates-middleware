package com.middleware.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import com.middleware.api.config.util.SSLUtils;
import com.middleware.api.model.User;
import com.middleware.api.repository.UserRepository;
import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
 
@SpringBootApplication
public class ShippingRateMiddlewareApplication {

	@Autowired
	private UserRepository repository;

	@PostConstruct
    public void initUsers() {
        List<User> users = Stream.of(
                new User(101, "user1", "123456", "user1@gmail.com"),
                new User(102, "user2", "654321", "user2@gmail.com")	                
        ).collect(Collectors.toList());
        repository.saveAll(users);
    }
	
	public static void main(String[] args) {
		SpringApplication.run(ShippingRateMiddlewareApplication.class, args);
	}
	@Bean
	public RestTemplate restTemplate() throws KeyManagementException, NoSuchAlgorithmException {
		SSLUtils.turnOffSslChecking();
	    return new RestTemplate();
	}


}
