package com.pm.patientservice.cursor;

import ch.qos.logback.core.util.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Objects;

@Data
@AllArgsConstructor
public class PatientCursor {
    private int size = 5;
    private final String nextPageCursor;
    private final String prevPageCursor;

    public boolean hasNext() {
        return nextPageCursor != null && !nextPageCursor.isEmpty();
    }

    public boolean hasPrev() {
        return prevPageCursor != null && !prevPageCursor.isEmpty();
    }

    public boolean hasCursor() {
        return hasNext() || hasPrev();
    }

    public String getDecodedCursor(String cursorValue) {
        if (cursorValue == null || cursorValue.isEmpty()) {
            throw new IllegalArgumentException("Cursor Value is not valid");
        }

        var decodedBytes = Base64.getDecoder().decode(cursorValue);
        var decodedValue = new String(decodedBytes);

        return StringUtils.substringBetween(decodedValue, "###");
    }


    public String getEncodedCursor(String field, boolean hasPrevOrNextElements) {
        Objects.requireNonNull(field);

        if (!hasPrevOrNextElements) {
            return null;
        }

        var structuredValue = "###" + field + "### - " + LocalDateTime.now();
        return Base64.getEncoder().encodeToString(structuredValue.getBytes());
    }

    public String getSearchValue() {
        if (!hasCursor()) return "";

        return hasPrev()
                ? getDecodedCursor(prevPageCursor)
                : getDecodedCursor(nextPageCursor);
    }
}
