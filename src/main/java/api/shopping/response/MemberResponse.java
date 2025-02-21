package api.shopping.response;

import api.shopping.domain.Member;
import lombok.Getter;

@Getter
public class MemberResponse {

    private final Long id;
    private final String name;

    public MemberResponse(Member user) {
        this.id = user.getId();
        this.name = user.getName();
    }
}
