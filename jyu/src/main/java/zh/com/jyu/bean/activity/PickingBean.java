package zh.com.jyu.bean.activity;

import java.util.List;

/**
 * author : dayezi
 * data :2019/6/13
 * description:
 */
public class PickingBean {
    /**
     * PlanNo : SCJH190610001
     * sDate : 2019-06-11T10:41:16.547
     * CurStatusStr : 待审核
     * details : [{"TradeNos":"XS190610001","SpecName":"32*95","GoodsName":"32*95","GoodsCount":3000}]
     */

    private String PlanNo;
    private String sDate;
    private String CurStatusStr;
    private List<DetailsBean> details;

    public String getPlanNo() {
        return PlanNo;
    }

    public void setPlanNo(String PlanNo) {
        this.PlanNo = PlanNo;
    }

    public String getSDate() {
        return sDate;
    }

    public void setSDate(String sDate) {
        this.sDate = sDate;
    }

    public String getCurStatusStr() {
        return CurStatusStr;
    }

    public void setCurStatusStr(String CurStatusStr) {
        this.CurStatusStr = CurStatusStr;
    }

    public List<DetailsBean> getDetails() {
        return details;
    }

    public void setDetails(List<DetailsBean> details) {
        this.details = details;
    }

    public static class DetailsBean {
        /**
         * TradeNos : XS190610001
         * SpecName : 32*95
         * GoodsName : 32*95
         * GoodsCount : 3000
         */

        private String TradeNos;
        private String SpecName;
        private String GoodsName;
        private String GoodsCount;

        public String getTradeNos() {
            return TradeNos;
        }

        public void setTradeNos(String TradeNos) {
            this.TradeNos = TradeNos;
        }

        public String getSpecName() {
            return SpecName;
        }

        public void setSpecName(String SpecName) {
            this.SpecName = SpecName;
        }

        public String getGoodsName() {
            return GoodsName;
        }

        public void setGoodsName(String GoodsName) {
            this.GoodsName = GoodsName;
        }

        public String getGoodsCount() {
            return GoodsCount;
        }

        public void setGoodsCount(String goodsCount) {
            GoodsCount = goodsCount;
        }
    }
}
