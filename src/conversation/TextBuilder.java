package conversation;

import loaders.PhraseLoader;

import java.util.ArrayList;
import java.util.List;

public class TextBuilder {

    private List<Tag>[] tagLists;
    private Tag convTag;
    private String[] texts;

    private PhraseLoader phraseLoader = new PhraseLoader();
    private List<Phrase> phraseList;

    public TextBuilder(Tag convTag, List<Tag>[] tagLists){
        this.convTag = convTag;
        this.tagLists = tagLists;
        texts = new String[tagLists.length];
        phraseList = phraseLoader.getPhraseList();
        build();
    }

    private void build(){
        for(int i = 0; i < texts.length; i++){
            texts[i] = phrase();
        }
    }

    private String phrase(){
        List<Phrase> temp = new ArrayList<>();

        for (Phrase phrase: phraseList) {
            for (Tag tag: phrase.getTagList()) {
                if(tag.getName() == convTag.getName() && tag.getType() == convTag.getType()){
                    //noch nach mood and char tags überprüfen
                    temp.add(phrase);
                }
            }
        }

        return temp.get((int) (Math.random() * ( temp.size() - 0 ))).getText();
    }

    public String[] getTexts() {
        return texts;
    }
}
