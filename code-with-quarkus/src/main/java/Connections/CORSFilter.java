///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package Connections;
//
////import io.vertx.ext.web.handler.CorsHandler;
//import io.vertx.ext.web.Router;
//import io.vertx.rxjava.ext.web.*;
//import jakarta.enterprise.event.Observes;
//
////import javax.enterprise.event.Observes;
//import javax.inject.Singleton;
//
////@Singleton
//public class CORSFilter {
//
//    void enableCorsSupport(@Observes Router router) {
//        router.route().handler(io.vertx.rxjava.ext.web.handler.CorsHandler.create("http://127.0.0.1:5173")
//                .allowedMethod(io.vertx.core.http.HttpMethod.GET)
//                .allowedMethod(io.vertx.core.http.HttpMethod.POST)
//                .allowedMethod(io.vertx.core.http.HttpMethod.OPTIONS)
//                .allowCredentials(true)
//                .allowedHeader("Access-Control-Allow-Method")
//                .allowedHeader("Access-Control-Allow-Origin")
//                .allowedHeader("Access-Control-Allow-Credentials")
//                .allowedHeader("Content-Type"));
//    }
//}
