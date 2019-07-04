package zh.com.jyu.bean.activity;

import java.util.List;

/**
 * author : dayezi
 * data :2019/6/17
 * description:
 */
public class AddReportParam {

    private String ProduceDate;
    private int UserId;
    private int CraftsReceiptID;
    private List<ChildParam> SubReportParam;

    public AddReportParam(String produceDate, int userId, int craftsReceiptID, List<ChildParam> subReportParam) {
        ProduceDate = produceDate;
        UserId = userId;
        CraftsReceiptID = craftsReceiptID;
        SubReportParam = subReportParam;
    }

    public String getProduceDate() {
        return ProduceDate;
    }

    public void setProduceDate(String produceDate) {
        ProduceDate = produceDate;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getCraftsReceiptID() {
        return CraftsReceiptID;
    }

    public void setCraftsReceiptID(int craftsReceiptID) {
        CraftsReceiptID = craftsReceiptID;
    }

    public List<ChildParam> getSubReportParam() {
        return SubReportParam;
    }

    public void setSubReportParam(List<ChildParam> subReportParam) {
        SubReportParam = subReportParam;
    }

    public static class ChildParam {
        private String Count;
        private String Remark;
        private int CrewID;
        private String CrewName;

        public ChildParam(String count, String remark, int CrewID, String CrewName) {
            Count = count;
            Remark = remark;
            this.CrewID = CrewID;
            this.CrewName = CrewName;
        }

        public String getCount() {
            return Count;
        }

        public void setCount(String count) {
            Count = count;
        }

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String remark) {
            Remark = remark;
        }

        public int getCrewID() {
            return CrewID;
        }

        public void setCrewID(int crewID) {
            CrewID = crewID;
        }

        public String getCrewName() {
            return CrewName;
        }

        public void setCrewName(String crewName) {
            CrewName = crewName;
        }
    }

}
