package retrofit.mygaadi.lms;

import android.content.Context;
import android.view.View;

import com.example.vaibhav.Utility.ProgressHUD;
import com.example.vaibhav.Utility.RestLog;
import com.example.vaibhav.Utility.Util;
import com.google.gson.GsonBuilder;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MyCallback<T> implements Callback<T>
{
private RestCallback restCallback;
private static String defaultMessage = "Loading";
private ProgressHUD mProgressHUD;
private View v;
private Context context;
private Util utilInstance;

/**
 * Description : Callback with custom message
 */
@SuppressWarnings("unchecked")
public MyCallback(Context context, RestCallback restCallback, boolean showProgress, View v, String message)
{
    this.restCallback = restCallback;
    this.context = context;
    utilInstance = Util.getInstance(context);

    if(message != null)
        defaultMessage = message;
    if(v != null) {
        this.v = v;
        v.setClickable(true);
    }
    if (showProgress)
    {
        StartProgress(defaultMessage);
    }
}

@Override
public void failure(RetrofitError error)
{
    StopProgress();
    if (error.getKind() == RetrofitError.Kind.NETWORK) {

    }
    if (restCallback != null)
        restCallback.onFailure(error);
}

@Override
public void success(T model, Response arg1)
{
    StopProgress();
    ShowLog(model, arg1);
    if (restCallback != null)
        restCallback.onSuccess(model, arg1);
}

private void ShowLog(T model, Response arg1)
{
    String body = Util.getInstance(context).getResponse(arg1);
    RestLog.l(this, "URL==> " + arg1.getUrl());
    RestLog.l(this, (body.equalsIgnoreCase("null")) ? "Model==> " + new GsonBuilder().setPrettyPrinting().create().toJson(model)
            : "Body==> " + body);
}

public void StartProgress(String message)
{
    mProgressHUD = ProgressHUD.show(context, defaultMessage, true, false, null);
}

public  void StopProgress()
{
    if (mProgressHUD != null && mProgressHUD.isShowing())
    {
        mProgressHUD.dismiss();
    }
}

}