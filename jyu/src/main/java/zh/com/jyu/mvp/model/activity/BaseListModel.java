package zh.com.jyu.mvp.model.activity;

import java.util.List;
import java.util.Map;

import core.app.zh.com.core.base.BaseModel;
import core.app.zh.com.core.bean.MyObservable;
import zh.com.jyu.bean.other.Data;

/**
 * author : dayezi
 * data :2019/6/14
 * description:
 */
public abstract class BaseListModel<T, K> implements BaseModel<K> {

    public abstract void requestData(Map<String, Object> params, MyObservable.OnSuccessListener<Data<List<T>>> listener);


}
