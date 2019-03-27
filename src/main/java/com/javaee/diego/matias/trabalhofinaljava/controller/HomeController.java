package com.javaee.diego.matias.trabalhofinaljava.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
  
  @RequestMapping("/")
  void handleFoo(HttpServletResponse response) throws IOException {
    response.sendRedirect("/swagger-ui.html");
  }
}