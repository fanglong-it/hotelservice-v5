package fiveman.hotelservice.controller;

import fiveman.hotelservice.entities.Bill;
import fiveman.hotelservice.request.BillRequest;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.BillService;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(tags = "bill")
@RequestMapping("/api/v1")
public class BillController {
    @Autowired
    BillService billService;
    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/bill/{id}")
    public ResponseEntity<Bill> getBillById(@PathVariable("id") long id) {
        return new ResponseEntity<>(billService.getBillById(id), HttpStatus.OK);
    }

    @PostMapping("/bill")
    public ResponseEntity<CustomResponseObject> saveBill(@RequestBody @Valid BillRequest billRequest) {
        Bill bill = modelMapper.map(billRequest, Bill.class);
        return new ResponseEntity<>(billService.saveBill(bill), HttpStatus.OK);
    }

    @PutMapping("/bill")
    public ResponseEntity<CustomResponseObject> updateBill(@RequestBody @Valid BillRequest billRequest) {
        Bill bill = modelMapper.map(billRequest, Bill.class);
        return new ResponseEntity<>(billService.updateBill(bill), HttpStatus.OK);
    }

    @DeleteMapping("/bill/{id}")
    public ResponseEntity<CustomResponseObject> deleteBill(@PathVariable("id") long id) {
        return new ResponseEntity<>(billService.deleteBillById(id), HttpStatus.OK);
    }


}
