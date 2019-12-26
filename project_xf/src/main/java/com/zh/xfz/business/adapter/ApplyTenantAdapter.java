package com.zh.xfz.business.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.zh.xfz.R;
import com.zh.xfz.bean.fragment.ApplyTenant;

import java.util.ArrayList;

import javax.inject.Inject;

import core.app.zh.com.core.base.MyBaseAdapter;

/**
 * author : dayezi
 * data :2019/12/24
 * description:
 */
public class ApplyTenantAdapter extends MyBaseAdapter<ApplyTenant> {

    @Inject
    public ApplyTenantAdapter() {
        super(R.layout.item_apply_tenant, new ArrayList<>());
    }

    @Override
    protected void convert(BaseViewHolder helper, ApplyTenant item) {
        helper.setText(R.id.name_tv, item.getChineseName());
        TextView statusTv = helper.getView(R.id.status_tv);
        for (ApplyTenantType tenantType : ApplyTenantType.values()) {
            statusTv.setText(mContext.getResources().getString(tenantType.getName()));
            statusTv.setTextColor(mContext.getResources().getColor(tenantType.getColorId()));
        }
    }

    public enum ApplyTenantType {
        WAITING(R.string.item_status_wait_at_apply_tenant_str, 0, R.color.color_cursor),
        APPLY(R.string.item_status_apply_at_apply_tenant_str, 1, R.color.bg_green_btn),
        REFUSE(R.string.item_status_refuse_at_apply_tenant_str, 2, R.color.d);
        private int name;
        private int status;
        private int colorId;

        ApplyTenantType(int name, int status, int colorId) {
            this.name = name;
            this.status = status;
            this.colorId = colorId;
        }

        public int getName() {
            return name;
        }

        public int getStatus() {
            return status;
        }

        public int getColorId() {
            return colorId;
        }
    }
}
