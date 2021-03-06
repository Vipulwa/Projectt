//package com.cybage.controller;
//
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import com.cybage.service.ICitizenService;
//
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Autowired
//	ICitizenService citizenService;
//	
//	
//	
//	
//	@Override
//    protected void configure(HttpSecurity http) throws Exception {
//		http = http.cors().and().csrf().disable();
//
//        // Set session management to stateless
//        http = http
//            .sessionManagement()
//            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            .and();
//
//        // Set unauthorized requests exception handler
//        http = http
//            .exceptionHandling()
//            .authenticationEntryPoint(
//                (request, response, ex) -> {
//                    response.sendError(
//                        HttpServletResponse.SC_UNAUTHORIZED,
//                        ex.getMessage()
//                    );
//                }
//            )
//            .and();
//
//        // Set permissions on endpoints
//        http.authorizeRequests()
//            // Our public endpoints
//            /*.antMatchers("/api/public/**").permitAll()
//            .antMatchers(HttpMethod.GET, "/api/author/**").permitAll()
//            .antMatchers(HttpMethod.POST, "/api/author/search").permitAll()
//            .antMatchers(HttpMethod.GET, "/api/book/**").permitAll()
//            .antMatchers(HttpMethod.POST, "/api/book/search").permitAll()*/
//            // Our private endpoints
//            .anyRequest().authenticated();
//
//        // Add JWT token filter
////        http.addFilterBefore(
////            jwtTokenFilter,
////            UsernamePasswordAuthenticationFilter.class
////        );
//    }
//
//    // Used by spring security if CORS is enabled.
//   
////	}
//}
