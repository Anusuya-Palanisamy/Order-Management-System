package com.order.router;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.PATCH;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.order.constants.Constants;

@Configuration
//@EnableWebFlux
public class Router {
	
   /* static final String V1_SECTION_PART = "v1/sections";

	 @Autowired
	    private ApplicationContext appContext;

	    @Override
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**").allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH", "HEAD");
	    }*/

	@Bean
	public RouterFunction<ServerResponse> root(Handler handler) {
		return RouterFunctions.route()
			
				.GET("/api/billData/{order_id}", handler::billData)
				.GET("/api/oderData/ {startDate}/ {endDate}", handler::orderListDate)
				.PATCH("/api/{order_id}/{status}", handler::updateStatus)
				.GET("/api/cityname/{city}", handler::OderListBasedonCity)
				.GET("/api/allData/{status}/{date}", handler::orderListByDate)
				.GET("/api/allList/{date}", handler::OderListBasedonData)
				.GET("/api/allData", handler::getListOrder)
				.POST("/api/CreateOrder", handler::createOrder)
				.build();
	}
	/* @Bean
	    public RouterFunction<ServerResponse> rosterV1Routes() {
		 Handler rosterServiceHandler = appContext.getBean(Handler.class);
	        return 
	                route(GET(Constants.CONTEXT_PATH +"/oderData/{startDate}/{endDate}"), rosterServiceHandler::OderListBasedonCity)
	                .andRoute(GET(Constants.CONTEXT_PATH +"/billData/{order_id}"), rosterServiceHandler::billData)
	        .andRoute(GET(Constants.CONTEXT_PATH +"/billData/{order_id}"), rosterServiceHandler::billData)
	        .andRoute(GET(Constants.CONTEXT_PATH +"/billData/{order_id}"), rosterServiceHandler::billData)
	        .andRoute(GET(Constants.CONTEXT_PATH +"/billData/{order_id}"), rosterServiceHandler::billData)
	        .andRoute(GET(Constants.CONTEXT_PATH +"/billData/{order_id}"), rosterServiceHandler::billData)
	        .andRoute(POST(Constants.CONTEXT_PATH +"/CreateOrder"), rosterServiceHandler::createOrder)
	        ;
	    }*/
}
