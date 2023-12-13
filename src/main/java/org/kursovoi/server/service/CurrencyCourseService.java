package org.kursovoi.server.service;

import lombok.RequiredArgsConstructor;
import org.kursovoi.server.dto.CurrencyCourseDto;
import org.kursovoi.server.dto.PeriodOfCurrencyCoursesDto;
import org.kursovoi.server.dto.UpdateCurrencyCourseDto;
import org.kursovoi.server.model.CurrencyCourse;
import org.kursovoi.server.repository.CurrencyCourseRepository;
import org.kursovoi.server.util.mapper.CurrencyCourseMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CurrencyCourseService {

    private final CurrencyCourseRepository repository;
    private final CurrencyCourseMapper mapper;

    @Transactional
    public List<CurrencyCourseDto> getAllCurrencyCourses() {
        return repository.findAll().stream().map(mapper::map).collect(Collectors.toList());
    }

    @Transactional
    public List<CurrencyCourseDto> getAllCurrencyCoursesFromPeriod(PeriodOfCurrencyCoursesDto period) {
        return repository.findByDateBetween(period.getDateOfStart(), period.getDateOfEnd())
                .stream().map(mapper::map).collect(Collectors.toList());
    }

    @Transactional
    public CurrencyCourseDto getCurrencyCourseForToday() {
        return mapper.map(repository.findByDate(LocalDate.now(ZoneId.of("UTC+3"))));
    }

    @Transactional
    public void updateCurrencyCourse(UpdateCurrencyCourseDto course) {
        CurrencyCourse newCourse = mapper.map(course);
        newCourse.setDate(LocalDate.now(ZoneId.of("UTC+3")));
        repository.save(newCourse);
    }

}
