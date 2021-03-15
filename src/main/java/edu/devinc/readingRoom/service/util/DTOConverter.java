package edu.devinc.readingRoom.service.util;

import edu.devinc.readingRoom.entity.Book;
import edu.devinc.readingRoom.entity.BookDTO;
import edu.devinc.readingRoom.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DTOConverter {
    @Autowired
    OrderService orderService;

    public BookDTO convertToDTO(Book book) {
        BookDTO dto = new BookDTO();
        dto.setAuthor(book.getAuthor());
        dto.setBookId(book.getBookId());
        dto.setDescription(book.getDescription());
        dto.setPublisher(book.getPublisher());
        dto.setTitle(book.getTitle());
        dto.setYear(book.getYear());
        dto.setTranslator(book.getTranslator());
        dto.setFree(book.isFree());
        if (!book.isFree()) {
            dto.setUserName(book.getOrders().get(0).getUserName());
        }
        return dto;
    }
}
