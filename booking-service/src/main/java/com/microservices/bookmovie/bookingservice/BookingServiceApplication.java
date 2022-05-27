package com.microservices.bookmovie.bookingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableHystrix
@EnableDiscoveryClient
public class BookingServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(BookingServiceApplication.class, args);
  }

}
