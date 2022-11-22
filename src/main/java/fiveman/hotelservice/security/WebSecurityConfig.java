package fiveman.hotelservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    CorsFilter corsFilter() {
        CorsFilter filter = new CorsFilter();
        return filter;
    }
    private final JwtTokenProvider jwtTokenProvider;

    //All resource That doesn't need token
    private final String[] permitURL = {
        
            "/h2-console/**/**",

            //resource
            // "/api/v1/login",
            // "/api/v1/signup",
            // "/api/v1/role/save",
            // "/api/v1/image/**/**",
            // "/api/v1/images",
            // "/api/v1/events",
            // "/api/v1/event",
            // "/api/v1/abstractions",
            // "/api/v1/abstraction/**/**",
            // "/api/v1/bookings",
            // "/api/v1/booking/**/**",
            // "/api/v1/customers",
            // "/api/v1/customer/**/**",
            // "/api/v1/services",
            // "/api/v1/service/**/**",
            // "/api/v1/serviceCategories",
            // "/api/v1/serviceCategory/**/**",
            // "/api/v1/devices",
            // "/api/v1/device/**/**",
            // "/api/v1/roomTypes",
            // "/api/v1/roomType/**/**",
            // "/api/v1/rooms",
            // "/api/v1/room/**/**",
            // "/api/v1/bills",
            // "/api/v1/bill/**/**",
            // "/api/v1/billDetail/**/**",
            // "/api/v1/hotels",
            // "/api/v1/hotel/**/**"
            "/api/v1/**/**/**"
            //end_resource
    };


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Disable CSRF (cross site request forgery)
        http.addFilterBefore(corsFilter(), SessionManagementFilter.class)
//                .cors()
//                .and()
                .csrf().disable(); //adds your custom CorsFilter
//        http.cors()


        // No session will be created or used by spring security
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Entry points
        http.authorizeRequests()//
                .antMatchers(permitURL).permitAll()//
                // Disallow everything else..
                .anyRequest().authenticated();

        // If a user try to access a resource without having enough permissions
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint());


//		http.exceptionHandling().accessDeniedPage("/api/msg");

        // Apply JWT
        http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));

        // Optional, if you want to test the API from a browser
        // http.httpBasic();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // Allow swagger to be accessed without authentication
        web.ignoring().antMatchers("/v2/api-docs")//
                .antMatchers("/swagger-resources/**")//
                .antMatchers("/swagger-ui.html")//
                .antMatchers("/configuration/**")//
                .antMatchers("/webjars/**")//
                .antMatchers("/public")

                // Un-secure H2 Database (for testing purposes, H2 console shouldn't be
                // unprotected in production)
                .and().ignoring().antMatchers("/h2-console/**/**");
        ;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint();
    }


}
