package com.routinise.request;

import com.routinise.domain.Routine;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Setter;
import lombok.ToString;

@Setter
public class RoutineCreate {
    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    private String weekList;

    @Pattern(regexp = "^(?:[01]\\d|2[0-3]):[0-5]\\d$"
            , message = "올바른 시간 형식이 아닙니다.")
    private String alarmTime;

    private String alarmYn;

    @NotBlank(message = "시작일을 입력해주세요.")
    @Pattern(regexp = "\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])"
            , message = "올바른 날짜 형식이 아닙니다.")
    private String startDate;

    @NotBlank(message = "종료일을 입력해주세요.")
    @Pattern(regexp = "\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])"
            , message = "올바른 날짜 형식이 아닙니다.")
    private String endDate;

    public RoutineCreate() {
    }

    @Builder
    public RoutineCreate(String title, String content, String weekList, String alarmTime, String alarmYn, String startDate, String endDate) {
        this.title = title;
        this.content = content;
        this.weekList = weekList;
        this.alarmTime = alarmTime;
        this.alarmYn = alarmYn;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Routine makeRoutine() {
        return Routine.builder()
                .title(title)
                .content(content)
                .week(weekList)
                .alarmTime(alarmTime)
                .alarmYn(alarmYn)
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }

}
