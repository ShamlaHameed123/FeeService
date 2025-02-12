package rak.skiply.fee.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import rak.skiply.common_services.dto.StudentDto;


@FeignClient(name = "student-service", url = "http://localhost:8080", path = "/student-service")
public interface StudentClient {

    @GetMapping("/students/{studentId}")
    public ResponseEntity<StudentDto> getStudentDetails(@PathVariable("studentId") String studentId);

}
