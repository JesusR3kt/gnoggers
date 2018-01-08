package entity;

import conversation.Tag;

import java.util.ArrayList;
import java.util.List;

public class Gnogger {

    private double size;
    private String name;
    private int moveSpeed;
    private enum charakteristics{
        funny
    }
    private List<charakteristics> charList = new ArrayList<>();
    private enum moods{
        angry
    }
    private List<moods> moodList = new ArrayList<>();
    //color

    public List getTagList(){
        List<Tag> tagList = new ArrayList<>();

        for (int i = 0; i < charList.size(); i++){
            tagList.add(new Tag("char",charList.get(i).toString()));
        }

        for (int i = 0; i < moodList.size(); i++){
            tagList.add(new Tag("mood",moodList.get(i).toString()));
        }

        return tagList;
    }


}
