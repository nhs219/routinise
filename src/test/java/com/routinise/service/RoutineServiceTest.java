package com.routinise.service;

import com.routinise.domain.Routine;
import com.routinise.repository.RoutineRepository;
import com.routinise.request.RoutineCreate;
import com.routinise.response.RoutineResponse;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class RoutineServiceTest {

    @Autowired
    private RoutineServiceImpl routineService;

    @Autowired
    private RoutineRepository routineRepository;

    @BeforeEach
    void init() {
        routineRepository.deleteAll();
    }

    @Test
    @DisplayName("routine 저장")
    void saveRoutine() {
        //given
        RoutineCreate routine = RoutineCreate.builder()
                .title("test")
                .content("test content")
                .build();

        //when
        routineService.createRoutine(routine);

        //then
        Assertions.assertThat(routineRepository.count()).isEqualTo(1);
    }

    @Test
    @DisplayName("routine 단건 조회")
    void getRoutine() {
        //given
        Routine requestRoutine = Routine.builder()
                .title("test")
                .content("test content")
                .alarmTime("12:20")
                .alarmYn("Y")
                .startDate("2024-01-01")
                .endDate("9999-12-31")
                .build();
        routineRepository.save(requestRoutine);

        //when
        RoutineResponse routine = routineService.getRoutine(requestRoutine.getId());

        //then
        Assertions.assertThat(routine.getTitle()).isEqualTo("test");
    }

    @Test
    @DisplayName("routine 단건 조회 오류 테스트")
    void getRoutine_exception() {
        assertThrows(IllegalArgumentException.class, () -> routineService.getRoutine(1L));
    }

    @Test
    @DisplayName("routines 다건 조회")
    void getRoutines() {
        //given
        List<Routine> requestRoutines = IntStream.range(0, 30)
                .mapToObj(i -> Routine.builder()
                        .title("title - " + i)
                        .content("content - " + i)
                        .alarmTime("12:20")
                        .alarmYn("Y")
                        .startDate("2024-01-01")
                        .endDate("9999-12-31")
                        .build())
                .collect(Collectors.toList());
        routineRepository.saveAll(requestRoutines);

        //when
        Pageable pageable = PageRequest.of(0, 5);
        List<RoutineResponse> response = routineService.getRoutines(pageable);

        //then
        Assertions.assertThat(response.size()).isEqualTo(5);
    }
}