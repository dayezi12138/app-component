package zh.com.jyu.bean.fragment;

import com.hgdendi.expandablerecycleradapter.BaseExpandableRecyclerViewAdapter;

import java.util.List;

/**
 * author : dayezi
 * data :2019/9/5
 * description:
 */
public class NBulletinBoard implements BaseExpandableRecyclerViewAdapter.BaseGroupBean<NBulletinBoard.ValueBean> {
    /**
     * CraftsName : 指接
     * CraftsID : 6
     * Value : [{"PlanNO":"SCJH190903003","GoodsName":"S2730 柚木3.5米","RealNumber":1400,"ReportedCount":0},{"PlanNO":"SCJH190903003","GoodsName":"S2730 黑杏3.5米","RealNumber":2100,"ReportedCount":0},{"PlanNO":"SCJH190903003","GoodsName":"S2730 秋香3.5米","RealNumber":1400,"ReportedCount":0},{"PlanNO":"SCJH190903003","GoodsName":"S2730 原木 3.5米","RealNumber":16068,"ReportedCount":0},{"PlanNO":"SCJH190903004","GoodsName":"S2230 青茶色 3米","RealNumber":543,"ReportedCount":0},{"PlanNO":"SCJH190903004","GoodsName":"S2230 原木 3米","RealNumber":543,"ReportedCount":0}]
     */

    private String CraftsName;
    private int CraftsID;
    private List<ValueBean> Value;


    public String getCraftsName() {
        return CraftsName;
    }

    public void setCraftsName(String CraftsName) {
        this.CraftsName = CraftsName;
    }

    public int getCraftsID() {
        return CraftsID;
    }

    public void setCraftsID(int CraftsID) {
        this.CraftsID = CraftsID;
    }

    public List<ValueBean> getValue() {
        return Value;
    }

    public void setValue(List<ValueBean> Value) {
        this.Value = Value;
    }

    @Override
    public int getChildCount() {
        return Value.size();
    }

    @Override
    public ValueBean getChildAt(int childIndex) {
        return Value.size() <= childIndex ? null : Value.get(childIndex);
    }

    @Override
    public boolean isExpandable() {
        return getChildCount() > 0 ;
    }


    public static class ValueBean implements Comparable<ValueBean> {
        /**
         * PlanNO : SCJH190903003
         * GoodsName : S2730 柚木3.5米
         * RealNumber : 1400
         * ReportedCount : 0
         */

        private String PlanNO;
        private String GoodsName;
        private String RealNumber;
        private String ReportedCount;
        private boolean isVisible;

        public boolean isVisible() {
            return isVisible;
        }

        public void setVisible(boolean visible) {
            isVisible = visible;
        }

        public String getPlanNO() {
            return PlanNO;
        }

        public void setPlanNO(String PlanNO) {
            this.PlanNO = PlanNO;
        }

        public String getGoodsName() {
            return GoodsName;
        }

        public void setGoodsName(String GoodsName) {
            this.GoodsName = GoodsName;
        }

        public String getRealNumber() {
            return RealNumber;
        }

        public void setRealNumber(String realNumber) {
            RealNumber = realNumber;
        }

        public String getReportedCount() {
            return ReportedCount;
        }

        public void setReportedCount(String reportedCount) {
            ReportedCount = reportedCount;
        }

        @Override
        public int compareTo(ValueBean child) {
            String t1 = this.PlanNO.substring(4);
            String t2 = child.getPlanNO().substring(4);
//            if (Integer.valueOf(t1) - Integer.valueOf(t2) < 0) {
//                return -1;
//            }
//            if (Integer.valueOf(t1) - Integer.valueOf(t2) == 0) {
//                return 0;
//            }
            return Integer.valueOf(t1) - Integer.valueOf(t2);
        }
    }
}
