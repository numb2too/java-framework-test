package oa.q212.bin.element.btn;

import oa.q212.bin.model.request.ToAddPageRequest;
import oa.q212.bin.model.response.ToAddPageResponse;

public class ToAddPageBtn {

    public ToAddPageResponse click(ToAddPageRequest request) {
        String value = request.getLoginUserId();

        ToAddPageResponse response = new ToAddPageResponse();
        return response;
    }

}
