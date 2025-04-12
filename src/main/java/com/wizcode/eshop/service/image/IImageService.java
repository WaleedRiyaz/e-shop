package com.wizcode.eshop.service.image;

import com.wizcode.eshop.dto.ImageDTO;
import com.wizcode.eshop.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    Image getImageById(Long id);
    void deleteImageById(Long id);
    List<ImageDTO> saveImage(List<MultipartFile> file, Long productId);
    void updateImage(MultipartFile file, Long imageId);

}