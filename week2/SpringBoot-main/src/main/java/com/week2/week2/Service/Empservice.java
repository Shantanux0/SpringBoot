package com.week2.week2.Service;

import com.week2.week2.DTO.EmpDTO;
import com.week2.week2.Entity.Empentity;
import com.week2.week2.Repositry.Emprepo;
import org.aspectj.util.Reflection;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class Empservice {

    private final Emprepo emprepo;

    private final ModelMapper mp;

    public Empservice(Emprepo emprepo, ModelMapper mp) {
        this.emprepo = emprepo;
        this.mp = mp;
    }

    public EmpDTO getEmpbyID(Long id) {
        Empentity empentity=emprepo.findById(id).orElse(null);
        return mp.map(empentity, EmpDTO.class);
    }

    public List<EmpDTO> getallEmp() {
        List<Empentity> empentities = emprepo.findAll();
      return   empentities
                .stream()
                .map(empentity -> mp.map(empentity, EmpDTO.class))
                .collect(Collectors.toList());

    }

    public EmpDTO createnew(EmpDTO inputemp) {
        Empentity tosaveEntity= mp.map(inputemp,Empentity.class);
        Empentity savedemp=emprepo.save(tosaveEntity);
        return mp.map(savedemp,EmpDTO.class) ;
    }


    public EmpDTO updateEmp(Long empId, EmpDTO empDTO) {
        Empentity empentity;

        if (emprepo.existsById(empId)) {
            // If empId exists, map and update
            empentity = emprepo.findById(empId).orElseThrow();
            mp.map(empDTO, empentity); // Update fields from DTO
            empentity.setId(empId);    // Ensure ID stays consistent
        } else {
            // If empId does not exist, create a new entity
            empentity = mp.map(empDTO, Empentity.class);
            empentity.setId(null); // Let JPA generate a new ID
        }

        // Save the entity
        Empentity savedempentity = emprepo.save(empentity);
        return mp.map(savedempentity, EmpDTO.class);
    }
    public Boolean isExits(Long empid){
        return emprepo.existsById(empid);
    }

    public Boolean deleteEmp(Long empid) {
        boolean exits = isExits(empid);
        if(!exits) return false;
        emprepo.deleteById(empid);
        return true ;


    }


    public EmpDTO updateEmp(Long empId, Map<String, Object> updates) {
        isExits(empId);
        Empentity empentity = emprepo.findById(empId).orElseThrow() ;

        updates.forEach((field, value) -> {
            Field fieldToUpdate = ReflectionUtils.findRequiredField(Empentity.class, field);
            fieldToUpdate.setAccessible(true);
            ReflectionUtils.setField(fieldToUpdate, empentity, value);
        });

        return mp.map(emprepo.save(empentity), EmpDTO.class);
    }


//    public EmployeeDTO updatePartialEmployeeById(Long employeeId, Map<String, Object> updates) {
//        isExistsByEmployeeId(employeeId);
//        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
//        updates.forEach((field, value) -> {
//            Field fieldToBeUpdated = ReflectionUtils.findRequiredField(EmployeeEntity.class, field);
//            fieldToBeUpdated.setAccessible(true);
//            ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);
//        });
//        return modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);
//    }
}

