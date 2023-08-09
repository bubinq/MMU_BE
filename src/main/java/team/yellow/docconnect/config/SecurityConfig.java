package team.yellow.docconnect.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import team.yellow.docconnect.security.JwtAuthenticationEntryPoint;
import team.yellow.docconnect.security.JwtAuthenticationFilter;
import team.yellow.docconnect.security.JwtTokenProvider;

import java.security.Key;
import java.util.Date;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "Bearer"
)
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtTokenProvider jwtTokenProvider;

    @Value("${app.jwt-secret}")
    private String jwtSecret;



    public SecurityConfig(UserDetailsService userDetailsService, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, JwtAuthenticationFilter jwtAuthenticationFilter, JwtTokenProvider jwtTokenProvider) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize ->
                        authorize.requestMatchers(
                                        HttpMethod.GET, "/api/v1/**").permitAll()
                                .requestMatchers("/api/v1/auth/**").permitAll()
                                .requestMatchers("/swagger-ui/**").permitAll()
                                .requestMatchers("/v3/api-docs/**").permitAll()
                                .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling((exceptions) ->
                        exceptions.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .oauth2Login(oauth2Login ->
                        oauth2Login.loginPage("/api/v1/auth/google_login"))
//                .oauth2ResourceServer(
//                        httpSecurityOAuth2ResourceServerConfigurer -> httpSecurityOAuth2ResourceServerConfigurer
//                                .jwt(
//                                        jwtConfigurer -> jwtConfigurer.decoder(customJwtDecoder())))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }

//    @Bean
//    public JwtDecoder jwtDecoder() {
//        return NimbusJwtDecoder.withPublicKey(this.key).build();
//    }
//@Bean
//public JwtDecoder jwtDecoder() {
//    return JwtDecoders.fromIssuerLocation(issuerUri);
//}
    @Bean
    public JwtDecoder customJwtDecoder() {
        // Use your JwtTokenProvider for token validation
        return token -> {
            if (jwtTokenProvider.validateToken(token)) {
                Claims claims = Jwts.parserBuilder()
                        .setSigningKeyResolver(signingKeyResolver()) // Use your key resolver
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                Date issuedAt = claims.getIssuedAt();
                Date expiresAt = claims.getExpiration();

                return new Jwt(token, issuedAt.toInstant(), expiresAt.toInstant(), claims, claims);
            } else {
                throw new RuntimeException("Invalid JWT token");
            }
        };
    }

    @Bean
    public SigningKeyResolver signingKeyResolver() {
        return new SigningKeyResolverAdapter() {
            @Override
            public Key resolveSigningKey(JwsHeader header, Claims claims) {
                return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
            }
        };
    }
}
