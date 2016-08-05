package com.ownlocal.ol.run;

import com.ownlocal.ol.entries.EntryServer;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;

@Configuration
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class Program implements ApplicationContextAware {
  @Bean
  public EmbeddedServletContainerFactory servletContainer() {
    return new TomcatEmbeddedServletContainerFactory();
  }

  public static void main(String[] args) throws Exception {
    SpringApplication.run(Program.class, args);
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    GenericApplicationContext context = (GenericApplicationContext) applicationContext;
    context.getBeanFactory().registerSingleton(EntryServer.ENTRY_SERVER_KEY,
      EntryServer.loadFromFile("engineering_project_businesses.csv"));
  }
}

