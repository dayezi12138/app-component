package zh.com.jyu.bean.fragment;

/**
 * author : dayezi
 * data :2019/6/12
 * description:
 */
public class PlanBean {
    /**
     * ID : 5
     * PlanNO : SCJH190610005
     * PlanStatus : 1
     * CreateTime : 2019-06-10T16:25:31.607
     */

    private int ID;
    private String PlanNO;
    private int PlanStatus;
    private String CreateTime;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPlanNO() {
        return PlanNO;
    }

    public void setPlanNO(String PlanNO) {
        this.PlanNO = PlanNO;
    }

    public int getPlanStatus() {
        return PlanStatus;
    }

    public void setPlanStatus(int PlanStatus) {
        this.PlanStatus = PlanStatus;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }
}
