package zh.com.jyu.bean.activity;

/**
 * author : dayezi
 * data :2019/6/14
 * description:
 */
public class GoodListBean {
    /**
     * ID : 1
     * ProduceGoodsReceiptNO : SCHPD190610001
     * RegTime : 2019-06-10T15:53:16.407
     * StatusStr : 生产中
     * RealNumber : 3000
     * GoodsName : B4551 红木 3.5米
     * SpecName :
     */

    private int ID;
    private String ProduceGoodsReceiptNO;
    private String RegTime;
    private String StatusStr;
    private String RealNumber;
    private String GoodsName;
    private String SpecName;
    private String Status;


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getProduceGoodsReceiptNO() {
        return ProduceGoodsReceiptNO;
    }

    public void setProduceGoodsReceiptNO(String ProduceGoodsReceiptNO) {
        this.ProduceGoodsReceiptNO = ProduceGoodsReceiptNO;
    }

    public String getRegTime() {
        return RegTime;
    }

    public void setRegTime(String RegTime) {
        this.RegTime = RegTime;
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

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
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
}
