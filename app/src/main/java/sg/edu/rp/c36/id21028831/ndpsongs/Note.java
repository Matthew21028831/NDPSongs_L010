package sg.edu.rp.c36.id21028831.ndpsongs;

import java.io.Serializable;

public class Note implements Serializable {
    int id;
    String song;
    String singers;
    String year;
    String stars;


    public Note( int id, String song, String singers,String year,String stars ) {
        this.id = id;
        this.song = song;
        this.singers = singers;
        this.year = year;
        this.stars = stars;
    }

    public int getId() {  return id;  }

    public String getSong() { return song; }

    public String getSingers() { return singers; }

    public String getYear() { return year; }

    public String getStars() { return stars; }

    public void setTitle(String song){
        this.song = song;
    }

    public void setSinger(String Singers){
        this.singers = Singers;
    }

    public void setYear(String Year){
        this.year = year;
    }

    public void setStars(String Stars){
        this.stars = stars;
    }
}
