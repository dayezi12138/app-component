package zh.com.jyu.bean.activity;

import java.util.List;

/**
 * author : dayezi
 * data :2019/6/25
 * description:
 */
public class OrderDetailBean {
    /**
     * Trade_TradeList : {"TradeID":1002,"RegTime":"2019-06-10T16:21:28.94","TradeTime":"2019-06-10T16:21:28.94","Days":"16小时","TradeStatus":3,"TradeStatusExt":"预订单","TradeNO":"XS190610003","ShopID":1008,"SellerId":10179,"Seller":"超级管理员","TradeType":"零售业务","CustomerID":10000,"CustomerLevel":"","CustomerType":"大客户","SndTo":"王伟","Country":"","Province":"","City":"","Town":"","Adr":"上海","Zip":"","Tel":"13245678910","RegOperator":"超级管理员","ConfirmTime":"2019-06-10T16:22:33.793","ConfirmOperator":"超级管理员","PrintSndBill":0,"PrintExpress":null,"Picker":null,"ChkOperator":null,"ChkTime":null,"PackageOperator":null,"SndTime":null,"SndOperator":null,"GoodsWeight":null,"PackagedWeight":null,"CustomerRemark":"","Remark":"","LogisticID":null,"PackageID":null,"Postage":0,"PostID":null,"ChargeType":"结算方式","ChargeID":null,"AccountID":0,"OtherCost":0,"GoodsTotal":0,"PostageTotal":0,"TaxValue":0,"CouponValue":0,"FavourableTotal":0,"AllTotal":0,"RcvTotal":0,"CurrencyType":"人民币","CurrencyRate":1,"GoodsCost":0,"PackageCost":0,"TotalProfit":0,"PriceSpec":"最近售价","PriceDis":1,"GoodsList":null,"TradeFlagID":0,"FlagName":"","DrawbackValue":null,"OtherGoods":null,"CouponID":null,"CommissionValue":0,"WarehouseID":1000,"PrintCollect":null,"PrintPos":null,"PrintChannel":null,"PreDate":null,"PreType":null,"bStockOut":null,"bManualCHK":null,"AppendRemark":null,"bInvoice":null,"PrintInvoice":null,"estimateWeight":null,"InvoiceTitle":null,"RMB_Value":null,"ProviderID":null,"ChargeOperator":null,"FreezeReason":null,"CancelReason":null,"bSellBack":false,"TradeFrom":"手工新建","TradeNO2":"","bSndSys":null,"PostageFee2":0,"Reserved1":null,"Reserved2":null,"Reserved3":null,"Reserved4":null,"PrintCHK":null,"FXTid":"","HH":null,"HuHao":null,"BXZCBM":null,"LSH":null,"LL":null,"TQ":null,"YDLB":null,"GDDYDY":null,"SBFL":null,"YWLX":null,"OrderId":null,"StockOutID":null,"WuliuAdr":"","YSndTime":"2019-06-29T00:00:00","CustomerName":"馨艺","RegOperatorId":10179,"status":"2","WarehouseName":"成品仓库","ShopName":"工厂"}
     * Trade_GoodsList : [{"SellCount":302,"SellPrice":0,"Unit":"米","GoodsID":1702,"GoodsName":"B0315 红 3.5米","IndexURL":"https://gd4.alicdn.com/imgextra/i2/2044741058/TB2PwwIebWJ.eBjSspdXXXiXFXa_!!2044741058.jpg_400x400.jpg","Price_Detail":0,"GoodsNo":"B0315 红 3.5米","SpecName":"B0315 红 3.5米","SpecID":1901},{"SellCount":302,"SellPrice":0,"Unit":"米","GoodsID":1701,"GoodsName":"B1225 咖啡","IndexURL":"https://gd4.alicdn.com/imgextra/i2/2044741058/TB2PwwIebWJ.eBjSspdXXXiXFXa_!!2044741058.jpg_400x400.jpg","Price_Detail":0,"GoodsNo":"B1225 咖啡","SpecName":"B1225 咖啡","SpecID":1902}]
     * GoodsCount : 2
     */

