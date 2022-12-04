package fiveman.hotelservice.controller;

import fiveman.hotelservice.entities.Abstraction;
import fiveman.hotelservice.request.AbstractionRequest;
import fiveman.hotelservice.response.AbstractionResponse;
import fiveman.hotelservice.service.AbstractionService;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(tags = "Abstraction")
@RequestMapping("/api/v1")
public class AbstractionController {

    @Autowired
    AbstractionService abstractionService;

    @GetMapping("/abstraction/{id}")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public ResponseEntity<Abstraction> getAbstractionById(@PathVariable("id") long id) {
        return new ResponseEntity<>(abstractionService.getAbstractionById(id), HttpStatus.OK);
    }


    @GetMapping("/abstractions")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public ResponseEntity<List<AbstractionResponse>> getAbstractions() {
        return new ResponseEntity<>(abstractionService.getAbstractions(), HttpStatus.OK);
    }

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/abstraction")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public ResponseEntity<List<AbstractionResponse>> saveAbstraction(@RequestBody @Valid AbstractionRequest abstractionRequest) {
        Abstraction abstraction = modelMapper.map(abstractionRequest, Abstraction.class);
        return new ResponseEntity<>(abstractionService.saveAbstraction(abstraction), HttpStatus.OK);
    }

    @PutMapping("/abstraction")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public ResponseEntity<List<AbstractionResponse>> updateAbstraction(@RequestBody @Valid AbstractionRequest abstractionRequest) {
        Abstraction abstraction = modelMapper.map(abstractionRequest, Abstraction.class);
        return new ResponseEntity<>(abstractionService.updateAbstraction(abstraction), HttpStatus.OK);
    }

    @DeleteMapping("/abstraction/{id}")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public ResponseEntity<List<AbstractionResponse>> deleteAbstraction(@PathVariable("id") long id) {
        return new ResponseEntity<>(abstractionService.deleteAbstractionById(id), HttpStatus.OK);
    }

}
