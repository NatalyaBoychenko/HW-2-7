package org.boichenko.domain;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
public class YoungestEldestWorker {
    private String type;
    private String name;
    private LocalDate birthday;
}
