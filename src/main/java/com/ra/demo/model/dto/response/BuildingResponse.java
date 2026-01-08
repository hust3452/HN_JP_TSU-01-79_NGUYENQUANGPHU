package com.ra.demo.model.dto.response;

import com.ra.demo.model.constant.Status;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class BuildingResponse {
    private int id;
    private String name;
    private double area;
    private String area_unit;
    private LocalDate start_date;
    private int time;
    private String time_unit;
    private String design;
    private String content;
    private Status status;

}
