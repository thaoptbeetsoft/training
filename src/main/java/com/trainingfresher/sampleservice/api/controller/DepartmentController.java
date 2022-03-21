package com.trainingfresher.sampleservice.api.controller;

import com.trainingfresher.sampleservice.api.form.DepartmentForm;
import com.trainingfresher.sampleservice.api.response.ApiResponse;
import com.trainingfresher.sampleservice.model.dto.DepartmentDto;
import com.trainingfresher.sampleservice.model.entity.Department;
import com.trainingfresher.sampleservice.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("department/api")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/addDepartment")
    public ResponseEntity<ApiResponse> addDepartment(@RequestBody DepartmentForm data) {
        Department department = departmentService.addNewDepartment(data);
        DepartmentDto dto = department.toDto();
        ApiResponse response = ApiResponse.success(department,HttpStatus.OK.value(), "Thêm thành công");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/departments")
    public ResponseEntity<ApiResponse> findAllDepartments() {
        List<Department> departments = departmentService.getListDepartment();
        List<DepartmentDto> dto = departments.stream().map(Department::toDto).collect(Collectors.toList());
        ApiResponse response = ApiResponse.success(dto, HttpStatus.OK.value(), "Danh sách các phòng ban");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/departmentByName/{name}")
    public ResponseEntity<ApiResponse> findDepartmentByName(@PathVariable String name) {
        Department department = departmentService.getByName(name);
        DepartmentDto dto = department.toDto();
        ApiResponse response = ApiResponse.success(department,HttpStatus.OK.value(), "Phòng ban: " + name);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/departmentById/{id}")
    public ResponseEntity<ApiResponse> findDepartmentById(@PathVariable long id) {
        Department department = departmentService.getById(id);
        DepartmentDto dto = department.toDto();
        ApiResponse response = ApiResponse.success(department,HttpStatus.OK.value(), "Phòng ban" + id);
        return ResponseEntity.ok(response);
    }

    @PutMapping( "/update/{id}")
    public ResponseEntity<ApiResponse> updateDepartment(@RequestBody DepartmentForm form, @PathVariable("id") Long id) {
        Department department = departmentService.updateDepartment(form.getName(), id);
        DepartmentDto dto = department.toDto();
        ApiResponse response = ApiResponse.success(department,HttpStatus.OK.value(), "Chỉnh sửa thành công " + id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteDepartment(@PathVariable long id) {
        departmentService.deleteDepartment(id);
        ApiResponse response = ApiResponse.success(null,HttpStatus.OK.value(), "Xóa thành công" + id);
        return ResponseEntity.ok(response);
    }
}
