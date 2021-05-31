package carrot.chatbot.proto.util;

import com.google.protobuf.ListValue;
import com.google.protobuf.Struct;
import com.google.protobuf.Value;

import java.util.ArrayList;

public class PayloadTemplate {
    Struct.Builder simpleResponse = Struct.newBuilder();
    Struct.Builder cardList = Struct.newBuilder();
    Struct.Builder carousel = Struct.newBuilder();

    ListValue linkButtons = ListValue.newBuilder().build();
    Value cardListTitle = Value.newBuilder().build();
    Value itemTitle = Value.newBuilder().build();
    Value itemDescription = Value.newBuilder().build();
    Value itemlinkUrl = Value.newBuilder().build();
    ListValue items = ListValue.newBuilder().build();

    ListValue suggestions = ListValue.newBuilder().build();

    Value suggestion1 = Value.newBuilder().build();
    Value suggestion2 = Value.newBuilder().build();
    Value suggestion3 = Value.newBuilder().build();
    Value suggestion4 = Value.newBuilder().build();
    Value suggestion5 = Value.newBuilder().build();
    Value suggestion6 = Value.newBuilder().build();
    Value suggestion7 = Value.newBuilder().build();
    Value suggestion8 = Value.newBuilder().build();

    //Simple Response
    public Struct.Builder makeDisplaytext(String displayText) {  //Simple Response only DisplayText
        this.simpleResponse.putFields("displayText", Value.newBuilder().setStringValue(displayText).build()).build();

        return this.simpleResponse;
    }

    //Simple Response
    public Struct.Builder makeTextToSpeech(String textToSpeech) {  //Simple Response only DisplayText
        this.simpleResponse.putFields("textToSpeech", Value.newBuilder().setStringValue(textToSpeech).build()).build();

        return this.simpleResponse;
    }

    public Struct.Builder makeSimpleResponse(String displayText,String textToSpeech) {  //Simple Response with DisplayText, TTS
        this.simpleResponse.putFields("displayText", Value.newBuilder().setStringValue(displayText).build()).build();
        this.simpleResponse.putFields("textToSpeech", Value.newBuilder().setStringValue(textToSpeech).build()).build();

        return this.simpleResponse;
    }

    //Basic Card
    public Struct.Builder makeImage(String url,String altImg) { // 'image url, alt' for basicCard, CardList, Carousel
        Struct.Builder basicCardImage = Struct.newBuilder();
        if(url!=null&&url.length()>0) {
            basicCardImage.putFields("url", Value.newBuilder().setStringValue(url).build());
        }else{
            System.out.println("url is null");
        }
        if(altImg!=null&&altImg.length()>0) {
            basicCardImage.putFields("altImg", Value.newBuilder().setStringValue(altImg).build());
        }else {
            System.out.println("altImg is null");
        }
        return basicCardImage;
    }

    public ListValue makeLinkButtons(String markUp) { //Link Buttons for BasicCard, Carousel
        this.linkButtons= ListValue.newBuilder().addValues(Value.newBuilder().setStringValue(markUp).build()).build();
        return this.linkButtons;
    }

    public ListValue makeLinkButtons(String markUp, String markUp2) { //Link Buttons for BasicCard, Carousel
        this.linkButtons= ListValue.newBuilder().addValues(Value.newBuilder().setStringValue(markUp).build()).addValues(Value.newBuilder().setStringValue(markUp2).build()).build();
        return this.linkButtons;
    }

