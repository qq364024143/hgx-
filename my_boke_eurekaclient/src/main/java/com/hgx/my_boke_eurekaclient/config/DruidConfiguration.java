package com.hgx.my_boke_eurekaclient.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;

/**
 * 阿里druid数据源和监控配置
 * @author hgx
 *
 */
@Configuration
public class DruidConfiguration {

	@Bean(destroyMethod = "close",initMethod = "init") 
	@ConfigurationProperties(prefix = "spring.datasource") //这里需要注意默认是读取的application.properties配置文件。 //如果你的配置文件不在默认文件中。 
	//需要在类中引入配置文件例如：@PropertySource(value = "classpath:druid.properties") 
	public DataSource druidDataSource(){ 
		DruidDataSource druidDataSource = new DruidDataSource(); 
		return druidDataSource; 
	} 
	/**
	 * 配置访问druid监控
	 * @return
	 */ 
	@Bean 
	public ServletRegistrationBean druidStateViewServlet(){ 
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*"); 
		//初始化参数initParams //添加白名单 
		servletRegistrationBean.addInitParameter("allow",""); 
		//添加ip黑名单 
		servletRegistrationBean.addInitParameter("deny","192.168.16.111"); 
		//登录查看信息的账号密码 
		servletRegistrationBean.addInitParameter("loginUsername","admin"); 
		servletRegistrationBean.addInitParameter("loginPassword","0805"); 
		//是否能够重置数据 
		servletRegistrationBean.addInitParameter("resetEnable","false"); 
		return servletRegistrationBean; 
	} 

	/**
	 * 过滤不需要监控的后缀
	 * @return
	 */ 
	@Bean 
	public FilterRegistrationBean druidStatFilter(){ 
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter()); 
		//添加过滤规则
		filterRegistrationBean.addUrlPatterns("/*"); 
		//添加不需要忽略的格式信息 
		filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*"); 
		return filterRegistrationBean; 
	}


	/**
	 * 防火墙配置
	 * @return
	 */
	@Bean
	public WallFilter wallFilter(){
		WallFilter wallFilter=new WallFilter();
		wallFilter.setConfig(wallConfig());
		return wallFilter;
	}

	@Bean
	public WallConfig wallConfig(){
		WallConfig config =new WallConfig();
		config.setMultiStatementAllow(true);//允许一次执行多条语句
		config.setNoneBaseStatementAllow(true);//允许非基本语句的其他语句
		return config;
	}
}
