package retrofit.mygaadi.lms;

import com.example.vaibhav.application.ApplicationController;

import java.util.HashMap;

import retrofit.Callback;
import retrofit.client.Response;

/**
 * Created by vaibhav on 13/7/2015.
 */
public class RetrofitRequest {

    private static RestService restService = ApplicationController.getRestAdapter().create(RestService.class);

   public static void getVideoList(HashMap<String,String> map, Callback<Response> callback){
       restService.getVideoList(map,callback);
   }
}
