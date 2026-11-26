package com.shade.morgue.deceased;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;
import java.util.UUID;

@Entity
class Deceased {

    // TODO: Validate deceasedId uniqueness at database and service level
    // TODO: Add date of creation

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private UUID id;
    @Column(nullable = false)
    private String deceasedId;
    @Column(nullable = false)
    private boolean deletionRequested = false;

    protected Deceased() {}

    public Deceased(String deceasedId) {
        this.deceasedId = deceasedId;
    }

    @Override
    public String toString() {
        return String.format("Deceased{id='%s', deceasedId='%s', deletionRequested=%b}",
                id, deceasedId, deletionRequested);

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Deceased deceased))
            return false;
        return Objects.equals(this.id, deceased.id) && Objects.equals(this.deceasedId, deceased.deceasedId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.deceasedId);
    }

    public boolean isDeletionRequested() {
        return deletionRequested;
    }

    public UUID getId() {
        return id;
    }

    public String getDeceasedId() {
        return deceasedId;
    }
}