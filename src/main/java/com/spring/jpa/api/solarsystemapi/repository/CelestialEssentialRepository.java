package com.spring.jpa.api.solarsystemapi.repository;

import com.spring.jpa.api.solarsystemapi.entity.CelestialEssential;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CelestialEssentialRepository
        extends JpaRepository<CelestialEssential, String> {

}
