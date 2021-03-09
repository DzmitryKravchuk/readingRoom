package edu.devinc.readingRoom.repository;

import edu.devinc.readingRoom.entity.Book;
import edu.devinc.readingRoom.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
