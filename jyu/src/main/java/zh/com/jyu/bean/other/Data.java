package zh.com.jyu.bean.other;

/**
 * author : dayezi
 * data :2019/4/15
 * description:
 */
public class Data<T> {
    private boolean State;
    private String Msg;
    private T Res;
    private int Total = -1;
    private int TotalPage = -1;

    public boolean isState() {
        return State;
    }

    public void setState(boolean state) {
        State = state;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public T getRes() {
        return Res;
    }

    public void setRes(T res) {
        Res = res;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int total) {
        Total = total;
    }

    public int getTotalPage() {
        return TotalPage;
    }

    public void setTotalPage(int totalPage) {
        TotalPage = totalPage;
    }
}
