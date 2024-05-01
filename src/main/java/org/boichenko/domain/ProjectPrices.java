package org.boichenko.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProjectPrices {
    private int projectId;
    private int price;
}
