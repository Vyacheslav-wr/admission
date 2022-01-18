package by.salei.admission.service;

import by.salei.admission.service.api.FileCreatePath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Slf4j
@Component
public class FileCreatePathImpl implements FileCreatePath {

    @Value("${icons.path}")
    private String uploadPath;

    @Override
    public String createFilePath(MultipartFile multipartFile) {

        log.info("Executing method createFilePath()");

        if (multipartFile != null) {
            File dir = new File(uploadPath);

            if(!dir.exists()){
                dir.mkdir();
            }

            String uuid = UUID.randomUUID().toString();

            return uuid + "." + multipartFile.getOriginalFilename();
        }
        return null;
    }
}
