package com.visma.warehouseApp.user;

import com.visma.warehouseApp.user.entity.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public HttpStatus createUser(@RequestBody UserDTO userDTO){
        userService.createUser(userDTO);
        return HttpStatus.OK;
    }

}
