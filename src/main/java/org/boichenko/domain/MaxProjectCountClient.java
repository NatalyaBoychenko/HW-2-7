package org.boichenko.domain;

import lombok.Data;
import lombok.Setter;
import lombok.ToString;

@Data
@ToString
public class MaxProjectCountClient {
    private String name;
    private int projectCount;
}
