package com.example.newapp;

import com.example.newapp.book.repository.BookRepository;
import com.example.newapp.model.book.Book;
import com.example.newapp.model.user.User;
import com.example.newapp.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@RestController
public class NewappApplication {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void addData2Db(){
        userRepository.saveAll(Stream.of(
                new User(12,"jon"),new User(13,"hon")).collect(Collectors.toList()));
        bookRepository.saveAll(Stream.of(
                new Book(01,"java"),new Book(02,"spring")).collect(Collectors.toList()));
    }
    @GetMapping("/getUsers")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/getBooks")
    public List<Book> getBooks(){
        return bookRepository.findAll();
    }

    public static void main(String[] args) {
        SpringApplication.run(NewappApplication.class, args);
    }

}
