package com.ticketing.controller;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import com.ticketing.dto.request.UpdateProfileRequest;
import com.ticketing.dto.response.UserProfileResponse;
import com.ticketing.service.UserService;




@RestController
@RequestMapping("/users")
public class UserController {



    private final UserService userService;







    public UserController(
            UserService userService
    ) {

        this.userService = userService;

    }








    @GetMapping("/profile")
    public ResponseEntity<UserProfileResponse> profile() {



        return ResponseEntity.ok(
                userService.getProfile()
        );

    }








    @PutMapping("/profile")
    public ResponseEntity<UserProfileResponse> update(
            @RequestBody UpdateProfileRequest request
    ) {



        return ResponseEntity.ok(
                userService.updateProfile(request)
        );

    }


}