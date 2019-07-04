package zh.com.jyu.bean.activity;

import java.io.Serializable;
import java.util.List;

/**
 * author : dayezi
 * data :2019/6/17
 * description:
 */
public class ReportListBean implements Serializable {
    /**
     * Reports : [{"CrewName":"班组2","ProduceDate":"2019-06-16","Count":400,"CreateTime":"2019-06-20 09:00","ID":25,"Remark":null},{"CrewName":"班组1","ProduceDate":"2019-06-18","Count":300,"CreateTime":"2019-06-18 15:12","ID":21,"Remark":"ABC"},{"CrewName":"班组2","ProduceDate":"2019-06-19","Count":23,"CreateTime":"2019-06-20 09:30","ID":26,"Remark":"2333"},{"CrewName":"班组3","ProduceDate":"2019-06-19","Count":11,"CreateTime":"2019-06-20 09:30","ID":27,"Remark":"22"},{"CrewName":"班组4","ProduceDate":"2019-06-20","Count":54354,"CreateTime":"2019-06-20 08:54","ID":22,"Remark":"jshshdhedj"},{"CrewName":"班组9","ProduceDate":"2019-06-20","Count":6,"CreateTime":"2019-06-20 08:54","ID":23,"Remark":null},{"CrewName":"班组3","ProduceDate":"2019-06-20","Count":63,"CreateTime":"2019-06-20 08:54","ID":24,"Remark":null}]
     * Number1 : 3000
     * ReportedCount : 55157
     */

    private int Number1;
    private int ReportedCount;
    private List<ReportsBean> Reports;

    public int getNumber1() {
        return Number1;
    }

    public void setNumber1(int Number1) {
        this.Number1 = Number1;
    }

    public int getReportedCount() {
        return ReportedCount;
    }

    public void setReportedCount(int reportedCount) {
        ReportedCount = reportedCount;
    }

    public List<ReportsBean> getReports() {
        return Reports;
    }

    public void setReports(List<ReportsBean> Reports) {
        this.Reports = Reports;
    }

    public static class ReportsBean implements Serializable {
        /**
         * CrewName : 班组2
         * ProduceDate : 2019-06-16
         * Count : 400
         * CreateTime : 2019-06-20 09:00
         * ID : 25
         * Remark : null
         */

        private String CrewName;
        private String ProduceDate;
        private int Count;
        private String CreateTime;
        private int ID;
        private String Remark;

        public String getCrewName() {
            return CrewName;
        }

        public void setCrewName(String CrewName) {
            this.CrewName = CrewName;
        }

        public String getProduceDate() {
            return ProduceDate;
        }

        public void setProduceDate(String ProduceDate) {
            this.ProduceDate = ProduceDate;
        }

        public int getCount() {
            return Count;
        }

        public void setCount(int count) {
            Count = count;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String remark) {
            Remark = remark;
        }
    }
}
