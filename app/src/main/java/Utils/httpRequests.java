package Utils;

import android.util.Log;
import android.view.textclassifier.TextLinks;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class httpRequests {

    OkHttpClient client;

    public httpRequests() {
        client = new OkHttpClient();
    }

    String postURL = Util.SERVER_PUBLIC_URL +"/";
    public void POSTRequest(File localFile) {
        RequestBody requestBody =
        new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("file", localFile.getName(),
                        RequestBody.create(localFile, MediaType.parse("audio/mp3"))).build();
        Request request = new Request.Builder().url(postURL).post(requestBody).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Log.d("df", "df");
            }
        });

    }
}
