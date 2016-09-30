package ru.spb.iac.storager.server;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class IntegrationConfiguration extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(final ApplicationContext context) {
        final MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(context);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/integration/*");
    }

    @Bean(name = "providing")
    public DefaultWsdl11Definition defaultWsdl11Definition(final XsdSchema schema) {
        final DefaultWsdl11Definition wsdl = new DefaultWsdl11Definition();
        wsdl.setPortTypeName("providingPort");
        wsdl.setLocationUri("/integration");
        wsdl.setTargetNamespace("http://iac.spb.ru/storager/server/integration/providing");
        wsdl.setSchema(schema);
        return wsdl;
    }

    @Bean
    public XsdSchema uploadSchema() {
        return new SimpleXsdSchema(new ClassPathResource("integration/providing.xsd"));
    }
}
