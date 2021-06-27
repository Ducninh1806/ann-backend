package com.ducninh.ann.service.impl;

import com.ducninh.ann.extension.ErrorCode;
import com.ducninh.ann.extension.LogicException;
import com.ducninh.ann.model.Color;
import com.ducninh.ann.repository.jpa.ColorRepository;
import com.ducninh.ann.service.ColorService;
import com.ducninh.ann.service.dto.ColorDTO;
import com.ducninh.ann.utils.CommonUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ColorServiceImpl implements ColorService {
    @Value("${location.resource}")
    private String locationResource;

    private static final Logger logger = LoggerFactory.getLogger(ColorServiceImpl.class);

    private ColorRepository colorRepository;
    private ModelMapper modelMapper;

    public ColorServiceImpl(ColorRepository colorRepository, ModelMapper modelMapper) {
        this.colorRepository = colorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ColorDTO> findAllColor() {
        logger.info("Service info about findAll Color");
        List<Color> colorList = colorRepository.findAll();
        return colorList.stream().map(color -> modelMapper.map(color, ColorDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ColorDTO findByIdColor(Long id) throws LogicException {
        logger.info("Service info about findById Color, {}", id);
        Optional<Color> color = colorRepository.findById(id);
        if (!color.isPresent()){
            throw new LogicException(ErrorCode.RECORD_NOT_EXISTED);
        }
        return modelMapper.map(color.get(), ColorDTO.class);
    }

    @Override
    public void createColor(ColorDTO colorDTO) throws LogicException, IOException {
        logger.info("Service info about create Color, {}", colorDTO);
        Optional<Color> colorOptional = colorRepository.findByCode(colorDTO.getCode());
        if (colorOptional.isPresent()) {
            throw new LogicException(ErrorCode.NAME_EXISTED);
        }
        Color color = modelMapper.map(colorDTO, Color.class);
        if (colorDTO.getImageColor() != null) {
            color.setPathImage(CommonUtils.upload(locationResource, "image_color", colorDTO.getImageColor()));
        }
        color.setCreatedDate(new Date());
        color.setCreatedBy("user create Admin");
        colorRepository.save(color);
    }

    @Override
    public void updateColor(ColorDTO colorDTO) throws LogicException, IOException {
        logger.info("Service info about update Color, {}", colorDTO);
        Optional<Color> colorOptional = colorRepository.findById(colorDTO.getId());
        if (!colorOptional.isPresent()) {
            throw new LogicException(ErrorCode.RECORD_NOT_EXISTED);
        }
        Color color = modelMapper.map(colorDTO, Color.class);
        if (colorDTO.getImageColor() != null) {
            color.setPathImage(CommonUtils.upload(locationResource, "image_color", colorDTO.getImageColor()));
        }
        color.setLastModifiedDate(new Date());
        color.setLastModifiedBy("user update Admin");
        colorRepository.save(color);
    }

    @Override
    public void deleteColor(Long id) throws LogicException {
        logger.info("Service info about delete Color, {}", id);
        Optional<Color> colorOptional = colorRepository.findById(id);
        if (!colorOptional.isPresent()) {
            throw new LogicException(ErrorCode.RECORD_NOT_EXISTED);
        }
        colorRepository.delete(colorOptional.get());
    }
}
