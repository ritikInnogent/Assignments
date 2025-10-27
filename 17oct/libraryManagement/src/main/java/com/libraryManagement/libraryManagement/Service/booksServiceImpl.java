package com.libraryManagement.libraryManagement.Service;


import com.libraryManagement.libraryManagement.Entity.Books;
import com.libraryManagement.libraryManagement.Entity.Member;
import com.libraryManagement.libraryManagement.Exception.InsufficientStockException;
import com.libraryManagement.libraryManagement.Repository.IbooksRepository;
import com.libraryManagement.libraryManagement.Repository.ImemberRepository;
import com.libraryManagement.libraryManagement.Request.BookBorrowRequest;
import com.libraryManagement.libraryManagement.Request.BookRequest;
import com.libraryManagement.libraryManagement.Response.BookResponse;
import com.libraryManagement.libraryManagement.Response.BorrowedMemberResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;


import javax.naming.InsufficientResourcesException;
import java.util.ArrayList;
import java.util.List;

@Service
public class booksServiceImpl implements IbooksService
{
    @Autowired
    private IbooksRepository ibooksRepository;
    @Autowired
    private ImemberRepository imemberRepository;


    @Override
    public void insertBook(BookRequest request) {
        Books book = new Books();
        book.setBookTitle(request.getBookTitle());
        book.setBookDesc(request.getBookDesc());
        book.setBookPrice(request.getBookPrice());
        book.setBookStock(request.getBookStock());
        ibooksRepository.save(book);
    }

    @Override
    public List<BookResponse> showAllBooks() {
        List<BookResponse>  bookResponses = new ArrayList<>();
        List<Books> books = ibooksRepository.findAll();

        for (Books book : books){
            BookResponse bookResponse = new BookResponse();
            bookResponse.setBookTitle(book.getBookTitle());
            bookResponse.setBookDesc(book.getBookDesc());
            bookResponse.setBookPrice(book.getBookPrice());
            bookResponse.setBookStock(book.getBookStock());
            bookResponses.add(bookResponse);
        }
        return bookResponses;
    }


    @Override
    public BookResponse findBookById(Integer id) {
        Books book = ibooksRepository.findById(id).get();
        BookResponse bookResponse = new BookResponse();
        bookResponse.setBookTitle(book.getBookTitle());
        bookResponse.setBookDesc(book.getBookDesc());
        bookResponse.setBookPrice(book.getBookPrice());
        bookResponse.setBookStock(book.getBookStock());
        return bookResponse;
    }

    @Override
    public BookResponse updateBook(Integer id,BookRequest request) {
        Books book = ibooksRepository.findById(id).get();
        book.setBookTitle(request.getBookTitle());
        book.setBookDesc(request.getBookDesc());
        book.setBookPrice(request.getBookPrice());
        book.setBookStock(request.getBookStock());
        ibooksRepository.save(book);
        BookResponse bookResponse = new BookResponse();
        bookResponse.setBookId(book.getId());
        bookResponse.setBookTitle(book.getBookTitle());
        bookResponse.setBookDesc(book.getBookDesc());
        bookResponse.setBookPrice(book.getBookPrice());
        bookResponse.setBookStock(book.getBookStock());
        return bookResponse;
    }

    @Override
    public String deleteBook(Integer id) {
        ibooksRepository.deleteById(id);
        return "Book deleted successfully";
    }

    @Override
    public void insertBookBorrow(BookBorrowRequest request){
        Member member =   imemberRepository.findById(request.getMember_id()).get();
        Books book = ibooksRepository.findById(request.getBook_id()).get();
        if(book.bookStock < 1){
            throw new InsufficientStockException("stock is empty");
        }
        book.bookStock= book.bookStock - 1;

        member.addBook(book);
        ibooksRepository.save(book);
        imemberRepository.save(member);
    }
    

    public List<BorrowedMemberResponse> showBorrowBooks() {
        System.out.println("---- Fetching Borrowed Books ----");
        List<BorrowedMemberResponse> result = ibooksRepository.showBorrowBook();
        System.out.println("Fetched " + result.size() + " records");
        return result;
    }

}
