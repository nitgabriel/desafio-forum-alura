package br.com.alura.desafioforumalura.domain.topicos;

import br.com.alura.desafioforumalura.domain.cursos.Curso;
import br.com.alura.desafioforumalura.domain.resposta.Resposta;
import br.com.alura.desafioforumalura.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "topico")
@Entity(name = "Topico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;

    @Column(name = "dataCriacao")
    private LocalDateTime dataCriacao;

    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "autor")
    private Usuario autor;

    @ManyToOne
    @JoinColumn(name = "curso")
    private Curso curso;

    @OneToMany(mappedBy = "topico")
    private List<Resposta> resposta = new ArrayList<>();

}