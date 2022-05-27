package com.microservices.bookmovie.premiereservice;

import com.microservices.bookmovie.premiereservice.cache.SeatCache;
import com.microservices.bookmovie.premiereservice.cache.ShowCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PremiereServiceApplication {


  public static void main(String[] args) {
    SpringApplication.run(PremiereServiceApplication.class, args);
  }

}
