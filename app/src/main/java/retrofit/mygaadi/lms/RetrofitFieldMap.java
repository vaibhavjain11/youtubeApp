package retrofit.mygaadi.lms;


import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vaibhav on 13/7/15.
 */
public class RetrofitFieldMap {

    private HashMap<String, String> mParams;
    private JSONObject jsonObject;

    public RetrofitFieldMap(
                            HashMap<String, String> params) {
        if (params == null) {
            mParams = new HashMap<>();
        } else {
            mParams = params;
        }

    }

    public RetrofitFieldMap(JSONObject jsonObject) {
       // mContext = context;

        if (jsonObject == null) {
            this.jsonObject = new JSONObject();
        } else {
            this.jsonObject = jsonObject;
        }
    }


    public Map<String, String> getParams() {
       /* mParams.put(GlobalVariables.KEY_USERNAME, GlobalVariables.VAL_USERNAME);
        mParams.put(GlobalVariables.KEY_PASSWORD, GlobalVariables.VAL_PASSWORD);*/
        return mParams;
    }

}
