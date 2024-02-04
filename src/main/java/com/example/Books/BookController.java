package com.example.Books;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    @Autowired
    private final BookRepository bookRepository;

    @GetMapping
    public ResponseEntity<?> getAllBooks() {
        try{
            List<Book> bookList = bookRepository.findAll();
            return ResponseEntity.ok(bookList);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        try{
            // Thử tìm một cuốn sách trong repository dựa trên ID
            Book book = bookRepository.findById(id).orElse(null);

            if (book != null) {
                // Nếu tìm thấy sách, trả về một ResponseEntity chứa thông tin sách và mã trạng thái 200 (OK)
                return ResponseEntity.ok(book);
            } else {
                // Nếu không tìm thấy sách, trả về một ResponseEntity với mã trạng thái lỗi 404 (Not Found)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không thể tìm thấy sách!");
            }

            //return ResponseEntity.ok(book);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        Book existingBook = bookRepository.findById(id).orElse(null);
        if (existingBook != null) {
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setPublishedDate(updatedBook.getPublishedDate());
            existingBook.setIsbn(updatedBook.getIsbn());
            existingBook.setGenre(updatedBook.getGenre());
            existingBook.setQuantity(updatedBook.getQuantity());
            existingBook.setShelfLocation(updatedBook.getShelfLocation());
            return bookRepository.save(existingBook);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
    }
}
