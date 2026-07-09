package noemicoppotelli.blog_backend.repositories;
import noemicoppotelli.blog_backend.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {
    boolean existsByEmail(String email);
}


