package com.formacionbdi.springboot.app.zuul.oauth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@RefreshScope
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

	@Value("${config.security.oauth.jwt.key}")
	private String jwtKey;
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore());
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		//Recordar al configurar las reglas, comenzar desde los mas general y terminar en lo mas especifico 
		//Simplificado
		http.authorizeRequests().antMatchers("/api/security/oauth/**").permitAll()
		.antMatchers(HttpMethod.GET,"/api/productos/listar","/api/items/listar","/api/usuarios/usuarios").permitAll()
		.antMatchers(HttpMethod.GET,"/api/productos/ver/{id}","/api/items/ver/{id}/cantidad/{cantidad}","/api/usuarios/usuarios/{id}").hasAnyRole("ADMIN","USER")
		.antMatchers("/api/productos/**","/api/items/**","/api/usuarios/**").hasRole("ADMIN") /*se simplifico POST PUT y DELETE en uno solo*/
		.anyRequest().authenticated() /*Para lo que se escape de las reglas*/
		.and().cors().configurationSource(corsConfigurationSource());
		
		// No simplificado POST PUT DELETE
		/*
		http.authorizeRequests().antMatchers("/api/security/oauth/**").permitAll()
		.antMatchers(HttpMethod.GET,"/api/productos/listar","/api/items/listar","/api/usuarios/usuarios").permitAll()
		.antMatchers(HttpMethod.GET,"/api/productos/ver/{id}","/api/items/ver/{id}/cantidad/{cantidad}","/api/usuarios/usuarios/{id}").hasAnyRole("ADMIN","USER")
		.antMatchers(HttpMethod.POST,"/api/productos/crear","/api/items/crear").hasRole("ADMIN")
		.antMatchers(HttpMethod.PUT,"/api/productos/editar/{id}","/api/items/editar/{id}","/api/usuarios/usuarios/{id}").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE,"/api/productos/eliminar/{id}","/api/items/eliminar/{id}","/api/usuarios/usuarios/{id}").hasRole("ADMIN")
		.anyRequest().authenticated();
		*/
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.addAllowedOrigin("*");
		//corsConfig.setAllowedOrigins(Arrays.asList("*","otro","otro mas")); //solo de ejemplo
		corsConfig.setAllowedMethods(Arrays.asList("POST","GET","PUT","DELETE","OPTIONS"));
		corsConfig.setAllowCredentials(true);
		corsConfig.setAllowedHeaders(Arrays.asList("Authorization","Content-Type"));
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig);
		
		return source;
	}
	
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter(){
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
				bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}

	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}
	
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		//tokenConverter.setSigningKey("algun_codigo_secreto_aeiou");
		tokenConverter.setSigningKey(jwtKey);
		return tokenConverter;
	}	
}
