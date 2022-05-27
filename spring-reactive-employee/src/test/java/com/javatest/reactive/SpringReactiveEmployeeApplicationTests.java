package com.javatest.reactive;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import com.javatest.reactive.controller.EmployeeController;
import com.javatest.reactive.dto.EmployeeDto;
import com.javatest.reactive.service.EmployeeService;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import  static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebFluxTest(EmployeeController.class)
class SpringReactiveEmployeeApplicationTests {

	@Autowired
    private WebTestClient webTestClient;
    @MockBean
    private EmployeeService service;

    @Test
    public void addProductTest(){
		Mono<EmployeeDto> employeeDtoMono=Mono.just(new EmployeeDto("101", "line1","lin2", null, null));
		when(service.saveEmployee(employeeDtoMono)).thenReturn(employeeDtoMono);

		webTestClient.post().uri("/products")
				.body(Mono.just(employeeDtoMono),EmployeeDto.class)
				.exchange()
				.expectStatus().isOk();//200

	}


}
