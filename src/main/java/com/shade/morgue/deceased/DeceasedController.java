package com.shade.morgue.deceased;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/deceased")
public class DeceasedController {
    private final DeceasedRepository deceasedRepository;

    private DeceasedController(DeceasedRepository deceasedRepository) {
        this.deceasedRepository = deceasedRepository;
    }

    @PostMapping
    public ResponseEntity<Deceased> newDeceased(@RequestBody Deceased newDeceased) {
        Deceased savedDeceased = deceasedRepository.save(newDeceased);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedDeceased);
    }
}
