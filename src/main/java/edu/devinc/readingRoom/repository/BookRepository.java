package edu.devinc.readingRoom.repository;

import edu.devinc.readingRoom.entity.Book;

import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, Integer> {

}
