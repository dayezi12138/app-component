package zh.com.jyu.bean.activity;

import java.util.List;

/**
 * author : dayezi
 * data :2019/6/19
 * description:
 */
public class UserList {
    /**
     * CrewID : 1
     * CrewName : 班组1
     * Members : [{"StaffID":1,"StaffName":"张三"},{"StaffID":3,"StaffName":"乙"},{"StaffID":9,"StaffName":"寅"}]
     */

    private int CrewID;
    private String CrewName;
    private List<MembersBean> Members;

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

    public List<MembersBean> getMembers() {
        return Members;
    }

    public void setMembers(List<MembersBean> Members) {
        this.Members = Members;
    }

    public static class MembersBean {
        /**
         * StaffID : 1
         * StaffName : 张三
         */

        private int StaffID;
        private String StaffName;
        private boolean isVisible;

        public int getStaffID() {
            return StaffID;
        }

        public void setStaffID(int StaffID) {
            this.StaffID = StaffID;
        }

        public String getStaffName() {
            return StaffName;
        }

        public void setStaffName(String StaffName) {
            this.StaffName = StaffName;
        }

        public boolean isVisible() {
            return isVisible;
        }

        public void setVisible(boolean visible) {
            isVisible = visible;
        }
    }
}
