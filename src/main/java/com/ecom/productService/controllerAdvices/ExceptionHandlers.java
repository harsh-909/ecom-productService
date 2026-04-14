package com.ecom.productService.controllerAdvices;


import com.ecom.productService.Exception.InvalidTokenException;
import com.ecom.productService.Exception.ProductNotFoundException;
import com.ecom.productService.Exception.UserNotFoundException;
import com.ecom.productService.dto.ArithmeticExceptionDto;
import com.ecom.productService.dto.ExceptionDto;
import com.ecom.productService.models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {

//    @ExceptionHandler(ArithmeticException.class)
//    public ResponseEntity<Void> handleArithmeticException(){
//        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//    }


    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ArithmeticExceptionDto> handleArithmeticException(){
        ArithmeticExceptionDto arithmeticExceptionDto = new ArithmeticExceptionDto();
        arithmeticExceptionDto.setMessage("Something went wrong");
        return new ResponseEntity<>(arithmeticExceptionDto,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleProductNotFoundException(
            ProductNotFoundException exception
    ){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(exception.getMessage());

        return new ResponseEntity<>(exceptionDto,HttpStatus.OK);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ExceptionDto> handleInvalidTokenException(
            InvalidTokenException exception
    ) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(exception.getMessage());

        return new ResponseEntity<>(exceptionDto, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleUserNotFoundException(
            UserNotFoundException exception
    ){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(exception.getMessage());

        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }
}
