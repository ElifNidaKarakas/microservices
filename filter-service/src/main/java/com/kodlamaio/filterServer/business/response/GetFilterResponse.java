package com.kodlamaio.filterServer.business.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetFilterResponse {
    private String id;
    private String carId;
    private String modelId;
    private String brandId;
    private String modelName;
    private String brandName;
    private int modelYear;
    private String plate;
    private int state;
    private int dailyPrice;
}