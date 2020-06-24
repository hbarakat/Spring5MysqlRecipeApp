package ma.aqary.recipeapp.services;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Hamid Barakat
 */
public interface ImageService {
    void saveImageFile(Long recipeId, MultipartFile file);
}
