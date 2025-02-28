package br.com.xs3.user_api.service;


import br.com.xs3.user_api.dto.UserDTO;
import br.com.xs3.user_api.exception.UserNotFoundException;
import br.com.xs3.user_api.model.User;
import br.com.xs3.user_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public List<UserDTO> getAll() {
        log.info("START - [user] - getAll ");
        List<User> usuarios = userRepository.findAll();
        log.info("END - [user] - getAll ");
        return usuarios
                .stream()
                .map(usuario ->modelMapper.map(usuario,UserDTO.class))
                .collect(Collectors.toList());
    }


    public UserDTO findById(long userId) throws UserNotFoundException {
        User usuario = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User  not	found"));
        return modelMapper.map(usuario,UserDTO.class);
    }

    public UserDTO save(UserDTO userDTO) {
        userDTO.setDataCadastro(LocalDateTime.now());
        User user = userRepository.save(modelMapper.map(userDTO,User.class));
        return modelMapper.map(user,UserDTO.class);
    }

    public UserDTO delete(long userId) throws UserNotFoundException {
        User user = userRepository
                .findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        userRepository.delete(user);
        return modelMapper.map(user,UserDTO.class);
    }

    public UserDTO findByCpf(String cpf) {
        User user = userRepository.findByCpf(cpf);
        if (user != null) {
            return modelMapper.map(user,UserDTO.class);
        }
        return null;
    }

    public List<UserDTO> queryByName(String name) {
        List<User> usuarios = userRepository.queryByNomeLike(name);
        return usuarios
                .stream()
                .map(usuario ->modelMapper.map(usuario,UserDTO.class))
                .collect(Collectors.toList());

    }


    public UserDTO editUser(Long userId, UserDTO userDTO) throws UserNotFoundException {
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
        return modelMapper.map(user,UserDTO.class);
    }

    public Page<UserDTO> getAllPage(Pageable page) {
        Page<User> users
                = userRepository.findAll(page);

        return	users
                .map(user ->modelMapper.map(user,UserDTO.class));


    }
}