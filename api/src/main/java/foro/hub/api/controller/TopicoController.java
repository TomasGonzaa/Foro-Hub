package foro.hub.api.controller;

import foro.hub.api.domain.respuesta.DatosRespuestaDTO;
import foro.hub.api.domain.respuesta.Respuesta;
import foro.hub.api.domain.topico.*;
import foro.hub.api.domain.usuarios.Usuario;
import foro.hub.api.domain.usuarios.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/topicos")
public class TopicoController {


    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private final TopicoService topicoService;


    public TopicoController(TopicoRepository topicoRepository, TopicoService topicoService){
        this.topicoRepository = topicoRepository;
        this.topicoService = topicoService;
    }

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico, UriComponentsBuilder uriComponentsBuilder) {
        Usuario usuario = usuarioRepository.findById(datosRegistroTopico.usuario_id())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Topico topico = new Topico(datosRegistroTopico, usuario, new ArrayList<Respuesta>());
        topicoRepository.save(topico);
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topico.getId(),topico.getUsuario().getNombre(),
                topico.getCurso(), topico.getTitulo(), topico.getMensaje(), topico.getFecha());
        URI url = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }


    @GetMapping
    public ResponseEntity<Page<DatosTopicoDTO>> getAllTopicosActivos(Pageable pageable){
        Page<DatosTopicoDTO> topicosPage = topicoService.getAllTopicosDTO(pageable);
        return ResponseEntity.ok(topicosPage);
    }



    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico){
        Topico topico = topicoRepository.getReferenceById(datosActualizarTopico.id());
        topico.actualizarDatos(datosActualizarTopico);
        return ResponseEntity.ok(new DatosRespuestaTopico(topico.getId(),topico.getUsuario().getNombre(),
                topico.getCurso(), topico.getTitulo(), topico.getMensaje(), topico.getFecha()));
    }


    //DELETE LOGICO
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id){
       topicoService.desactivarTopic(id);
       return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<DatosTopicoDTO> retornaDatosTopico(@PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        var datosTopico = new DatosTopicoDTO(topico.getId(),topico.getUsuario().getNombre(),
                topico.getCurso(), topico.getTitulo(), topico.getMensaje(), topico.getFecha(),topico.getRespuestas().stream()
                .map(DatosRespuestaDTO::new)
                .collect(Collectors.toList()));
        return ResponseEntity.ok(datosTopico);
    }

}


