package com.shouwn.oj.repository.builiding;

import com.shouwn.oj.model.entity.building.Building;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingRepository extends JpaRepository<Building, Long> {
}