    public ListValue makeLinkButtons(String markUp, String markUp2, String markUp3) { //Link Buttons for BasicCard, Carousel
        this.linkButtons= ListValue.newBuilder().addValues(Value.newBuilder().setStringValue(markUp).build()).addValues(Value.newBuilder().setStringValue(markUp2).build()).addValues(Value.newBuilder().setStringValue(markUp3).build()).build();
        return this.linkButtons;
    }
    public ListValue makeLinkButtons(String markUp, String markUp2, String markUp3, String markUp4) { //Link Buttons for BasicCard, Carousel
        this.linkButtons= ListValue.newBuilder().addValues(Value.newBuilder().setStringValue(markUp).build()).addValues(Value.newBuilder().setStringValue(markUp2).build()).addValues(Value.newBuilder().setStringValue(markUp3).build()).addValues(Value.newBuilder().setStringValue(markUp4).build()).build();
        return this.linkButtons;
    }
    public ListValue makeLinkButtons(String markUp, String markUp2, String markUp3, String markUp4, String markUp5) { //Link Buttons for BasicCard, Carousel
        this.linkButtons= ListValue.newBuilder().addValues(Value.newBuilder().setStringValue(markUp).build()).addValues(Value.newBuilder().setStringValue(markUp2).build()).addValues(Value.newBuilder().setStringValue(markUp3).build()).addValues(Value.newBuilder().setStringValue(markUp4).build()).addValues(Value.newBuilder().setStringValue(markUp5).build()).build();
        return this.linkButtons;
    }

    public Struct.Builder makeBasicCard(String title,String subtitle,String description,Struct.Builder image, ListValue linkButtons) {
        Struct.Builder basicCard = Struct.newBuilder();
        ListValue types = ListValue.newBuilder().build();
        ArrayList<String> typesArray = new ArrayList<>();

        if(title!=null&&title.length()>0) {
            basicCard.putFields("title", Value.newBuilder().setStringValue(title).build()).build();
            typesArray.add("title");
        }else{
            System.out.println("basic card title is null");
        }

        if(subtitle!=null&&subtitle.length()>0) {
            basicCard.putFields("subtitle", Value.newBuilder().setStringValue(subtitle).build()).build();
            typesArray.add("subtitle");
        }else{
            System.out.println("basic card subtitle is null");
        }

        if(description!=null&&description.length()>0) {
            basicCard.putFields("description", Value.newBuilder().setStringValue(description).build()).build();
            typesArray.add("description");
        }else{
            System.out.println("basic card description is null");
        }

        if(image!=null&&image.getFieldsCount()>0) {
            basicCard.putFields("image", Value.newBuilder().setStructValue(image).build());
            typesArray.add("image");
        }else{
            System.out.println("basic card image is null");
        }
        if(linkButtons!=null&&linkButtons.getValuesCount()>0) {
            basicCard.putFields("linkButtons", Value.newBuilder().setListValue(linkButtons).build()).build();
            typesArray.add("linkButtons");
        }else{
            System.out.println("basic card linkButton is null");
        }

        System.out.println("Types Array >> "+typesArray);
        if(typesArray.size()==1){
            types = ListValue.newBuilder().addValues(Value.newBuilder().setStringValue(typesArray.get(0))).build();
        } else if(typesArray.size()==2){
            types = ListValue.newBuilder().addValues(Value.newBuilder().setStringValue(typesArray.get(0))).addValues(Value.newBuilder().setStringValue(typesArray.get(1))).build();
        } else if (typesArray.size()==3) {
            types = ListValue.newBuilder().addValues(Value.newBuilder().setStringValue(typesArray.get(0))).addValues(Value.newBuilder().setStringValue(typesArray.get(1))).addValues(Value.newBuilder().setStringValue(typesArray.get(2))).build();
        } else if (typesArray.size()==4) {
            types = ListValue.newBuilder().addValues(Value.newBuilder().setStringValue(typesArray.get(0))).addValues(Value.newBuilder().setStringValue(typesArray.get(1))).addValues(Value.newBuilder().setStringValue(typesArray.get(2))).addValues(Value.newBuilder().setStringValue(typesArray.get(3))).build();
        } else if (typesArray.size()==5) {
            types = ListValue.newBuilder().addValues(Value.newBuilder().setStringValue(typesArray.get(0))).addValues(Value.newBuilder().setStringValue(typesArray.get(1))).addValues(Value.newBuilder().setStringValue(typesArray.get(2))).addValues(Value.newBuilder().setStringValue(typesArray.get(3))).addValues(Value.newBuilder().setStringValue(typesArray.get(4))).build();
        }else{
            System.out.println("types array is empty");
        }

        basicCard.putFields("type", Value.newBuilder().setListValue(types).build()).build();
        return basicCard;
    }

