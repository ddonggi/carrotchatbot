package carrot.chatbot.proto.dialogflow;

import carrot.chatbot.proto.util.PayloadTemplate;
import com.google.cloud.dialogflow.v2.WebhookRequest;
import com.google.cloud.dialogflow.v2.WebhookResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.ListValue;
import com.google.protobuf.Struct;
import com.google.protobuf.Value;
import com.google.protobuf.util.JsonFormat;

public class CarrotChatbot {
    public static String makeResponse(String webhookRequest) {
        PayloadTemplate payloadTemplate = new PayloadTemplate();
        System.out.println("webhookRequest >>");
        System.out.println(webhookRequest);
        String jsonResponse = null;
        JsonParser parser = new JsonParser();

        WebhookRequest.Builder webhookRequestBuilder = WebhookRequest.newBuilder();
        try {
            JsonFormat.parser().merge(webhookRequest, webhookRequestBuilder);
            System.out.println("webHook Request + Builder Merge >>>>\n" + webhookRequestBuilder + "\n-------------------------------------\n");
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        Object obj = parser.parse(webhookRequest);
        JsonObject jsonObj = (JsonObject) obj;
        JsonObject queryResult = (JsonObject) jsonObj.get("queryResult");
        JsonObject intent = (JsonObject) queryResult.get("intent");
        String intentName = intent.get("displayName").getAsString();
        System.out.println("intentName >> " + intentName);

        Struct.Builder payload = Struct.newBuilder();
        Struct.Builder simpleResponse = Struct.newBuilder();
        Struct.Builder basicCard = Struct.newBuilder();
        ListValue suggestions = ListValue.newBuilder().build();
        Struct.Builder o2o = Struct.newBuilder();
        Struct.Builder carousel = Struct.newBuilder();

        Struct.Builder image = Struct.newBuilder();
        ListValue linkButtons = ListValue.newBuilder().build();

        String displayText = "";
        String tts = "";
        String description = "";
        /*
         * Intent별 Template 작성
         */
        if (intentName.equals("Default Welcome Intent")) {
            //Simple Response
            displayText = "안녕하세요.SKB 서비스 입니다. 무엇을 도와드릴까요?";
            tts = "안녕하세요.SKB 서비스 입니다. 무엇을 도와드릴까요?";
            simpleResponse = payloadTemplate.makeSimpleResponse(displayText,tts);
            //Basic Card
            image = payloadTemplate.makeImage("https://actions.o2o.kr/content/new/Skylife-ServiceCenter/Ko/aipersonKo.gif","웰컴 이미지");
            description = "원하시는 서비스를 추천 키워드를 참고하셔서 말씀해 주세요.";
            basicCard = payloadTemplate.makeBasicCard(null,null,description,image,null);

            //Suggestions
            suggestions = payloadTemplate.makeSuggestionChips("chip1","chip2");

            o2o.putFields("simpleResponse", Value.newBuilder().setStructValue(simpleResponse).build());
            o2o.putFields("basicCard", Value.newBuilder().setStructValue(basicCard).build());
            o2o.putFields("suggestions", Value.newBuilder().setListValue(suggestions).build());

            payload.putFields("o2o", Value.newBuilder().setStructValue(o2o).build());
        }
        else if (intentName.equals("Default Fallback Intent")) {
            //Simple Response
            displayText = "죄송합니다. 무슨 말씀을 하시는지 못알아 들었어요.추천 키워드를 참고하시고 다시 한번 말씀해 주시겠습니까?";
            tts = "죄송합니다. 무슨 말씀을 하시는지 못알아 들었어요.추천 키워드를 참고하시고 다시 한번 말씀해 주시겠습니까?";
            simpleResponse = payloadTemplate.makeSimpleResponse(displayText, tts);

            //Basic Card
            image = payloadTemplate.makeImage("https://actions.o2o.kr/content/new/Skylife-ServiceCenter/Ko/aipersonKo.gif", "Fallback 이미지");
            description = "죄송합니다. SK BroadBand 상담원에게 추천 키워드를 참고하시고 다시 말씀해 주세요.";
            basicCard = payloadTemplate.makeBasicCard(null, null, description, image, null);

            //Suggestions
            suggestions = payloadTemplate.makeSuggestionChips("화면이 이상해요", "인터넷 연결 방법");

            o2o.putFields("simpleResponse", Value.newBuilder().setStructValue(simpleResponse).build());
            o2o.putFields("basicCard", Value.newBuilder().setStructValue(basicCard).build());
            o2o.putFields("suggestions", Value.newBuilder().setListValue(suggestions).build());

            payload.putFields("o2o", Value.newBuilder().setStructValue(o2o).build());

        }

        else if (intentName.equals("Search Goods")) {
            //Simple Response
            displayText = "찾으시는 물품 리스트를 불러옵니다.";
            tts = "찾으시는 리스트를 불러옵니다.";
            simpleResponse = payloadTemplate.makeSimpleResponse(displayText, tts);

            //Basic Card
            image = payloadTemplate.makeImage("https://actions.o2o.kr/content/new/Skylife-ServiceCenter/Ko/aipersonKo.gif", "Fallback 이미지");
            description = "물품 리스트를 불러옵니다.";
            basicCard = payloadTemplate.makeBasicCard(null, null, description, image, null);

            //Suggestions
            suggestions = payloadTemplate.makeSuggestionChips("화면이 이상해요", "인터넷 연결 방법");

            o2o.putFields("simpleResponse", Value.newBuilder().setStructValue(simpleResponse).build());
            o2o.putFields("basicCard", Value.newBuilder().setStructValue(basicCard).build());
            o2o.putFields("suggestions", Value.newBuilder().setListValue(suggestions).build());

            payload.putFields("o2o", Value.newBuilder().setStructValue(o2o).build());
        }

        else if (intentName.equals("Prohibited Items")) {
            //Simple Response
            displayText = "판매금지 품목은 ooo,ooo, 등이 있습니다. 더 자세한 사항은 당근마켓 어플 또는 홈페이지에서 확인해 주세요.";
            tts = "판매금지 품목은 ooo,ooo, 등이 있습니다. 더 자세한 사항은 당근마켓 어플 또는 홈페이지에서 확인해 주세요.";
            simpleResponse = payloadTemplate.makeSimpleResponse(displayText, tts);

            //Basic Card
            image = payloadTemplate.makeImage("https://actions.o2o.kr/content/new/Skylife-ServiceCenter/Ko/aipersonKo.gif", "Fallback 이미지");
            description = "물품 리스트를 불러옵니다.";
            linkButtons = payloadTemplate.makeLinkButtons("<d>","<>");
            basicCard = payloadTemplate.makeBasicCard(null, null, description, image, linkButtons);

            //Suggestions
            suggestions = payloadTemplate.makeSuggestionChips("화면이 이상해요", "인터넷 연결 방법");

            o2o.putFields("simpleResponse", Value.newBuilder().setStructValue(simpleResponse).build());
            o2o.putFields("basicCard", Value.newBuilder().setStructValue(basicCard).build());
            o2o.putFields("suggestions", Value.newBuilder().setListValue(suggestions).build());

            payload.putFields("o2o", Value.newBuilder().setStructValue(o2o).build());
        }
        else if (intentName.equals("account questions")) {
            //Simple Response
            displayText = "계정문의";
            tts = "계정문의";
            simpleResponse = payloadTemplate.makeSimpleResponse(displayText, tts);

            //Basic Card
            image = payloadTemplate.makeImage("https://actions.o2o.kr/content/new/Skylife-ServiceCenter/Ko/aipersonKo.gif", "Fallback 이미지");
            description = "계정문의";
            linkButtons = payloadTemplate.makeLinkButtons("<d>","<>");
            basicCard = payloadTemplate.makeBasicCard(null, null, description, image, linkButtons);

            //Suggestions
            suggestions = payloadTemplate.makeSuggestionChips("화면이 이상해요", "인터넷 연결 방법");

            o2o.putFields("simpleResponse", Value.newBuilder().setStructValue(simpleResponse).build());
            o2o.putFields("basicCard", Value.newBuilder().setStructValue(basicCard).build());
            o2o.putFields("suggestions", Value.newBuilder().setListValue(suggestions).build());

            payload.putFields("o2o", Value.newBuilder().setStructValue(o2o).build());
        }
        else if (intentName.equals("neighborhood life")) {
            //Simple Response
            displayText = "동네생활";
            tts = "동네생활";
            simpleResponse = payloadTemplate.makeSimpleResponse(displayText, tts);

            //Basic Card
            image = payloadTemplate.makeImage("https://actions.o2o.kr/content/new/Skylife-ServiceCenter/Ko/aipersonKo.gif", "Fallback 이미지");
            description = "동네생활";
            linkButtons = payloadTemplate.makeLinkButtons("<d>","<>");
            basicCard = payloadTemplate.makeBasicCard(null, null, description, image, linkButtons);

            //Suggestions
            suggestions = payloadTemplate.makeSuggestionChips("화면이 이상해요", "인터넷 연결 방법");

            o2o.putFields("simpleResponse", Value.newBuilder().setStructValue(simpleResponse).build());
            o2o.putFields("basicCard", Value.newBuilder().setStructValue(basicCard).build());
            o2o.putFields("suggestions", Value.newBuilder().setListValue(suggestions).build());

            payload.putFields("o2o", Value.newBuilder().setStructValue(o2o).build());
        }

        else if (intentName.equals("FAQ")) {
            //Simple Response
            displayText = "FAQ";
            tts = "FAQ";
            simpleResponse = payloadTemplate.makeSimpleResponse(displayText, tts);

            //Basic Card
            image = payloadTemplate.makeImage("https://actions.o2o.kr/content/new/Skylife-ServiceCenter/Ko/aipersonKo.gif", "Fallback 이미지");
            description = "자주찾는 질문";
            linkButtons = payloadTemplate.makeLinkButtons("<d>","<>");
            basicCard = payloadTemplate.makeBasicCard(null, null, description, image, linkButtons);

            //Suggestions
            suggestions = payloadTemplate.makeSuggestionChips("화면이 이상해요", "인터넷 연결 방법");

            o2o.putFields("simpleResponse", Value.newBuilder().setStructValue(simpleResponse).build());
            o2o.putFields("basicCard", Value.newBuilder().setStructValue(basicCard).build());
            o2o.putFields("suggestions", Value.newBuilder().setListValue(suggestions).build());

            payload.putFields("o2o", Value.newBuilder().setStructValue(o2o).build());
        }
        try {
            jsonResponse = JsonFormat.printer().print(
                    WebhookResponse.newBuilder().setPayload(payload).build()
            );
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }



        System.out.println("jsonResponse >>");
        System.out.println(jsonResponse);

        return jsonResponse;
    }
}
