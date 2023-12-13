package org.kursovoi.server.repository;

import org.kursovoi.server.model.CurrencyCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CurrencyCourseRepository extends JpaRepository<CurrencyCourse, Long> {

    List<CurrencyCourse> findByDateBetween(LocalDate dateStart, LocalDate dateEnd);

    CurrencyCourse findByDate(LocalDate date);



}
