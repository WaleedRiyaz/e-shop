package com.wizcode.eshop.contoller;

import com.wizcode.eshop.dto.ImageDTO;
import com.wizcode.eshop.exception.ResourceNotFoundException;
import com.wizcode.eshop.model.Image;
import com.wizcode.eshop.response.APIResponse;
import com.wizcode.eshop.service.image.IImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("${api.prefix}/images")
@RequiredArgsConstructor
public class ImageController {
    private final IImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<APIResponse> saveImages(@RequestParam List<MultipartFile> files, @RequestParam Long productId) {
        try {
            List<ImageDTO> imageDTOList = imageService.saveImage(files, productId);
            return ResponseEntity.ok(new APIResponse("Upload success!", imageDTOList));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new APIResponse("Upload failed", e.getMessage()));
        }
    }

    @GetMapping("/image/download/{imageId}")
    public ResponseEntity<Resource> downloadImage(@PathVariable Long imageId) throws SQLException {
        Image image =  imageService.getImageById(imageId);
        ByteArrayResource resource = new ByteArrayResource(image.getImage()
                .getBytes(1, (int)image.getImage().length()));

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(image.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +
                        image.getFileName() + "\"").body(resource);
    }

    @PutMapping("/image/{imageId}/update")
    public ResponseEntity<APIResponse> updateImage(@PathVariable Long imageId, @RequestBody MultipartFile file) {
        try {
            Image image = imageService.getImageById(imageId);
            if(image != null) {
                imageService.updateImage(file, imageId);
                return ResponseEntity.ok(new APIResponse("Update success!", null));
            }
        } catch (ResourceNotFoundException e) {
            return  ResponseEntity.status(NOT_FOUND).body(new APIResponse(e.getMessage(), null));
        }
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new APIResponse("Update failed!", INTERNAL_SERVER_ERROR));
    }


    @DeleteMapping("/image/{imageId}/delete")
    public ResponseEntity<APIResponse> deleteImage(@PathVariable Long imageId) {
        try {
            Image image = imageService.getImageById(imageId);
            if(image != null) {
                imageService.deleteImageById( imageId);
                return ResponseEntity.ok(new APIResponse("Delete success!", null));
            }
        } catch (ResourceNotFoundException e) {
            return  ResponseEntity.status(NOT_FOUND).body(new APIResponse(e.getMessage(), null));
        }
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new APIResponse("Delete failed!", INTERNAL_SERVER_ERROR));
    }
}
