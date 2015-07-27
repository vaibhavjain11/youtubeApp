package retrofit.mygaadi.lms;

import retrofit.RetrofitError;
import retrofit.client.Response;

public interface RestCallback<T> {

    public void onFailure(RetrofitError e);
    public void onSuccess(T model, Response response);

}