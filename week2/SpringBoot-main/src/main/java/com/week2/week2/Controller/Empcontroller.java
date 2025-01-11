package com.week2.week2.Controller;

import com.week2.week2.DTO.EmpDTO;
import com.week2.week2.Entity.Empentity;
import com.week2.week2.Service.Empservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(path = "/emp")
public class Empcontroller {

    private final Empservice empservice; // injected in controller

    public Empcontroller(Empservice empservice) {
        this.empservice = empservice;
    }

    //    @GetMapping(path={"/emp"})
//    public String getEmp(){
//        return "hello from emp";
//    }
    //PathVariable example
    @GetMapping(path = "{empid}")
    public EmpDTO getEmpid(@PathVariable(name="empid") Long id){//ager hume separete names use karneho tho apn "name" use karte hai
        return empservice.getEmpbyID(id);

    }
    //Requestparam example
    @GetMapping
    public List<EmpDTO> getallEmp(@RequestParam(required = false,name = "inputage")Integer age,
                                     @RequestParam(required = false)String name){
        return empservice.getallEmp();
    }

    @PostMapping
    public EmpDTO CreateNewEmp(@RequestBody EmpDTO inputemp){
//        return "hello from post";
//        inputemp.setId(100L);
        return empservice.createnew(inputemp);
    }

    @PutMapping(path = "/{empId}")
    public EmpDTO updateEmp(@RequestBody EmpDTO empDTO, @PathVariable Long empId) {
        return empservice.updateEmp(empId, empDTO);
    }

    @DeleteMapping(path = "/{empid}")
    public Boolean deleteEmp(@PathVariable Long empid){
       return empservice.deleteEmp(empid);
    }

//    n Spring, a ResponseEntity is a class used to represent the entire HTTP response,
//            including the status code, headers, and body. It allows you to have full control
//    over the HTTP response sent to the client.
    @PatchMapping(path = "/{empId}")
    public ResponseEntity<Object> patchemp(@RequestBody Map<String, Object> updates, @PathVariable Long empId) {
        EmpDTO empDTO = empservice.updateEmp(empId, updates); // Pass updates directly
        if (empDTO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(empDTO);
    }



//    @PatchMapping(path = "/{employeeId}")
//    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@RequestBody Map<String, Object> updates,
//                                                                 @PathVariable Long employeeId) {
//        EmployeeDTO employeeDTO = employeeService.updatePartialEmployeeById(employeeId, updates);
//        if (employeeDTO == null) return ResponseEntity.notFound().build();
//        return ResponseEntity.ok(employeeDTO);
//    }



}
