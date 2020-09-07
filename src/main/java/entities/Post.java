package main.java.entities;

public class Post {
    final byte[] image;
    public Post( byte[] image){
        this.image=image;
    }

    public byte[] getImage() {
        return image;
    }

}
