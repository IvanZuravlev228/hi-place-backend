package hi.place.dto;

import lombok.Data;

@Data
public class Response {
    private int status;
    private String message;

    public Response(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
