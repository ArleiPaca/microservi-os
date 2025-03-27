package br.com.xs3.user_api.service.impl;


import br.com.xs3.user_api.dto.UserDTO;
import br.com.xs3.user_api.exception.UserNotFoundException;
import br.com.xs3.user_api.model.User;
import br.com.xs3.user_api.repository.UserRepository;
import br.com.xs3.user_api.service.IService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IService<UserDTO,UserDTO> {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserDTO> findAll() {

        List<User> usuarios = userRepository.findAll();
        return usuarios
                .stream()
                .map(usuario ->modelMapper.map(usuario,UserDTO.class))
                .collect(Collectors.toList());
    }


    @Override
    public UserDTO save(UserDTO userDTO) {

        User user = userRepository.save(modelMapper.map(userDTO,User.class));
        return modelMapper.map(user,UserDTO.class);
    }

    @Override
    public UserDTO delete(long userId) throws UserNotFoundException {
        User user = userRepository
                .findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        userRepository.delete(user);
        return modelMapper.map(user,UserDTO.class);
    }



    @Override
    public UserDTO update(Long userId, UserDTO userDTO) throws UserNotFoundException {

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



    @Override
    public UserDTO findById(long userId) throws UserNotFoundException {
        User usuario = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User  not	found"));
        return modelMapper.map(usuario,UserDTO.class);
    }

    @Override
    public Page<UserDTO> findAll(Pageable page) {
        Page<User> users
                = userRepository.findAll(page);

        return	users
                .map(user ->modelMapper.map(user,UserDTO.class));

    }


    public List<UserDTO> findByCpf(String cpf)  {
        List<User> users = userRepository.findByCpf(cpf);
        return users.stream().map(u -> modelMapper.map(u,UserDTO.class)).collect(Collectors.toList());
    }

    public List<UserDTO> queryByName(String name) {
        List<User> usuarios = userRepository.queryByNomeLike(name);
        return usuarios
                .stream()
                .map(usuario ->modelMapper.map(usuario,UserDTO.class))
                .collect(Collectors.toList());
    }
}