package bucketlistapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  UserDetailsServiceImpl userDetailsService;

  @Bean
  public BCryptPasswordEncoder passwordEncoder(){
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    return bCryptPasswordEncoder;
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception{
    http.csrf().disable();
    http.authorizeRequests().antMatchers("/", "/login").permitAll();

    http.authorizeRequests().antMatchers("/user_profile").authenticated();

    http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/login");

    http.authorizeRequests.and().formLogin()
      .loginProcessingUrl("/security_check")
      .loginPage("/login")
      .defaultSuccessUrl("/user_profile")
      .failureUrl("/login?error=true")
      .usernameParameter("username")
      .passwordParameter("password")
      .and().logoutUrl("logout").logoutSuccessUrl("/logoutSuccessful");
  }
}
