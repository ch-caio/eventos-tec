package com.eventostec.api.service;

import com.amazonaws.services.s3.AmazonS3;
import com.eventostec.api.domain.event.Event;
import com.eventostec.api.domain.event.EventRequestDTO;
import com.eventostec.api.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

import static com.eventostec.api.domain.event.Event.buildEvent;

@Service
public class EventService {

    @Value("${aws.bucket.name}")
    private String bucketName;
    @Autowired
    private AmazonS3 amazonS3;
    @Autowired
    private EventRepository eventRepository;

    public Event createEvent(EventRequestDTO eventRequestDTO) {
        String imgUrl = null;

        if (eventRequestDTO.image() != null) {
            imgUrl = upLoadImg(eventRequestDTO.image());
        }
        var event = buildEvent(eventRequestDTO, imgUrl);
        eventRepository.save(event);

        return event;
    }

    private String upLoadImg(MultipartFile multipartFile) {
        String filename = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

        try {
            File file = convertMultipartToFile(multipartFile);
            amazonS3.putObject(bucketName, filename, file);
            file.delete();
            return amazonS3.getUrl(bucketName, filename).toString();
        } catch (Exception e) {
            System.out.println("error saving image");
            return null;
        }
    }

    private File convertMultipartToFile(MultipartFile multipartFile) throws IOException {
        File convFile = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convFile);

        fos.write(multipartFile.getBytes());
        fos.close();
        return convFile;
    }
}
