package conversation;

import entity.Gnogger;

import java.util.ArrayList;
import java.util.List;

public class Dialog {

    private List<Gnogger> parList;
    private int par = 2;
    private TextBuilder textBuilder;
    private Tag convTag = new Tag("convTag", "dialog");
    private List<Tag>[] tagLists = new List[par];
    private List<String[]> textList = new ArrayList<>();

    public Dialog(List<Gnogger> parList) {
        this.parList = parList;
        tagLists[0] = parList.get(0).getTagList();
        tagLists[1] = parList.get(1).getTagList();
        conv();
    }

    private void conv(){
        //greet();
        //talk();
        //end();
    }

    private void greet(){
        textBuilder = new TextBuilder(convTag, tagLists);
        textList.add(textBuilder.getTexts());
    }

    private void talk(){

    }

    private void end(){

    }

}
