package com.batch.core;

import org.springframework.beans.factory.annotation.Autowired;


public class JOBConfiguration {


	
	/*@Autowired	
	private SessionFactory myftpSessionFactory;
	*/
	//For reference only no use in this project
/*
	@Bean	
	public RemoteFilesTasklet ftpRemoteFilesTasklet()
	{
		System.out.println("FTP task: setting up FTP to fetch file locally");
		RemoteFilesTasklet  ftpTasklet = new RemoteFilesTasklet();
		ftpTasklet.setRetryIfNotFound(true);
		ftpTasklet.setDownloadFileAttempts(3);
		ftpTasklet.setRetryIntervalMilliseconds(10000);
		ftpTasklet.setFileNamePattern("carrier_usdot.csv");
		//ftpTasklet.setFileNamePattern("TestFile");
		ftpTasklet.setRemoteDirectory("/deepakp");
		
		ftpTasklet.setLocalDirectory(new File("F:/jars"));
		ftpTasklet.setSessionFactory(myftpSessionFactory);
		
		return ftpTasklet;
	}
	
	
	 
	@SuppressWarnings("rawtypes")
	@Bean	
	public SessionFactory myftpSessionFactory()
	{
		DefaultFtpSessionFactory ftpSessionFactory = new DefaultFtpSessionFactory();
		ftpSessionFactory.setHost("ftp.rscube.com");
		ftpSessionFactory.setClientMode(0);
		ftpSessionFactory.setFileType(0);
		ftpSessionFactory.setPort(21);
		ftpSessionFactory.setUsername("dev");
		ftpSessionFactory.setPassword("D3v3l0pm3nt@5093");
		
		return ftpSessionFactory;
	}*/
	
	

	/*@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}*/
	
	/*@Bean
	public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        //System.err.println("env.getProperty(batch.jdbc.driver\")"+ ${batch.jdbc.driver}");
        dataSource.setUrl("jdbc:sqlserver://localhost;databaseName=batchrepo");
        dataSource.setUsername("la");
        dataSource.setPassword("la");
 
        return dataSource;
    }*/

	/*@Bean
    public JobRepository jobRepository() throws Exception {
        return new MapJobRepositoryFactoryBean(transactionManager()).getJobRepository();
    }

    @Bean
    public JobLauncher jobLauncher() throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository());
        return jobLauncher;
    }*/
}
