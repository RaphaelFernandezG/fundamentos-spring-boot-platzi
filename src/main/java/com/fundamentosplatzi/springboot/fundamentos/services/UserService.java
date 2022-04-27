package com.fundamentosplatzi.springboot.fundamentos.services;

import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {
    private final Log LOG = LogFactory.getLog(UserService.class);  //
    private UserRepository userRepository;  // inyectamos la dependencia

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional  //si existe algun error en el insert ayuda a hacer un rollback de las transacciones
    public void saveTransactional(List<User> users){
        users.stream().peek(user -> LOG.info("Usuario insertado: "+user)).
                forEach(userRepository::save);
                //forEach(user -> userRepository.save(user));  //en java 8 se puede utilizar los metodos a partir de referencias donde el :: save y funciona de la misma manera

    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User save(User newUser) {
        return userRepository.save(newUser);
    }

    public void delete(Long id) {
        userRepository.delete(new User(id));
    }

    public User update(User newUser, Long id) {
        return userRepository.findById(id).map(
                user -> {
            user.setEmail(newUser.getEmail());
            user.setBirthDate(newUser.getBirthDate());
            user.setName(newUser.getName());
            return userRepository.save(user);
        }).get();
    }


}
