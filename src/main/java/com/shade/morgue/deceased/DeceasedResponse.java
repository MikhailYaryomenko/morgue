package com.shade.morgue.deceased;

import java.util.UUID;

public record DeceasedResponse(
        UUID id,
        String deceasedId,
        boolean deletionRequested
) {}
