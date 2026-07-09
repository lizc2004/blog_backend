package noemicoppotelli.blog_backend.controllers;

import noemicoppotelli.blog_backend.entities.Author;
import noemicoppotelli.blog_backend.payloads.AuthorPayload;
import noemicoppotelli.blog_backend.payloads.AuthorResponsePayload;
import noemicoppotelli.blog_backend.services.AuthorsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorsService authorsService;

    public AuthorController(AuthorsService authorsService) {
        this.authorsService = authorsService;
    }

    @GetMapping
    public List<Author> getAll() {
        return authorsService.findAll();
    }

    @GetMapping("/{id}")
    public Author getById(@PathVariable UUID id) {
        return authorsService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorResponsePayload create(@RequestBody AuthorPayload payload) {
        Author saved = authorsService.save(payload);
        return new AuthorResponsePayload(saved.getId());
    }

    @PutMapping("/{id}")
    public Author update(@PathVariable UUID id, @RequestBody AuthorPayload payload) {
        return authorsService.update(id, payload);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        authorsService.delete(id);
    }
}
