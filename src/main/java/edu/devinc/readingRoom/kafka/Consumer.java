package edu.devinc.readingRoom.kafka;

import edu.devinc.readingRoom.entity.Book;
import edu.devinc.readingRoom.entity.BookDTO;
import edu.devinc.readingRoom.service.BookService;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Consumer {
    @Autowired
    private BookService bookService;

    @KafkaListener(topics = "msg")
    public void orderListener(ConsumerRecord<Long, String> record) {
        String newBook = record.value();

        // Парсим json
        BookDTO dto = parseJsonString(newBook);

        // Конвертим обратно в book
        Book book = convertFromDTO(dto);
        bookService.save(book);

    }

    private BookDTO parseJsonString(String newBook) {
        BookDTO dto = new BookDTO();
        JSONObject parsedObject = new JSONObject(newBook);
        dto.setAuthor(parsedObject.getString("author"));
        dto.setTitle(parsedObject.getString("title"));
        dto.setPublisher(parsedObject.getString("publisher"));
        dto.setYear(parsedObject.getInt("year"));
        dto.setTranslator(parsedObject.getString("translator"));
        dto.setDescription(parsedObject.getString("description"));
        dto.setFree(true);
        return dto;
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
