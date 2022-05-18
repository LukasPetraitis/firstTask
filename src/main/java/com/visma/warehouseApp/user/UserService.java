package com.visma.warehouseApp.user;

import com.visma.warehouseApp.user.entity.User;
import com.visma.warehouseApp.user.entity.UserDTO;
import com.visma.warehouseApp.user.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    PlatformTransactionManager platformTransactionManager;

    private TransactionTemplate transactionTemplate;

    @PostConstruct
    public void addFirstUser() {

        transactionTemplate = new TransactionTemplate(platformTransactionManager);

        User user = new User("charlie", "password", UserRole.USER);
        userRepository.save(user);
    }

    public void createUser(UserDTO userDTO){

        User user = new User(userDTO.getUsername(),
                                passwordEncoder.encode(userDTO.getPassword()),
                                UserRole.USER);

        transactionTemplate.execute(status -> {
            return userRepository.save(user);

        });
    }

}
