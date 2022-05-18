package com.visma.warehouseApp.user;

import com.visma.warehouseApp.user.entity.User;
import com.visma.warehouseApp.user.entity.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path="create")
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO){

        return new ResponseEntity<User>(userService.createUser(userDTO), HttpStatus.OK) ;
    }

}
