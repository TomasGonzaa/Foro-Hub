package foro.hub.api.domain.respuesta;

import foro.hub.api.domain.topico.DatosTopicoDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor

public class DatosRespuestaDTO{
    @NotBlank String mensaje;
    @NotBlank String usuario;
    @NotBlank LocalDateTime fecha;


        public DatosRespuestaDTO(String mensaje,String usuario, LocalDateTime fecha){
            this.mensaje = mensaje;
            this.usuario = usuario;
            this.fecha = fecha;
        }

        public DatosRespuestaDTO(Respuesta respuesta){
            this.mensaje = respuesta.getMensaje();
            this.usuario = respuesta.getUsuario().getNombre();
            this.fecha = respuesta.getFecha();
        }

}
