package com.sist.config;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration  // 4문제 xml과 자바의 차이점 (보안)
// <aop:aspectj-autoproxy/> 
@EnableAspectJAutoProxy
// <context:component-scan base-package="com.sist.*"/>
@ComponentScan(basePackages = {"com.sist.*"})
// <tx:annotation-driven/>
@EnableTransactionManagement
//<mybatis-spring:scan base-package="com.sist.mapper"/>
@MapperScan(basePackages ={"com.sist.mapper"})
public class FoodConfig {
	
	// ViewResolver 처리
	/*  <bean id="viewResolver"
	 		class="org.springframework.web.servlet.view.InternalResourceViewResolver"
	  		p:prefix="/"
	 		p:suffix=".jsp"
	 	/>
	*/
	@Bean("viewResolver")
	public ViewResolver viewResolver() {
		InternalResourceViewResolver vr = 
				new InternalResourceViewResolver();
		vr.setPrefix("/");
		vr.setSuffix(".jsp");
		return vr;
	}
	
	// BasicDataSource
	/*
		  <bean id="ds" class="org.apache.commons.dbcp.BasicDataSource"
				p:driverClassName="#{db['driver']}"
				p:url="#{db['url']}"
				p:username="#{db['username']}"
				p:password="#{db['password']}"
				p:maxActive="#{db['maxActive']}"
				p:maxIdle="#{db['maxIdle']}"
			/>
	 */
	@Bean("ds")
	// DataSource (interface) => 구현된 클래스 (BasicDataSource)
	// public class BasicDataSource implements DataSource
	public DataSource dataSource() {
		BasicDataSource ds = new BasicDataSource();
		// 데이터베이스 정보를 변수로 가지고 있다
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver"); // p:driverClassNaem
		ds.setUrl("jdbc:oracle:thin:@oracle_high?TNS_ADMIN=/Users/kimheejun/Documents/Wallet_oracle");
		ds.setUsername("admin");
		ds.setPassword("Gmlwnsskgus!@1208");
		return ds;
	}
	
	// SqlSessionFactoryBean
	/*
	  	<bean id="ssf" class="org.mybatis.spring.SqlSessionFactoryBean"
			p:dataSource-ref="dataSource"
		/>
	 */
	@Bean("ssf")
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean ssf=
				new SqlSessionFactoryBean();
		ssf.setDataSource(dataSource()); // p:dataSource-ref="dataSource"
		return ssf.getObject();
	}
	
	// Transaction 처리
	/*
	 	<bean id="transactionManager"
			class="org.springframework.jdbc.datasource.DataSourceTransactionManager"
			p:dataSource-ref="ds"
		/>	
	 */
	@Bean("transactionManager")
	public DataSourceTransactionManager txManager() {
		DataSourceTransactionManager tx=
				new DataSourceTransactionManager();
		tx.setDataSource(dataSource());
		return tx;
	}
}
