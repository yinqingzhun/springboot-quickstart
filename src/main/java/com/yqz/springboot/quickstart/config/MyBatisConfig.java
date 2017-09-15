package com.yqz.springboot.quickstart.config;


//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(basePackages = "com.yqz.springboot.quickstart.repository")
//@MapperScan("com.yqz.springboot.quickstart.mapper")
public class MyBatisConfig
//implements TransactionManagementConfigurer 
{

//	@Autowired
//	DataSource dataSource;
//
//	@Bean(name = "sqlSessionFactory")
//	public SqlSessionFactory sqlSessionFactoryBean() {
//		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//		bean.setDataSource(dataSource);
//		// bean.setTypeAliasesPackage("com.yqz.springboot.quickstart.model.po");
//
//		// 分页插件
//		/*
//		 * PageHelper pageHelper = new PageHelper(); Properties properties = new
//		 * Properties(); properties.setProperty("reasonable", "true");
//		 * properties.setProperty("supportMethodsArguments", "true");
//		 * properties.setProperty("returnPageInfo", "check");
//		 * properties.setProperty("params", "count=countSql");
//		 * pageHelper.setProperties(properties);
//		 * 
//		 * //添加插件 bean.setPlugins(new Interceptor[]{pageHelper});
//		 */
//
//		// 添加XML目录
//		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//		try {
//			bean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
//			return bean.getObject();
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
//	}
//
//	@Bean
//	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
//		return new SqlSessionTemplate(sqlSessionFactory);
//	}
//
//	@Bean
//	public PlatformTransactionManager transactionManager() {
//		return new DataSourceTransactionManager(dataSource);
//	}
//
//	@Override
//	public PlatformTransactionManager annotationDrivenTransactionManager() {
//		return transactionManager();
//	}

	/**
	 * JPA
	 * 
	 * @Bean JpaTransactionManager jpaTransactionManager() {
	 *       JpaTransactionManager manager = new
	 *       JpaTransactionManager(entityManagerFactory()); return manager; }
	 * 
	 * @Bean EntityManagerFactory entityManagerFactory() {
	 *       LocalContainerEntityManagerFactoryBean bean = new
	 *       LocalContainerEntityManagerFactoryBean();
	 *       bean.setDataSource(dataSource); return bean.getObject(); }
	 */

}
