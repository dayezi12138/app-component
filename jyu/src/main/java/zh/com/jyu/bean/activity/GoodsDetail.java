package zh.com.jyu.bean.activity;

/**
 * author : dayezi
 * data :2019/6/14
 * description:
 */
public class GoodsDetail {
    /**
     * ID : 1
     * TradeNOs : XS190610001
     * ProduceGoodsReceiptNO : SCHPD190610001
     * PlanNO : SCJH190610001
     * RegTime : 2019-06-10T15:53:16.407
     * PreDate : 2019-06-30T00:00:00
     * GoodsName : B4551 红木 3.5米
     * SpecName :
     * RealNumber : 3000
     * PackageCount : 100
     * Count2 : 0
     * Rate2 : 30
     * StatusStr : 生产中
     */

    private int ID;
    private String TradeNOs;
    private String ProduceGoodsReceiptNO;
    private String PlanNO;
    private String RegTime;
    private String PreDate;
    private String GoodsName;
    private String SpecName;
    private String RealNumber;
    private String PackageCount;
    private String Count2;
    private String Rate2;
    private String StatusStr;
    private String picURL;
    private String Length;

    public String getLength() {
        return Length;
    }

    public void setLength(String length) {
        Length = length;
    }

    public String getPicURL() {
        return picURL;
    }

    public void setPicURL(String picURL) {
        this.picURL = picURL;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTradeNOs() {
        return TradeNOs;
    }

    public void setTradeNOs(String TradeNOs) {
        this.TradeNOs = TradeNOs;
    }

    public String getProduceGoodsReceiptNO() {
        return ProduceGoodsReceiptNO;
    }

    public void setProduceGoodsReceiptNO(String ProduceGoodsReceiptNO) {
        this.ProduceGoodsReceiptNO = ProduceGoodsReceiptNO;
    }

    public String getPlanNO() {
        return PlanNO;
    }

    public void setPlanNO(String PlanNO) {
        this.PlanNO = PlanNO;
    }

    public String getRegTime() {
        return RegTime;
    }

    public void setRegTime(String RegTime) {
        this.RegTime = RegTime;
    }

    public String getPreDate() {
        return PreDate;
    }

    public void setPreDate(String PreDate) {
        this.PreDate = PreDate;
    }

    public String getGoodsName() {
        return GoodsName;
    }

    public void setGoodsName(String GoodsName) {
        this.GoodsName = GoodsName;
    }

    public String getSpecName() {
        return SpecName;
    }

    public void setSpecName(String SpecName) {
        this.SpecName = SpecName;
    }


    public String getStatusStr() {
        return StatusStr;
    }

    public void setStatusStr(String StatusStr) {
        this.StatusStr = StatusStr;
    }

    public String getRealNumber() {
        return RealNumber;
    }

    public void setRealNumber(String realNumber) {
        RealNumber = realNumber;
    }

    public String getPackageCount() {
        return PackageCount;
    }

    public void setPackageCount(String packageCount) {
        PackageCount = packageCount;
    }

    public String getCount2() {
        return Count2;
    }

    public void setCount2(String count2) {
        Count2 = count2;
    }

    public String getRate2() {
        return Rate2;
    }

    public void setRate2(String rate2) {
        Rate2 = rate2;
    }
}
