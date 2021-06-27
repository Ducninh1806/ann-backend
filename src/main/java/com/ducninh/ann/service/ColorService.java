package com.ducninh.ann.service;

import com.ducninh.ann.extension.LogicException;
import com.ducninh.ann.service.dto.ColorDTO;

import java.io.IOException;
import java.util.List;

public interface ColorService {

    List<ColorDTO> findAllColor();

    ColorDTO findByIdColor(Long id) throws LogicException;

    void  createColor(ColorDTO colorDTO) throws LogicException, IOException;

    void updateColor(ColorDTO colorDTO) throws LogicException, IOException;

    void deleteColor(Long id) throws LogicException;
}
