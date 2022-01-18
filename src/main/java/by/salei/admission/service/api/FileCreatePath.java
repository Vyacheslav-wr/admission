package by.salei.admission.service.api;

import org.springframework.web.multipart.MultipartFile;

public interface FileCreatePath {

    String createFilePath(MultipartFile file);
}
