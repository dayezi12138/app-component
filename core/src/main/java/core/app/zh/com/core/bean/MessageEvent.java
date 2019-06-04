package core.app.zh.com.core.bean;

/**
 * @auther create by Administrator
 * DATE:2019/3/29 0029 13
 */
public class MessageEvent {
    private int code;
    private String msg;

    public MessageEvent(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
