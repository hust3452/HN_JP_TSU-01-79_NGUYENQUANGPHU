package com.ra.demo.model.entity;

import com.ra.demo.model.constant.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity

public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
