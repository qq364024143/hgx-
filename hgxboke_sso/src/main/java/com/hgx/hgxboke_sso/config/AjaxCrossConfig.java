package com.hgx.hgxboke_sso.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 允许ajax跨域并传输cookie
 * @author hgx
 *
 */
/*@Configuration
public class AjaxCrossConfig extends WebMvcConfigurationSupport {
	
	static final String[] ORIGINS = new String[] { "GET", "POST", "PUT", "DELETE" };
	@Override
	protected void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").allowCredentials(true).allowedMethods(ORIGINS)
        .maxAge(3600);
	}
	
}*/



@Configuration
public class AjaxCrossConfig {

    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); // 原始请求的域名
        corsConfiguration.addAllowedHeader("*"); // 添加请求头字段Cache-Control, Expires, Content-Type等
        corsConfiguration.addAllowedMethod("*"); // 服务器支持的所有跨域请求的方法（'GET'、'POST'）等
        corsConfiguration.setMaxAge(3600L);
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig()); //设置过滤条件
        return new CorsFilter(source);
    }
}
