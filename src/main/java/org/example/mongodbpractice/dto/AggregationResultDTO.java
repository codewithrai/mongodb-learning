package org.example.mongodbpractice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AggregationResultDTO {
    private String id;
    private String carrierName;
    private String carrierCategory;
    private String localName;
    private String transportName;
    private String freightName;
}
