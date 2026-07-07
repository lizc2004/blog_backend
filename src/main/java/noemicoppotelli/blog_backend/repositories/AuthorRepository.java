package noemicoppotelli.blog_backend.repositories;

import noemicoppotelli.blog_backend.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
