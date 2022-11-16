package fiveman.hotelservice.controller;

import fiveman.hotelservice.entities.OrderDetail;
import fiveman.hotelservice.request.OrderDetailRequest;
import fiveman.hotelservice.response.OrderDetailResponse;
import fiveman.hotelservice.response.ServiceResponse;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.OrderDetailService;
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
@Api(tags = "Order_Detail")
@RequestMapping("/api/v1")
public class OrderDetailController {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    OrderDetailService orderDetailService;

    @GetMapping("/orderDetail")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public ResponseEntity<List<OrderDetailResponse>> getAllOrderDetailByOrder_Id(@RequestParam("order_id") long id) {
        return new ResponseEntity<>(orderDetailService.getAllByBill_Id(id), HttpStatus.OK);
    }

    @GetMapping("/orderDetail/{id}")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public ResponseEntity<OrderDetailResponse> getOrderDetailById(@PathVariable("id") long id) {
        return new ResponseEntity<>(orderDetailService.getBillDetailById(id), HttpStatus.OK);
    }

    @PostMapping("/orderDetail")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public ResponseEntity<CustomResponseObject> saveOrderDetail(@RequestBody @Valid OrderDetailRequest orderDetailRequest) {
        OrderDetail orderDetail = modelMapper.map(orderDetailRequest, OrderDetail.class);
        return new ResponseEntity<>(orderDetailService.saveBillDetail(orderDetail), HttpStatus.OK);
    }

    @PutMapping("/orderDetail")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public ResponseEntity<CustomResponseObject> updateOrderDetail(@RequestBody @Valid OrderDetailRequest orderDetailRequest) {
        OrderDetail orderDetail = modelMapper.map(orderDetailRequest, OrderDetail.class);
        return new ResponseEntity<>(orderDetailService.updateBillDetail(orderDetail), HttpStatus.OK);
    }

    @DeleteMapping("/orderDetail/{id}")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public ResponseEntity<CustomResponseObject> deleteorderDetail(@PathVariable("id") long id) {
        return new ResponseEntity<>(orderDetailService.deleteBillDetail(id), HttpStatus.OK);
    }

    @GetMapping("/existTaxiService")
    public ResponseEntity<ServiceResponse> checkExistTaxiService(@RequestParam("booking_id") long id){
        return new ResponseEntity<>(orderDetailService.checkExistTaxiServiceInBooking(id), HttpStatus.OK);
    }

}
