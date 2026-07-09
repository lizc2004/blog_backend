package noemicoppotelli.blog_backend.services;

import noemicoppotelli.blog_backend.entities.Author;
import noemicoppotelli.blog_backend.entities.BlogPost;
import noemicoppotelli.blog_backend.exceptions.NotFoundException;
import noemicoppotelli.blog_backend.payloads.BlogPostPayload;
import noemicoppotelli.blog_backend.repositories.AuthorRepository;
import noemicoppotelli.blog_backend.repositories.BlogPostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BlogPostsService {

    private final BlogPostRepository blogPostRepository;
    private final AuthorRepository authorRepository;

    public BlogPostsService(BlogPostRepository blogPostRepository, AuthorRepository authorRepository) {
        this.blogPostRepository = blogPostRepository;
        this.authorRepository = authorRepository;
    }

    public BlogPost save(BlogPostPayload payload) {
        Author author = findAuthorOrThrow(payload.getAuthorId());

        BlogPost blogPost = new BlogPost();
        blogPost.setCategoria(payload.getCategoria());
        blogPost.setTitolo(payload.getTitolo());
        blogPost.setContenuto(payload.getContenuto());
        blogPost.setTempoDiLettura(payload.getTempoDiLettura());
        blogPost.setAuthor(author);
        return blogPostRepository.save(blogPost);
    }

    public Page<BlogPost> findAll(Pageable pageable) {
        return blogPostRepository.findAll(pageable);
    }

    public BlogPost findById(UUID id) {
        return blogPostRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("BlogPost con id " + id + " non trovato"));
    }

    public BlogPost update(UUID id, BlogPostPayload payload) {
        BlogPost blogPost = findById(id);
        Author author = findAuthorOrThrow(payload.getAuthorId());

        blogPost.setCategoria(payload.getCategoria());
        blogPost.setTitolo(payload.getTitolo());
        blogPost.setContenuto(payload.getContenuto());
        blogPost.setTempoDiLettura(payload.getTempoDiLettura());
        blogPost.setAuthor(author);
        return blogPostRepository.save(blogPost);
    }

    public void delete(UUID id) {
        BlogPost blogPost = findById(id);
        blogPostRepository.delete(blogPost);
    }

    private Author findAuthorOrThrow(UUID authorId) {
        return authorRepository.findById(authorId)
                .orElseThrow(() -> new NotFoundException("Author con id " + authorId + " non trovato"));
    }
}
