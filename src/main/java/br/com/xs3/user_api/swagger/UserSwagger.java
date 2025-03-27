package br.com.xs3.user_api.swagger;

import br.com.xs3.user_api.dto.UserDTO;
import br.com.xs3.user_api.exception.ExceptionValidationRecord;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "Cadastro de Usuários", description = """
        Essa fucionalidade é responsável pelo CRRD de Usuários.
        """)

@ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class))),
        @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ExceptionValidationRecord.class)))),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionValidationRecord.class)))
})

public @interface UserSwagger {
}
