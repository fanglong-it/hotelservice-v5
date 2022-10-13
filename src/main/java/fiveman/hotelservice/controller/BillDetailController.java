package fiveman.hotelservice.controller;

import fiveman.hotelservice.entities.BillDetail;
import fiveman.hotelservice.request.BillDetailRequest;
import fiveman.hotelservice.response.BillDetailResponse;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.BillDetailService;
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
@Api(tags = "Bill_Detail")
@RequestMapping("/api/v1")
public class BillDetailController {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    BillDetailService billDetailService;

    @GetMapping("/billDetail")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public ResponseEntity<List<BillDetailResponse>> getAllBillDetailByBill_Id(@RequestParam("bill_id") long id) {
        return new ResponseEntity<>(billDetailService.getAllByBill_Id(id), HttpStatus.OK);
    }

    @GetMapping("/billDetail/{id}")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public ResponseEntity<BillDetailResponse> getBillDetailById(@PathVariable("id") long id) {
        return new ResponseEntity<>(billDetailService.getBillDetailById(id), HttpStatus.OK);
    }

    @PostMapping("/billDetail")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public ResponseEntity<CustomResponseObject> saveBillDetail(@RequestBody @Valid BillDetailRequest billDetailRequest) {
        BillDetail billDetail = modelMapper.map(billDetailRequest, BillDetail.class);
        return new ResponseEntity<>(billDetailService.saveBillDetail(billDetail), HttpStatus.OK);
    }

    @PutMapping("/billDetail")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public ResponseEntity<CustomResponseObject> updateBillDetail(@RequestBody @Valid BillDetailRequest billDetailRequest) {
        BillDetail billDetail = modelMapper.map(billDetailRequest, BillDetail.class);
        return new ResponseEntity<>(billDetailService.updateBillDetail(billDetail), HttpStatus.OK);
    }

    @DeleteMapping("/billDetail/{id}")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public ResponseEntity<CustomResponseObject> deleteBillDetail(@PathVariable("id") long id) {
        return new ResponseEntity<>(billDetailService.deleteBillDetail(id), HttpStatus.OK);
    }

}