    //Card List
    public Struct.Builder makeListItem(String title,String description, Struct.Builder image, String linkUrl){ //list item 생성
        Struct.Builder listItem = Struct.newBuilder();
        itemTitle = Value.newBuilder().setStringValue(title).build();
        itemDescription = Value.newBuilder().setStringValue(description).build();
        itemlinkUrl = Value.newBuilder().setStringValue(linkUrl).build();

        listItem.putFields("title", itemTitle).build();
        listItem.putFields("description", itemDescription).build();
        listItem.putFields("image", Value.newBuilder().setStructValue(image).build());
        listItem.putFields("linkUrl", itemlinkUrl).build();

        return listItem;
    }

    public Struct.Builder makeCardList(String listTitle, Struct.Builder item1){ //Card List 생성
        this.items = ListValue.newBuilder().addValues(Value.newBuilder().setStructValue(item1)).build();
        this.cardListTitle = Value.newBuilder().setStringValue(listTitle).build();

        this.cardList.putFields("listTitle", this.cardListTitle);
        this.cardList.putFields("items", Value.newBuilder().setListValue(this.items).build());

        return this.cardList;
    }

    public Struct.Builder makeCardList(String listTitle, Struct.Builder item1, Struct.Builder item2){ //Card List 생성
        this.items = ListValue.newBuilder().addValues(Value.newBuilder().setStructValue(item1)).addValues(Value.newBuilder().setStructValue(item2)).build();
        this.cardListTitle = Value.newBuilder().setStringValue(listTitle).build();

        this.cardList.putFields("listTitle", this.cardListTitle);
        this.cardList.putFields("items", Value.newBuilder().setListValue(this.items).build());
        return this.cardList;
    }

    public Struct.Builder makeCardList(String listTitle, Struct.Builder item1, Struct.Builder item2, Struct.Builder item3){ //Card List 생성
        this.items = ListValue.newBuilder().addValues(Value.newBuilder().setStructValue(item1)).addValues(Value.newBuilder().setStructValue(item2)).addValues(Value.newBuilder().setStructValue(item3)).build();
        this.cardListTitle = Value.newBuilder().setStringValue(listTitle).build();

        this.cardList.putFields("listTitle", this.cardListTitle);
        this.cardList.putFields("items", Value.newBuilder().setListValue(this.items).build());
        return this.cardList;
    }

    public Struct.Builder makeCardList(String listTitle, Struct.Builder item1, Struct.Builder item2, Struct.Builder item3, Struct.Builder item4){ //Card List 생성
        this.items = ListValue.newBuilder().addValues(Value.newBuilder().setStructValue(item1)).addValues(Value.newBuilder().setStructValue(item2)).addValues(Value.newBuilder().setStructValue(item3)).addValues(Value.newBuilder().setStructValue(item4)).build();
        this.cardListTitle = Value.newBuilder().setStringValue(listTitle).build();

        this.cardList.putFields("listTitle", this.cardListTitle);
        this.cardList.putFields("items", Value.newBuilder().setListValue(this.items).build());
        return this.cardList;
    }

    public Struct.Builder makeCardList(String listTitle, Struct.Builder item1, Struct.Builder item2, Struct.Builder item3, Struct.Builder item4, Struct.Builder item5){ //Card List 생성
        this.items = ListValue.newBuilder().addValues(Value.newBuilder().setStructValue(item1)).addValues(Value.newBuilder().setStructValue(item2)).addValues(Value.newBuilder().setStructValue(item3)).addValues(Value.newBuilder().setStructValue(item4)).addValues(Value.newBuilder().setStructValue(item5)).build();
        this.cardListTitle = Value.newBuilder().setStringValue(listTitle).build();

        this.cardList.putFields("listTitle", this.cardListTitle);
        this.cardList.putFields("items", Value.newBuilder().setListValue(this.items).build());
        return this.cardList;
    }

