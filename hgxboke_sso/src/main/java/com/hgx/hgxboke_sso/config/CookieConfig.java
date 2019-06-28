package com.hgx.hgxboke_sso.config;

import org.apache.tomcat.util.http.LegacyCookieProcessor;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CookieConfig {

	/**
	 * 解决问题： * There was an unexpected error (type=Internal Server Error, status=500). * An invalid domain [.localhost.com] was specified for this cookie
	 * @return
	 */
	@Bean 
	public WebServerFactoryCustomizer<TomcatServletWebServerFactory> cookieProcessorCustomizer() { 
		return (factory) -> factory.addContextCustomizers( 
				(context) -> context.setCookieProcessor(new LegacyCookieProcessor())
				); 
		}
}
