package zh.com.jyu.bean.activity;

/**
 * author : dayezi
 * data :2019/6/14
 * description:
 */
public class CraftBean {
    /**
     * CraftsReceiptID : 4
     * CraftsName : 砂光
     * Number1 : 3000
     * ReportedCount : 0
     * CraftsStatusStr : 未开始
     * RegTime : 2019-06-10T15:53:16.407
     */
    private String ProduceGoodsReceiptNO;
    private int CraftsReceiptID;
    private String CraftsName;
    private String Number1;
    private String ReportedCount;
    private String CraftsStatusStr;
    private String RegTime;
    private boolean expanded = false;
    private String PlanNO;
    private String GoodsName;
    private String Length;

    public String getGoodsName() {
        return GoodsName;
    }

    public void setGoodsName(String goodsName) {
        GoodsName = goodsName;
    }


    public String getPlanNO() {
        return PlanNO;
    }

    public void setPlanNO(String planNO) {
        PlanNO = planNO;
    }

    public String getProduceGoodsReceiptNO() {
        return ProduceGoodsReceiptNO;
    }

    public void setProduceGoodsReceiptNO(String produceGoodsReceiptNO) {
        ProduceGoodsReceiptNO = produceGoodsReceiptNO;
    }

    public int getCraftsReceiptID() {
        return CraftsReceiptID;
    }

    public void setCraftsReceiptID(int CraftsReceiptID) {
        this.CraftsReceiptID = CraftsReceiptID;
    }

    public String getCraftsName() {
        return CraftsName;
    }

    public void setCraftsName(String CraftsName) {
        this.CraftsName = CraftsName;
    }

    public String getNumber1() {
        return Number1;
    }

    public void setNumber1(String number1) {
        Number1 = number1;
    }

    public String getReportedCount() {
        return ReportedCount;
    }

    public void setReportedCount(String reportedCount) {
        ReportedCount = reportedCount;
    }

    public String getLength() {
        return Length;
    }

    public void setLength(String length) {
        Length = length;
    }

    public String getCraftsStatusStr() {
        return CraftsStatusStr;
    }

    public void setCraftsStatusStr(String CraftsStatusStr) {
        this.CraftsStatusStr = CraftsStatusStr;
    }

    public String getRegTime() {
        return RegTime;
    }

    public void setRegTime(String RegTime) {
        this.RegTime = RegTime;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}
