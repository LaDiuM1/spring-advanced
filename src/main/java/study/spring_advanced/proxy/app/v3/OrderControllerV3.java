package study.spring_advanced.proxy.app.v3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface OrderControllerV3 {

    @GetMapping("/v3/request")
    String request(@RequestParam("itemId") String itemId);

    @GetMapping("/v3/no-log")
    String noLog();

}
