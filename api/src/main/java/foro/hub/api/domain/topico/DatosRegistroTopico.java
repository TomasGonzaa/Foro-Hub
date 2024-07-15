package foro.hub.api.domain.topico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


//PARA MAPEAR INFORMACION DEL POST
public record DatosRegistroTopico(
        @NotNull Long usuario_id,
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotNull @Valid Curso curso) {
}