    private TradeTradeListBean Trade_TradeList;
    private int GoodsCount;
    private List<TradeGoodsListBean> Trade_GoodsList;

    public TradeTradeListBean getTrade_TradeList() {
        return Trade_TradeList;
    }

    public void setTrade_TradeList(TradeTradeListBean Trade_TradeList) {
        this.Trade_TradeList = Trade_TradeList;
    }

    public int getGoodsCount() {
        return GoodsCount;
    }

    public void setGoodsCount(int GoodsCount) {
        this.GoodsCount = GoodsCount;
    }

    public List<TradeGoodsListBean> getTrade_GoodsList() {
        return Trade_GoodsList;
    }

    public void setTrade_GoodsList(List<TradeGoodsListBean> Trade_GoodsList) {
        this.Trade_GoodsList = Trade_GoodsList;
    }

    public static class TradeTradeListBean {
        /**
         * TradeID : 1002
         * RegTime : 2019-06-10T16:21:28.94
         * TradeTime : 2019-06-10T16:21:28.94
         * Days : 16小时
         * TradeStatus : 3
         * TradeStatusExt : 预订单
         * TradeNO : XS190610003
         * ShopID : 1008
         * SellerId : 10179
         * Seller : 超级管理员
         * TradeType : 零售业务
         * CustomerID : 10000
         * CustomerLevel :
         * CustomerType : 大客户
         * SndTo : 王伟
         * Country :
         * Province :
         * City :
         * Town :
         * Adr : 上海
         * Zip :
         * Tel : 13245678910
         * RegOperator : 超级管理员
         * ConfirmTime : 2019-06-10T16:22:33.793
         * ConfirmOperator : 超级管理员
         * PrintSndBill : 0
         * PrintExpress : null
         * Picker : null
         * ChkOperator : null
         * ChkTime : null
         * PackageOperator : null
         * SndTime : null
         * SndOperator : null
         * GoodsWeight : null
         * PackagedWeight : null
         * CustomerRemark :
         * Remark :
         * LogisticID : null
         * PackageID : null
         * Postage : 0
         * PostID : null
         * ChargeType : 结算方式
         * ChargeID : null
         * AccountID : 0
         * OtherCost : 0
         * GoodsTotal : 0
         * PostageTotal : 0
         * TaxValue : 0
         * CouponValue : 0
         * FavourableTotal : 0
         * AllTotal : 0
         * RcvTotal : 0
         * CurrencyType : 人民币
         * CurrencyRate : 1
         * GoodsCost : 0
         * PackageCost : 0
         * TotalProfit : 0
         * PriceSpec : 最近售价
         * PriceDis : 1
         * GoodsList : null
         * TradeFlagID : 0
         * FlagName :
         * DrawbackValue : null
         * OtherGoods : null
         * CouponID : null
         * CommissionValue : 0
         * WarehouseID : 1000
         * PrintCollect : null
         * PrintPos : null
         * PrintChannel : null
         * PreDate : null
         * PreType : null
         * bStockOut : null
         * bManualCHK : null
         * AppendRemark : null
         * bInvoice : null
         * PrintInvoice : null
         * estimateWeight : null
         * InvoiceTitle : null
         * RMB_Value : null
         * ProviderID : null
         * ChargeOperator : null
         * FreezeReason : null
         * CancelReason : null
         * bSellBack : false
         * TradeFrom : 手工新建
         * TradeNO2 :
         * bSndSys : null
         * PostageFee2 : 0
         * Reserved1 : null
         * Reserved2 : null
         * Reserved3 : null
         * Reserved4 : null
         * PrintCHK : null
         * FXTid :
         * HH : null
         * HuHao : null
         * BXZCBM : null
         * LSH : null
         * LL : null
         * TQ : null
         * YDLB : null
         * GDDYDY : null
         * SBFL : null
         * YWLX : null
         * OrderId : null
         * StockOutID : null
         * WuliuAdr :
         * YSndTime : 2019-06-29T00:00:00
         * CustomerName : 馨艺
         * RegOperatorId : 10179
         * status : 2
         * WarehouseName : 成品仓库
         * ShopName : 工厂
         */

