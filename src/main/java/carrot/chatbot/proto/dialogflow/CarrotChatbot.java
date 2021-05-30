package carrot.chatbot.proto.dialogflow;

import carrot.chatbot.proto.util.PayloadTemplate;

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
            simpleResponse = o2oTemplate.makeSimpleResponse(displayText,tts);
            //Basic Card
            image = o2oTemplate.makeImage("https://actions.o2o.kr/content/new/Skylife-ServiceCenter/Ko/aipersonKo.gif","웰컴 이미지");
            description = "원하시는 서비스를 추천 키워드를 참고하셔서 말씀해 주세요.";
            basicCard = o2oTemplate.makeBasicCard(null,null,description,image,null);

            //Suggestions
            suggestions = o2oTemplate.makeSuggestionChips("chip1","chip2");

            o2o.putFields("simpleResponse", Value.newBuilder().setStructValue(simpleResponse).build());
            o2o.putFields("basicCard", Value.newBuilder().setStructValue(basicCard).build());
            o2o.putFields("suggestions", Value.newBuilder().setListValue(suggestions).build());

            payload.putFields("o2o", Value.newBuilder().setStructValue(o2o).build());
        }
        else if (intentName.equals("Default Fallback Intent")) {
            //Simple Response
            displayText = "죄송합니다. 무슨 말씀을 하시는지 못알아 들었어요.추천 키워드를 참고하시고 다시 한번 말씀해 주시겠습니까?";
            tts = "죄송합니다. 무슨 말씀을 하시는지 못알아 들었어요.추천 키워드를 참고하시고 다시 한번 말씀해 주시겠습니까?";
            simpleResponse = o2oTemplate.makeSimpleResponse(displayText, tts);

            //Basic Card
            image = o2oTemplate.makeImage("https://actions.o2o.kr/content/new/Skylife-ServiceCenter/Ko/aipersonKo.gif", "Fallback 이미지");
            description = "죄송합니다. SK BroadBand 상담원에게 추천 키워드를 참고하시고 다시 말씀해 주세요.";
            basicCard = o2oTemplate.makeBasicCard(null, null, description, image, null);

            //Suggestions
            suggestions = o2oTemplate.makeSuggestionChips("화면이 이상해요", "인터넷 연결 방법");

            o2o.putFields("simpleResponse", Value.newBuilder().setStructValue(simpleResponse).build());
            o2o.putFields("basicCard", Value.newBuilder().setStructValue(basicCard).build());
            o2o.putFields("suggestions", Value.newBuilder().setListValue(suggestions).build());

            payload.putFields("o2o", Value.newBuilder().setStructValue(o2o).build());

        }

        else if (intentName.equals("find charge")) {
            //Simple Response
            displayText = "찾으시는 물품에 대해 평균 가격을 알려드립니다";
            tts = "찾으시는 물품에 대해 평균 가격을 알려드립니다";
            simpleResponse = o2oTemplate.makeSimpleResponse(displayText, tts);

            //Basic Card
            image = o2oTemplate.makeImage("https://actions.o2o.kr/content/new/Skylife-ServiceCenter/Ko/aipersonKo.gif", "Fallback 이미지");
            description = "찾으시는 물품에 대해 평균 가격을 알려드립니다";
            basicCard = o2oTemplate.makeBasicCard(null, null, description, image, null);

            //Suggestions
            suggestions = o2oTemplate.makeSuggestionChips("화면이 이상해요", "인터넷 연결 방법");

            o2o.putFields("simpleResponse", Value.newBuilder().setStructValue(simpleResponse).build());
            o2o.putFields("basicCard", Value.newBuilder().setStructValue(basicCard).build());
            o2o.putFields("suggestions", Value.newBuilder().setListValue(suggestions).build());

            payload.putFields("o2o", Value.newBuilder().setStructValue(o2o).build());
        }

        else if (intentName.equals("search goods")) {
            //Simple Response
            displayText = "물품 리스트를 불러옵니다.";
            tts = "물품 리스트를 불러옵니다.";
            simpleResponse = o2oTemplate.makeSimpleResponse(displayText, tts);

            //Basic Card
            image = o2oTemplate.makeImage("https://actions.o2o.kr/content/new/Skylife-ServiceCenter/Ko/aipersonKo.gif", "Fallback 이미지");
            description = "물품 리스트를 불러옵니다.";
            basicCard = o2oTemplate.makeBasicCard(null, null, description, image, null);

            //Suggestions
            suggestions = o2oTemplate.makeSuggestionChips("화면이 이상해요", "인터넷 연결 방법");

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
