package br.com.xs3.user_api.repository;

import br.com.xs3.user_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,	Long>
{
    User findByCpf(String	cpf);
    List<User> queryByNomeLike(String	name);
}