        private int TradeID;
        private String RegTime;
        private String TradeTime;
        private String Days;
        private int TradeStatus;
        private String TradeStatusExt;
        private String TradeNO;
        private int ShopID;
        private int SellerId;
        private String Seller;
        private String TradeType;
        private int CustomerID;
        private String CustomerLevel;
        private String CustomerType;
        private String SndTo;
        private String Country;
        private String Province;
        private String City;
        private String Town;
        private String Adr;
        private String Zip;
        private String Tel;
        private String RegOperator;
        private String ConfirmTime;
        private String ConfirmOperator;
        private int PrintSndBill;
        private Object PrintExpress;
        private Object Picker;
        private Object ChkOperator;
        private Object ChkTime;
        private Object PackageOperator;
        private Object SndTime;
        private Object SndOperator;
        private Object GoodsWeight;
        private Object PackagedWeight;
        private String CustomerRemark;
        private String Remark;
        private Object LogisticID;
        private Object PackageID;
        private int Postage;
        private Object PostID;
        private String ChargeType;
        private Object ChargeID;
        private int AccountID;
        private int OtherCost;
        private int GoodsTotal;
        private int PostageTotal;
        private int TaxValue;
        private int CouponValue;
        private int FavourableTotal;
        private int AllTotal;
        private int RcvTotal;
        private String CurrencyType;
        private int CurrencyRate;
        private int GoodsCost;
        private int PackageCost;
        private int TotalProfit;
        private String PriceSpec;
        private int PriceDis;
        private Object GoodsList;
        private int TradeFlagID;
        private String FlagName;
        private Object DrawbackValue;
        private Object OtherGoods;
        private Object CouponID;
        private int CommissionValue;
        private int WarehouseID;
        private Object PrintCollect;
        private Object PrintPos;
        private Object PrintChannel;
        private Object PreDate;
        private Object PreType;
        private Object bStockOut;
        private Object bManualCHK;
        private Object AppendRemark;
        private Object bInvoice;
        private Object PrintInvoice;
        private Object estimateWeight;
        private Object InvoiceTitle;
        private Object RMB_Value;
        private Object ProviderID;
        private Object ChargeOperator;
        private Object FreezeReason;
        private Object CancelReason;
        private boolean bSellBack;
        private String TradeFrom;
        private String TradeNO2;
        private Object bSndSys;
        private int PostageFee2;
        private Object Reserved1;
        private Object Reserved2;
        private Object Reserved3;
        private Object Reserved4;
        private Object PrintCHK;
        private String FXTid;
        private Object HH;
        private Object HuHao;
        private Object BXZCBM;
        private Object LSH;
        private Object LL;
        private Object TQ;
        private Object YDLB;
        private Object GDDYDY;
        private Object SBFL;
        private Object YWLX;
        private Object OrderId;
        private Object StockOutID;
        private String WuliuAdr;
        private String YSndTime;
        private String CustomerName;
        private int RegOperatorId;
        private String status;
        private String WarehouseName;
        private String ShopName;

        public int getTradeID() {
            return TradeID;
        }

        public void setTradeID(int TradeID) {
            this.TradeID = TradeID;
        }

        public String getRegTime() {
            return RegTime;
        }

        public void setRegTime(String RegTime) {
            this.RegTime = RegTime;
        }

        public String getTradeTime() {
            return TradeTime;
        }

        public void setTradeTime(String TradeTime) {
            this.TradeTime = TradeTime;
        }

        public String getDays() {
            return Days;
        }

        public void setDays(String Days) {
            this.Days = Days;
        }

