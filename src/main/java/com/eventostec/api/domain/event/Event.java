package com.eventostec.api.domain.event;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Table(name = "event")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {

    @Id
    @GeneratedValue
    private UUID id;
    private String title;
    private String description;
    private String imgUrl;
    private String eventUrl;
    private Boolean remote;
    private Timestamp date;

    public static Event buildEvent(EventRequestDTO eventRequestDTO, String imgUrl) {
        return Event.builder()
                .title(eventRequestDTO.title())
                .description(eventRequestDTO.description())
                .imgUrl(imgUrl)
                .eventUrl(eventRequestDTO.eventUrl())
                .remote(eventRequestDTO.remote())
                .date(convertStringToTimestamp(eventRequestDTO.date()))
                .build();
    }

    private static Timestamp convertStringToTimestamp(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(dateString, formatter);
        return Timestamp.valueOf(localDateTime);
    }
}
