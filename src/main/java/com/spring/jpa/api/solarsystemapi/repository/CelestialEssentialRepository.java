package com.spring.jpa.api.solarsystemapi.repository;

import com.spring.jpa.api.solarsystemapi.entity.CelestialEssential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CelestialEssentialRepository
        extends JpaRepository<CelestialEssential, String> {

    // 특정 천체의 정보를 가져오는 기능
    // SELECT * FROM tbl_todo WHERE user_id = ?
//    @Query("SELECT ce FROM CelestialEssential ce WHERE ce.celestialNumber = :celestialNumber")
//    List<CelestialEssential>
//        findAllByName(@Param("celestialNumber") CelestialEssential ce);



}
