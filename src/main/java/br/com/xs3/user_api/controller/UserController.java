package br.com.xs3.user_api.controller;

import br.com.xs3.user_api.dto.UserDTO;

import br.com.xs3.user_api.exception.UserNotFoundException;
import br.com.xs3.user_api.service.impl.UserService;
import br.com.xs3.user_api.swagger.UserSwagger;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
@Tag(name = "Usuarios")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;


    @GetMapping
    @UserSwagger
    @Operation(summary =  "Listar todos os usuários")
    public	List<UserDTO>	getUsers() {
        return	userService.findAll();
    }
    @GetMapping("/{id}")
    @UserSwagger
    @Operation(summary =  "Listar usuários por ID")
    public	UserDTO	findById(@PathVariable	Long	id)  {
        try {
            return	userService.findById(id);
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @UserSwagger
    @Operation(summary =  "Criar um usuário")
    public	UserDTO	newUser(@RequestBody	@Valid	UserDTO	userDTO) {
        return	userService.save(userDTO);
    }
    @GetMapping("/cpf/{cpf}")
    @UserSwagger
    @Operation(summary = "Listar usuários por CPF")
    public	List<UserDTO>	findByCpf(@PathVariable	String	cpf)  {
        return	userService.findByCpf(cpf);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @UserSwagger
    @Operation(summary =  "Excluir Usuário")
    public	void	delete(@PathVariable	Long	id)	throws	UserNotFoundException	{
        userService.delete(id);
    }
    @GetMapping("/search")
    @UserSwagger
    @Operation(summary =  "Listar Usuários por nome")
    public	List<UserDTO>	queryByName(
            @RequestParam(name="nome",	required	=	true)	String	nome) {
        return	userService.queryByName(nome);
    }

    @PutMapping("/{id}")
    @UserSwagger
    @Operation(summary = "Atualizar Usuários")
    public	UserDTO	editUser(@PathVariable	Long	id,
                                  @RequestBody	UserDTO	userDTO) throws UserNotFoundException {
        return	userService.update(id,	userDTO);
    }

    @GetMapping("/pageable")
    @UserSwagger
    @Operation(summary =  "Listar Usuários paginados")
    public Page<UserDTO> getUsersPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                      @RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage)
    {
        Pageable pageable = Pageable.ofSize(linesPerPage).withPage(page);
        return	userService.findAll(pageable);
    }


}



    /*

    @PostConstruct
    public void initiateList() {


    }

   */