package zh.com.jyu.bean.activity;

import java.io.Serializable;

/**
 * author : dayezi
 * data :2019/6/20
 * description:
 */
public class GroupBean implements Serializable {
    /**
     * CrewID : 1
     * CrewName : 班组1
     * Enabled : true
     * CreationTime : 2019-06-18 14:07
     * Remark : null
     * Members : 张三,乙,寅
     */

    private int CrewID;
    private String CrewName;
    private boolean Enabled;
    private String CreationTime;
    private String Remark;
    private String Members;

    public int getCrewID() {
        return CrewID;
    }

    public void setCrewID(int CrewID) {
        this.CrewID = CrewID;
    }

    public String getCrewName() {
        return CrewName;
    }

    public void setCrewName(String CrewName) {
        this.CrewName = CrewName;
    }

    public boolean isEnabled() {
        return Enabled;
    }

    public void setEnabled(boolean Enabled) {
        this.Enabled = Enabled;
    }

    public String getCreationTime() {
        return CreationTime;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public void setCreationTime(String CreationTime) {
        this.CreationTime = CreationTime;
    }

    public String getMembers() {
        return Members;
    }

    public void setMembers(String Members) {
        this.Members = Members;
    }
}
