package com.week2.week2.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class EmpDTO {
    Long Id ;
    String name ;
    String email ;
    int age ;
    LocalDate dateofj;
    @JsonProperty("active")
    boolean active ;




}

