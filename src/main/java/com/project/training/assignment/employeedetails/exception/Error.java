package com.project.training.assignment.employeedetails.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Error extends RuntimeException{
    private String message;
    private HttpStatus status;


}
