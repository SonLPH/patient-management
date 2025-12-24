package com.pm.patientservice.dto;

public record CursorPageResponseDTO<T>(
        T content,
        String prevCursor,
        String nextCursor
) {}
