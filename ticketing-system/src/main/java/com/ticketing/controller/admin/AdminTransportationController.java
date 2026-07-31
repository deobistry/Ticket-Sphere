package com.ticketing.controller.admin;


import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.ticketing.dto.admin.AdminTransportationRequest;
import com.ticketing.entity.Transportation;
import com.ticketing.service.admin.AdminTransportationService;



@RestController
@RequestMapping("/admin/transportation")
public class AdminTransportationController {



    private final AdminTransportationService adminTransportationService;





    public AdminTransportationController(
            AdminTransportationService adminTransportationService
    ) {

        this.adminTransportationService =
                adminTransportationService;

    }









    @PostMapping
    public ResponseEntity<String> create(
            @RequestBody AdminTransportationRequest request
    ) {



        adminTransportationService.createTransportation(
                request
        );


        return ResponseEntity.ok(
                "Transportation created successfully"
        );

    }









    @GetMapping
    public ResponseEntity<List<Transportation>> getAll() {


        return ResponseEntity.ok(
                adminTransportationService
                .getAllTransportation()
        );

    }








    @GetMapping("/{id}")
    public ResponseEntity<Transportation> getById(
            @PathVariable Long id
    ) {



        return ResponseEntity.ok(
                adminTransportationService
                .getById(id)
        );

    }









    @PutMapping("/{id}")
    public ResponseEntity<String> update(
            @PathVariable Long id,
            @RequestBody AdminTransportationRequest request
    ) {



        adminTransportationService
                .updateTransportation(
                        id,
                        request
                );



        return ResponseEntity.ok(
                "Transportation updated successfully"
        );

    }









    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Long id
    ) {



        adminTransportationService
                .deleteTransportation(id);



        return ResponseEntity.ok(
                "Transportation deleted successfully"
        );

    }


}