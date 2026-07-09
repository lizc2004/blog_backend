package noemicoppotelli.blog_backend.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@AllArgsConstructor
@Getter
@ToString
public class AuthorResponsePayload {
    private UUID id;
}
