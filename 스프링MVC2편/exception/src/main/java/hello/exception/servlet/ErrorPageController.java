package hello.exception.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class ErrorPageController {

    public static final String FORWARD_REQUEST_URI = "jakarta.servlet.forward.request_uri";

    public static final String FORWARD_CONTEXT_PATH = "jakarta.servlet.forward.context_path";

    public static final String FORWARD_MAPPING = "jakarta.servlet.forward.mapping";

    public static final String FORWARD_PATH_INFO = "jakarta.servlet.forward.path_info";

    public static final String FORWARD_SERVLET_PATH = "jakarta.servlet.forward.servlet_path";

    public static final String FORWARD_QUERY_STRING = "jakarta.servlet.forward.query_string";

    public static final String INCLUDE_REQUEST_URI = "jakarta.servlet.include.request_uri";

    public static final String INCLUDE_CONTEXT_PATH = "jakarta.servlet.include.context_path";

    public static final String INCLUDE_PATH_INFO = "jakarta.servlet.include.path_info";

    public static final String INCLUDE_MAPPING = "jakarta.servlet.include.mapping";

    public static final String INCLUDE_SERVLET_PATH = "jakarta.servlet.include.servlet_path";

    public static final String INCLUDE_QUERY_STRING = "jakarta.servlet.include.query_string";

    public static final String ERROR_EXCEPTION = "jakarta.servlet.error.exception";

    public static final String ERROR_EXCEPTION_TYPE = "jakarta.servlet.error.exception_type";

    public static final String ERROR_MESSAGE = "jakarta.servlet.error.message";
    public static final String ERROR_REQUEST_URI = "jakarta.servlet.error.request_uri";
    public static final String ERROR_SERVLET_NAME = "jakarta.servlet.error.servlet_name";
    public static final String ERROR_STATUS_CODE = "jakarta.servlet.error.status_code";
    @RequestMapping("/error-page/404")
    public String errorPage404(HttpServletResponse response, HttpServletRequest request){
        log.info("errorPage 404");
        printErrorInfo(request);
        return "error-page/404";
    }

    @RequestMapping("/error-page/500")
    public String errorPage500(HttpServletResponse response, HttpServletRequest request){
        log.info("errorPage 500");
        printErrorInfo(request);
        return "error-page/500";
    }

    @RequestMapping(value = "/error-page/500", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> errorPage500Api(HttpServletRequest request, HttpServletResponse response){
        log.info("API errorPage 500");

        Map<String, Object> result = new HashMap<>();
        Exception ex = (Exception) request.getAttribute(ERROR_EXCEPTION);
        result.put("status",request.getAttribute(ERROR_STATUS_CODE));
        result.put("message",ex.getMessage());

        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        return new ResponseEntity<>(result, HttpStatus.valueOf(statusCode));
    }

    private void printErrorInfo(HttpServletRequest request){
        log.info("FORWARD_REQUEST_URI = {}",request.getAttribute(FORWARD_REQUEST_URI));
        log.info("FORWARD_CONTEXT_PATH = {}",request.getAttribute(FORWARD_CONTEXT_PATH));
        log.info("FORWARD_MAPPING = {}",request.getAttribute(FORWARD_MAPPING));
        log.info("FORWARD_PATH_INFO = {}",request.getAttribute(FORWARD_PATH_INFO));
        log.info("FORWARD_SERVLET_PATH = {}",request.getAttribute(FORWARD_SERVLET_PATH));
        log.info("FORWARD_QUERY_STRING = {}",request.getAttribute(FORWARD_QUERY_STRING));
        log.info("INCLUDE_REQUEST_URI = {}",request.getAttribute(INCLUDE_REQUEST_URI));
        log.info("INCLUDE_CONTEXT_PATH = {}",request.getAttribute(INCLUDE_CONTEXT_PATH));
        log.info("INCLUDE_MAPPING = {}",request.getAttribute(INCLUDE_MAPPING));
        log.info("INCLUDE_SERVLET_PATH = {}",request.getAttribute(INCLUDE_SERVLET_PATH));
        log.info("INCLUDE_QUERY_STRING = {}",request.getAttribute(INCLUDE_QUERY_STRING));
        log.info("ERROR_EXCEPTION = {}",request.getAttribute(ERROR_EXCEPTION));
        log.info("ERROR_EXCEPTION_TYPE = {}",request.getAttribute(ERROR_EXCEPTION_TYPE));
        log.info("ERROR_MESSAGE = {}",request.getAttribute(ERROR_MESSAGE));
        log.info("ERROR_REQUEST_URI = {}",request.getAttribute(ERROR_REQUEST_URI));
        log.info("ERROR_SERVLET_NAME = {}",request.getAttribute(ERROR_SERVLET_NAME));
        log.info("ERROR_STATUS_CODE = {}",request.getAttribute(ERROR_STATUS_CODE));
    }
}
