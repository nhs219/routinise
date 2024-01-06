package com.routinise.service;

import com.routinise.domain.Routine;
import com.routinise.repository.RoutineRepository;
import com.routinise.request.RoutineCreate;
import com.routinise.response.RoutineResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoutineServiceImpl implements RoutineService{
    private final RoutineRepository routineRepository;

    public void createRoutine(RoutineCreate routineCreate) {
        routineRepository.save(routineCreate.makeRoutine());
    }

    public RoutineResponse getRoutine(Long id) {
        Routine routine = routineRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("데이터를 찾을 수 없습니다."));

        return routine.makeResponse();
    }

    public List<RoutineResponse> getRoutines(Pageable pageable) {
        List<RoutineResponse> routines =  routineRepository.findAll(pageable).stream()
                .map(Routine::makeResponse)
                .collect(Collectors.toList());

        return routines;
    }
}
