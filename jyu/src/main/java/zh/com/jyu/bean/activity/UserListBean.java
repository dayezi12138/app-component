package zh.com.jyu.bean.activity;

import java.io.Serializable;

/**
 * author : dayezi
 * data :2019/6/17
 * description:
 */
public class UserListBean implements Serializable {
    /**
     * RecID : 1
     * JobNo : 001
     * Name : 张三
     */
    private int ID;
    private int RecID;
    private String JobNo;
    private String Name;

    private String count_;
    private String memo_;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getRecID() {
        return RecID;
    }

    public void setRecID(int RecID) {
        this.RecID = RecID;
    }

    public String getJobNo() {
        return JobNo;
    }

    public void setJobNo(String JobNo) {
        this.JobNo = JobNo;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getCount_() {
        return count_;
    }

    public void setCount_(String count_) {
        this.count_ = count_;
    }

    public String getMemo_() {
        return memo_;
    }

    public void setMemo_(String memo_) {
        this.memo_ = memo_;
    }
}
