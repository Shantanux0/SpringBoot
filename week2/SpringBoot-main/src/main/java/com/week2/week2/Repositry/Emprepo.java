package com.week2.week2.Repositry;

import com.week2.week2.Entity.Empentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Emprepo extends JpaRepository<Empentity,Long> {

}
