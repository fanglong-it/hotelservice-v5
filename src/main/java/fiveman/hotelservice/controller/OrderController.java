package fiveman.hotelservice.controller;

import fiveman.hotelservice.entities.Order;
import fiveman.hotelservice.request.BillRequest;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.OrderService;
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
@Api(tags = "Order")
@RequestMapping("/api/v1")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/order/{id}")
    @PreAuthorize("isAuthenticated() or isAnonymous()")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") long id) {
        return new ResponseEntity<>(orderService.getBillById(id), HttpStatus.OK);
    }

    @GetMapping("/orders")
    @PreAuthorize("isAuthenticated() or isAnonymous()")
    public ResponseEntity<List<Order>> getAllOrder() {
        return new ResponseEntity<>(orderService.getAllBill(), HttpStatus.OK);
    }

    @PostMapping("/order")
    @PreAuthorize("isAuthenticated() or isAnonymous()")
    public ResponseEntity<CustomResponseObject> saveOrder(@RequestBody @Valid BillRequest billRequest) {
        Order bill = modelMapper.map(billRequest, Order.class);
        return new ResponseEntity<>(orderService.saveBill(bill), HttpStatus.OK);
    }

    @PutMapping("/order")
    @PreAuthorize("isAuthenticated() or isAnonymous()")
    public ResponseEntity<CustomResponseObject> updateOrder(@io.swagger.v3.oas.annotations.parameters.RequestBody @Valid BillRequest billRequest) {
        Order bill = modelMapper.map(billRequest, Order.class);
        return new ResponseEntity<>(orderService.updateBill(bill), HttpStatus.OK);
    }

    @DeleteMapping("/order/{id}")
    @PreAuthorize("isAuthenticated() or isAnonymous()")
    public ResponseEntity<CustomResponseObject> deleteOrder(@PathVariable("id") long id) {
        return new ResponseEntity<>(orderService.deleteBillById(id), HttpStatus.OK);
    }


}