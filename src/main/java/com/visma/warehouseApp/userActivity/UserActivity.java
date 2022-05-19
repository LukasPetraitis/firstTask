package com.visma.warehouseApp.userActivity;

import com.visma.warehouseApp.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String userActivity;
    @ManyToOne
    private User user;

}
