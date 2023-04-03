package com.example.spacenter.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;

public class ErrorHandlingController implements ErrorController {

    @GetMapping("/error")
    public String handleError(HttpServletRequest request) {
        String errorPage = "error";

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                errorPage = "error/404";

            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                errorPage = "error/403";

            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                errorPage = "error/500";

            } else if (statusCode == HttpStatus.METHOD_NOT_ALLOWED.value()) {
                errorPage = "error/405";

            } else if (statusCode == HttpStatus.BAD_REQUEST.value()) {
                errorPage = "error/400";
            }
        }
        return errorPage;
    }
}
