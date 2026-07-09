package noemicoppotelli.blog_backend.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class AuthorPayload {
    private String nome;
    private String cognome;
    private String email;
    private LocalDate dataDiNascita;
}
