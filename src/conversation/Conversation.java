package conversation;

import entity.Gnogger;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Conversation {

    private List<Gnogger> parList = new ArrayList<>();
    private enum type{
        Dialog
    };

    public Conversation(Gnogger... gnogger) {
        for (int i = 0; i < gnogger.length; i++){
            parList.add(gnogger[i]);
        }
        //conv typ

    }



}
