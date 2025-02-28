package br.com.xs3.user_api.controller;

import br.com.xs3.user_api.dto.UserDTO;

import br.com.xs3.user_api.exception.UserNotFoundException;
import br.com.xs3.user_api.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public	List<UserDTO>	getUsers() {
        return	userService.getAll();
    }
    @GetMapping("/{id}")
    public	UserDTO	findById(@PathVariable	Long	id)  {
        try {
            return	userService.findById(id);
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public	UserDTO	newUser(@RequestBody	@Valid	UserDTO	userDTO) {
        return	userService.save(userDTO);
    }
    @GetMapping("/cpf/{cpf}")
    public	UserDTO	findByCpf(@PathVariable	String	cpf) {
        return	userService.findByCpf(cpf);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public	void	delete(@PathVariable	Long	id)	throws	UserNotFoundException	{
        userService.delete(id);
    }
    @GetMapping("/search")
    public	List<UserDTO>	queryByName(
            @RequestParam(name="nome",	required	=	true)	String	nome) {
        return	userService.queryByName(nome);
    }

    @PutMapping("/{id}")
    public	UserDTO	editUser(@PathVariable	Long	id,
                                  @RequestBody	UserDTO	userDTO) throws UserNotFoundException {
        return	userService.editUser(id,	userDTO);
    }

    @GetMapping("/pageable")
    public Page<UserDTO> getUsersPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                      @RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage)
    {
        Pageable pageable = Pageable.ofSize(linesPerPage).withPage(page);
        return	userService.getAllPage(pageable);
    }


}



    /*
    public static List<UserDTO> usuarios = new ArrayList<UserDTO>();

    @PostConstruct
    public void initiateList() {
        UserDTO userDTO = new UserDTO();
        userDTO.setNome("Eduardo");
        userDTO.setCpf("123");
        userDTO.setEndereco("Rua	a");
        userDTO.setEmail("eduardo@email.com");
        userDTO.setTelefone("1234-3454");
        userDTO.setDataCadastro(LocalDateTime.now());
        UserDTO userDTO2 = new UserDTO();
        userDTO2.setNome("Luiz");
        userDTO2.setCpf("456");
        userDTO2.setEndereco("Rua	b");
        userDTO2.setEmail("luiz@email.com");
        userDTO2.setTelefone("1234-3454");
        userDTO2.setDataCadastro(LocalDateTime.now());

        UserDTO	userDTO3	=	new	UserDTO();
        userDTO3.setNome("Bruna");
        userDTO3.setCpf("678");
        userDTO3.setEndereco("Rua	c");
        userDTO3.setEmail("bruna@email.com");

        userDTO3.setTelefone("1234-3454");
        userDTO3.setDataCadastro(LocalDateTime.now());
        usuarios.add(userDTO);
        usuarios.add(userDTO2);
        usuarios.add(userDTO3);

    }

    @GetMapping
    public	List<UserDTO>	getUsers() {
        return	usuarios;
    }

    @GetMapping("/{cpf}")
    public	UserDTO	getUsersFiltro(@PathVariable String	cpf) throws UserNotFoundException {

            return	usuarios
                    .stream()
                    .filter(userDTO	->	userDTO.getCpf().equals(cpf))
                    .findFirst()
                    .orElseThrow(()	->	new UserNotFoundException("User	not	found." + cpf ));

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public	UserDTO	inserir(@RequestBody @Valid UserDTO	userDTO) {
        userDTO.setDataCadastro(LocalDateTime.now());
        usuarios.add(userDTO);
        return	userDTO;
    }


    @DeleteMapping("/{cpf}")
    public	boolean	remover(@PathVariable	String	cpf) {
        return	usuarios
                .removeIf(userDTO	->	userDTO.getCpf().equals(cpf));
    }

     */

