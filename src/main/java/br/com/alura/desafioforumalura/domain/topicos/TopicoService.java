package br.com.alura.desafioforumalura.domain.topicos;

import br.com.alura.desafioforumalura.domain.TratamentoDeException;
import br.com.alura.desafioforumalura.domain.cursos.CursoService;
import br.com.alura.desafioforumalura.domain.cursos.DadosDetalhamentoCurso;
import br.com.alura.desafioforumalura.domain.resposta.DadosDetalhamentoResposta;
import br.com.alura.desafioforumalura.domain.usuario.DadosDetalhamentoUsuario;
import br.com.alura.desafioforumalura.domain.usuario.Usuario;
import br.com.alura.desafioforumalura.domain.usuario.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CursoService cursoService;

    @Transactional
    public Long cadastrar(DadosCadastroTopico dados, String usuarioLogado){
        if (dados.titulo() == null || dados.mensagem() == null){
            throw new IllegalArgumentException("Título e Mensagem são obrigatórios.");
        }
        Usuario usuario = usuarioService.findByLogin(usuarioLogado);
        if (repository.existsByTituloAndMensagem(dados.titulo(), dados.mensagem())){
            throw new IllegalArgumentException("Combinação de Título, Mensagem e Curso já existe.");
        }
        var curso = cursoService.buscarCurso(dados.nomeCurso());
        if (curso == null) {
            throw new TratamentoDeException(" O Curso nao consta na nossa lista de cursos cadastrado ");
        }
        Topico topico = new Topico();
        topico.setTitulo(dados.titulo());
        topico.setMensagem(dados.mensagem());
        topico.setAutor(usuario);
        topico.setCurso(curso);
        topico.setDataCriacao(LocalDateTime.now());
        topico.setStatus(true);
        Topico cadastrarTopico = repository.save(topico);
        return cadastrarTopico.getId();
    }

    public Page<DadosDetalhamentoTopicoAtivo> getAllTopicosAtivos(Pageable pageable, String nomeCurso, Integer ano) {
        Page<Topico> topicoPage;
        topicoPage = repository.findByStatusTrue(pageable);
        return topicoPage.map(topico -> new DadosDetalhamentoTopicoAtivo(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                new DadosDetalhamentoUsuario(topico.getAutor().getId(),topico.getAutor().getNome(),topico.getAutor().getEmail()),
                new DadosDetalhamentoCurso(topico.getCurso().getId(),topico.getCurso().getNome(),topico.getCurso().getCategoria())));
    }

    public Page<DadosListagemTopico> getAllTopicosOrderByDataCriacao(Pageable pageable, String cursoNome, Integer ano) {
        Page<Topico> topicosPage;
        if (cursoNome != null && ano != null) {
            topicosPage = repository.findByCursoNomeAndAno(cursoNome, ano, pageable);
        } else {
            topicosPage = repository.findAllByOrderByDataCriacaoAsc(pageable);
        }
        return topicosPage.map(topico -> new DadosListagemTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                new DadosDetalhamentoUsuario(topico.getAutor().getId(), topico.getAutor().getNome(), topico.getAutor().getEmail()),
                new DadosDetalhamentoCurso(topico.getCurso().getId(), topico.getCurso().getNome(), topico.getCurso().getCategoria()),
                topico.getStatus()
        ));
    }

    public void atualizar(Long topicoId, DadosDetalhamentoTopico dados){
        Optional<Topico> optionalTopico = repository.findById(topicoId);
        if (optionalTopico.isEmpty()) {
            throw new IllegalStateException("Tópico não encontrado");
        }
        Topico existingTopico = optionalTopico.get();
        existingTopico.setTitulo(dados.titulo());
        existingTopico.setMensagem(dados.mensagem());
        try {
            repository.save(existingTopico);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Título ou mensagem não podem ser nulos", e);
        }
    }

    @Transactional
    public void deletar(Long id) {
        Topico topico = repository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Tópico não encontrado"));
        topico.setStatus(false);
        repository.save(topico);
    }

    public Topico findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico não encontrado"));
    }

    public Optional<DadosDetalhamentoTopico> detalharTopico(Long id) {
        Optional<Topico> optionalTopico = repository.findByIdAndStatusTrue(id);
        return optionalTopico.map(this::mapToDetalhamentoDto);
    }

    private DadosDetalhamentoTopico mapToDetalhamentoDto(Topico topico) {
        List<DadosDetalhamentoResposta> respostas = topico.getResposta().stream()
                .map(resposta -> new DadosDetalhamentoResposta(
                        resposta.getId(),
                        resposta.getMensagem(),
                        resposta.getDataCriacao(),
                        resposta.getSolucao(),
                        resposta.getAutor().getId(),
                        resposta.getTopico().getId()
                ))
                .toList();

        return new DadosDetalhamentoTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                new DadosDetalhamentoUsuario(topico.getAutor().getId(), topico.getAutor().getNome(), topico.getAutor().getEmail()),
                new DadosDetalhamentoCurso(topico.getCurso().getId(), topico.getCurso().getNome(), topico.getCurso().getCategoria()),
                respostas,
                topico.getStatus());
    }
}