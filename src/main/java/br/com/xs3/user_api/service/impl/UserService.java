package br.com.xs3.user_api.service.impl;


import br.com.xs3.user_api.dto.UserDTO;
import br.com.xs3.user_api.exception.UserNotFoundException;
import br.com.xs3.user_api.model.User;
import br.com.xs3.user_api.repository.UserRepository;
import br.com.xs3.user_api.service.IService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements IService<UserDTO,UserDTO> {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    @Override
    public List<UserDTO> findAll() {

        log.info("findAll Inicio" );

        List<User> usuarios = userRepository.findAll();

        log.info("findAll retorno banco" );

        return usuarios
                .stream()
                .map(usuario ->modelMapper.map(usuario,UserDTO.class))
                .collect(Collectors.toList());
    }


    @Override
    public UserDTO save(UserDTO userDTO) {

        log.info("save Inicio " + userDTO.getNome() );
        User user = userRepository.save(modelMapper.map(userDTO,User.class));
        log.info("save retorno banco " + userDTO.getId() );
        return modelMapper.map(user,UserDTO.class);
    }

    @Override
    public void delete(long userId) throws UserNotFoundException {
        log.info("delete Inicio " + userId );
        User user = userRepository
                .findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        userRepository.delete(user);
        log.info("delete retorno banco " + userId );

    }


    @Override
    public UserDTO update(Long userId, UserDTO userDTO) throws UserNotFoundException {

        log.info("update Inicio " + userId );

        User user = userRepository
                .findById(userId).orElseThrow(() -> new UserNotFoundException("User Not Found"));

        if (userDTO.getEmail() != null &&
                !user.getEmail().equals(userDTO.getEmail())) {
            user.setEmail(userDTO.getEmail());
        }
        if (userDTO.getTelefone() != null &&
                !user.getTelefone().equals(userDTO.getTelefone())) {
            user.setTelefone(userDTO.getTelefone());
        }
        if (userDTO.getEndereco() != null &&
                !user.getEndereco().equals(userDTO.getEndereco())) {
            user.setEndereco(userDTO.getEndereco());
        }
        user = userRepository.save(user);
        log.info("update retorno banco " + userId );
        return modelMapper.map(user,UserDTO.class);
    }



    @Override
    public UserDTO findById(long userId) throws UserNotFoundException {
        log.info("findById findById inicio" + userId );
        User usuario = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User  not	found"));
        log.info("findById findById retorno banco " + userId );
        return modelMapper.map(usuario,UserDTO.class);
    }

    @Override
    public Page<UserDTO> findAll(Pageable page) {
        log.info("findById findAll paginado inicio"  );

        Page<User> users
                = userRepository.findAll(page);

        log.info("findById findAll paginado retorno banco"  );
        return	users
                .map(user ->modelMapper.map(user,UserDTO.class));

    }


    public List<UserDTO> findByCpf(String cpf)  {
        log.info("findByCpf inicio"  + cpf );
        List<User> users = userRepository.findByCpf(cpf);
        log.info("findByCpf retorno banco"  + cpf );
        return users.stream().map(u -> modelMapper.map(u,UserDTO.class)).collect(Collectors.toList());
    }

    public List<UserDTO> queryByName(String name) {
        log.info("queryByName inicio"  + name );
        List<User> usuarios = userRepository.queryByNomeLike(name);
        log.info("queryByName retorno banco"  + name );
        return usuarios
                .stream()
                .map(usuario ->modelMapper.map(usuario,UserDTO.class))
                .collect(Collectors.toList());
    }
}