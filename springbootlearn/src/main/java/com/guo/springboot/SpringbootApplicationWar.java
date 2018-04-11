package com.guo.springboot;

import com.guo.springboot.filter.TimeFilter;
import com.guo.springboot.listener.ListenerTest;
import com.guo.springboot.serlvet.ServletTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.EnumSet;

@SpringBootApplication
public class SpringbootApplicationWar extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringbootApplicationWar.class);
    }

    /**
     * 配置servlet filter listner 另外的一种方式
     * @param servletContext
     * @throws ServletException
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // 配置servlet
        servletContext.addServlet("servletTest", new ServletTest()).addMapping("/servletTest");

        // 配置filter
        servletContext.addFilter("timeFilter", new TimeFilter()).addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");

        // 配置listner
        servletContext.addListener(new ListenerTest());
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplicationWar.class, args);
    }
}
