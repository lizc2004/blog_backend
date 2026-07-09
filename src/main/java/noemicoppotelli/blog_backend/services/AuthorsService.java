package noemicoppotelli.blog_backend.services;

import noemicoppotelli.blog_backend.entities.Author;
import noemicoppotelli.blog_backend.exceptions.BadRequestException;
import noemicoppotelli.blog_backend.exceptions.NotFoundException;
import noemicoppotelli.blog_backend.payloads.AuthorPayload;
import noemicoppotelli.blog_backend.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AuthorsService {

    private final AuthorRepository authorRepository;

    public AuthorsService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author save(AuthorPayload payload) {
        if (authorRepository.existsByEmail(payload.getEmail()))
            throw new BadRequestException("L'indirizzo email " + payload.getEmail() + " è già utilizzato!");

        Author author = new Author();
        author.setNome(payload.getNome());
        author.setCognome(payload.getCognome());
        author.setEmail(payload.getEmail());
        author.setDataDiNascita(payload.getDataDiNascita());
        return authorRepository.save(author);
    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public Author findById(UUID id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Author con id " + id + " non trovato"));
    }

    public Author update(UUID id, AuthorPayload payload) {
        Author author = findById(id);
        author.setNome(payload.getNome());
        author.setCognome(payload.getCognome());
        author.setEmail(payload.getEmail());
        author.setDataDiNascita(payload.getDataDiNascita());
        return authorRepository.save(author);
    }

    public void delete(UUID id) {
        Author author = findById(id);
        authorRepository.delete(author);
    }
}
