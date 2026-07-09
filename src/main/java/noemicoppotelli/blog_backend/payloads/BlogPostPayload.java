package noemicoppotelli.blog_backend.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class BlogPostPayload {
    private String categoria;
    private String titolo;
    private String contenuto;
    private int tempoDiLettura;
    private UUID authorId;
}
