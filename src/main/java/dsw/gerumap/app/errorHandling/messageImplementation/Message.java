package dsw.gerumap.app.errorHandling.messageImplementation;

import dsw.gerumap.app.errorHandling.EventType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
    private String message;
    private EventType eventType;

    public Message(String message, EventType eventType) {
        this.message = message;
        this.eventType = eventType;
    }
}
