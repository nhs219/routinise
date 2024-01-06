package com.routinise.controller;

import com.routinise.request.RoutineCreate;
import com.routinise.response.RoutineResponse;
import com.routinise.service.RoutineService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/routines")
@RequiredArgsConstructor
public class RoutineController {

    private final RoutineService routineService;

    @PostMapping
    public void createRoutine(@RequestBody @Valid RoutineCreate request) {
        routineService.createRoutine(request);
    }

    @GetMapping("/{routineId}")
    public RoutineResponse getRoutine(@PathVariable Long routineId) {
        return routineService.getRoutine(routineId);
    }

    @GetMapping
    public List<RoutineResponse> getRoutines(Pageable pageable) {
        return routineService.getRoutines(pageable);
    }

    @DeleteMapping("/{routineId}")
    public void deleteRoutine(@PathVariable Long routineId) {

    }
}
