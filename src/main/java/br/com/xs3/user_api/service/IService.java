package br.com.xs3.user_api.service;

import br.com.xs3.user_api.dto.UserDTO;
import br.com.xs3.user_api.exception.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;
public interface IService<T,X> {


    @Transactional
    T save(@Valid @NotNull X entity);

    @Transactional
    T update(@Positive @NotNull Long id, @Valid @NotNull X entity) throws UserNotFoundException;


    @Transactional(readOnly = true)
    List<T> findAll();

    @Transactional
    T delete(long userId) throws UserNotFoundException;

    @Transactional
    T findById(long userId) throws UserNotFoundException;

    @Transactional(readOnly = true)
    Page<T> findAll(Pageable pageable);




}
