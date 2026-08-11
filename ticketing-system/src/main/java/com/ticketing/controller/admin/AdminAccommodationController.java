package com.ticketing.controller.admin;


import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.ticketing.dto.admin.AdminAccommodationRequest;
import com.ticketing.entity.Accommodation;
import com.ticketing.service.admin.AdminAccommodationService;



@RestController
@RequestMapping("/admin/accommodation")
public class AdminAccommodationController {



    private final AdminAccommodationService adminAccommodationService;





    public AdminAccommodationController(
            AdminAccommodationService adminAccommodationService
    ) {

        this.adminAccommodationService =
                adminAccommodationService;

    }









    @PostMapping
    public ResponseEntity<String> create(
            @RequestBody AdminAccommodationRequest request
    ) {



        adminAccommodationService
                .createAccommodation(request);



        return ResponseEntity.ok(
                "Accommodation created successfully"
        );

    }









    @GetMapping
    public ResponseEntity<List<Accommodation>> getAll() {



        return ResponseEntity.ok(
                adminAccommodationService
                .getAllAccommodation()
        );

    }









    @GetMapping("/{id}")
    public ResponseEntity<Accommodation> getById(
            @PathVariable Long id
    ) {



        return ResponseEntity.ok(
                adminAccommodationService
                .getById(id)
        );

    }









    @PutMapping("/{id}")
    public ResponseEntity<String> update(
            @PathVariable Long id,
            @RequestBody AdminAccommodationRequest request
    ) {



        adminAccommodationService
                .updateAccommodation(
                        id,
                        request
                );



        return ResponseEntity.ok(
                "Accommodation updated successfully"
        );

    }









    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Long id
    ) {



        adminAccommodationService
                .deleteAccommodation(id);



        return ResponseEntity.ok(
                "Accommodation deleted successfully"
        );

    }


}