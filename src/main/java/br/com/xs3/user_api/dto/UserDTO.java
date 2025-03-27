package br.com.xs3.user_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public  class UserDTO	{
    private long	id;

    @NotBlank(message = "{nome.required}")
    private	String	nome;
    @NotBlank(message = "{cpf.required}")
    private	String	cpf;
    private	String	endereco;
    @NotBlank
    @Email(message = "{email.required}")
    private	String	email;
    private	String	telefone;
    private	LocalDateTime	dataCadastro;



}