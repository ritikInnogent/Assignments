package com.libraryManagement.libraryManagement.Service;

import com.libraryManagement.libraryManagement.Entity.Author;
import com.libraryManagement.libraryManagement.Repository.IauthorRepository;
import com.libraryManagement.libraryManagement.Request.AuthorRequest;
import com.libraryManagement.libraryManagement.Response.AuthorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class authorServiceImpl implements IauthorService{

    @Autowired
    private IauthorRepository iauthorRepository;

    @Override
    public void insertAuthor(AuthorRequest request) {
        Author author = new Author();
        author.setEmail(request.getEmail());
        author.setName(request.getName());
        author.setId(request.getId());
        iauthorRepository.save(author);
    }

    @Override
    public List<AuthorResponse> showAllAuthor() {
        List<AuthorResponse>  authorResponses = new ArrayList<>();
        List<Author> authors = iauthorRepository.findAll();

        for (Author author : authors){
            AuthorResponse authorResponse = new AuthorResponse();
            authorResponse.setId(author.getId());
            authorResponse.setName(author.getName());
            authorResponse.setEmail(author.getEmail());
            authorResponses.add(authorResponse);
        }
        return authorResponses;
    }

    @Override
    public AuthorResponse getAuthorById(Integer id) {
       Author author = iauthorRepository.findById(id).get();
       AuthorResponse authorResponse = new AuthorResponse();
       authorResponse.setId(author.getId());
       authorResponse.setName(author.getName());
       authorResponse.setEmail(author.getEmail());
       return authorResponse;
    }

    @Override
    public AuthorResponse updateAuthor(Integer id,AuthorRequest request) {
        Author author = iauthorRepository.findById(id).get();
        author.setName(request.getName());
        author.setEmail(request.getEmail());
        iauthorRepository.save(author);
        AuthorResponse authorResponse = new AuthorResponse();
        authorResponse.setId(author.getId());
        authorResponse.setName(author.getName());
        authorResponse.setEmail(author.getEmail());
        return authorResponse;
    }

    @Override
    public String deleteAuthor(Integer id) {
        iauthorRepository.deleteById(id);
        return "Author deleted successfully";
    }
}
