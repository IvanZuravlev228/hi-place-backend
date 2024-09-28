package hi.place.controller;

import hi.place.dto.UserServiceImageResponseDto;
import hi.place.model.UserServiceImages;
import hi.place.service.DiscountService;
import hi.place.service.TypeOfServiceService;
import hi.place.service.UserService;
import hi.place.service.UserServiceImagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/images")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class ImageController {
    private static final String SERVER_URL = "http://localhost:8080";
    private static final String PATH_TO_STATIC_IMAGES = "src/main/resources/static/images/";

    private final UserService userService;
    private final UserServiceImagesService userServiceImagesService;
    private final TypeOfServiceService typeOfServiceService;
    private final DiscountService discountService;

    @GetMapping
    public ResponseEntity<List<UserServiceImageResponseDto>> getImagesByTypeOfServiceAndUserId(@RequestParam Long typeOfServiceId,
                                                                                               @RequestParam Long userId) {
        return new ResponseEntity<>(userServiceImagesService.getByTypeOfServiceAndUserId(typeOfServiceId, userId), HttpStatus.OK);
    }

    @GetMapping("/main")
    public ResponseEntity<List<UserServiceImageResponseDto>> getImagesByMainTypeOfServiceAndUserId(@RequestParam Long mainTypeOfServiceId,
                                                                                                   @RequestParam Long userId) {
        return new ResponseEntity<>(userServiceImagesService.getByMainTypeOfServiceAndUserId(mainTypeOfServiceId, userId), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserServiceImageResponseDto>> getImagesByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(userServiceImagesService.getByUserId(userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file,
                                              @RequestParam Long userId) {
        try {
            if (file.isEmpty()) {
                return new ResponseEntity<>("No file to upload", HttpStatus.BAD_REQUEST);
            }

            String uploadPath = PATH_TO_STATIC_IMAGES + userId + "/";
            createDirectoryIfNotExist(uploadPath);
            String fileName = file.getOriginalFilename();
            saveFile(file, uploadPath, fileName);

            String openPath = SERVER_URL + "/images/" + userId + "/" + fileName;
            userService.addLogoUrlToUser(openPath, userId);

            return new ResponseEntity<>("File upload successfully: " + fileName, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to upload file", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (MaxUploadSizeExceededException e) {
            return new ResponseEntity<>("Max upload size exceeded", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/discount")
    public ResponseEntity<String> uploadDiscountImage(@RequestParam("file") MultipartFile file,
                                                      @RequestParam Long discountId,
                                                      @RequestParam Long userId) {
        try {
            if (file.isEmpty()) {
                return new ResponseEntity<>("No file to upload", HttpStatus.BAD_REQUEST);
            }

            String uploadPath = PATH_TO_STATIC_IMAGES + userId + "/discounts/";
            createDirectoryIfNotExist(uploadPath);
            String fileName = file.getOriginalFilename();
            saveFile(file, uploadPath, fileName);

            String openPath = SERVER_URL + "/images/" + userId + "/discounts/" + fileName;

            discountService.updatePhotoUrl(openPath, discountId);

            return new ResponseEntity<>(openPath, HttpStatus.OK);
        } catch (FileAlreadyExistsException e) {
            return new ResponseEntity<>("File already exists", HttpStatus.BAD_GATEWAY);
        } catch (MaxUploadSizeExceededException e) {
            return new ResponseEntity<>("Max upload size exceeded", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to upload file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/examples/user/{userId}")
    public ResponseEntity<String> uploadUserExampleImages(@RequestParam("files") List<MultipartFile> files,
                                                          @RequestParam("typeOfServiceIds") List<Long> typeOfServiceIds,
                                                          @PathVariable Long userId) {
        try {
            if (files.isEmpty()) {
                return new ResponseEntity<>("No files to upload", HttpStatus.BAD_REQUEST);
            }

            String uploadPath = PATH_TO_STATIC_IMAGES + userId + "/examples/";
            createDirectoryIfNotExist(uploadPath);

            for (int i = 0; i < files.size(); i++) {
                MultipartFile file = files.get(i);
                Long typeOfServiceId = typeOfServiceIds.get(i);
                String fileName = file.getOriginalFilename();
                saveFile(file, uploadPath, fileName);

                String openPath = SERVER_URL + "/images/" + userId + "/examples/" + fileName;
                saveUserServiceImageModel(openPath, userId, typeOfServiceId);
            }
            return new ResponseEntity<>("Files uploaded successfully", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Error uploading files: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void createDirectoryIfNotExist(String uploadPath) {
        File directory = new File(uploadPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    private void saveUserServiceImageModel(String openPath, Long userId, Long typeOfServiceId) {
        UserServiceImages model = new UserServiceImages();
        model.setPath(openPath);
        model.setUser(userService.getById(userId));
        model.setTypeOfService(typeOfServiceService.getById(typeOfServiceId));
        userServiceImagesService.save(model);
    }

    private void saveFile(MultipartFile file, String uploadPath, String fileName) throws IOException {
        Path path = Paths.get(uploadPath + fileName);
        Files.copy(file.getInputStream(), path);
    }
}
