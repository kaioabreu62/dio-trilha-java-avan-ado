package one.digitalinnovation.santander_dev_week_2023.service;

import one.digitalinnovation.santander_dev_week_2023.domain.model.User;

public interface UserService {

    User findById(Long id);

    User create(User userToCreate);
}
