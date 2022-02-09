package com.example.back;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(BookRepository theBook) {

    return args -> {
      log.info("Preloading " + theBook.save(new Book("auteur", "titre", "langue", "sujet", "date")));
      log.info("Preloading " + theBook.save(new Book("tintin","Toto", "fr", "blague", "2022")));
    };
  }
}