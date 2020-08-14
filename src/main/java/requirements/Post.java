package main.java.requirements;

public class Post {
    byte[] image;
    String text;
    public Post(String text, byte[] image){
        this.text=text;
        this.image=image;
    }
    public Post(){}
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
