package dio.aula_spring_data_jpa_exception.repository;

import dio.aula_spring_data_jpa_exception.model.User;
import dio.aula_spring_data_jpa_exception.handler.CampoObrigatorioException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository  {
    private final List<User> users = new ArrayList<>();
    private int idCounter = 1;

    public void save(User user){
        if (user.getLogin()==null)
            throw new CampoObrigatorioException("login");
        if (user.getPassword()==null)
            throw new CampoObrigatorioException("password");
        if(user.getId()==null) {
            user.setId(idCounter++);
            users.add(user);
            System.out.println("SAVE - Recebendo o usuário na camada de repositório");
        } else {
            Optional<User> existsUser = users.stream().filter(u -> u.getId().equals(user.getId())).findFirst();
            if (existsUser.isPresent()) {
                users.remove(existsUser.get());
                users.add(user);
                System.out.println("UPDATE - Recebendo o usuário na camada de repositório");
            } else {
                throw new IllegalArgumentException("Usuário não encontrado com este id: " + user.getId());
            }
        }
        System.out.println(user);
    }
    public void deleteById(Integer id){
        users.removeIf(user -> user.getId().equals(id));
        System.out.println(String.format("DELETE/id - Recebendo o id: %d para excluir um usuário", id));
        System.out.println("O usuário pertencente ao id " + id + " foi excluído com sucesso!");
    }
    public void deleteByUsername(String username){
        users.removeIf(user -> user.getLogin().equals(username));
        System.out.println(String.format("DELETE/id - Recebendo o username: %s para excluir um usuário", username));
        System.out.println("O usuário com o username " + username + " foi excluído com sucesso!");
    }

    public List<User> findAll(){
        System.out.println("LIST - Listando os usuários do sistema");
        for (User u : users) {
            System.out.println(u);
        }
        return new ArrayList<>(users);
    }
    public User findById(Integer id){
        System.out.println(String.format("FIND/id - Recebendo o id: %d para localizar um usuário", id));
        User foundUser = users.stream().
                filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Usuário não foi encontrado com este id: " + id));
        System.out.println("Pelo id " + id + " foi encontrado o usuário: " + foundUser);

        return foundUser;
    }
    public User findByUsername(String username){
        System.out.println(String.format("FIND/username - Recebendo o username: %s para localizar um usuário", username));
        User foundUser = users.stream().
                filter(user -> user.getLogin().equals(username))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Usuário não foi encontrado com este username: " + username));
        System.out.println("Usuário encontrado: " + foundUser);

        return foundUser;
    }
}
