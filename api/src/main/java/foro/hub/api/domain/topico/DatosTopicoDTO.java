package foro.hub.api.domain.topico;

import com.fasterxml.jackson.annotation.JsonInclude;
import foro.hub.api.domain.respuesta.DatosRespuestaDTO;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;


@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DatosTopicoDTO {
    private Long id;
    private String nombre;
    private Curso curso;
    private String titulo;
    private String mensaje;
    private LocalDateTime fecha;
    private List<DatosRespuestaDTO> respuestas;
    private String status;
    private Boolean activo;

    public DatosTopicoDTO() {
    }

    public DatosTopicoDTO(Long id, String nombre, Curso curso, String titulo, String mensaje, LocalDateTime fecha, List<DatosRespuestaDTO> respuestas) {
        this.id = id;
        this.nombre = nombre;
        this.curso = curso;
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.respuestas = respuestas;
    }
}