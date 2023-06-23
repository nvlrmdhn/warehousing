package id.co.indivara.jdt12.warehousing.secutiry;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Security extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("{noop}password").roles("ADMIN")
                .and()
                .withUser("warehouseAdmin").password("{noop}password").roles("WAREHOUSEADMIN")
                .and()
                .withUser("supplyer").password("{noop}password").roles("SUPPLYER")
                .and()
                .withUser("storeAdmin").password("{noop}password").roles("STOREADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .formLogin()
                .and()
                .csrf().disable()
                .formLogin().disable();
    }
}
