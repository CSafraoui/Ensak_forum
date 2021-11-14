package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    public void globalConfig(AuthenticationManagerBuilder auth, DataSource dataSource) throws Exception {

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("select user.login as username, user.password as password, true from user where user.login = ?")
                .authoritiesByUsernameQuery("select user.login, profil.profil " +
                        "from user " +
                        "inner join profil on user.profil = profil.idprofil " +
                        "where user.login = ?");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
        		.antMatchers("/register","/login","/error").permitAll()
                .antMatchers("/register","/login","/error").anonymous()
                .antMatchers("/user").hasAnyRole("USER")
                .antMatchers("/**").hasAnyAuthority("ROLE_USER", "ROLE_REPRESENTANT", "ROLE_ADMIN", "ROLE_ETUDIANT")
                .and().logout().logoutSuccessUrl("/login")
                .and().formLogin().loginPage("/login").permitAll()
                .defaultSuccessUrl("/indexx").failureUrl("/login");
        http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/");
       
        	
    }
    @Override
    public void configure(WebSecurity webSecurityConfig){
        webSecurityConfig.ignoring().antMatchers("/");
        webSecurityConfig.ignoring().antMatchers("/error");
        webSecurityConfig.ignoring().antMatchers("/pdf","/google");
        webSecurityConfig.ignoring().antMatchers("/register");
        webSecurityConfig.ignoring().antMatchers("/assets/css/**");
        webSecurityConfig.ignoring().antMatchers("/assets/img/**");
        webSecurityConfig.ignoring().antMatchers("/assets/vendor/**");
        webSecurityConfig.ignoring().antMatchers("/assets/js/**");
        webSecurityConfig.ignoring().antMatchers("/css/**");
        webSecurityConfig.ignoring().antMatchers("/offre/**");
        webSecurityConfig.ignoring().antMatchers("/images/**");
        webSecurityConfig.ignoring().antMatchers("/cvs/**");
        webSecurityConfig.ignoring().antMatchers("/*.jpg");
        webSecurityConfig.ignoring().antMatchers("/*.png");
    }


    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public static NoOpPasswordEncoder passwordEncoder(){
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}

