package noemicoppotelli.blog_backend.controllers;

import noemicoppotelli.blog_backend.entities.Author;
import noemicoppotelli.blog_backend.entities.BlogPost;
import noemicoppotelli.blog_backend.payloads.BlogPostPayload;
import noemicoppotelli.blog_backend.repositories.AuthorRepository;
import noemicoppotelli.blog_backend.repositories.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/blogPosts")
public class BlogPostController {

    @Autowired
    private BlogPostRepository blogPostRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping
    public Page<BlogPost> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return blogPostRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy)));
    }

    @GetMapping("/{id}")
    public BlogPost getById(@PathVariable long id) {
        return blogPostRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "BlogPost con id " + id + " non trovato"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BlogPost create(@RequestBody BlogPostPayload payload) {
        Author author = findAuthorOrThrow(payload.authorId());

        BlogPost blogPost = new BlogPost();
        blogPost.setCategoria(payload.categoria());
        blogPost.setTitolo(payload.titolo());
        blogPost.setContenuto(payload.contenuto());
        blogPost.setTempoDiLettura(payload.tempoDiLettura());
        blogPost.setAuthor(author);
        return blogPostRepository.save(blogPost);
    }

    @PutMapping("/{id}")
    public BlogPost update(@PathVariable long id, @RequestBody BlogPostPayload payload) {
        BlogPost blogPost = getById(id);
        Author author = findAuthorOrThrow(payload.authorId());

        blogPost.setCategoria(payload.categoria());
        blogPost.setTitolo(payload.titolo());
        blogPost.setContenuto(payload.contenuto());
        blogPost.setTempoDiLettura(payload.tempoDiLettura());
        blogPost.setAuthor(author);
        return blogPostRepository.save(blogPost);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        BlogPost blogPost = getById(id);
        blogPostRepository.delete(blogPost);
    }

    private Author findAuthorOrThrow(Long authorId) {
        return authorRepository.findById(authorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Author con id " + authorId + " non trovato"));
    }
}
