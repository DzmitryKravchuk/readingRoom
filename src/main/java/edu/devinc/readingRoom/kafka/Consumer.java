package edu.devinc.readingRoom.kafka;

import edu.devinc.readingRoom.entity.Book;
import edu.devinc.readingRoom.entity.BookDTO;
import edu.devinc.readingRoom.service.BookService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Consumer {
    @Autowired
    private BookService bookService;

    @KafkaListener(topics = "msg")
    public void orderListener(ConsumerRecord<Long, BookDTO> record) {
        Book book = convertFromDTO(record.value());
        bookService.save(book);

    }

    private Book convertFromDTO(BookDTO dto) {
        Book book = new Book();
        book.setBookId(dto.getBookId());
        book.setAuthor(dto.getAuthor());
        book.setTranslator(dto.getTranslator());
        book.setTitle(dto.getTitle());
        book.setDescription(dto.getDescription());
        book.setPublisher(dto.getPublisher());
        book.setYear(dto.getYear());
        book.setFree(dto.isFree());

        return book;
    }
}
