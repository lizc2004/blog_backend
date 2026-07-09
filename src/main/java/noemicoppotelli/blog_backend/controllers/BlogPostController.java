package noemicoppotelli.blog_backend.controllers;

import noemicoppotelli.blog_backend.entities.BlogPost;
import noemicoppotelli.blog_backend.payloads.BlogPostPayload;
import noemicoppotelli.blog_backend.payloads.BlogPostResponsePayload;
import noemicoppotelli.blog_backend.services.BlogPostsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/blogPosts")
public class BlogPostController {

    private final BlogPostsService blogPostsService;

    public BlogPostController(BlogPostsService blogPostsService) {
        this.blogPostsService = blogPostsService;
    }

    @GetMapping
    public Page<BlogPost> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "titolo") String sortBy
    ) {
        return blogPostsService.findAll(PageRequest.of(page, size, Sort.by(sortBy)));
    }

    @GetMapping("/{id}")
    public BlogPost getById(@PathVariable UUID id) {
        return blogPostsService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BlogPostResponsePayload create(@RequestBody BlogPostPayload payload) {
        BlogPost saved = blogPostsService.save(payload);
        return new BlogPostResponsePayload(saved.getId());
    }

    @PutMapping("/{id}")
    public BlogPost update(@PathVariable UUID id, @RequestBody BlogPostPayload payload) {
        return blogPostsService.update(id, payload);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        blogPostsService.delete(id);
    }
}
