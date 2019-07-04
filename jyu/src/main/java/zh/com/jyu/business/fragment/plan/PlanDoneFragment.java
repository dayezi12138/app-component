package zh.com.jyu.business.fragment.plan;

import java.util.HashMap;
import java.util.Map;

/**
 * author : dayezi
 * data :2019/6/13
 * description:
 */
public class PlanDoneFragment extends PlanWaitFragment {

    private Map<String, Object> paramsMap = new HashMap<>();

    @Override
    public Map<String, Object> params() {
        paramsMap.put("keywords", clearEditTextView.getText().toString());
        paramsMap.put("status", 2);
        return paramsMap;
    }
}
