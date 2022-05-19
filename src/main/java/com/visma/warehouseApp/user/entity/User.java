package com.visma.warehouseApp.user.entity;

import com.visma.warehouseApp.userActivity.UserActivity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String username;

    private String password;

    private String userRole;

//    @OneToMany
//    private List<UserActivity> userActivityList;
//
//    public void addToUserActivityList(UserActivity userActivity){
//        userActivityList.add(userActivity);
//    }

}