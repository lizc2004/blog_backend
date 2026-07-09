package noemicoppotelli.blog_backend.payloads;

public record BlogPostPayload(
        String categoria,
        String titolo,
        String contenuto,
        int tempoDiLettura,
        Long authorId
) {
}
