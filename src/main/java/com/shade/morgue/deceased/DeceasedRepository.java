package com.shade.morgue.deceased;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeceasedRepository extends JpaRepository<Deceased, UUID> {
}
