package br.com.exemplo.angular.pessoaservice;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import br.com.exemplo.angular.pessoaservice.JWT.JwtAuthEntryPoint;
import br.com.exemplo.angular.pessoaservice.JWT.JwtAuthTokenFilter;
import br.com.exemplo.angular.pessoaservice.repository.PessoaRepository;
import br.com.exemplo.angular.pessoaservice.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private PessoaRepository pessoaRepositorio;
	
	@Autowired
	private JwtAuthEntryPoint unauthorizedHandler;
	
	@Autowired
    UserDetailsServiceImpl userDetailsService;
	
	@Bean
	public JwtAuthTokenFilter authenticationJwtTokenFilter() {
        return new JwtAuthTokenFilter();
    }

	@Autowired
	private DataSource dataSource;
	
	//@Value("${spring.queries.users-query}")
	//private String useQuery;
	
	//@Value("${spring.queries.roles-query}")
	//private String roleQuery;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("Teste auth");
		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder);
	}
	
	@Bean
	@Override
	 public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.cors().and().csrf().disable()
			.authorizeRequests()
			.antMatchers("/api/auth/**").permitAll()
			.anyRequest().authenticated()
            .and()
            .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    
    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//			.antMatchers("/registration").permitAll()
//			.anyRequest()
//				.authenticated()
//					.and().csrf().disable()
//				.formLogin()
//					.loginPage("/login").failureUrl("/login?error=true").defaultSuccessUrl("/")
//					.usernameParameter("email").passwordParameter("senha")
//				.and().logout()
//					.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/webjars/**");
		
	}

}
