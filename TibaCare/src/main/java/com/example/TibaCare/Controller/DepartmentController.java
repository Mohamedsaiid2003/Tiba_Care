package com.example.TibaCare.Controller;

import com.example.TibaCare.department.Department;
import com.example.TibaCare.department.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class DepartmentController {
    private DepartmentService DepartmentService;
    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        DepartmentService = departmentService;
    }

    @GetMapping
    public List<Department> getAllDepartment() {
        return DepartmentService.getAllDepartments();
    }
    @GetMapping("/{departmentid}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long departmentid) {
        Optional<Department> Department = DepartmentService.getDepartmentsById(departmentid);
        return Department.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody Department Department) {
        Department savedDepartment = DepartmentService.saveDepartments(Department);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }
    @PutMapping("/{departmentid}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long departmentid, @RequestBody Department serviceDetails) {
        Optional<Department> updateService = DepartmentService.updatedService(departmentid, serviceDetails);
        return updateService.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{departmentid}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long departmentid) {
        boolean isDeleted = DepartmentService.deleteDepartments(departmentid);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
