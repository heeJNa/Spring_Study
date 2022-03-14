package com.sist.di2;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.sist.di.EmpDAO;
// 자동 메모리 할당
@Configuration
public class LocationConfig {
	// Mybatis에 오라클 정보 => DataSource => 확장 (BasicDataSource)
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
	@Bean("dao1")
	public LocationDAO empDao() throws Exception{
		LocationDAO dao =new LocationDAO();
		dao.setSqlSessionFactory(sqlSessionFactory());
		return dao;
	}
		
}
