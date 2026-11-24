package com.shade.morgue.deceased;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

// TODO: Disallow duplicates of deceasedId
// Only allow if all previous copies are requested for deletion

@RestController
@RequestMapping("/deceased")
public class DeceasedController {
    private final DeceasedRepository deceasedRepository;

    private DeceasedController(DeceasedRepository deceasedRepository) {
        this.deceasedRepository = deceasedRepository;
    }
    
    @PostMapping
    public ResponseEntity<DeceasedResponse> createDeceased(@RequestBody CreateDeceasedRequest request) {

        Deceased deceased = new Deceased(request.deceasedId());

        Deceased savedDeceased = deceasedRepository.save(deceased);

        DeceasedResponse response = new DeceasedResponse(
                savedDeceased.getId(),
                savedDeceased.getDeceasedId(),
                savedDeceased.isDeletionRequested()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeceasedResponse> findById(@PathVariable UUID id) {
        return deceasedRepository.findById(id)
                .map(deceased -> ResponseEntity.ok(
                        new DeceasedResponse(
                                deceased.getId(),
                                deceased.getDeceasedId(),
                                deceased.isDeletionRequested()
                        )
                ))
                .orElse(ResponseEntity.notFound().build());
    }
}
