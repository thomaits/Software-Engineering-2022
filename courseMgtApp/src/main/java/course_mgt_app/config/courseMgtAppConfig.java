package course_mgt_app.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class courseMgtAppConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    DataSource dataSource;

	/*
	 * @Autowired methods are config methods
	 * This one enables jdbc authentication
	 */
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests() 
		/*
		 * authorizeRequests returns an ExpressionInterceptUrlRegistry that 
		 * allows restricting access to uris based on 
		 * RequestMatcher implementations
		 */
		.antMatchers("/*").hasAnyRole("USER", "ADMIN") // match specific apache ant reg exprs for urls and specify rights
		.antMatchers("/login*").permitAll()
		.anyRequest().authenticated()
		/*
		 * returns the http security object and allows to compose 
		 * multiple chains of configuration method calls
		 */
		.and() 
		.formLogin(); // specifically this method tells spring to generate a default login page
	
		// .loginPage(...).loginProcessingUrl(...)
	}
	
		
}
