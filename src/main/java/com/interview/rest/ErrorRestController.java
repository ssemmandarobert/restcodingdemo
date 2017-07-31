package com.interview.rest;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by robert.j.ssemmanda on 29/07/2017.
 * This class handles /error page
 */
@RestController
public class ErrorRestController implements ErrorController {
    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error() {
        return "Oops, looks like an invalid user action :(";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
