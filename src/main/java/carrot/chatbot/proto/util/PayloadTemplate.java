package carrot.chatbot.proto.util;

import com.google.protobuf.ListValue;
import com.google.protobuf.Struct;
import com.google.protobuf.Value;

import java.util.ArrayList;

public class PayloadTemplate {
    Struct.Builder simpleResponse = Struct.newBuilder();
    Struct.Builder cardList = Struct.newBuilder();
    Struct.Builder carousel = Struct.newBuilder();
    Struct.Builder table = Struct.newBuilder();

    ListValue.Builder linkButtons = ListValue.newBuilder();
    Value cardListTitle = Value.newBuilder().build();
    Value itemTitle = Value.newBuilder().build();
    Value itemDescription = Value.newBuilder().build();
    Value itemlinkUrl = Value.newBuilder().build();
    ListValue.Builder items = ListValue.newBuilder();

    ListValue.Builder columns = ListValue.newBuilder();
    ListValue.Builder rows = ListValue.newBuilder();

    ListValue.Builder suggestions = ListValue.newBuilder();

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

    public Struct.Builder makeSimpleResponse(String displayText, String textToSpeech) {  //Simple Response with DisplayText, TTS
        if(!displayText.equals("")) {
            this.simpleResponse.putFields("displayText", Value.newBuilder().setStringValue(displayText).build()).build();
        }
        if(!textToSpeech.equals("")) {
            this.simpleResponse.putFields("textToSpeech", Value.newBuilder().setStringValue(textToSpeech).build()).build();
        }
        return this.simpleResponse;
    }

    public Struct.Builder makeBasicCard(String title, String subtitle, String description, Struct.Builder image, ListValue.Builder linkButtons) {
        Struct.Builder basicCard = Struct.newBuilder();
        ListValue.Builder types = ListValue.newBuilder();
        ArrayList<String> typesArray = new ArrayList<>();

        if (title != null && title.length() > 0) {
            basicCard.putFields("title", Value.newBuilder().setStringValue(title).build()).build();
            typesArray.add("title");
        } else {
            System.out.println("basic card title is null");
        }

        if (subtitle != null && subtitle.length() > 0) {
            basicCard.putFields("subtitle", Value.newBuilder().setStringValue(subtitle).build()).build();
            typesArray.add("subtitle");
        } else {
            System.out.println("basic card subtitle is null");
        }

        if (description != null && description.length() > 0) {
            basicCard.putFields("description", Value.newBuilder().setStringValue(description).build()).build();
            typesArray.add("description");
        } else {
            System.out.println("basic card description is null");
        }

        if (image != null && image.getFieldsCount() > 0) {
            basicCard.putFields("image", Value.newBuilder().setStructValue(image).build());
            typesArray.add("image");
        } else {
            System.out.println("basic card image is null");
        }
        if (linkButtons != null && linkButtons.getValuesCount() > 0) {
            basicCard.putFields("linkButtons", Value.newBuilder().setListValue(linkButtons).build()).build();
            typesArray.add("linkButtons");
        } else {
            System.out.println("basic card linkButton is null");
        }

        System.out.println("Types Array >> " + typesArray);
        if (typesArray.size() >= 1) {
            for (String type : typesArray) {
                types.addValues(Value.newBuilder().setStringValue(type).build()).build();
            }
        } else {
            System.out.println("types array is empty");
        }

        basicCard.putFields("type", Value.newBuilder().setListValue(types).build()).build();
        return basicCard;
    }

    public ListValue.Builder makeLinkButtons(String... markUps) { //Link Buttons for BasicCard, Carousel
        for (String markUp : markUps) {
            this.linkButtons.addValues(Value.newBuilder().setStringValue(markUp).build()).build();
        }

        return this.linkButtons;
    }

    public Struct.Builder makeImage(String url, String altImg) { // 'image url, alt' for basicCard, CardList, Carousel
        Struct.Builder basicCardImage = Struct.newBuilder();
        if (url != null && url.length() > 0) {
            basicCardImage.putFields("url", Value.newBuilder().setStringValue(url).build());
        } else {
            System.out.println("url is null");
        }
        if (altImg != null && altImg.length() > 0) {
            basicCardImage.putFields("altImg", Value.newBuilder().setStringValue(altImg).build());
        } else {
            System.out.println("altImg is null");
        }
        return basicCardImage;
    }

    //Card List
    public Struct.Builder makeListItem(String title, String description, Struct.Builder image, String linkUrl) { //list item 생성
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

    public Struct.Builder makeCardList(String listTitle, Struct.Builder... items) {

        this.cardListTitle = Value.newBuilder().setStringValue(listTitle).build();

        for (Struct.Builder item : items) {
            this.items.addValues(Value.newBuilder().setStructValue(item).build()).build();
        }

        this.cardList.putFields("listTitle", this.cardListTitle);
        this.cardList.putFields("items", Value.newBuilder().setListValue(this.items).build());
        return this.cardList;
    }

    //Carousel
    public Struct.Builder makeCarousel(Struct.Builder... items) {

        for (Struct.Builder item : items) {
            this.items.addValues(Value.newBuilder().setStructValue(item).build()).build();
        }
        this.carousel.putFields("items", Value.newBuilder().setListValue(this.items).build());

        return this.carousel;
    }

    //Table
    public Struct.Builder makeTable(String title, String subTitle, ListValue.Builder columns, ListValue.Builder rows){
        table.putFields("title",Value.newBuilder().setStringValue(title).build());
        table.putFields("subTitle",Value.newBuilder().setStringValue(subTitle).build());
        table.putFields("columns",Value.newBuilder().setListValue(columns).build());
        table.putFields("rows",Value.newBuilder().setListValue(rows).build());
        return this.table;
    }

    public ListValue.Builder makeColumns(String[]... columns){
        for(String[] column:columns){
            ListValue.Builder elemList = ListValue.newBuilder();
            for(String columnElem:column){
                elemList.addValues(Value.newBuilder().setStringValue(columnElem).build()).build();
            }
            this.columns.addValues(Value.newBuilder().setListValue(elemList).build()).build();
        }
        return this.columns;
    }

    public ListValue.Builder makeRows(String[]... rows){
        for(String[] row:rows){
            ListValue.Builder elemList = ListValue.newBuilder();
            for(String rowElem:row){
                elemList.addValues(Value.newBuilder().setStringValue(rowElem).build()).build();
            }
            this.rows.addValues(Value.newBuilder().setListValue(elemList).build()).build();
        }
        return this.rows;
    }

    //Suggestion Chips
    public ListValue.Builder makeSuggestionChipsArr(String... suggestions) {
        for (String suggestion : suggestions) {
            this.suggestions.addValues(Value.newBuilder().setStringValue(suggestion).build()).build();
        }
        return this.suggestions;
    }
}