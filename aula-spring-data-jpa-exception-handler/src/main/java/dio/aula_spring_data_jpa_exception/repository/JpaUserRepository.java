package dio.aula_spring_data_jpa_exception.repository;

import dio.aula_spring_data_jpa_exception.model.JpaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaUserRepository extends JpaRepository<JpaUser, Integer> {

    //Query Method
    List<JpaUser> findByNameContaining(String name);

    //Query Method
    JpaUser findByUsername(String username);

    //Query Override
    @Query("SELECT u FROM JpaUser u WHERE u.name LIKE %:name%")
    List<JpaUser> filtrarPorNome(@Param("name") String name);

}
