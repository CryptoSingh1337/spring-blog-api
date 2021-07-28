package com.saransh.springblog.web.mapper;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/**
 * Created by CryptoSingh1337 on 7/27/2021
 */
@Component
public class DateMapper {
    public OffsetDateTime asOffsetDateTime(LocalDateTime ld) {
        if (ld != null)
            return OffsetDateTime.of(ld, ZoneOffset.UTC);
        return null;
    }

    public LocalDateTime asLocalDateTime(OffsetDateTime of) {
        if (of != null)
            return of.toLocalDateTime();
        return null;
    }
}
