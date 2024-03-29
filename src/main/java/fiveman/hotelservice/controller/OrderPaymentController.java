package fiveman.hotelservice.controller;


import fiveman.hotelservice.entities.OrderPayment;
import fiveman.hotelservice.request.BillPaymentRequest;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.OrderPaymentService;
import io.swagger.annotations.Api;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(tags = "Order_Payment")
@RequestMapping("/api/v1")
public class OrderPaymentController {
    @Autowired
    OrderPaymentService billPaymentService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/orderPayment/{id}")
    public ResponseEntity<OrderPayment> getOrderPaymentById(@PathVariable("id") long id) {
        return new ResponseEntity<>(billPaymentService.getOrderPaymentById(id), HttpStatus.OK);
    }

    // @GetMapping("/orderPayment")
    // public ResponseEntity<OrderPayment> getOrderPaymentByOrderId(@RequestParam("Order_Id") long orderId){
    //     return new ResponseEntity<OrderPayment>(billPaymentService.getOrderPaymentByOrderId(orderId), HttpStatus.OK);
    // }

    @GetMapping("/orderPayments")
    public ResponseEntity<List<OrderPayment>> getOrderPayments() {
        return new ResponseEntity<>(billPaymentService.getAllOrderPayment(), HttpStatus.OK);
    }

    @PostMapping("/orderPayment")
    public ResponseEntity<CustomResponseObject> saveOrderPayment(@RequestBody @Valid BillPaymentRequest billPaymentRequest) {
        OrderPayment billPayment = modelMapper.map(billPaymentRequest, OrderPayment.class);
        return new ResponseEntity<>(billPaymentService.saveOrderPayment(billPayment), HttpStatus.OK);
    }

    @PutMapping("/orderPayment")
    public ResponseEntity<CustomResponseObject> updateOrderPayment(@RequestBody @Valid BillPaymentRequest billPaymentRequest) {
        OrderPayment billPayment = modelMapper.map(billPaymentRequest, OrderPayment.class);
        return new ResponseEntity<>(billPaymentService.updateOrderPayment(billPayment), HttpStatus.OK);
    }

    @DeleteMapping("/orderPayment/{id}")
    public ResponseEntity<CustomResponseObject> deleteOrderPayment(@PathVariable("id") long id) {
        return new ResponseEntity<>(billPaymentService.deleteOrderPayment(id), HttpStatus.OK);
    }


}
