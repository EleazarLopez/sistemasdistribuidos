package es.urjc.laliga.Security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;



@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
    	// private pages
                
        http.authorizeRequests().antMatchers("/**/nuevo_form/**").authenticated();
        http.authorizeRequests().antMatchers("/**/add").authenticated();
        http.authorizeRequests().antMatchers("/**/update").authenticated();
        

        // public pages (all other pages)
        http.authorizeRequests().anyRequest().permitAll();

        // Login form
        http.formLogin().loginPage("/login");
        http.formLogin().usernameParameter("username");
        http.formLogin().passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/temporada");
        http.formLogin().failureUrl("/loginerror");

        // Logout
        http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
        http.logout().logoutSuccessUrl("/temporada");
     
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        
    	// User
        auth.inMemoryAuthentication().withUser("user").password("pass").roles("ADMIN");
    }

}
