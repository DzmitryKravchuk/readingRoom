package edu.devinc.readingRoom.kafka;

import edu.devinc.readingRoom.entity.Book;
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

        // Парсим json и Конвертим обратно в book
        Book book = parseJsonString(newBook);

        bookService.save(book);

    }

    private Book parseJsonString(String newBook) {
        Book book = new Book();
        JSONObject parsedObject = new JSONObject(newBook);
        book.setAuthor(parsedObject.getString("author"));
        book.setTitle(parsedObject.getString("title"));
        book.setPublisher(parsedObject.getString("publisher"));
        book.setYear(parsedObject.getInt("year"));
        book.setTranslator(parsedObject.getString("translator"));
        book.setDescription(parsedObject.getString("description"));
        book.setFree(true);
        return book;
    }


}
