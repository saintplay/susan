package com.susan.app.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
@ComponentScan("com.thymeleafexamples")
public class ThymeleafConfig extends WebMvcConfigurerAdapter {

  @Bean
  public ViewResolver viewResolver() {
      ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
      viewResolver.setTemplateEngine(templateEngine());
      viewResolver.setCharacterEncoding("UTF-8");
      return viewResolver;
  }

  @Bean
  public ITemplateEngine templateEngine() {
      SpringTemplateEngine templateEngine = new SpringTemplateEngine();
      templateEngine.setTemplateResolver(templateResolver());
      templateEngine.addDialect(new LayoutDialect());
      return templateEngine;
  }

  private ITemplateResolver templateResolver() {
      SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
      templateResolver.setPrefix("classpath:/templates/");
      templateResolver.setSuffix(".html");
      templateResolver.setTemplateMode(TemplateMode.HTML);
      templateResolver.setCharacterEncoding("UTF-8");
      templateResolver.setCacheable(false);
      return templateResolver;
  }
}