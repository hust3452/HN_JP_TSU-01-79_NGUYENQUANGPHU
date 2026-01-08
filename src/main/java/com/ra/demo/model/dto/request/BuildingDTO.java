package com.ra.demo.model.dto.request;

import com.ra.demo.model.constant.Status;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class BuildingDTO {
    @NotNull(message = "Name can not Null")
    private String name;
    @NotNull(message = "Area can not Null")
    private double area;
    @NotNull(message = "Area Unit can not Null")
    private String area_unit;
    @NotNull(message = "Start Date can not Null")
    private LocalDate start_date;
    @NotNull(message = "Time can not Null")
    private int time;
    @NotNull(message = "Time Unit can not Null")
    private String time_unit;
    @NotNull(message = "Design can not Null")
    private String design;
    @NotNull(message = "Content can not Null")
    private String content;

    private Status status;
}
