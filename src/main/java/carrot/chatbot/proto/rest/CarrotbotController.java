package carrot.chatbot.proto.rest;

import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController
public class CarrotbotController {

    @GetMapping("/hello")
    public String getCarrot(@RequestBody HttpRequest httpRequest, HttpResponse httpResponse){

        return "";
    }
}
