package noemicoppotelli.blog_backend.controllers;

import noemicoppotelli.blog_backend.entities.BlogPost;
import noemicoppotelli.blog_backend.repositories.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/blogPosts")
public class BlogPostController {

    @Autowired
    private BlogPostRepository blogPostRepository;

    @GetMapping
    public List<BlogPost> getAll() {
        return blogPostRepository.findAll();
    }

    @GetMapping("/{id}")
    public BlogPost getById(@PathVariable long id) {
        return blogPostRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "BlogPost con id " + id + " non trovato"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BlogPost create(@RequestBody BlogPost blogPost) {
        return blogPostRepository.save(blogPost);
    }

    @PutMapping("/{id}")
    public BlogPost update(@PathVariable long id, @RequestBody BlogPost payload) {
        BlogPost blogPost = getById(id);
        blogPost.setCategoria(payload.getCategoria());
        blogPost.setTitolo(payload.getTitolo());
        blogPost.setContenuto(payload.getContenuto());
        blogPost.setTempoDiLettura(payload.getTempoDiLettura());
        blogPost.setAuthor(payload.getAuthor());
        return blogPostRepository.save(blogPost);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        BlogPost blogPost = getById(id);
        blogPostRepository.delete(blogPost);
    }
}
