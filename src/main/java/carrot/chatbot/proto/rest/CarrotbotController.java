package carrot.chatbot.proto.rest;

import carrot.chatbot.proto.dialogflow.CarrotChatbot;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CarrotbotController {
    private Logger logger = LoggerFactory.getLogger(CarrotbotController.class);

    @GetMapping(value = "/test" )
    public @ResponseBody
    String restTest2(HttpServletRequest request,
                    HttpServletResponse response) {
        String jsonResponse = null;
        try {
        //아래 URL 은 삭제되었을 수 있으므로, 문제가 발생한다면 최신 기사의 URL 을 사용한다.
        String url= "https://www.daangn.com/search/"+ "ipad";
        System.out.println("url : "+url);
        Document doc = Jsoup.connect(url).get();

        Elements elements = doc.select(".articles-wrap");

//        String[] p = elements.get(0).text().split("\\.");
        List<Element> list = new ArrayList<>();
        for(Element element:elements) {
            list.add(element);
        }
        for(int i=0; i<list.size();i++) {
            System.out.println("list "+i+":"+list.get(i));
        }
//
//        for (int i = 0; i < p.length; i++) {
//            System.out.println("p"+i+":"+p[i]);
//        }
    } catch (
    IOException e) {
        e.printStackTrace();
    }
        return jsonResponse;
    }


    @GetMapping(value = "/carrot-chatbot-test" )
    public @ResponseBody
    String restTest(HttpServletRequest request,
                  HttpServletResponse response) {
        String jsonResponse = null;
        jsonResponse="success";
//        jsonResponse = CarrotChatbot.makeResponse(webhookRequest);;

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
