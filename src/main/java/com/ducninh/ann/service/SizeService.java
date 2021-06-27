package com.ducninh.ann.service;

import com.ducninh.ann.extension.LogicException;
import com.ducninh.ann.model.Size;
import com.ducninh.ann.service.dto.SizeDTO;

import java.util.List;

public interface SizeService {

    List<SizeDTO> findAllSize();

    SizeDTO findByIdSize(Long id) throws LogicException;

    void  createSize(SizeDTO sizeDTO) throws LogicException;

    void updateSize(SizeDTO sizeDTO) throws LogicException;

    void deleteSize(Long id) throws LogicException;

}