    //Carousel
    public Struct.Builder makeCarousel(Struct.Builder item1, Struct.Builder item2) {
        this.items = ListValue.newBuilder().addValues(Value.newBuilder().setStructValue(item1)).addValues(Value.newBuilder().setStructValue(item2)).build();
        this.carousel.putFields("items", Value.newBuilder().setListValue(items).build());
        return this.carousel;
    }
    public Struct.Builder makeCarousel(Struct.Builder item1, Struct.Builder item2, Struct.Builder item3) {
        this.items = ListValue.newBuilder().addValues(Value.newBuilder().setStructValue(item1)).addValues(Value.newBuilder().setStructValue(item2)).addValues(Value.newBuilder().setStructValue(item3)).build();
        this.carousel.putFields("items", Value.newBuilder().setListValue(items).build());
        return this.carousel;
    }
    public Struct.Builder makeCarousel(Struct.Builder item1, Struct.Builder item2, Struct.Builder item3, Struct.Builder item4) {
        this.items = ListValue.newBuilder().addValues(Value.newBuilder().setStructValue(item1)).addValues(Value.newBuilder().setStructValue(item2)).addValues(Value.newBuilder().setStructValue(item3)).addValues(Value.newBuilder().setStructValue(item4)).build();
        this.carousel.putFields("items", Value.newBuilder().setListValue(items).build());
        return this.carousel;
    }
    public Struct.Builder makeCarousel(Struct.Builder item1, Struct.Builder item2, Struct.Builder item3, Struct.Builder item4, Struct.Builder item5) {
        this.items = ListValue.newBuilder().addValues(Value.newBuilder().setStructValue(item1)).addValues(Value.newBuilder().setStructValue(item2)).addValues(Value.newBuilder().setStructValue(item3)).addValues(Value.newBuilder().setStructValue(item4)).addValues(Value.newBuilder().setStructValue(item5)).build();
        this.carousel.putFields("items", Value.newBuilder().setListValue(items).build());
        return this.carousel;
    }

    //Suggestion Chips
    public ListValue makeSuggestionChips(String suggestion1) {
        this.suggestion1 = Value.newBuilder().setStringValue(suggestion1).build();

        this.suggestions = ListValue.newBuilder().addValues(this.suggestion1).build();
        return this.suggestions;
    }

