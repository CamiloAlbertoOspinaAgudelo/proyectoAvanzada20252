package co.edu.uniquindio.application.service.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ImageService {
    Map upload(MultipartFile image) throws Exception;
    Map delete(String imageId) throws Exception;
}