        public int getTradeStatus() {
            return TradeStatus;
        }

        public void setTradeStatus(int TradeStatus) {
            this.TradeStatus = TradeStatus;
        }

        public String getTradeStatusExt() {
            return TradeStatusExt;
        }

        public void setTradeStatusExt(String TradeStatusExt) {
            this.TradeStatusExt = TradeStatusExt;
        }

        public String getTradeNO() {
            return TradeNO;
        }

        public void setTradeNO(String TradeNO) {
            this.TradeNO = TradeNO;
        }

        public int getShopID() {
            return ShopID;
        }

        public void setShopID(int ShopID) {
            this.ShopID = ShopID;
        }

        public int getSellerId() {
            return SellerId;
        }

        public void setSellerId(int SellerId) {
            this.SellerId = SellerId;
        }

        public String getSeller() {
            return Seller;
        }

        public void setSeller(String Seller) {
            this.Seller = Seller;
        }

        public String getTradeType() {
            return TradeType;
        }

        public void setTradeType(String TradeType) {
            this.TradeType = TradeType;
        }

        public int getCustomerID() {
            return CustomerID;
        }

        public void setCustomerID(int CustomerID) {
            this.CustomerID = CustomerID;
        }

        public String getCustomerLevel() {
            return CustomerLevel;
        }

        public void setCustomerLevel(String CustomerLevel) {
            this.CustomerLevel = CustomerLevel;
        }

        public String getCustomerType() {
            return CustomerType;
        }

        public void setCustomerType(String CustomerType) {
            this.CustomerType = CustomerType;
        }

        public String getSndTo() {
            return SndTo;
        }

        public void setSndTo(String SndTo) {
            this.SndTo = SndTo;
        }

        public String getCountry() {
            return Country;
        }

        public void setCountry(String Country) {
            this.Country = Country;
        }

        public String getProvince() {
            return Province;
        }

        public void setProvince(String Province) {
            this.Province = Province;
        }

        public String getCity() {
            return City;
        }

        public void setCity(String City) {
            this.City = City;
        }

        public String getTown() {
            return Town;
        }

        public void setTown(String Town) {
            this.Town = Town;
        }

        public String getAdr() {
            return Adr;
        }

        public void setAdr(String Adr) {
            this.Adr = Adr;
        }

        public String getZip() {
            return Zip;
        }

        public void setZip(String Zip) {
            this.Zip = Zip;
        }

        public String getTel() {
            return Tel;
        }

        public void setTel(String Tel) {
            this.Tel = Tel;
        }

        public String getRegOperator() {
            return RegOperator;
        }

        public void setRegOperator(String RegOperator) {
            this.RegOperator = RegOperator;
        }

        public String getConfirmTime() {
            return ConfirmTime;
        }

        public void setConfirmTime(String ConfirmTime) {
            this.ConfirmTime = ConfirmTime;
        }

        public String getConfirmOperator() {
            return ConfirmOperator;
        }

        public void setConfirmOperator(String ConfirmOperator) {
            this.ConfirmOperator = ConfirmOperator;
        }

        public int getPrintSndBill() {
            return PrintSndBill;
        }

        public void setPrintSndBill(int PrintSndBill) {
            this.PrintSndBill = PrintSndBill;
        }

        public Object getPrintExpress() {
            return PrintExpress;
        }

        public void setPrintExpress(Object PrintExpress) {
            this.PrintExpress = PrintExpress;
        }

        public Object getPicker() {
            return Picker;
        }

        public void setPicker(Object Picker) {
            this.Picker = Picker;
        }

        public Object getChkOperator() {
            return ChkOperator;
        }

        public void setChkOperator(Object ChkOperator) {
            this.ChkOperator = ChkOperator;
        }

        public Object getChkTime() {
            return ChkTime;
        }

        public void setChkTime(Object ChkTime) {
            this.ChkTime = ChkTime;
        }

        public Object getPackageOperator() {
            return PackageOperator;
        }

