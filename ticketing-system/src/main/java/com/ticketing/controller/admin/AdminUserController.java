package com.ticketing.controller.admin;


import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.ticketing.dto.admin.AdminUserResponse;
import com.ticketing.enums.UserRole;
import com.ticketing.service.admin.AdminUserService;



@RestController
@RequestMapping("/admin/users")
public class AdminUserController {



    private final AdminUserService adminUserService;




    public AdminUserController(
            AdminUserService adminUserService
    ) {

        this.adminUserService = adminUserService;

    }








    @GetMapping
    public ResponseEntity<List<AdminUserResponse>> getAllUsers() {



        return ResponseEntity.ok(
                adminUserService.getAllUsers()
        );

    }








    @GetMapping("/{id}")
    public ResponseEntity<AdminUserResponse> getUserById(
            @PathVariable Long id
    ) {



        return ResponseEntity.ok(
                adminUserService.getUserById(id)
        );

    }









    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(
            @PathVariable Long id
    ) {



        adminUserService.deleteUser(id);



        return ResponseEntity.ok(
                "User deleted successfully"
        );

    }









    @PutMapping("/{id}/role")
    public ResponseEntity<String> updateRole(
            @PathVariable Long id,
            @RequestBody UserRole role
    ) {



        adminUserService.updateRole(
                id,
                role
        );



        return ResponseEntity.ok(
                "User role updated successfully"
        );

    }


}