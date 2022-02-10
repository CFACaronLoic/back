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
      Book test = new Book("auteur", "titre", "langue", "sujet", "date");
      System.out.println(test.countWord("l","bonjour je m'appelle toto mon surnom est toto et j'aime les totos "));
      System.out.println(test.tabWord("bonjour je m'appelle toto mon surnom est toto et j'aime les totos "));
      log.info("Preloading " + theBook.save(new Book("auteur", "titre", "langue", "sujet", "date")));
      log.info("Preloading " + theBook.save(new Book("tintin","Toto", "fr", "blague", "2022")));
    };
  }
}