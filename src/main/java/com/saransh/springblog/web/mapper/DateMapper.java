package com.saransh.springblog.web.mapper;

import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/**
 * Created by CryptoSingh1337 on 7/27/2021
 */
@Mapper
public class DateMapper {
    public OffsetDateTime asOffsetDateTime(LocalDateTime ld) {
        return OffsetDateTime.of(ld, ZoneOffset.UTC);
    }

    public LocalDateTime asLocalDateTime(OffsetDateTime of) {
        return of.toLocalDateTime();
    }
}
