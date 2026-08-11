package com.ticketing.service;


import org.springframework.stereotype.Service;


import com.ticketing.dto.request.UpdateProfileRequest;
import com.ticketing.dto.response.UserProfileResponse;
import com.ticketing.entity.User;
import com.ticketing.exception.ResourceNotFoundException;
import com.ticketing.repository.UserRepository;
import com.ticketing.security.UserContext;



@Service
public class UserService {



    private final UserRepository userRepository;


    private final UserContext userContext;





    public UserService(
            UserRepository userRepository,
            UserContext userContext
    ) {

        this.userRepository = userRepository;
        this.userContext = userContext;

    }







    public UserProfileResponse getProfile() {


        User user = getLoggedUser();


        return convert(user);

    }








    public UserProfileResponse updateProfile(
            UpdateProfileRequest request
    ) {


        User user = getLoggedUser();





        if(request.getName()!=null &&
                !request.getName().isEmpty()) {


            user.setName(
                    request.getName()
            );

        }






        if(request.getEmail()!=null &&
                !request.getEmail().isEmpty()) {


            user.setEmail(
                    request.getEmail()
            );

        }







        if(request.getPassword()!=null &&
                !request.getPassword().isEmpty()) {


            user.setPassword(
                    request.getPassword()
            );

        }







        userRepository.save(user);



        return convert(user);

    }








    private User getLoggedUser() {



        if(userContext.getUserId()==null) {

            throw new RuntimeException(
                    "User not authenticated"
            );

        }






        return userRepository.findById(
                userContext.getUserId()
        )
        .orElseThrow(

                () -> new ResourceNotFoundException(
                        "User not found"
                )

        );

    }









    private UserProfileResponse convert(
            User user
    ) {


        return new UserProfileResponse(

                user.getId(),

                user.getName(),

                user.getEmail(),

                user.getRole()

        );

    }


}