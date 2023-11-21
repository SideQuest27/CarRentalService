package com.karan.CarRentalService.Error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorClass
{
    private HttpStatus httpStatus;
    private String message;
}
