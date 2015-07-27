package retrofit.mygaadi.lms;


import com.example.vaibhav.Utility.Constants;

import java.util.Map;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.QueryMap;

public interface RestService
{	
	/*@GET()
    void getCityList(MyCallback<Response> callback);
*/

    @GET(Constants.VIDEO_URL)
   // void getVideoList(@FieldMap Map<String, String> map, Callback<Response> callback);
    void getVideoList(@QueryMap Map<String,String> map,Callback<Response> callback);

}
