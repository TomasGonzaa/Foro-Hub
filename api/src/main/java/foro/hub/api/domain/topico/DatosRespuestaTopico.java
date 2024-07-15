package foro.hub.api.domain.topico;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import foro.hub.api.domain.respuesta.Respuesta;
import foro.hub.api.domain.usuarios.Usuario;

import java.time.LocalDateTime;
import java.util.List;

public record DatosRespuestaTopico(Long id,
                                   String nombre,
                                   Curso curso,
                                   String titulo,
                                   String mensaje,
                                   LocalDateTime fecha) {

}
