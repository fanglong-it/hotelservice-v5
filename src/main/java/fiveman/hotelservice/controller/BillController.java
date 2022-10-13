package fiveman.hotelservice.controller;

import fiveman.hotelservice.entities.Bill;
import fiveman.hotelservice.request.BillRequest;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.BillService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(tags = "bill")
@RequestMapping("/api/v1")
public class BillController {
    @Autowired
    BillService billService;
    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/bill/{id}")
    @PreAuthorize("isAuthenticated() or isAnonymous()")
    public ResponseEntity<Bill> getBillById(@PathVariable("id") long id) {
        return new ResponseEntity<>(billService.getBillById(id), HttpStatus.OK);
    }

    @GetMapping("/bills")
    @PreAuthorize("isAuthenticated() or isAnonymous()")
    public ResponseEntity<List<Bill>> getAllBill() {
        return new ResponseEntity<>(billService.getAllBill(), HttpStatus.OK);
    }

    @PostMapping("/bill")
    @PreAuthorize("isAuthenticated() or isAnonymous()")
    public ResponseEntity<CustomResponseObject> saveBill(@RequestBody @Valid BillRequest billRequest) {
        Bill bill = modelMapper.map(billRequest, Bill.class);
        return new ResponseEntity<>(billService.saveBill(bill), HttpStatus.OK);
    }

    @PutMapping("/bill")
    @PreAuthorize("isAuthenticated() or isAnonymous()")
    public ResponseEntity<CustomResponseObject> updateBill(@io.swagger.v3.oas.annotations.parameters.RequestBody @Valid BillRequest billRequest) {
        Bill bill = modelMapper.map(billRequest, Bill.class);
        return new ResponseEntity<>(billService.updateBill(bill), HttpStatus.OK);
    }

    @DeleteMapping("/bill/{id}")
    @PreAuthorize("isAuthenticated() or isAnonymous()")
    public ResponseEntity<CustomResponseObject> deleteBill(@PathVariable("id") long id) {
        return new ResponseEntity<>(billService.deleteBillById(id), HttpStatus.OK);
    }


}
