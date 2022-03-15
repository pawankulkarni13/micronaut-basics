package com.stark.controller;

import com.stark.domain.Employee;
import com.stark.domain.Message;
import com.stark.repository.EmployeeRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;
import jakarta.inject.Inject;

import javax.validation.Valid;

@Validated
@Controller("/employees")
public class EmployeeController {

    @Inject
    private EmployeeRepository employeeRepository;

    @Post()
    public HttpResponse<?> savePerson(@Body @Valid Employee employee) {
        this.employeeRepository.save(employee);
        return HttpResponse.status(HttpStatus.CREATED).body(new Message(HttpStatus.CREATED.getCode(),"Saved successfully !"));
    }

    @Get()
    public HttpResponse<?> getPersons() {
        return HttpResponse.status(HttpStatus.OK).body(this.employeeRepository.findAll());
    }
}
