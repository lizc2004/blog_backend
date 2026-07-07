package noemicoppotelli.blog_backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "blog_posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String categoria;
    private String titolo;
    private String cover;

    @Column(columnDefinition = "TEXT")
    private String contenuto;

    @Column(name = "tempo_di_lettura")
    private int tempoDiLettura;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @PrePersist
    public void generaCover() {
        this.cover = "https://picsum.photos/200/300";
    }
}
