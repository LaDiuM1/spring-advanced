package study.spring_advanced.proxy.app.v0;

import org.springframework.web.bind.annotation.*;

@RestController
public interface OrderControllerV0 {

    @GetMapping("/v0/request")
    String request(@RequestParam("itemId") String itemId);

    @GetMapping("/v0/no-log")
    String noLog();

}
