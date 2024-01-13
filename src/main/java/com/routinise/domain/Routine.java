package com.routinise.domain;

import jakarta.persistence.*;

import com.routinise.response.RoutineResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Routine extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String content;

    private String userSeq;

    private String week;

    private LocalTime alarmTime;
    private String alarmYn;

    private LocalDate startDate;

    private LocalDate endDate;

    @Builder
    public Routine(String title, String content, String userSeq, String week, String alarmTime, String alarmYn, String startDate, String endDate){
        this.title = title;
        this.content = content;
        this.userSeq = userSeq;
        this.week = week;
        this.alarmTime = getLocalTime(alarmTime);
        this.alarmYn = alarmYn;
        this.startDate = getLocalDate(startDate);
        this.endDate = getLocalDate(endDate);
    }

    private LocalDate getLocalDate(String date) {
        if (date == null) {
            return null;
        }
        return LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
    }

    private LocalTime getLocalTime(String time) {
        if (time == null) {
            return null;
        }
        return LocalTime.parse(time, DateTimeFormatter.ISO_TIME);
    }

    public RoutineResponse makeResponse() {
        return RoutineResponse.builder()
                .id(id)
                .title(title)
                .content(content)
                .alarmTime(alarmTime.toString())
                .alarmYn(alarmYn)
                .startDate(startDate.toString())
                .endDate(endDate.toString())
                .build();
    }
}
