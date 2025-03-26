package com.ohgiraffers.springmybatis.config;

import com.ohgiraffers.springmybatis.section01.factorybean.MenuDTO;
import com.ohgiraffers.springmybatis.section01.factorybean.MenuMapper;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisConfig {
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.jdbc-url}")
    private String jdbcUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Bean
    public HikariDataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        /* 커넥션 획득 대기 시간 */
        dataSource.setConnectionTimeout(30000);

        /* 풀에서 동시에 유지 가능한 최대 커넥션 수 */
        dataSource.setMaximumPoolSize(50);

        /* 사용하지 않는 커넥션의 유휴 시간 */
        dataSource.setIdleTimeout(600000);

        /* 커넥션의 최대 생명 주기를 설정 (오래된 커넥션 주기적 교체) */
        dataSource.setMaxLifetime(1800000);

        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {

        org.apache.ibatis.session.Configuration configuration
                = new org.apache.ibatis.session.Configuration();
        configuration.getTypeAliasRegistry().registerAlias("MenuDTO", MenuDTO.class);
        configuration.addMapper(MenuMapper.class);
        configuration.setMapUnderscoreToCamelCase(true);

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        sqlSessionFactoryBean.setConfiguration(configuration);

        return sqlSessionFactoryBean.getObject();

    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }
}
