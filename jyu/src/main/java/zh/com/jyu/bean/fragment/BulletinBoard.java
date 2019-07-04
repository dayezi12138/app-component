package zh.com.jyu.bean.fragment;

import com.hgdendi.expandablerecycleradapter.BaseExpandableRecyclerViewAdapter;

import java.util.List;

/**
 * author : dayezi
 * data :2019/6/17
 * description:
 */
public class BulletinBoard implements BaseExpandableRecyclerViewAdapter.BaseGroupBean<BulletinBoard.BulletinBoardChild> {
    /**
     * key : 指接
     * value : []
     */

    private String key;
    private List<BulletinBoardChild> value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<BulletinBoardChild> getValue() {
        return value;
    }

    public void setValue(List<BulletinBoardChild> value) {
        this.value = value;
    }

    @Override
    public int getChildCount() {
        return value.size();
    }

    @Override
    public BulletinBoardChild getChildAt(int childIndex) {
        return value.size() <= childIndex ? null : value.get(childIndex);
    }

    @Override
    public boolean isExpandable() {
        return getChildCount() > 0;
    }

    public static class BulletinBoardChild {


        /**
         * Color : green
         * CraftsId : 17
         * CraftsName : 修补
         * CraftsReceiptID : 3
         * GoodsName : B4551 红木 3.5米
         * ProduceGoodsReceiptId : 1
         * RealNumber : 3000
         * ReportedCount : 101
         * ReportTime : 2019-06-14T13:55:57.38
         * ShortStr : XS190610001
         * Sort : 3
         * SpecName :
         * TimeOut : 0天0小时0分钟
         * TradeNOs : XS190610001
         * RegTime : 2019-06-10T15:53:16.407
         * HandoverTime : 2019-06-14T13:55:37.927
         */

        private String Color;
        private int CraftsId;
        private String CraftsName;
        private int CraftsReceiptID;
        private String GoodsName;
        private int ProduceGoodsReceiptId;
        private int RealNumber;
        private int ReportedCount;
        private String ReportTime;
        private String ShortStr;
        private int Sort;
        private String SpecName;
        private String TimeOut;
        private String TradeNOs;
        private String RegTime;
        private String HandoverTime;

        public String getColor() {
            return Color;
        }

        public void setColor(String Color) {
            this.Color = Color;
        }

        public int getCraftsId() {
            return CraftsId;
        }

        public void setCraftsId(int CraftsId) {
            this.CraftsId = CraftsId;
        }

        public String getCraftsName() {
            return CraftsName;
        }

        public void setCraftsName(String CraftsName) {
            this.CraftsName = CraftsName;
        }

        public int getCraftsReceiptID() {
            return CraftsReceiptID;
        }

        public void setCraftsReceiptID(int CraftsReceiptID) {
            this.CraftsReceiptID = CraftsReceiptID;
        }

        public String getGoodsName() {
            return GoodsName;
        }

        public void setGoodsName(String GoodsName) {
            this.GoodsName = GoodsName;
        }

        public int getProduceGoodsReceiptId() {
            return ProduceGoodsReceiptId;
        }

        public void setProduceGoodsReceiptId(int ProduceGoodsReceiptId) {
            this.ProduceGoodsReceiptId = ProduceGoodsReceiptId;
        }

        public int getRealNumber() {
            return RealNumber;
        }

        public void setRealNumber(int RealNumber) {
            this.RealNumber = RealNumber;
        }

        public int getReportedCount() {
            return ReportedCount;
        }

        public void setReportedCount(int ReportedCount) {
            this.ReportedCount = ReportedCount;
        }

        public String getReportTime() {
            return ReportTime;
        }

        public void setReportTime(String ReportTime) {
            this.ReportTime = ReportTime;
        }

        public String getShortStr() {
            return ShortStr;
        }

        public void setShortStr(String ShortStr) {
            this.ShortStr = ShortStr;
        }

        public int getSort() {
            return Sort;
        }

        public void setSort(int Sort) {
            this.Sort = Sort;
        }

        public String getSpecName() {
            return SpecName;
        }

        public void setSpecName(String SpecName) {
            this.SpecName = SpecName;
        }

        public String getTimeOut() {
            return TimeOut;
        }

        public void setTimeOut(String TimeOut) {
            this.TimeOut = TimeOut;
        }

        public String getTradeNOs() {
            return TradeNOs;
        }

        public void setTradeNOs(String TradeNOs) {
            this.TradeNOs = TradeNOs;
        }

        public String getRegTime() {
            return RegTime;
        }

        public void setRegTime(String RegTime) {
            this.RegTime = RegTime;
        }

        public String getHandoverTime() {
            return HandoverTime;
        }

        public void setHandoverTime(String HandoverTime) {
            this.HandoverTime = HandoverTime;
        }
    }
}
