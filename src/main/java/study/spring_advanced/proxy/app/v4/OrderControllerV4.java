package study.spring_advanced.proxy.app.v4;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface OrderControllerV4 {

    @GetMapping("/v4/request")
    String request(@RequestParam("itemId") String itemId);

    @GetMapping("/v4/no-log")
    String noLog();

}
