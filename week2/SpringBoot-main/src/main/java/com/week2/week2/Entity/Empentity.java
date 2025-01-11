package com.week2.week2.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Emp")
public class Empentity {

   @jakarta.persistence.Id // we wil provide you the ID
   @GeneratedValue(strategy = GenerationType.AUTO) //autoincrement or generation
    private Long Id ;
   private String name ;
  private  String email ;
  private  int age ;
   private LocalDate dateofj;
   private boolean active ;


}
