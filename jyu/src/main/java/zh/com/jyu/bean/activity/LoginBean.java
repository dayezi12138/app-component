package zh.com.jyu.bean.activity;

/**
 * author : dayezi
 * data :2019/4/15
 * description:
 */
public class LoginBean {
    /**
     * UserId : 10179
     * Linkman : 超级管理员
     * GroupID : 1
     */

    private int UserId;
    private String Linkman;
    private int GroupID;

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public String getLinkman() {
        return Linkman;
    }

    public void setLinkman(String Linkman) {
        this.Linkman = Linkman;
    }

    public int getGroupID() {
        return GroupID;
    }

    public void setGroupID(int GroupID) {
        this.GroupID = GroupID;
    }
}
