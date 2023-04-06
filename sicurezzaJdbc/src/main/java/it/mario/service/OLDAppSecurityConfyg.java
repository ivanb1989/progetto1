package it.mario.service;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

//  @Configuration
public class OLDAppSecurityConfyg {

    @Bean
    // UserDetailsService --> recupera user e passwor dalla lettura dei dati sul database
    public UserDetailsService userDetailsService (DataSource dataSource){ // fa il lavoro del drivermanager (ma senza scrive

    return new JdbcUserDetailsManager(dataSource); // --> recupera le informazioni dell'utente e le inserice nell'oggetto
                                                   //     JdbcUserDetailsManager

    }


    // AREA PER GESIRE LE AUTORIZZAZIONI SUGLI ACCESSI
    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configurer ->
                configurer.requestMatchers("/benvenuto").permitAll()
                .requestMatchers("/ospiti").hasRole("GUEST")
                        // I RUELO VENGONO PRESI DALLA AUTORITIES COLONNA authority
                        .requestMatchers("/impiegati").hasAnyRole("IMPIEGATO","MANAGER"));// nel caso di più ruoli


                http.httpBasic();
                http.csrf().disable();
                return http.build();
     }

}
