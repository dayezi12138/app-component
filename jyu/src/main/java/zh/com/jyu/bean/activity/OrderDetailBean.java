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
    private String GoodsCount;
    private List<TradeGoodsListBean> Trade_GoodsList;

    public TradeTradeListBean getTrade_TradeList() {
        return Trade_TradeList;
    }

    public void setTrade_TradeList(TradeTradeListBean Trade_TradeList) {
        this.Trade_TradeList = Trade_TradeList;
    }

    public String getGoodsCount() {
        return GoodsCount;
    }

    public void setGoodsCount(String goodsCount) {
        GoodsCount = goodsCount;
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
        private String TradeStatus;
        private String TradeStatusExt;
        private String TradeNO;
        private String ShopID;
        private String SellerId;
        private String Seller;
        private String TradeType;
        private String CustomerID;
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
        private String PrintSndBill;
        private String PrintExpress;
        private String Picker;
        private String ChkOperator;
        private String ChkTime;
        private String PackageOperator;
        private String SndTime;
        private String SndOperator;
        private String GoodsWeight;
        private String PackagedWeight;
        private String CustomerRemark;
        private String Remark;
        private String LogisticID;
        private String PackageID;
        private String Postage;
        private String PostID;
        private String ChargeType;
        private String ChargeID;
        private String AccountID;
        private String OtherCost;
        private String GoodsTotal;
        private String PostageTotal;
        private String TaxValue;
        private String CouponValue;
        private String FavourableTotal;
        private String AllTotal;
        private String RcvTotal;
        private String CurrencyType;
        private String CurrencyRate;
        private String GoodsCost;
        private String PackageCost;
        private String TotalProfit;
        private String PriceSpec;
        private String PriceDis;
        private String GoodsList;
        private String TradeFlagID;
        private String FlagName;
        private String DrawbackValue;
        private String OtherGoods;
        private String CouponID;
        private String CommissionValue;
        private String WarehouseID;
        private String PrintCollect;
        private String PrintPos;
        private String PrintChannel;
        private String PreDate;
        private String PreType;
        private String bStockOut;
        private String bManualCHK;
        private String AppendRemark;
        private String bInvoice;
        private String PrintInvoice;
        private String estimateWeight;
        private String InvoiceTitle;
        private String RMB_Value;
        private String ProviderID;
        private String ChargeOperator;
        private String FreezeReason;
        private String CancelReason;
        private boolean bSellBack;
        private String TradeFrom;
        private String TradeNO2;
        private String bSndSys;
        private String PostageFee2;
        private String Reserved1;
        private String Reserved2;
        private String Reserved3;
        private String Reserved4;
        private String PrintCHK;
        private String FXTid;
        private String HH;
        private String HuHao;
        private String BXZCBM;
        private String LSH;
        private String LL;
        private String TQ;
        private String YDLB;
        private String GDDYDY;
        private String SBFL;
        private String YWLX;
        private String OrderId;
        private String StockOutID;
        private String WuliuAdr;
        private String YSndTime;
        private String CustomerName;
        private String RegOperatorId;
        private String status;
        private String WarehouseName;
        private String ShopName;

        public int getTradeID() {
            return TradeID;
        }

        public void setTradeID(int tradeID) {
            TradeID = tradeID;
        }

        public String getRegTime() {
            return RegTime;
        }

        public void setRegTime(String regTime) {
            RegTime = regTime;
        }

        public String getTradeTime() {
            return TradeTime;
        }

        public void setTradeTime(String tradeTime) {
            TradeTime = tradeTime;
        }

        public String getDays() {
            return Days;
        }

        public void setDays(String days) {
            Days = days;
        }

        public String getTradeStatus() {
            return TradeStatus;
        }

        public void setTradeStatus(String tradeStatus) {
            TradeStatus = tradeStatus;
        }

        public String getTradeStatusExt() {
            return TradeStatusExt;
        }

        public void setTradeStatusExt(String tradeStatusExt) {
            TradeStatusExt = tradeStatusExt;
        }

        public String getTradeNO() {
            return TradeNO;
        }

        public void setTradeNO(String tradeNO) {
            TradeNO = tradeNO;
        }

        public String getShopID() {
            return ShopID;
        }

        public void setShopID(String shopID) {
            ShopID = shopID;
        }

        public String getSellerId() {
            return SellerId;
        }

        public void setSellerId(String sellerId) {
            SellerId = sellerId;
        }

        public String getSeller() {
            return Seller;
        }

        public void setSeller(String seller) {
            Seller = seller;
        }

        public String getTradeType() {
            return TradeType;
        }

        public void setTradeType(String tradeType) {
            TradeType = tradeType;
        }

        public String getCustomerID() {
            return CustomerID;
        }

        public void setCustomerID(String customerID) {
            CustomerID = customerID;
        }

        public String getCustomerLevel() {
            return CustomerLevel;
        }

        public void setCustomerLevel(String customerLevel) {
            CustomerLevel = customerLevel;
        }

        public String getCustomerType() {
            return CustomerType;
        }

        public void setCustomerType(String customerType) {
            CustomerType = customerType;
        }

        public String getSndTo() {
            return SndTo;
        }

        public void setSndTo(String sndTo) {
            SndTo = sndTo;
        }

        public String getCountry() {
            return Country;
        }

        public void setCountry(String country) {
            Country = country;
        }

        public String getProvince() {
            return Province;
        }

        public void setProvince(String province) {
            Province = province;
        }

        public String getCity() {
            return City;
        }

        public void setCity(String city) {
            City = city;
        }

        public String getTown() {
            return Town;
        }

        public void setTown(String town) {
            Town = town;
        }

        public String getAdr() {
            return Adr;
        }

        public void setAdr(String adr) {
            Adr = adr;
        }

        public String getZip() {
            return Zip;
        }

        public void setZip(String zip) {
            Zip = zip;
        }

        public String getTel() {
            return Tel;
        }

        public void setTel(String tel) {
            Tel = tel;
        }

        public String getRegOperator() {
            return RegOperator;
        }

        public void setRegOperator(String regOperator) {
            RegOperator = regOperator;
        }

        public String getConfirmTime() {
            return ConfirmTime;
        }

        public void setConfirmTime(String confirmTime) {
            ConfirmTime = confirmTime;
        }

        public String getConfirmOperator() {
            return ConfirmOperator;
        }

        public void setConfirmOperator(String confirmOperator) {
            ConfirmOperator = confirmOperator;
        }

        public String getPrintSndBill() {
            return PrintSndBill;
        }

        public void setPrintSndBill(String printSndBill) {
            PrintSndBill = printSndBill;
        }

        public String getPrintExpress() {
            return PrintExpress;
        }

        public void setPrintExpress(String printExpress) {
            PrintExpress = printExpress;
        }

        public String getPicker() {
            return Picker;
        }

        public void setPicker(String picker) {
            Picker = picker;
        }

        public String getChkOperator() {
            return ChkOperator;
        }

        public void setChkOperator(String chkOperator) {
            ChkOperator = chkOperator;
        }

        public String getChkTime() {
            return ChkTime;
        }

        public void setChkTime(String chkTime) {
            ChkTime = chkTime;
        }

        public String getPackageOperator() {
            return PackageOperator;
        }

        public void setPackageOperator(String packageOperator) {
            PackageOperator = packageOperator;
        }

        public String getSndTime() {
            return SndTime;
        }

        public void setSndTime(String sndTime) {
            SndTime = sndTime;
        }

        public String getSndOperator() {
            return SndOperator;
        }

        public void setSndOperator(String sndOperator) {
            SndOperator = sndOperator;
        }

        public String getGoodsWeight() {
            return GoodsWeight;
        }

        public void setGoodsWeight(String goodsWeight) {
            GoodsWeight = goodsWeight;
        }

        public String getPackagedWeight() {
            return PackagedWeight;
        }

        public void setPackagedWeight(String packagedWeight) {
            PackagedWeight = packagedWeight;
        }

        public String getCustomerRemark() {
            return CustomerRemark;
        }

        public void setCustomerRemark(String customerRemark) {
            CustomerRemark = customerRemark;
        }

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String remark) {
            Remark = remark;
        }

        public String getLogisticID() {
            return LogisticID;
        }

        public void setLogisticID(String logisticID) {
            LogisticID = logisticID;
        }

        public String getPackageID() {
            return PackageID;
        }

        public void setPackageID(String packageID) {
            PackageID = packageID;
        }

        public String getPostage() {
            return Postage;
        }

        public void setPostage(String postage) {
            Postage = postage;
        }

        public String getPostID() {
            return PostID;
        }

        public void setPostID(String postID) {
            PostID = postID;
        }

        public String getChargeType() {
            return ChargeType;
        }

        public void setChargeType(String chargeType) {
            ChargeType = chargeType;
        }

        public String getChargeID() {
            return ChargeID;
        }

        public void setChargeID(String chargeID) {
            ChargeID = chargeID;
        }

        public String getAccountID() {
            return AccountID;
        }

        public void setAccountID(String accountID) {
            AccountID = accountID;
        }

        public String getOtherCost() {
            return OtherCost;
        }

        public void setOtherCost(String otherCost) {
            OtherCost = otherCost;
        }

        public String getGoodsTotal() {
            return GoodsTotal;
        }

        public void setGoodsTotal(String goodsTotal) {
            GoodsTotal = goodsTotal;
        }

        public String getPostageTotal() {
            return PostageTotal;
        }

        public void setPostageTotal(String postageTotal) {
            PostageTotal = postageTotal;
        }

        public String getTaxValue() {
            return TaxValue;
        }

        public void setTaxValue(String taxValue) {
            TaxValue = taxValue;
        }

        public String getCouponValue() {
            return CouponValue;
        }

        public void setCouponValue(String couponValue) {
            CouponValue = couponValue;
        }

        public String getFavourableTotal() {
            return FavourableTotal;
        }

        public void setFavourableTotal(String favourableTotal) {
            FavourableTotal = favourableTotal;
        }

        public String getAllTotal() {
            return AllTotal;
        }

        public void setAllTotal(String allTotal) {
            AllTotal = allTotal;
        }

        public String getRcvTotal() {
            return RcvTotal;
        }

        public void setRcvTotal(String rcvTotal) {
            RcvTotal = rcvTotal;
        }

        public String getCurrencyType() {
            return CurrencyType;
        }

        public void setCurrencyType(String currencyType) {
            CurrencyType = currencyType;
        }

        public String getCurrencyRate() {
            return CurrencyRate;
        }

        public void setCurrencyRate(String currencyRate) {
            CurrencyRate = currencyRate;
        }

        public String getGoodsCost() {
            return GoodsCost;
        }

        public void setGoodsCost(String goodsCost) {
            GoodsCost = goodsCost;
        }

        public String getPackageCost() {
            return PackageCost;
        }

        public void setPackageCost(String packageCost) {
            PackageCost = packageCost;
        }

        public String getTotalProfit() {
            return TotalProfit;
        }

        public void setTotalProfit(String totalProfit) {
            TotalProfit = totalProfit;
        }

        public String getPriceSpec() {
            return PriceSpec;
        }

        public void setPriceSpec(String priceSpec) {
            PriceSpec = priceSpec;
        }

        public String getPriceDis() {
            return PriceDis;
        }

        public void setPriceDis(String priceDis) {
            PriceDis = priceDis;
        }

        public String getGoodsList() {
            return GoodsList;
        }

        public void setGoodsList(String goodsList) {
            GoodsList = goodsList;
        }

        public String getTradeFlagID() {
            return TradeFlagID;
        }

        public void setTradeFlagID(String tradeFlagID) {
            TradeFlagID = tradeFlagID;
        }

        public String getFlagName() {
            return FlagName;
        }

        public void setFlagName(String flagName) {
            FlagName = flagName;
        }

        public String getDrawbackValue() {
            return DrawbackValue;
        }

        public void setDrawbackValue(String drawbackValue) {
            DrawbackValue = drawbackValue;
        }

        public String getOtherGoods() {
            return OtherGoods;
        }

        public void setOtherGoods(String otherGoods) {
            OtherGoods = otherGoods;
        }

        public String getCouponID() {
            return CouponID;
        }

        public void setCouponID(String couponID) {
            CouponID = couponID;
        }

        public String getCommissionValue() {
            return CommissionValue;
        }

        public void setCommissionValue(String commissionValue) {
            CommissionValue = commissionValue;
        }

        public String getWarehouseID() {
            return WarehouseID;
        }

        public void setWarehouseID(String warehouseID) {
            WarehouseID = warehouseID;
        }

        public String getPrintCollect() {
            return PrintCollect;
        }

        public void setPrintCollect(String printCollect) {
            PrintCollect = printCollect;
        }

        public String getPrintPos() {
            return PrintPos;
        }

        public void setPrintPos(String printPos) {
            PrintPos = printPos;
        }

        public String getPrintChannel() {
            return PrintChannel;
        }

        public void setPrintChannel(String printChannel) {
            PrintChannel = printChannel;
        }

        public String getPreDate() {
            return PreDate;
        }

        public void setPreDate(String preDate) {
            PreDate = preDate;
        }

        public String getPreType() {
            return PreType;
        }

        public void setPreType(String preType) {
            PreType = preType;
        }

        public String getbStockOut() {
            return bStockOut;
        }

        public void setbStockOut(String bStockOut) {
            this.bStockOut = bStockOut;
        }

        public String getbManualCHK() {
            return bManualCHK;
        }

        public void setbManualCHK(String bManualCHK) {
            this.bManualCHK = bManualCHK;
        }

        public String getAppendRemark() {
            return AppendRemark;
        }

        public void setAppendRemark(String appendRemark) {
            AppendRemark = appendRemark;
        }

        public String getbInvoice() {
            return bInvoice;
        }

        public void setbInvoice(String bInvoice) {
            this.bInvoice = bInvoice;
        }

        public String getPrintInvoice() {
            return PrintInvoice;
        }

        public void setPrintInvoice(String printInvoice) {
            PrintInvoice = printInvoice;
        }

        public String getEstimateWeight() {
            return estimateWeight;
        }

        public void setEstimateWeight(String estimateWeight) {
            this.estimateWeight = estimateWeight;
        }

        public String getInvoiceTitle() {
            return InvoiceTitle;
        }

        public void setInvoiceTitle(String invoiceTitle) {
            InvoiceTitle = invoiceTitle;
        }

        public String getRMB_Value() {
            return RMB_Value;
        }

        public void setRMB_Value(String RMB_Value) {
            this.RMB_Value = RMB_Value;
        }

        public String getProviderID() {
            return ProviderID;
        }

        public void setProviderID(String providerID) {
            ProviderID = providerID;
        }

        public String getChargeOperator() {
            return ChargeOperator;
        }

        public void setChargeOperator(String chargeOperator) {
            ChargeOperator = chargeOperator;
        }

        public String getFreezeReason() {
            return FreezeReason;
        }

        public void setFreezeReason(String freezeReason) {
            FreezeReason = freezeReason;
        }

        public String getCancelReason() {
            return CancelReason;
        }

        public void setCancelReason(String cancelReason) {
            CancelReason = cancelReason;
        }

        public boolean isbSellBack() {
            return bSellBack;
        }

        public void setbSellBack(boolean bSellBack) {
            this.bSellBack = bSellBack;
        }

        public String getTradeFrom() {
            return TradeFrom;
        }

        public void setTradeFrom(String tradeFrom) {
            TradeFrom = tradeFrom;
        }

        public String getTradeNO2() {
            return TradeNO2;
        }

        public void setTradeNO2(String tradeNO2) {
            TradeNO2 = tradeNO2;
        }

        public String getbSndSys() {
            return bSndSys;
        }

        public void setbSndSys(String bSndSys) {
            this.bSndSys = bSndSys;
        }

        public String getPostageFee2() {
            return PostageFee2;
        }

        public void setPostageFee2(String postageFee2) {
            PostageFee2 = postageFee2;
        }

        public String getReserved1() {
            return Reserved1;
        }

        public void setReserved1(String reserved1) {
            Reserved1 = reserved1;
        }

        public String getReserved2() {
            return Reserved2;
        }

        public void setReserved2(String reserved2) {
            Reserved2 = reserved2;
        }

        public String getReserved3() {
            return Reserved3;
        }

        public void setReserved3(String reserved3) {
            Reserved3 = reserved3;
        }

        public String getReserved4() {
            return Reserved4;
        }

        public void setReserved4(String reserved4) {
            Reserved4 = reserved4;
        }

        public String getPrintCHK() {
            return PrintCHK;
        }

        public void setPrintCHK(String printCHK) {
            PrintCHK = printCHK;
        }

        public String getFXTid() {
            return FXTid;
        }

        public void setFXTid(String FXTid) {
            this.FXTid = FXTid;
        }

        public String getHH() {
            return HH;
        }

        public void setHH(String HH) {
            this.HH = HH;
        }

        public String getHuHao() {
            return HuHao;
        }

        public void setHuHao(String huHao) {
            HuHao = huHao;
        }

        public String getBXZCBM() {
            return BXZCBM;
        }

        public void setBXZCBM(String BXZCBM) {
            this.BXZCBM = BXZCBM;
        }

        public String getLSH() {
            return LSH;
        }

        public void setLSH(String LSH) {
            this.LSH = LSH;
        }

        public String getLL() {
            return LL;
        }

        public void setLL(String LL) {
            this.LL = LL;
        }

        public String getTQ() {
            return TQ;
        }

        public void setTQ(String TQ) {
            this.TQ = TQ;
        }

        public String getYDLB() {
            return YDLB;
        }

        public void setYDLB(String YDLB) {
            this.YDLB = YDLB;
        }

        public String getGDDYDY() {
            return GDDYDY;
        }

        public void setGDDYDY(String GDDYDY) {
            this.GDDYDY = GDDYDY;
        }

        public String getSBFL() {
            return SBFL;
        }

        public void setSBFL(String SBFL) {
            this.SBFL = SBFL;
        }

        public String getYWLX() {
            return YWLX;
        }

        public void setYWLX(String YWLX) {
            this.YWLX = YWLX;
        }

        public String getOrderId() {
            return OrderId;
        }

        public void setOrderId(String orderId) {
            OrderId = orderId;
        }

        public String getStockOutID() {
            return StockOutID;
        }

        public void setStockOutID(String stockOutID) {
            StockOutID = stockOutID;
        }

        public String getWuliuAdr() {
            return WuliuAdr;
        }

        public void setWuliuAdr(String wuliuAdr) {
            WuliuAdr = wuliuAdr;
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

        public void setCustomerName(String customerName) {
            CustomerName = customerName;
        }

        public String getRegOperatorId() {
            return RegOperatorId;
        }

        public void setRegOperatorId(String regOperatorId) {
            RegOperatorId = regOperatorId;
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

        public void setWarehouseName(String warehouseName) {
            WarehouseName = warehouseName;
        }

        public String getShopName() {
            return ShopName;
        }

        public void setShopName(String shopName) {
            ShopName = shopName;
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

        private String SellCount;
        private String SellPrice;
        private String Unit;
        private String GoodsID;
        private String GoodsName;
        private String IndexURL;
        private String Price_Detail;
        private String GoodsNo;
        private String SpecName;
        private String SpecID;

        public String getSellCount() {
            return SellCount;
        }

        public void setSellCount(String sellCount) {
            SellCount = sellCount;
        }

        public String getSellPrice() {
            return SellPrice;
        }

        public void setSellPrice(String sellPrice) {
            SellPrice = sellPrice;
        }

        public String getUnit() {
            return Unit;
        }

        public void setUnit(String unit) {
            Unit = unit;
        }

        public String getGoodsID() {
            return GoodsID;
        }

        public void setGoodsID(String goodsID) {
            GoodsID = goodsID;
        }

        public String getGoodsName() {
            return GoodsName;
        }

        public void setGoodsName(String goodsName) {
            GoodsName = goodsName;
        }

        public String getIndexURL() {
            return IndexURL;
        }

        public void setIndexURL(String indexURL) {
            IndexURL = indexURL;
        }

        public String getPrice_Detail() {
            return Price_Detail;
        }

        public void setPrice_Detail(String price_Detail) {
            Price_Detail = price_Detail;
        }

        public String getGoodsNo() {
            return GoodsNo;
        }

        public void setGoodsNo(String goodsNo) {
            GoodsNo = goodsNo;
        }

        public String getSpecName() {
            return SpecName;
        }

        public void setSpecName(String specName) {
            SpecName = specName;
        }

        public String getSpecID() {
            return SpecID;
        }

        public void setSpecID(String specID) {
            SpecID = specID;
        }
    }
}
