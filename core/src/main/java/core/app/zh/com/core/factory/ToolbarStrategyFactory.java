package core.app.zh.com.core.factory;

import android.support.v7.widget.Toolbar;

import java.util.HashMap;
import java.util.Map;

import core.app.zh.com.core.base.BaseActivity;
import core.app.zh.com.core.bean.i.IPunish;

/**
 * @auther create by Administrator
 * DATE:2019/3/26 0026 13
 */
public  class ToolbarStrategyFactory{
    private static Map<String,IPunish> punishMap = new HashMap<>();
    private static final IPunish EMPTY = new EmptyPunish();
    //获取
    public static IPunish getPunish(String key) {
        IPunish result = punishMap.get(key);
        return result == null ? EMPTY : result;
    }

    public static  void put(String key,IPunish punish){
        punishMap.put(key, punish);
    }


    private static class EmptyPunish implements IPunish {

        @Override
        public void punish(BaseActivity activity, Toolbar toolbar, IPunish bean) {

        }
    }
}
