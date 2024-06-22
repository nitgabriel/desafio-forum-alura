package br.com.alura.desafioforumalura.domain.cursos;

import br.com.alura.desafioforumalura.domain.topicos.Topico;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Table(name= "curso")
@Entity(name = "Curso")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String categoria;

    @OneToMany(mappedBy = "curso")
    private List<Topico> topicos = new ArrayList<>();

    public Curso(Long id, String nome, String categoria) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
    }

}