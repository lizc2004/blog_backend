package noemicoppotelli.blog_backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "authors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    @Id
    @GeneratedValue
    private UUID id;

    private String nome;
    private String cognome;
    private String email;

    @Column(name = "data_di_nascita")
    private LocalDate dataDiNascita;

    private String avatar;

    @PrePersist
    public void generaAvatar() {
        String nomeCompleto = (nome + "+" + cognome).replace(" ", "+");
        this.avatar = "https://ui-avatars.com/api/?name=" + nomeCompleto;
    }
}
