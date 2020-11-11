package br.com.desafio.controllers;

import org.springframework.http.MediaType;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;

@Api(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class GenericController {
  
}
