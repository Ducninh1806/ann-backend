package com.ducninh.ann.controller;

import com.ducninh.ann.extension.LogicException;
import com.ducninh.ann.service.dto.ColorDTO;
import com.ducninh.ann.service.impl.ColorServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/color")
public class ColorController {

    private static final Logger logger = LoggerFactory.getLogger(ColorController.class);

    private ColorServiceImpl colorService;

    public ColorController(ColorServiceImpl colorService) {
        this.colorService = colorService;
    }

    @GetMapping
    public ResponseEntity<List<ColorDTO>> findAllColor(){
        logger.info("Controller info about findAll Color");
        List<ColorDTO> colorDTOList = colorService.findAllColor();
        return new ResponseEntity<>(colorDTOList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ColorDTO> findByIdColor(@PathVariable Long id) throws LogicException {
        logger.info("Controller info about findById Color, {}", id);
        ColorDTO colorDTO = colorService.findByIdColor(id);
        return new ResponseEntity<>(colorDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ColorDTO> createColor(@ModelAttribute ColorDTO colorDTO) throws IOException, LogicException {
        logger.info("Controller info about create Color, {}", colorDTO);
        colorService.createColor(colorDTO);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ColorDTO> updateColor(@ModelAttribute ColorDTO colorDTO) throws IOException, LogicException {
        logger.info("Controller info about update Color, {}", colorDTO);
        colorService.updateColor(colorDTO);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ColorDTO> deleteColor(@PathVariable Long id) throws LogicException {
        logger.info("Controller info about delete Color, {}", id);
        colorService.deleteColor(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
