package com.ticketing.security;


import java.io.IOException;
import java.util.List;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;



import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@Component
public class JwtAuthFilter
extends OncePerRequestFilter {




    private final JwtUtil jwtUtil;


    private final UserContext userContext;





    public JwtAuthFilter(
            JwtUtil jwtUtil,
            UserContext userContext
    ) {

        this.jwtUtil = jwtUtil;
        this.userContext = userContext;

    }







    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    )
            throws ServletException, IOException {



        String header =
                request.getHeader(
                        "Authorization"
                );






        if(header != null &&
                header.startsWith("Bearer ")) {



            String token =
                    header.substring(7);





            try {



                if(jwtUtil.validateToken(token)) {



                    String email =
                            jwtUtil.extractEmail(
                                    token
                            );



                    Long userId =
                            jwtUtil.extractUserId(
                                    token
                            );



                    String role =
                            jwtUtil.extractRole(
                                    token
                            );






                    userContext.setUserId(
                            userId
                    );






                    UsernamePasswordAuthenticationToken authentication =

                            new UsernamePasswordAuthenticationToken(

                                    email,

                                    null,

                                    List.of(

                                            new SimpleGrantedAuthority(

                                                    "ROLE_" + role

                                            )

                                    )

                            );






                    SecurityContextHolder
                            .getContext()
                            .setAuthentication(
                                    authentication
                            );

                }




            }
            catch(Exception e) {


                SecurityContextHolder
                        .clearContext();

            }


        }






        filterChain.doFilter(
                request,
                response
        );

    }

}