    public ListValue makeSuggestionChips(String suggestion1, String suggestion2) {
        this.suggestion1 = Value.newBuilder().setStringValue(suggestion1).build();
        this.suggestion2 = Value.newBuilder().setStringValue(suggestion2).build();
        this.suggestions = ListValue.newBuilder().addValues(this.suggestion1).addValues(this.suggestion2).build();
        return this.suggestions;
    }
    public ListValue makeSuggestionChips(String suggestion1, String suggestion2, String suggestion3) {
        this.suggestion1 = Value.newBuilder().setStringValue(suggestion1).build();
        this.suggestion2 = Value.newBuilder().setStringValue(suggestion2).build();
        this.suggestion3 = Value.newBuilder().setStringValue(suggestion3).build();
        this.suggestions = ListValue.newBuilder().addValues(this.suggestion1).addValues(this.suggestion2).addValues(this.suggestion3).build();
        return this.suggestions;
    }
    public ListValue makeSuggestionChips(String suggestion1, String suggestion2, String suggestion3, String suggestion4) {
        this.suggestion1 = Value.newBuilder().setStringValue(suggestion1).build();
        this.suggestion2 = Value.newBuilder().setStringValue(suggestion2).build();
        this.suggestion3 = Value.newBuilder().setStringValue(suggestion3).build();
        this.suggestion4 = Value.newBuilder().setStringValue(suggestion4).build();
        this.suggestions = ListValue.newBuilder().addValues(this.suggestion1).addValues(this.suggestion2).addValues(this.suggestion3).addValues(this.suggestion4).build();
        return this.suggestions;
    }
    public ListValue makeSuggestionChips(String suggestion1, String suggestion2, String suggestion3, String suggestion4, String suggestion5) {
        this.suggestion1 = Value.newBuilder().setStringValue(suggestion1).build();
        this.suggestion2 = Value.newBuilder().setStringValue(suggestion2).build();
        this.suggestion3 = Value.newBuilder().setStringValue(suggestion3).build();
        this.suggestion4 = Value.newBuilder().setStringValue(suggestion4).build();
        this.suggestion5 = Value.newBuilder().setStringValue(suggestion5).build();
        this.suggestions = ListValue.newBuilder().addValues(this.suggestion1).addValues(this.suggestion2).addValues(this.suggestion3).addValues(this.suggestion4).addValues(this.suggestion5).build();
        return this.suggestions;
    }
    public ListValue makeSuggestionChips(String suggestion1, String suggestion2, String suggestion3, String suggestion4, String suggestion5, String suggestion6) {
        this.suggestion1 = Value.newBuilder().setStringValue(suggestion1).build();
        this.suggestion2 = Value.newBuilder().setStringValue(suggestion2).build();
        this.suggestion3 = Value.newBuilder().setStringValue(suggestion3).build();
        this.suggestion4 = Value.newBuilder().setStringValue(suggestion4).build();
        this.suggestion5 = Value.newBuilder().setStringValue(suggestion5).build();
        this.suggestion6 = Value.newBuilder().setStringValue(suggestion6).build();
        this.suggestions = ListValue.newBuilder().addValues(this.suggestion1).addValues(this.suggestion2).addValues(this.suggestion3).addValues(this.suggestion4).addValues(this.suggestion5).addValues(this.suggestion6).build();
        return this.suggestions;
    }
    public ListValue makeSuggestionChips(String suggestion1, String suggestion2, String suggestion3, String suggestion4, String suggestion5, String suggestion6, String suggestion7) {
        this.suggestion1 = Value.newBuilder().setStringValue(suggestion1).build();
        this.suggestion2 = Value.newBuilder().setStringValue(suggestion2).build();
        this.suggestion3 = Value.newBuilder().setStringValue(suggestion3).build();
        this.suggestion4 = Value.newBuilder().setStringValue(suggestion4).build();
        this.suggestion5 = Value.newBuilder().setStringValue(suggestion5).build();
        this.suggestion6 = Value.newBuilder().setStringValue(suggestion6).build();
        this.suggestion7 = Value.newBuilder().setStringValue(suggestion7).build();
        this.suggestions = ListValue.newBuilder().addValues(this.suggestion1).addValues(this.suggestion2).addValues(this.suggestion3).addValues(this.suggestion4).addValues(this.suggestion5).addValues(this.suggestion6).addValues(this.suggestion7).build();
        return this.suggestions;
    }
    public ListValue makeSuggestionChips(String suggestion1, String suggestion2, String suggestion3, String suggestion4, String suggestion5, String suggestion6, String suggestion7, String suggestion8) {
        this.suggestion1 = Value.newBuilder().setStringValue(suggestion1).build();
        this.suggestion2 = Value.newBuilder().setStringValue(suggestion2).build();
        this.suggestion3 = Value.newBuilder().setStringValue(suggestion3).build();
        this.suggestion4 = Value.newBuilder().setStringValue(suggestion4).build();
        this.suggestion5 = Value.newBuilder().setStringValue(suggestion5).build();
        this.suggestion6 = Value.newBuilder().setStringValue(suggestion6).build();
        this.suggestion7 = Value.newBuilder().setStringValue(suggestion7).build();
        this.suggestion8 = Value.newBuilder().setStringValue(suggestion8).build();
        this.suggestions = ListValue.newBuilder().addValues(this.suggestion1).addValues(this.suggestion2).addValues(this.suggestion3).addValues(this.suggestion4).addValues(this.suggestion5).addValues(this.suggestion6).addValues(this.suggestion7).addValues(this.suggestion8).build();
        return this.suggestions;
    }
}
