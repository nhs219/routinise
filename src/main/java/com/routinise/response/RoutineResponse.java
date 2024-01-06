package com.routinise.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RoutineResponse {
    private final Long id;
    private final String title;
    private final String content;

    private final String alarmYn;

    private final String alarmTime;

    private final String startDate;

    private final String endDate;

    @Builder
    public RoutineResponse(Long id, String title, String content, String alarmYn, String alarmTime, String startDate, String endDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.alarmYn = alarmYn;
        this.alarmTime = alarmTime;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
