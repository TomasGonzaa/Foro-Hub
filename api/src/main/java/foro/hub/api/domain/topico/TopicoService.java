package foro.hub.api.domain.topico;

import foro.hub.api.domain.respuesta.DatosRespuestaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
public class TopicoService {

    private final TopicoRepository topicoRepository;

    public TopicoService(TopicoRepository topicoRepository) {
        this.topicoRepository = topicoRepository;
    }

    public DatosTopicoDTO getTopicoById(Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        return convertToDatosTopicoDTO(topico);
    }


    public Page<DatosTopicoDTO> getAllTopicosDTO(Pageable pageable) {
        Page<Topico> topicos = topicoRepository.findByActivoTrue(pageable);
        return topicos.map(this::convertToDatosTopicoDTO);
    }

    private DatosTopicoDTO convertToDatosTopicoDTO(Topico topico) {
            DatosTopicoDTO dto = new DatosTopicoDTO();
            dto.setId(topico.getId());
            dto.setNombre(topico.getUsuario().getNombre());
            dto.setCurso(topico.getCurso());
            dto.setTitulo(topico.getTitulo());
            dto.setMensaje(topico.getMensaje());
            dto.setFecha(topico.getFecha());
            dto.setActivo(topico.getActivo());
            dto.setRespuestas(topico.getRespuestas().stream()
                    .map(DatosRespuestaDTO::new)
                    .collect(Collectors.toList()));
            if (dto.getRespuestas().isEmpty()) {
                dto.setRespuestas(null);
                dto.setStatus("No hay respuestas aún.");
            }
            return dto;
    }

    public void desactivarTopic(Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        topico.desactivarTopico();
    }
}

