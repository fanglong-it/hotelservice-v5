package fiveman.hotelservice.controller;

import fiveman.hotelservice.entities.PaymentMethod;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.PaymentMethodService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(tags = "PaymentMethod")
@RequestMapping("/api/v1")
public class PaymentMethodController {
    @Autowired
    PaymentMethodService paymentMethodService;

    @GetMapping("/paymentMethod/{id}")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public ResponseEntity<PaymentMethod> getPaymentMethodById(@PathVariable("id") long id) {
        return new ResponseEntity<>(paymentMethodService.getPaymentMethodById(id), HttpStatus.OK);
    }

    @GetMapping("/paymentMethods")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public ResponseEntity<List<PaymentMethod>> getPaymentMethods() {
        return new ResponseEntity<>(paymentMethodService.getPaymentMethods(), HttpStatus.OK);
    }

    @PostMapping("/paymentMethod")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public ResponseEntity<CustomResponseObject> savePaymentMethod(@RequestBody @Valid PaymentMethod paymentMethod) {
        return new ResponseEntity<>(paymentMethodService.savePaymentMethod(paymentMethod), HttpStatus.OK);
    }

    @PutMapping("/paymentMethod")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public ResponseEntity<CustomResponseObject> updatePaymentMethod(@RequestBody @Valid PaymentMethod paymentMethod) {
        return new ResponseEntity<>(paymentMethodService.updatePaymentMethod(paymentMethod), HttpStatus.OK);
    }

    @DeleteMapping("/paymentMethod/{id}")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public ResponseEntity<CustomResponseObject> getPaymentMethod(@PathVariable("id") long id) {
        return new ResponseEntity<>(paymentMethodService.deletePaymentMethod(id), HttpStatus.OK);
    }


}
