package zh.com.jyu.bean.fragment;

/**
 * author : dayezi
 * data :2019/6/27
 * description:
 */
public class ProduceListBean {
    /**
     * ID : 6
     * ProduceGoodsReceiptNO : SCHPD190626006
     * RegTime : 2019-06-26 15:43
     * Status : 0
     * StatusStr : 待生产
     * RealNumber : 1000
     * GoodsName : B1225 原木
     * SpecName : B1225 白
     */

    private int ID;
    private String ProduceGoodsReceiptNO;
    private String RegTime;
    private int Status;
    private String StatusStr;
    private int RealNumber;
    private String GoodsName;
    private String SpecName;

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

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public String getStatusStr() {
        return StatusStr;
    }

    public void setStatusStr(String StatusStr) {
        this.StatusStr = StatusStr;
    }

    public int getRealNumber() {
        return RealNumber;
    }

    public void setRealNumber(int RealNumber) {
        this.RealNumber = RealNumber;
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