        public void setPackageOperator(Object PackageOperator) {
            this.PackageOperator = PackageOperator;
        }

        public Object getSndTime() {
            return SndTime;
        }

        public void setSndTime(Object SndTime) {
            this.SndTime = SndTime;
        }

        public Object getSndOperator() {
            return SndOperator;
        }

        public void setSndOperator(Object SndOperator) {
            this.SndOperator = SndOperator;
        }

        public Object getGoodsWeight() {
            return GoodsWeight;
        }

        public void setGoodsWeight(Object GoodsWeight) {
            this.GoodsWeight = GoodsWeight;
        }

        public Object getPackagedWeight() {
            return PackagedWeight;
        }

        public void setPackagedWeight(Object PackagedWeight) {
            this.PackagedWeight = PackagedWeight;
        }

        public String getCustomerRemark() {
            return CustomerRemark;
        }

        public void setCustomerRemark(String CustomerRemark) {
            this.CustomerRemark = CustomerRemark;
        }

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String Remark) {
            this.Remark = Remark;
        }

        public Object getLogisticID() {
            return LogisticID;
        }

        public void setLogisticID(Object LogisticID) {
            this.LogisticID = LogisticID;
        }

        public Object getPackageID() {
            return PackageID;
        }

        public void setPackageID(Object PackageID) {
            this.PackageID = PackageID;
        }

        public int getPostage() {
            return Postage;
        }

        public void setPostage(int Postage) {
            this.Postage = Postage;
        }

        public Object getPostID() {
            return PostID;
        }

        public void setPostID(Object PostID) {
            this.PostID = PostID;
        }

        public String getChargeType() {
            return ChargeType;
        }

        public void setChargeType(String ChargeType) {
            this.ChargeType = ChargeType;
        }

        public Object getChargeID() {
            return ChargeID;
        }

        public void setChargeID(Object ChargeID) {
            this.ChargeID = ChargeID;
        }

        public int getAccountID() {
            return AccountID;
        }

        public void setAccountID(int AccountID) {
            this.AccountID = AccountID;
        }

        public int getOtherCost() {
            return OtherCost;
        }

        public void setOtherCost(int OtherCost) {
            this.OtherCost = OtherCost;
        }

        public int getGoodsTotal() {
            return GoodsTotal;
        }

        public void setGoodsTotal(int GoodsTotal) {
            this.GoodsTotal = GoodsTotal;
        }

        public int getPostageTotal() {
            return PostageTotal;
        }

        public void setPostageTotal(int PostageTotal) {
            this.PostageTotal = PostageTotal;
        }

        public int getTaxValue() {
            return TaxValue;
        }

        public void setTaxValue(int TaxValue) {
            this.TaxValue = TaxValue;
        }

        public int getCouponValue() {
            return CouponValue;
        }

        public void setCouponValue(int CouponValue) {
            this.CouponValue = CouponValue;
        }

        public int getFavourableTotal() {
            return FavourableTotal;
        }

        public void setFavourableTotal(int FavourableTotal) {
            this.FavourableTotal = FavourableTotal;
        }

        public int getAllTotal() {
            return AllTotal;
        }

        public void setAllTotal(int AllTotal) {
            this.AllTotal = AllTotal;
        }

        public int getRcvTotal() {
            return RcvTotal;
        }

        public void setRcvTotal(int RcvTotal) {
            this.RcvTotal = RcvTotal;
        }

        public String getCurrencyType() {
            return CurrencyType;
        }

        public void setCurrencyType(String CurrencyType) {
            this.CurrencyType = CurrencyType;
        }

        public int getCurrencyRate() {
            return CurrencyRate;
        }

        public void setCurrencyRate(int CurrencyRate) {
            this.CurrencyRate = CurrencyRate;
        }

        public int getGoodsCost() {
            return GoodsCost;
        }

        public void setGoodsCost(int GoodsCost) {
            this.GoodsCost = GoodsCost;
        }

