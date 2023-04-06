package it.mario.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class AppSecurityConfyg {

    @Autowired
    Environment env; // crea un oggetto (env) che permette di accedere anche ai dati che si trovano nel file.properties o .yaml

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    // UserDetailsService --> recupera user e passwor dalla lettura dei dati sul database
    public UserDetailsService userDetailsService(DataSource dataSource) {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        // ISTRUZIONI PER L'AUTETICAZIONE
        userDetailsManager.setUsersByUsernameQuery("select username, password, enabled from utenti where username = ?");

        // ISTRUZIONI PER LE AUTORIZZAZIONI
        userDetailsManager.setAuthoritiesByUsernameQuery("select user,ruolo from ruoli where user = ?");

        return userDetailsManager; // --> recupera le informazioni dell'utente e le inserice nell'oggetto
        //     JdbcUserDetailsManager

    }
    // AREA PER GESIRE LE AUTORIZZAZIONI SUGLI ACCESSI
    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configurer ->
                configurer.requestMatchers(HttpMethod.POST,"/inserisci").permitAll().
                requestMatchers(HttpMethod.GET,"/benvenuto","/estraidati","/orario",
                        "/mostraorario","/oraedata","/estraidipendenti").permitAll()
               .requestMatchers("/ospiti").hasRole("GUEST")
        );
                http.httpBasic();
                http.csrf().disable();
                return http.build();
     }

     // creimo due bean che ci saranno la possibilità di usare i due db diversi
    // DataSource -> sostituisce la Connection(url, user, paww)  classica sul database
   @Bean
   @Primary
    public DataSource primoDatasource (){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        return dataSource;
     }
    //---------------------------------------------------------------------------------//
    @Bean
        public DataSource secondoDatasource (){  // opera sul database degli impiegati
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(env.getProperty("spring.datasourceprimario.url"));
        dataSource.setPassword(env.getProperty("spring.datasourceprimario.password"));
        dataSource.setUsername(env.getProperty("spring.datasourceprimario.username"));
        return dataSource;
    }

    // creiamo oggetti dall'Interfaccia JDBCTemplate che utilizzerano lo specifico datasorce per
    // effettuare query sul database


    @Bean
    public JdbcTemplate jdbcTemplatePrimo (@Qualifier("primoDatasource") DataSource ds){
       return new JdbcTemplate(ds);
    }
    @Bean
    @Primary   //database degli impiegati
    public JdbcTemplate jdbcTemplateSecondo (@Qualifier("secondoDatasource") DataSource ds){
        return new JdbcTemplate(ds);
    }

}


