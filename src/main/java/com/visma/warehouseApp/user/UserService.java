package com.visma.warehouseApp.user;

import com.visma.warehouseApp.user.entity.User;
import com.visma.warehouseApp.user.entity.UserDTO;
import com.visma.warehouseApp.user.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public User createUser(UserDTO userDTO){
        User user = new User();

        user.setUsername( userDTO.getUsername() );
        user.setPassword(passwordEncoder.encode( userDTO.getPassword() ));
        user.setUserRole( UserRole.USER.toString() );

        return userRepository.save(user);
    }

}
