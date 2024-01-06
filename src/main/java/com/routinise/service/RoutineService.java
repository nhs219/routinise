package com.routinise.service;

import com.routinise.request.RoutineCreate;
import com.routinise.response.RoutineResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoutineService {

    void createRoutine(RoutineCreate routineCreate);
    RoutineResponse getRoutine(Long id);
    List<RoutineResponse> getRoutines(Pageable pageable);
}
