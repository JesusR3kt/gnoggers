package conversation;

public class Tag {
    String name;
    String type;

    public Tag(String type, String name){
        this.type = type;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