        public int getPackageCost() {
            return PackageCost;
        }

        public void setPackageCost(int PackageCost) {
            this.PackageCost = PackageCost;
        }

        public int getTotalProfit() {
            return TotalProfit;
        }

        public void setTotalProfit(int TotalProfit) {
            this.TotalProfit = TotalProfit;
        }

        public String getPriceSpec() {
            return PriceSpec;
        }

        public void setPriceSpec(String PriceSpec) {
            this.PriceSpec = PriceSpec;
        }

        public int getPriceDis() {
            return PriceDis;
        }

        public void setPriceDis(int PriceDis) {
            this.PriceDis = PriceDis;
        }

        public Object getGoodsList() {
            return GoodsList;
        }

        public void setGoodsList(Object GoodsList) {
            this.GoodsList = GoodsList;
        }

        public int getTradeFlagID() {
            return TradeFlagID;
        }

        public void setTradeFlagID(int TradeFlagID) {
            this.TradeFlagID = TradeFlagID;
        }

        public String getFlagName() {
            return FlagName;
        }

        public void setFlagName(String FlagName) {
            this.FlagName = FlagName;
        }

        public Object getDrawbackValue() {
            return DrawbackValue;
        }

        public void setDrawbackValue(Object DrawbackValue) {
            this.DrawbackValue = DrawbackValue;
        }

        public Object getOtherGoods() {
            return OtherGoods;
        }

        public void setOtherGoods(Object OtherGoods) {
            this.OtherGoods = OtherGoods;
        }

        public Object getCouponID() {
            return CouponID;
        }

        public void setCouponID(Object CouponID) {
            this.CouponID = CouponID;
        }

        public int getCommissionValue() {
            return CommissionValue;
        }

        public void setCommissionValue(int CommissionValue) {
            this.CommissionValue = CommissionValue;
        }

        public int getWarehouseID() {
            return WarehouseID;
        }

        public void setWarehouseID(int WarehouseID) {
            this.WarehouseID = WarehouseID;
        }

        public Object getPrintCollect() {
            return PrintCollect;
        }

        public void setPrintCollect(Object PrintCollect) {
            this.PrintCollect = PrintCollect;
        }

        public Object getPrintPos() {
            return PrintPos;
        }

        public void setPrintPos(Object PrintPos) {
            this.PrintPos = PrintPos;
        }

        public Object getPrintChannel() {
            return PrintChannel;
        }

        public void setPrintChannel(Object PrintChannel) {
            this.PrintChannel = PrintChannel;
        }

        public Object getPreDate() {
            return PreDate;
        }

        public void setPreDate(Object PreDate) {
            this.PreDate = PreDate;
        }

        public Object getPreType() {
            return PreType;
        }

        public void setPreType(Object PreType) {
            this.PreType = PreType;
        }

        public Object getBStockOut() {
            return bStockOut;
        }

        public void setBStockOut(Object bStockOut) {
            this.bStockOut = bStockOut;
        }

        public Object getBManualCHK() {
            return bManualCHK;
        }

        public void setBManualCHK(Object bManualCHK) {
            this.bManualCHK = bManualCHK;
        }

        public Object getAppendRemark() {
            return AppendRemark;
        }

        public void setAppendRemark(Object AppendRemark) {
            this.AppendRemark = AppendRemark;
        }

        public Object getBInvoice() {
            return bInvoice;
        }

        public void setBInvoice(Object bInvoice) {
            this.bInvoice = bInvoice;
        }

        public Object getPrintInvoice() {
            return PrintInvoice;
        }

        public void setPrintInvoice(Object PrintInvoice) {
            this.PrintInvoice = PrintInvoice;
        }

        public Object getEstimateWeight() {
            return estimateWeight;
        }

        public void setEstimateWeight(Object estimateWeight) {
            this.estimateWeight = estimateWeight;
        }

        public Object getInvoiceTitle() {
            return InvoiceTitle;
        }

