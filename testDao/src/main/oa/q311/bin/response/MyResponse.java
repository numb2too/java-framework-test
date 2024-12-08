package oa.q311.bin.response;

public class MyResponse {
    String code;
    String msg;
    Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    // Private constructor for Builder pattern
    private MyResponse() {}

    public static class Builder {
        private final MyResponse response;

        public Builder() {
            response = new MyResponse();
        }

        public Builder code(String code) {
            response.setCode(code);
            return this;
        }

        public Builder msg(String msg) {
            response.setMsg(msg);
            return this;
        }

        public Builder data(Object data) {
            response.setData(data);
            return this;
        }

        public MyResponse build() {
            return response;
        }
    }

    // Static method to get Builder instance
    public static Builder builder() {
        return new Builder();
    }
}
