package api.shopping.request;

import lombok.Builder;
import lombok.Data;

@Data
public class MemberRequest {
    private String name;
    private String email;
    private String password;

    @Builder
    public MemberRequest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