        public void setInvoiceTitle(Object InvoiceTitle) {
            this.InvoiceTitle = InvoiceTitle;
        }

        public Object getRMB_Value() {
            return RMB_Value;
        }

        public void setRMB_Value(Object RMB_Value) {
            this.RMB_Value = RMB_Value;
        }

        public Object getProviderID() {
            return ProviderID;
        }

        public void setProviderID(Object ProviderID) {
            this.ProviderID = ProviderID;
        }

        public Object getChargeOperator() {
            return ChargeOperator;
        }

        public void setChargeOperator(Object ChargeOperator) {
            this.ChargeOperator = ChargeOperator;
        }

        public Object getFreezeReason() {
            return FreezeReason;
        }

        public void setFreezeReason(Object FreezeReason) {
            this.FreezeReason = FreezeReason;
        }

        public Object getCancelReason() {
            return CancelReason;
        }

        public void setCancelReason(Object CancelReason) {
            this.CancelReason = CancelReason;
        }

        public boolean isBSellBack() {
            return bSellBack;
        }

        public void setBSellBack(boolean bSellBack) {
            this.bSellBack = bSellBack;
        }

        public String getTradeFrom() {
            return TradeFrom;
        }

        public void setTradeFrom(String TradeFrom) {
            this.TradeFrom = TradeFrom;
        }

        public String getTradeNO2() {
            return TradeNO2;
        }

        public void setTradeNO2(String TradeNO2) {
            this.TradeNO2 = TradeNO2;
        }

        public Object getBSndSys() {
            return bSndSys;
        }

        public void setBSndSys(Object bSndSys) {
            this.bSndSys = bSndSys;
        }

        public int getPostageFee2() {
            return PostageFee2;
        }

        public void setPostageFee2(int PostageFee2) {
            this.PostageFee2 = PostageFee2;
        }

        public Object getReserved1() {
            return Reserved1;
        }

        public void setReserved1(Object Reserved1) {
            this.Reserved1 = Reserved1;
        }

        public Object getReserved2() {
            return Reserved2;
        }

        public void setReserved2(Object Reserved2) {
            this.Reserved2 = Reserved2;
        }

        public Object getReserved3() {
            return Reserved3;
        }

        public void setReserved3(Object Reserved3) {
            this.Reserved3 = Reserved3;
        }

        public Object getReserved4() {
            return Reserved4;
        }

        public void setReserved4(Object Reserved4) {
            this.Reserved4 = Reserved4;
        }

        public Object getPrintCHK() {
            return PrintCHK;
        }

        public void setPrintCHK(Object PrintCHK) {
            this.PrintCHK = PrintCHK;
        }

        public String getFXTid() {
            return FXTid;
        }

        public void setFXTid(String FXTid) {
            this.FXTid = FXTid;
        }

        public Object getHH() {
            return HH;
        }

        public void setHH(Object HH) {
            this.HH = HH;
        }

        public Object getHuHao() {
            return HuHao;
        }

        public void setHuHao(Object HuHao) {
            this.HuHao = HuHao;
        }

        public Object getBXZCBM() {
            return BXZCBM;
        }

        public void setBXZCBM(Object BXZCBM) {
            this.BXZCBM = BXZCBM;
        }

        public Object getLSH() {
            return LSH;
        }

        public void setLSH(Object LSH) {
            this.LSH = LSH;
        }

        public Object getLL() {
            return LL;
        }

        public void setLL(Object LL) {
            this.LL = LL;
        }

        public Object getTQ() {
            return TQ;
        }

        public void setTQ(Object TQ) {
            this.TQ = TQ;
        }

        public Object getYDLB() {
            return YDLB;
        }

        public void setYDLB(Object YDLB) {
            this.YDLB = YDLB;
        }

        public Object getGDDYDY() {
            return GDDYDY;
        }

        public void setGDDYDY(Object GDDYDY) {
            this.GDDYDY = GDDYDY;
        }

