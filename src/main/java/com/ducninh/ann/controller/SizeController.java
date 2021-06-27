package com.ducninh.ann.controller;

import com.ducninh.ann.extension.LogicException;
import com.ducninh.ann.service.dto.SizeDTO;
import com.ducninh.ann.service.impl.SizeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/size")
public class SizeController {

    private static final Logger logger = LoggerFactory.getLogger(SizeController.class);

    private SizeServiceImpl sizeService;

    public SizeController(SizeServiceImpl sizeService) {
        this.sizeService = sizeService;
    }

    @GetMapping
    public ResponseEntity<List<SizeDTO>> findAllSize(){
        logger.info("Controller info about findAll Size");
        List<SizeDTO> sizeDTOS = sizeService.findAllSize();
        return new ResponseEntity<>(sizeDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SizeDTO> findByIdSize(@PathVariable Long id) throws LogicException {
        logger.info("Controller info about findById Size, {}", id);
        SizeDTO sizeDTO = sizeService.findByIdSize(id);
        return new ResponseEntity<>(sizeDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SizeDTO> createSize(@RequestBody SizeDTO sizeDTO) throws LogicException {
        logger.info("Controller info about create Size, {}", sizeDTO);
        sizeService.createSize(sizeDTO);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<SizeDTO> updateSize(@RequestBody SizeDTO sizeDTO) throws LogicException {
        logger.info("Controller info about update Size, {}", sizeDTO);
        sizeService.updateSize(sizeDTO);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SizeDTO> deleteSize(@PathVariable Long id) throws LogicException {
        logger.info("Controller info about update Size, {}", id);
        sizeService.deleteSize(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
