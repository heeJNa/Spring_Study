package com.sist.di;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
/*
 * <bean id="ds" class="oracle.ucp.jdbc.PoolDataSourceImpl"
		p:driverClassName="oracle.jdbc.driver.OracleDriver"
		p:url="jdbc:oracle:thin:@oracle_medium?TNS_ADMIN=/Users/kimheejun/Documents/Wallet_oracle"
		p:username="admin"
		p:password="Gmlwnsskgus!@1208"
	/>
	<bean id="ssf" class="org.mybatis.spring.SqlSessionFactoryBean"
		p:dataSource-ref="ds"
		p:configLocation="classpath:Config.xml"
	/>
	<!-- MyBatis생성 --> 
	<bean id="dao" class="com.sist.di.EmpDAO"
		p:sqlSessionFactory-ref="ssf"
	/>
	<bean id="dao2" class="com.sist.di2.BooksDAO"
		p:sqlSessionFactory-ref="ssf"
	/>
	<bean id="dao3" class="com.sist.di3.HotelDAO"
		p:sqlSessionFactory-ref="ssf"
	/>
 * 
 */
// app.xml을 대신 => XML 대신 자바로 설정(5)
@Configuration
public class EmpConfig {
	@Bean("ds")
	public BasicDataSource basicDataSource() {
		BasicDataSource ds=new BasicDataSource();
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@211.63.89.131:1521:xe");
		ds.setUsername("hr");
		ds.setPassword("happy");
		return ds;
	}
	@Bean("ssf")
	public SqlSessionFactory sqlSessionFactory() throws Exception{
		SqlSessionFactoryBean ssf=
				new SqlSessionFactoryBean();
		ssf.setDataSource(basicDataSource());
		Resource res=new ClassPathResource("Config.xml");
		ssf.setConfigLocation(res);
		return ssf.getObject();
	}
	@Bean("dao")
	public EmpDAO empDao() throws Exception{
		EmpDAO dao =new EmpDAO();
		dao.setSqlSessionFactory(sqlSessionFactory());
		return dao;
	}
}
