package br.com.xs3.user_api.model;

import br.com.xs3.user_api.dto.UserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity

public class User {
    @Id
    @GeneratedValue(strategy	=	GenerationType.IDENTITY)
    private long	id;
    private	String	nome;
    private	String	cpf;
    private	String	endereco;
    private	String	email;
    private	String	telefone;
    private LocalDateTime dataCadastro;

    @PrePersist
    protected void onCreate() {
        this.dataCadastro = LocalDateTime.now();
    }

    public User() {
    }

    public User(long id, String nome, String cpf, String endereco, String email, String telefone, LocalDateTime dataCadastro) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.email = email;
        this.telefone = telefone;
        this.dataCadastro = dataCadastro;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}