package eu.sos.ttc.webapp.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;

import eu.sos.ttc.core.domain.User;
import eu.sos.ttc.core.service.UserService;


/**
 * Spring security configuration.
 * @author BauerMitFackel
 */
@Configuration
@EnableWebMvcSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


	@Autowired
	private UserService userService;


	@Override
	protected void configure (HttpSecurity http) throws Exception {

		http.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/resources/**").permitAll()
			.antMatchers("/").permitAll()
			.antMatchers("/signup").permitAll()
			.anyRequest().authenticated();

		http.formLogin()
			.defaultSuccessUrl("/")
			.failureUrl("/login?error=true")
			.loginPage("/login")
			.permitAll();

		http.logout()
			.invalidateHttpSession(false)
			.logoutUrl("/logout")
			.logoutSuccessUrl("/");

		http.csrf().disable();
	}


	@Override
	protected void configure (AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(new BaseUserDetailsService())
			.passwordEncoder(new BCryptPasswordEncoder());
	}


	/**
	 * Base {@link org.springframework.security.core.userdetails.UserDetailsService} implementation.
	 */
	private class BaseUserDetailsService implements UserDetailsService {


		@Override
		public UserDetails loadUserByUsername (String email) throws UsernameNotFoundException {

			User user = userService.getByEmail(email);
			if (user == null) {
				String msg = String.format("%s{email=%s} not found", User.class.getSimpleName(), email);
				throw new UsernameNotFoundException(msg);
			}

			return createUserDetails(user);
		}


		/**
		 * Creates a new {@link org.springframework.security.core.userdetails.UserDetails} object.
		 */
		private UserDetails createUserDetails (User user) {

			Collection<GrantedAuthority> authorities = new ArrayList<>();
			return new org.springframework.security.core.userdetails.User (
					user.getEmail(), user.getPassword(), true, true, true, !user.isLocked(), authorities
			);
		}
	}
}
