package br.com.art;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.StandardEnvironment;



@SpringBootApplication
@ComponentScan(basePackages = { "br.com.art" })
@EnableAutoConfiguration(exclude = { ErrorMvcAutoConfiguration.class, SecurityAutoConfiguration.class })
public class ArtApplication {

	public static void main(String[] args) {		
		System.out.println("Spring Boot iniciando");
		SpringApplication application = new SpringApplication(ArtApplication.class);
		ConfigurableEnvironment environment = new StandardEnvironment();
		environment.setDefaultProfiles("dev");
		environment.setActiveProfiles("dev");
		application.setAdditionalProfiles("dev");
		application.setEnvironment(environment);
		application.run(args);
	}

	@Value("${server.servlet.contextPath}")
	private String path;

	@Value("${server.port}")
	private String port;

	/**m
	 *
	 * @param ctx
	 * @return
	 */
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Start concluido");
			//System.out.println("http://localhost:" + port + path);
			System.out.println("http://localhost:" + port);
		};
	}

	/*@Bean
	public FilterRegistrationBean myFilterBean() {
		final FilterRegistrationBean filterRegBean = new FilterRegistrationBean();
		filterRegBean.setFilter(new AuthenticFilter());
		filterRegBean.addUrlPatterns("/*");
		filterRegBean.setEnabled(Boolean.TRUE);
		filterRegBean.setName("authenticFilter");	
		filterRegBean.setAsyncSupported(Boolean.TRUE);
		return filterRegBean;
	}*/
}
