package com.example.back;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

@Entity
class Book {

  private static final Logger log = LoggerFactory.getLogger(Book.class);
  private @Id @GeneratedValue Long id;
  private String author;
  private String title;
  private String language;
  private String subject;
  private String releaseDate;

  Book() {}

  Book(String author, String title, String language, String subject, String releaseDate) {

    this.author = author;
    this.title = title;
    this.language = language;
    this.subject = subject;
    this.releaseDate = releaseDate;
  }

  public Long getId() {
    return this.id;
  }

  public String getAuthor() {
    return this.author;
  }

  public String getTitle() {
    return this.title;
  }

   public String getLanguage() {
    return this.language;
  }

   public String getSubject() {
    return this.subject;
  }

   public String getRealiseDate() {
    return this.releaseDate;
  }

  public void setId(Long id) {
    this.id = id;
  }
  public void setRealiseDate(String releaseDate) {
    this.releaseDate = releaseDate;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public void setTitle(String title) {
    this.title = title;
  }

   public void setLanguage(String language) {
    this.language = language;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o)
      return true;
    if (!(o instanceof Book))
      return false;
    Book book = (Book) o;
    return Objects.equals(this.id, book.id) && Objects.equals(this.author, book.author);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.author, this.title, this.language, this.subject, this.releaseDate);
  }

  @Override
  public String toString() {
    return "Book{" + "id=" + this.id + ", author='" + this.author + '\'' + ", title='" + this.language + '\'' + this.subject + '\'' + this.releaseDate + '\'' +'}';
  }

  public int countWord(String subStr, String str){
    return str.split(Pattern.quote(subStr), -1).length - 1;
  }

  public HashMap<String, ArrayList<String>> tabWord(String str) {
    HashMap<String, ArrayList<String>> tabWords = new HashMap<String, ArrayList<String>>();
    String[] words = str.split(" ", 0);
    int totalWord = words.length;

    ArrayList<String> word = new ArrayList<String>();
    ArrayList<String> countWord = new ArrayList<String>();
    ArrayList<String> statWord = new ArrayList<String>();
    for (int i = 0;i<words.length;i++) { 
        if (! word.contains(words[i])) {
          word.add(words[i]);
          int count = countWord(words[i],str);
          countWord.add(String.valueOf(count));
          double stat = (double)count/(double)totalWord;
          statWord.add(String.valueOf(stat));
        }
    }
    tabWords.put("word", word);
    tabWords.put("count", countWord);
    tabWords.put("stat", statWord);
    return tabWords;
  }
}