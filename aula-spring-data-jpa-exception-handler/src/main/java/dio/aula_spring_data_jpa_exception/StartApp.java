package dio.aula_spring_data_jpa_exception;

import dio.aula_spring_data_jpa_exception.model.JpaUser;
import dio.aula_spring_data_jpa_exception.model.User;
import dio.aula_spring_data_jpa_exception.repository.JpaUserRepository;
import dio.aula_spring_data_jpa_exception.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StartApp implements CommandLineRunner {
    @Autowired
    private JpaUserRepository jpaRepository;

    @Autowired
    private UserRepository repository;

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User(null, "fulano", "fulaninho123");
        User user2 = new User(null, "sicrano", "sicrano123");

        repository.save(user1);
        repository.save(user2);

        List<JpaUser> users = jpaRepository.findByNameContaining("Kaio");
        System.out.println("\nOs usuários que contém " + "Kaio" + " são eles:");
        for(JpaUser u: users) {
            System.out.println(u);
        }

        JpaUser jpaUser = jpaRepository.findByUsername("kaio62");
        System.out.println("\nO usuário que contém o username " + "kaio62" + " é o seguinte: " + jpaUser);

        List<JpaUser> filtrar = jpaRepository.filtrarPorNome("Gabriel Nunes");
        System.out.println("\nFiltrando por nome o usuário que possui o name " + "Gabriel Nunes: " + filtrar);

        //insertUser();
    }
    private void insertUser() {
        JpaUser user = new JpaUser();
        user.setName("KAIO");
        user.setUsername("kaio");
        user.setPassword("dio123");
        jpaRepository.save(user);

        System.out.println("\nListando todos os usuários criados na tabela " + "tab_user:");
        for(JpaUser u: jpaRepository.findAll()) {
            System.out.println(u);
        }
    }
}
