package fiveman.hotelservice.controller;


import fiveman.hotelservice.entities.OrderPayment;
import fiveman.hotelservice.request.BillPaymentRequest;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.OrderPaymentService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(tags = "Bill_Payment")
@RequestMapping("/api/v1")
public class OrderPaymentController {
    @Autowired
    OrderPaymentService billPaymentService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/billPayment/{id}")
    public ResponseEntity<OrderPayment> getOrderPaymentById(@PathVariable("id") long id) {
        return new ResponseEntity<>(billPaymentService.getOrderPaymentById(id), HttpStatus.OK);
    }

    @GetMapping("/billPayments")
    public ResponseEntity<List<OrderPayment>> getOrderPayments() {
        return new ResponseEntity<>(billPaymentService.getAllOrderPayment(), HttpStatus.OK);
    }

    @PostMapping("/billPayment")
    public ResponseEntity<CustomResponseObject> saveOrderPayment(@RequestBody @Valid BillPaymentRequest billPaymentRequest) {
        OrderPayment billPayment = modelMapper.map(billPaymentRequest, OrderPayment.class);
        return new ResponseEntity<>(billPaymentService.saveOrderPayment(billPayment), HttpStatus.OK);
    }

    @PutMapping("/billPayment")
    public ResponseEntity<CustomResponseObject> updateOrderPayment(@RequestBody @Valid BillPaymentRequest billPaymentRequest) {
        OrderPayment billPayment = modelMapper.map(billPaymentRequest, OrderPayment.class);
        return new ResponseEntity<>(billPaymentService.updateOrderPayment(billPayment), HttpStatus.OK);
    }

    @DeleteMapping("/billPayment/{id}")
    public ResponseEntity<CustomResponseObject> deleteOrderPayment(@PathVariable("id") long id) {
        return new ResponseEntity<>(billPaymentService.deleteOrderPayment(id), HttpStatus.OK);
    }


}
