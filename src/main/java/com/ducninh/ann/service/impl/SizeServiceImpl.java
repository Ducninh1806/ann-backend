package com.ducninh.ann.service.impl;

import com.ducninh.ann.extension.ErrorCode;
import com.ducninh.ann.extension.LogicException;
import com.ducninh.ann.model.Size;
import com.ducninh.ann.repository.jpa.SizeRepository;
import com.ducninh.ann.service.SizeService;
import com.ducninh.ann.service.dto.SizeDTO;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SizeServiceImpl implements SizeService {

    private final static Logger logger = LoggerFactory.getLogger(SizeServiceImpl.class);

    private SizeRepository sizeRepository;
    private ModelMapper modelMapper;
    public SizeServiceImpl(SizeRepository sizeRepository, ModelMapper modelMapper) {
        this.sizeRepository = sizeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<SizeDTO> findAllSize() {
        logger.info("Service info about findAll Size");
        List<Size> sizeList = sizeRepository.findAll();
        return sizeList.stream().map(size -> modelMapper.map(size, SizeDTO.class)).collect(Collectors.toList());
    }

    @Override
    public SizeDTO findByIdSize(Long id) throws LogicException {
        logger.info("Service info about findById Size, {}", id);
        Optional<Size> size = sizeRepository.findById(id);
        if (!size.isPresent()){
            throw new LogicException(ErrorCode.RECORD_NOT_EXISTED);
        }
        return modelMapper.map(size.get(), SizeDTO.class);
    }

    @Override
    public void createSize(SizeDTO sizeDTO) throws LogicException {
        logger.info("Service info about create Size, {}", sizeDTO);
        Optional<Size> sizeExist = sizeRepository.findByName(sizeDTO.getName());
        if (sizeExist.isPresent()){
            throw new LogicException(ErrorCode.NAME_EXISTED);
        }
        Size size = modelMapper.map(sizeDTO, Size.class);
        size.setCreatedBy("user login admin");
        size.setCreatedDate(new Date());
        sizeRepository.save(size);
    }

    @Override
    public void updateSize(SizeDTO sizeDTO) throws LogicException {
        logger.info("Service info about update Size, {}", sizeDTO);
        Optional<Size> sizeExist = sizeRepository.findByName(sizeDTO.getName());
        if (!sizeExist.isPresent()){
            throw new LogicException(ErrorCode.RECORD_NOT_EXISTED);
        }
        Size size = modelMapper.map(sizeDTO, Size.class);
        size.setLastModifiedBy("user login admin");
        size.setLastModifiedDate(new Date());
        sizeRepository.save(size);
    }

    @Override
    public void deleteSize(Long id) throws LogicException {
        logger.info("Service info about delete Size, {}", id);
        Optional<Size> size = sizeRepository.findById(id);
        if (!size.isPresent()){
            throw new LogicException(ErrorCode.RECORD_NOT_EXISTED);
        }
        sizeRepository.delete(size.get());
    }
}
