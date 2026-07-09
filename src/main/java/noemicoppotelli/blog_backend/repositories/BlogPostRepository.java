package noemicoppotelli.blog_backend.repositories;

import noemicoppotelli.blog_backend.entities.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BlogPostRepository extends JpaRepository<BlogPost, UUID> {
}
