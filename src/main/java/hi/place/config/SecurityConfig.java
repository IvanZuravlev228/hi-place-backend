package hi.place.config;

import hi.place.model.user.User;
import hi.place.security.jwt.JwtTokenFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    private final UserDetailsService userDetailsService;
    private final JwtTokenFilter jwtTokenFilter;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(HttpMethod.POST, "/auth/login", "/auth/register")
                            .permitAll();
                    auth.requestMatchers(HttpMethod.PUT, "/auth/register/second", "/auth/register/third")
                            .permitAll();

                    auth.requestMatchers(HttpMethod.GET, "/email/send/user/{userId}", "/email/confirm")
                            .permitAll();

                    auth.requestMatchers(HttpMethod.GET, "/address/{id}", "/address/user/{userId}", "/address/near", "/address/cities")
                            .permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/address")
                            .permitAll();
                    auth.requestMatchers(HttpMethod.DELETE, "/address/{addressID}")
                            .hasAnyRole(User.UserType.MASTER.name(), User.UserType.SALON.name());

                    auth.requestMatchers(HttpMethod.GET, "/main-type-service", "/main-type-service/{userId}")
                            .permitAll();

                    auth.requestMatchers(HttpMethod.GET, "/images", "/images/main", "/images/user/{userId}")
                            .permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/images/examples/user/{userId}", "/images/discount")
                            .hasAnyRole(User.UserType.MASTER.name(), User.UserType.SALON.name());
                    auth.requestMatchers(HttpMethod.POST, "/images")
                                    .permitAll();

                    auth.requestMatchers(HttpMethod.GET, "/price/user/{userId}", "/price/user/{userId}/type/{typeOfServiceId}", "/price/type/{typeId}/user/{userId}")
                            .permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/price")
                            .hasAnyRole(User.UserType.MASTER.name(), User.UserType.SALON.name());

                    auth.requestMatchers(HttpMethod.PUT, "/price/{prevPriceId}")
                            .hasAnyRole(User.UserType.MASTER.name(), User.UserType.SALON.name());
                    auth.requestMatchers(HttpMethod.DELETE, "/price/{id}")
                            .hasAnyRole(User.UserType.MASTER.name(), User.UserType.SALON.name());

                    auth.requestMatchers(HttpMethod.GET, "/rating/{userId}")
                            .permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/rating")
                            .permitAll();

                    auth.requestMatchers(HttpMethod.GET, "/service/{typeServiceId}")
                            .permitAll();

                    auth.requestMatchers(HttpMethod.GET, "/type-service/{mainTypeId}", "/type-service/user/{userId}", "/type-service/discounts", "/type-service")
                            .permitAll();

                    auth.requestMatchers(HttpMethod.GET,"/user/email",  "/user/type-of-service/city/{city}", "/user/main-service/city/{city}", "/user/service-item/city/{city}", "/user/{userId}")
                            .permitAll();

                    auth.requestMatchers(HttpMethod.GET, "/discounts", "/discounts/type-service/{typeOfService}", "/discounts/user/{userId}").permitAll();

                    auth.requestMatchers(HttpMethod.POST, "/discounts")
                            .hasAnyRole(User.UserType.MASTER.name(), User.UserType.SALON.name());

                    auth.requestMatchers(HttpMethod.DELETE, "/discounts/{discountId}")
                            .hasAnyRole(User.UserType.MASTER.name(), User.UserType.SALON.name());

                    auth.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();

                    auth.requestMatchers("/images/**").permitAll();
                })
                .authenticationProvider(authenticationProvider())
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .headers(h -> h.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}