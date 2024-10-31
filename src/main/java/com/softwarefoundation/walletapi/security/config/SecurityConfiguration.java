package com.softwarefoundation.walletapi.security.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Value("${jwt.public.key}")
    private RSAPublicKey publicKey;

    @Value("${jwt.private.key}")
    private RSAPrivateKey privateKey;

    @Autowired
    private Boolean isConsoleH2Enabled;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        habilitarAcessoSwagger(http);

        if(isConsoleH2Enabled){
            habilitarAcessoAoConsoleH2(http);
        }

        http.authorizeHttpRequests(
                        auth -> auth
                                .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .oauth2ResourceServer(conf -> conf.jwt(
                        jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder())
                ));

        http.csrf(csrf -> csrf.disable());

        return http.build();
    }


    private void habilitarAcessoSwagger(HttpSecurity http) throws Exception {
        String[] urls = {"/swagger-ui/index.html", "/swagger-ui/swagger-ui.css", "/swagger-ui/index.css", "/swagger-ui/swagger-ui-bundle.js", "/swagger-ui/swagger-ui-standalone-preset.js", "/swagger-ui/swagger-initializer.js", "/swagger-ui/swagger-initializer.js", "/v3/api-docs/swagger-config","/v3/api-docs"};

        http.authorizeHttpRequests(
                auth -> auth.requestMatchers(urls).permitAll());
    }

    private void habilitarAcessoAoConsoleH2(HttpSecurity http) throws Exception {
        http.headers(headers -> headers.frameOptions(frameOptionsConfig -> frameOptionsConfig.sameOrigin()));

        http.authorizeHttpRequests(
                auth -> auth.requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll());
    }

    @Bean
    public JwtDecoder jwtDecoder(){
        return NimbusJwtDecoder.withPublicKey(publicKey).build();
    }

    @Bean
    public JwtEncoder jwtEncoder(){
        var jwk = new RSAKey.Builder(this.publicKey).privateKey(privateKey).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
