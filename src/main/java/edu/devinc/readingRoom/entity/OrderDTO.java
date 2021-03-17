package edu.devinc.readingRoom.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderDTO {
    @NotNull
    private String userName;
    @NotNull
    private Integer bookId;
}
