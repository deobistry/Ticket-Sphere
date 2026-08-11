package com.ticketing.service.admin;


import java.util.List;
import java.util.stream.Collectors;


import org.springframework.stereotype.Service;


import com.ticketing.dto.admin.AdminUserResponse;
import com.ticketing.entity.User;
import com.ticketing.enums.UserRole;
import com.ticketing.repository.UserRepository;



@Service
public class AdminUserService {



    private final UserRepository userRepository;




    public AdminUserService(
            UserRepository userRepository
    ) {

        this.userRepository = userRepository;

    }







    public List<AdminUserResponse> getAllUsers() {



        return userRepository
                .findAll()
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());

    }







    public AdminUserResponse getUserById(
            Long id
    ) {



        User user =
                userRepository
                .findById(id)
                .orElseThrow(
                        () -> new RuntimeException(
                                "User not found"
                        )
                );



        return convert(user);

    }








    public void deleteUser(
            Long id
    ) {



        User user =
                userRepository
                .findById(id)
                .orElseThrow(
                        () -> new RuntimeException(
                                "User not found"
                        )
                );



        userRepository.delete(user);

    }








    public void updateRole(
            Long id,
            UserRole role
    ) {



        User user =
                userRepository
                .findById(id)
                .orElseThrow(
                        () -> new RuntimeException(
                                "User not found"
                        )
                );



        user.setRole(role);



        userRepository.save(user);

    }









    private AdminUserResponse convert(
            User user
    ) {



        return new AdminUserResponse(

                user.getId(),

                user.getName(),

                user.getEmail(),

                user.getRole()

        );

    }


}