        public Object getSBFL() {
            return SBFL;
        }

        public void setSBFL(Object SBFL) {
            this.SBFL = SBFL;
        }

        public Object getYWLX() {
            return YWLX;
        }

        public void setYWLX(Object YWLX) {
            this.YWLX = YWLX;
        }

        public Object getOrderId() {
            return OrderId;
        }

        public void setOrderId(Object OrderId) {
            this.OrderId = OrderId;
        }

        public Object getStockOutID() {
            return StockOutID;
        }

        public void setStockOutID(Object StockOutID) {
            this.StockOutID = StockOutID;
        }

        public String getWuliuAdr() {
            return WuliuAdr;
        }

        public void setWuliuAdr(String WuliuAdr) {
            this.WuliuAdr = WuliuAdr;
        }

        public String getYSndTime() {
            return YSndTime;
        }

        public void setYSndTime(String YSndTime) {
            this.YSndTime = YSndTime;
        }

        public String getCustomerName() {
            return CustomerName;
        }

        public void setCustomerName(String CustomerName) {
            this.CustomerName = CustomerName;
        }

        public int getRegOperatorId() {
            return RegOperatorId;
        }

        public void setRegOperatorId(int RegOperatorId) {
            this.RegOperatorId = RegOperatorId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getWarehouseName() {
            return WarehouseName;
        }

        public void setWarehouseName(String WarehouseName) {
            this.WarehouseName = WarehouseName;
        }

        public String getShopName() {
            return ShopName;
        }

        public void setShopName(String ShopName) {
            this.ShopName = ShopName;
        }
    }

    public static class TradeGoodsListBean {
        /**
         * SellCount : 302
         * SellPrice : 0
         * Unit : 米
         * GoodsID : 1702
         * GoodsName : B0315 红 3.5米
         * IndexURL : https://gd4.alicdn.com/imgextra/i2/2044741058/TB2PwwIebWJ.eBjSspdXXXiXFXa_!!2044741058.jpg_400x400.jpg
         * Price_Detail : 0
         * GoodsNo : B0315 红 3.5米
         * SpecName : B0315 红 3.5米
         * SpecID : 1901
         */

        private int SellCount;
        private int SellPrice;
        private String Unit;
        private int GoodsID;
        private String GoodsName;
        private String IndexURL;
        private int Price_Detail;
        private String GoodsNo;
        private String SpecName;
        private int SpecID;

        public int getSellCount() {
            return SellCount;
        }

        public void setSellCount(int SellCount) {
            this.SellCount = SellCount;
        }

        public int getSellPrice() {
            return SellPrice;
        }

        public void setSellPrice(int SellPrice) {
            this.SellPrice = SellPrice;
        }

        public String getUnit() {
            return Unit;
        }

        public void setUnit(String Unit) {
            this.Unit = Unit;
        }

        public int getGoodsID() {
            return GoodsID;
        }

        public void setGoodsID(int GoodsID) {
            this.GoodsID = GoodsID;
        }

        public String getGoodsName() {
            return GoodsName;
        }

        public void setGoodsName(String GoodsName) {
            this.GoodsName = GoodsName;
        }

        public String getIndexURL() {
            return IndexURL;
        }

        public void setIndexURL(String IndexURL) {
            this.IndexURL = IndexURL;
        }

        public int getPrice_Detail() {
            return Price_Detail;
        }

        public void setPrice_Detail(int Price_Detail) {
            this.Price_Detail = Price_Detail;
        }

        public String getGoodsNo() {
            return GoodsNo;
        }

        public void setGoodsNo(String GoodsNo) {
            this.GoodsNo = GoodsNo;
        }

        public String getSpecName() {
            return SpecName;
        }

        public void setSpecName(String SpecName) {
            this.SpecName = SpecName;
        }

        public int getSpecID() {
            return SpecID;
        }

        public void setSpecID(int SpecID) {
            this.SpecID = SpecID;
        }
    }
}
