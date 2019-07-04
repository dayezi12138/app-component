package zh.com.jyu.bean.activity;

/**
 * author : dayezi
 * data :2019/6/14
 * description:
 */
public class RelationOrderBean {
    /**
     * RecID : 3
     * TradeID : 1001
     * tradeNO : XS190610002
     * companyName : 馨艺
     * SellCount : 22
     * YSndTime : 2019-06-29T00:00:00
     * Tel : 13245678910
     * SndTo : 王伟
     */

    private int RecID;
    private int TradeID;
    private String tradeNO;
    private String companyName;
    private int SellCount;
    private String YSndTime;
    private String Tel;
    private String SndTo;

    public int getRecID() {
        return RecID;
    }

    public void setRecID(int RecID) {
        this.RecID = RecID;
    }

    public int getTradeID() {
        return TradeID;
    }

    public void setTradeID(int TradeID) {
        this.TradeID = TradeID;
    }

    public String getTradeNO() {
        return tradeNO;
    }

    public void setTradeNO(String tradeNO) {
        this.tradeNO = tradeNO;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getSellCount() {
        return SellCount;
    }

    public void setSellCount(int SellCount) {
        this.SellCount = SellCount;
    }

    public String getYSndTime() {
        return YSndTime;
    }

    public void setYSndTime(String YSndTime) {
        this.YSndTime = YSndTime;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String Tel) {
        this.Tel = Tel;
    }

    public String getSndTo() {
        return SndTo;
    }

    public void setSndTo(String SndTo) {
        this.SndTo = SndTo;
    }
}
