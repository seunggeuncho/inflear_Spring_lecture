package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class LogTestController {

 //   private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/log-test", method = RequestMethod.GET)
    public String logTest(){
        String name = "Spring";

        log.trace(" trace log={}", name);
        log.debug(" debug log = {}", name);
        log.info(" info log={}",name);
        log.warn(" info log={}", name);
        log.error(" info log={}", name);
        return "ok";
    }

    @GetMapping("mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data){
        log.info("mappingPath userId={}", data);
        return "ok";
    }

    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable String orderId){
        log.info("mappingPath userId = {}, orderId = {}", userId, orderId);

        return "ok";
    }

    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsumes(){
        log.info("mappingConsumes");
        return "ok";
    }
}
