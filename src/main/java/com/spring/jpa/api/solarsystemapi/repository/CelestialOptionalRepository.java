package com.spring.jpa.api.solarsystemapi.repository;

import com.spring.jpa.api.solarsystemapi.entity.CelestialOptional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CelestialOptionalRepository
            extends JpaRepository<CelestialOptional, String> {
}
