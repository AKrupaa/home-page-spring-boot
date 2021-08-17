package krupinski.arkadiusz.home;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import krupinski.arkadiusz.home.models.CustomUser;
import krupinski.arkadiusz.home.models.CustomUserRole;
import krupinski.arkadiusz.home.models.RoleName;
import krupinski.arkadiusz.home.security.auth.request.AuthenticationRequest;
import krupinski.arkadiusz.home.security.auth.response.AuthenticationResponse;
import krupinski.arkadiusz.home.security.service.JwtUtil;
import krupinski.arkadiusz.home.security.service.MyUserDetailsService;
import krupinski.arkadiusz.home.services.CustomUserRoleService;
import krupinski.arkadiusz.home.services.CustomUserService;

@SpringBootApplication
@RestController
public class HomeApplication {

	private static final String Set = null;
	CustomUserService customUserService;
	CustomUserRoleService customUserServiceRoleService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	public HomeApplication(CustomUserService customUserService, CustomUserRoleService customUserServiceRoleService) {
		this.customUserService = customUserService;
		this.customUserServiceRoleService = customUserServiceRoleService;
	}

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
	@CrossOrigin(origins = "*", maxAge = 4800)
	public Map<String, Object> home() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("id", UUID.randomUUID().toString());
		model.put("content", "Hello World");
		return model;
	}

	@RequestMapping("/user")
	@CrossOrigin(origins = "*", maxAge = 4800)
	public String user() {

		CustomUser customUser = new CustomUser();
		customUser.setEmail("arkadiusz.krupinski15@gmail.com");
		customUser.setEnabled(true);
		customUser.setFirstName("Arkadiusz");
		;
		customUser.setLastName("Krupiński");
		customUser.setLogin("AKrupaa");
		customUser.setPassword("arkadiusz.krupinski15@gmail.com");
		Optional<CustomUserRole> userRole = customUserServiceRoleService.getUserRole(1);
		customUser.getUserRoles().add(userRole.get());
		customUserService.addCustomUser(customUser);
		// customUser.setUserRoles(userRole.get());

		return "Okej";
		// return user;
	}

	@PostMapping("/role")
	@CrossOrigin(origins = "*", maxAge = 4800)
	public String addRole(@RequestBody CustomUserRole role) {
		CustomUserRole customUserRole = new CustomUserRole();
		customUserRole.setRole(RoleName.ROLE_ADMIN);
		customUserServiceRoleService.addUserRole(customUserRole);
		// customUserServiceRoleService.getUserRole(0);
		// return
		// customUserServiceRoleService.getUserRole(0).orElseThrow(IllegalStateException::new);
		return "okey";
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getLogin(),
					authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getLogin());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	// @PostMapping("/user")
	// @CrossOrigin(origins = "http://localhost:4200", maxAge = 4800)
	// public String registerUser(@RequestBody @Validated CustomUser user) {
	// return user;
	// }

	// @Configuration
	// @Order(SecurityProperties.BASIC_AUTH_ORDER)
	// protected static class SecurityConfiguration extends
	// WebSecurityConfigurerAdapter {
	// @Override
	// protected void configure(HttpSecurity http) throws Exception {
	// http.cors().and().authorizeRequests().antMatchers("/index.html", "/",
	// "/home", "/login").permitAll()
	// .anyRequest().authenticated().and().csrf();
	// }
	// }
}

//
//

// @Configuration
// class LoadDatabase {

// private static final Logger log =
// LoggerFactory.getLogger(LoadDatabase.class);

// @Bean
// CommandLineRunner initDatabase(CustomUserRepository repository) {

// return args -> {

// // log.info("Preloading " + repository.save(new Employee("Bilbo Baggins",
// "burglar")));
// // log.info("Preloading " + repository.save(new Employee("Frodo Baggins",
// "thief")));
// };
// }
// }