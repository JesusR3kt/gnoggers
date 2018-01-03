package loaders;

import conversation.Phrase;
import conversation.Tag;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PhraseLoader {

    private List<Phrase> phraseList;

    public PhraseLoader(){
        load();
    }

    private void load(){
        try {
            FileReader fr = new FileReader(new File("/res/phrases/phra.ses"));
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] args = line.split("|");
                for (int i = 0;  i < args.length; i++) {
                    if(i%2==0){
                        Phrase phrase = new Phrase(args[i]);
                        phraseList.add(phrase);
                    }
                    else {
                        List<Tag> tagList = new ArrayList<>();
                        String[] tags = args[i].split(";");
                        for (int j = 0; i < tags.length; i++){
                            String[] tagtype = tags[j].split(",");
                            tagList.add(new Tag(tagtype[0],tagtype[1]));
                        }
                        phraseList.get(i).addTags(tagList);
                    }
                }

            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Phrase> getPhraseList() {
        return phraseList;
    }


}
