package krupinski.arkadiusz.home;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HomeApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeApplication.class, args);

		// String idForEncode = "bcrypt";
		// Map encoders = new HashMap<>();
		// encoders.put(idForEncode, new BCryptPasswordEncoder());
		// // encoders.put("noop", NoOpPasswordEncoder.getInstance());
		// encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
		// encoders.put("scrypt", new SCryptPasswordEncoder());
		// // encoders.put("sha256", new StandardPasswordEncoder());

		// PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder(idForEncode,
		// encoders);
		// passwordEncoder.toString();

		// UserDetails user =
		// User.withDefaultPasswordEncoder().username("user").password("password").roles("user").build();
		// System.out.println(user.getPassword());
	}

	@RequestMapping("/resource")
	// @CrossOrigin(origins= "http://localhost:4200", maxAge = 4800)
	@CrossOrigin(origins = "http://localhost:4200", maxAge = 4800)
	public Map<String, Object> home() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("id", UUID.randomUUID().toString());
		model.put("content", "Hello World");
		return model;
	}

	@RequestMapping("/user")
	@CrossOrigin(origins = "http://localhost:4200", maxAge = 4800)
	public Principal user(Principal user) {
		return user;
	}

	// @Configuration
	// @Order(SecurityProperties.BASIC_AUTH_ORDER)
	// protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	// 	@Override
	// 	protected void configure(HttpSecurity http) throws Exception {
	// 		http.cors().and().authorizeRequests().antMatchers("/index.html", "/", "/home", "/login").permitAll()
	// 				.anyRequest().authenticated().and().csrf();
	// 	}
	// }
}

	//    
	//   