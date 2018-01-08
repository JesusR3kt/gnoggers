package conversation;

import java.util.List;

public class Phrase {
    private String text;
    private List<Tag> tagList;

    public Phrase(String text){
        this.text = text;
    }

    public void addTags(List<Tag> tagList){
        this.tagList = tagList;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public String getText() {
        return text;
    }
}
