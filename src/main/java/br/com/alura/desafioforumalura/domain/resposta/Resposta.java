package br.com.alura.desafioforumalura.domain.resposta;

import br.com.alura.desafioforumalura.domain.topicos.Topico;
import br.com.alura.desafioforumalura.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name= "resposta")
@Entity(name = "Resposta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensagem;

    @Column(name = "dataCriacao")
    private LocalDateTime dataCriacao;

    private Boolean solucao;

    @ManyToOne
    @JoinColumn(name = "autor")
    private Usuario autor;

    @ManyToOne
    @JoinColumn(name = "topico")
    private Topico topico;

}