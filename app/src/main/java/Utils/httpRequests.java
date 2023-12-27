package Utils;

import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.textclassifier.TextLinks;

import androidx.annotation.NonNull;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

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

    public httpRequests(String nameAudio) {
        client = new OkHttpClient.Builder()
                .callTimeout(3, TimeUnit.HOURS)
                .build();
        nameFile = nameAudio + ".pdf";
    }

    String postURL = Util.SERVER_PUBLIC_URL +"/";
    String nameFile;
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
                Log.d("POST", "was successful");
                GETRequest();
            }
        });

    }

    public void GETRequest() {
        Request request = new Request.Builder().addHeader("key", nameFile).url(Util.SERVER_PUBLIC_URL).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                InputStream inputStream = response.body().byteStream();
                File localFile = new File(Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), nameFile);
                FileOutputStream outputStream = new FileOutputStream(localFile);
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                outputStream.flush();
                outputStream.close();
                inputStream.close();

                StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("conspects/" + nameFile);
                storageReference.putFile(Uri.fromFile(localFile));
            }
        });
    }
}