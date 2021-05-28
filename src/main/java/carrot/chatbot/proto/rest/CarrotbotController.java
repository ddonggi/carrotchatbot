package carrot.chatbot.proto.rest;

import carrot.chatbot.proto.dialogflow.CarrotChatbot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpResponse;

@RestController
public class CarrotbotController {
    private Logger logger = LoggerFactory.getLogger(CarrotbotController.class);

    @RequestMapping(value = "/carrot-chatbot-test", method = RequestMethod.GET)
    public @ResponseBody
    String restTest(HttpServletRequest request,
                  HttpServletResponse response) {
        String jsonResponse = null;
        jsonResponse = "Good!";

        logger.debug("log testing...");

        return jsonResponse;
    }

    @PostMapping(value = "/carrot-chatbot")
    public @ResponseBody
    String hookCarrotBot(@RequestBody String webhookRequest, HttpServletRequest request,
                       HttpServletResponse response) {
        String jsonResponse = null;
        jsonResponse = CarrotChatbot.makeResponse(webhookRequest);

        return jsonResponse;
    }
}
