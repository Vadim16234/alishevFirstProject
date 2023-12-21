package springcourse.models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class Book {
    private int id;

    @NotEmpty(message = "Укажите название книги")
    @Size(min = 2, max = 100, message = "Название книги должно быть длинной от 2 до 100 символов")
    private String title;

    @NotEmpty(message = "Укажите имя автора")
    @Size(min = 2, max = 100, message = "Имя автора должно быть длинной от 2 до 100 символов")
    private String author;

    @Min(value = 1600, message = "Возраст книги должен быть больше, чем 1600")
    private int year;

    public Book() {}

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }
}
