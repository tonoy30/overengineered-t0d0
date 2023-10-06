package me.tonoy.recapspringboot.utils;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateService {
    public static ZonedDateTime getCurrentZonedDateTime() {
        return ZonedDateTime.now(ZoneId.of("UTC"));
    }
}
