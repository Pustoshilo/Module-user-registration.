package session;

import java.io.Serializable;

public class Message implements Serializable {
    public static final long serialVersionUID = 1L;

    private String type;
    private String text;
    private String nextLocation;


    public Message(String type, String text, String nextLocation) {
        this.type = type;
        this.text = text;
        this.nextLocation = nextLocation;
    }


    public Message() {
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getNextLocation() {
        return this.nextLocation;
    }

    public void setNextLocation(String nextLocation) {
        this.nextLocation = nextLocation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
