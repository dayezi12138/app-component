package zh.com.jyu.bean.activity;

import java.util.List;

/**
 * author : dayezi
 * data :2019/6/14
 * description:
 */
public class CraftDetailBean {
    /**
     * CraftsReceiptID : 1
     * CraftsName : 指接
     * Number1 : 3000
     * ReportedCount : 3000
     * CraftsStatusStr : 已开始（汇报过）
     * firstReportTime : 2019/6/11 8:17:47
     * lastReportTime : 2019/6/11 8:17:47
     * people : 张三、子
     * HandoverTime :
     * RegTime : 2019-06-10T15:53:16.407
     * picURL : http://120.26.41.167:7082/upload/201906/636957816290221351.jpg
     * cmd : [{"Sender":"超级管理员","SendTime":"2019-06-10T16:40:41.513+08:00","Content":"暂停"},{"Sender":"超级管理员","SendTime":"2019-06-10T16:40:46.327+08:00","Content":"开始"}]
     */

    private int ProduceGoodsReceiptID;
    private int CraftsReceiptID;
    private String CraftsName;
    private String Number1;
    private String ReportedCount;
    private String ProduceGoodsReceiptNO;
    private String CraftsStatusStr;
    private String firstReportTime;
    private String lastReportTime;
    private String people;
    private String HandoverTime;
    private String RegTime;
    private String picURL;
    private String cmdPicURL;
    private List<CmdBean> cmd;
    private String crews;
    private String crewIDs;
    private String GoodsName;
    private String SpecName;
    private String Length;

    public String getLength() {
        return Length;
    }

    public void setLength(String length) {
        Length = length;
    }

    public String getCmdPicURL() {
        return cmdPicURL;
    }

    public void setCmdPicURL(String cmdPicURL) {
        this.cmdPicURL = cmdPicURL;
    }

    public String getGoodsName() {
        return GoodsName;
    }

    public void setGoodsName(String goodsName) {
        GoodsName = goodsName;
    }

    public String getSpecName() {
        return SpecName;
    }

    public void setSpecName(String specName) {
        SpecName = specName;
    }

    public int getProduceGoodsReceiptID() {
        return ProduceGoodsReceiptID;
    }

    public void setProduceGoodsReceiptID(int produceGoodsReceiptID) {
        ProduceGoodsReceiptID = produceGoodsReceiptID;
    }

    public String getCrews() {
        return crews;
    }

    public void setCrews(String crews) {
        this.crews = crews;
    }

    public String getCrewIDs() {
        return crewIDs;
    }

    public void setCrewIDs(String crewIDs) {
        this.crewIDs = crewIDs;
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

    public String getCraftsStatusStr() {
        return CraftsStatusStr;
    }

    public void setCraftsStatusStr(String CraftsStatusStr) {
        this.CraftsStatusStr = CraftsStatusStr;
    }

    public String getProduceGoodsReceiptNO() {
        return ProduceGoodsReceiptNO;
    }

    public void setProduceGoodsReceiptNO(String produceGoodsReceiptNO) {
        ProduceGoodsReceiptNO = produceGoodsReceiptNO;
    }

    public String getFirstReportTime() {
        return firstReportTime;
    }

    public void setFirstReportTime(String firstReportTime) {
        this.firstReportTime = firstReportTime;
    }

    public String getLastReportTime() {
        return lastReportTime;
    }

    public void setLastReportTime(String lastReportTime) {
        this.lastReportTime = lastReportTime;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public String getHandoverTime() {
        return HandoverTime;
    }

    public void setHandoverTime(String HandoverTime) {
        this.HandoverTime = HandoverTime;
    }

    public String getRegTime() {
        return RegTime;
    }

    public void setRegTime(String RegTime) {
        this.RegTime = RegTime;
    }

    public String getPicURL() {
        return picURL;
    }

    public void setPicURL(String picURL) {
        this.picURL = picURL;
    }

    public List<CmdBean> getCmd() {
        return cmd;
    }

    public void setCmd(List<CmdBean> cmd) {
        this.cmd = cmd;
    }

    public static class CmdBean {
        /**
         * Sender : 超级管理员
         * SendTime : 2019-06-10T16:40:41.513+08:00
         * Content : 暂停
         */

        private String Sender;
        private String SendTime;
        private String Content;

        public String getSender() {
            return Sender;
        }

        public void setSender(String Sender) {
            this.Sender = Sender;
        }

        public String getSendTime() {
            return SendTime;
        }

        public void setSendTime(String SendTime) {
            this.SendTime = SendTime;
        }

        public String getContent() {
            return Content;
        }

        public void setContent(String Content) {
            this.Content = Content;
        }
    }
}
