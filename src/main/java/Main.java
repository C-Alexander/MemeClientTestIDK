import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jasypt.util.text.BasicTextEncryptor;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        BasicTextEncryptor decryptor = new BasicTextEncryptor();
        decryptor.setPassword("SE42");

        Request request = new Request.Builder()
                .url(HttpUrl.parse("http://localhost:9000/memes/2"))
                .build();
        OkHttpClient client = new OkHttpClient();
        try {
            Response response = client.newCall(request).execute();
            Gson gson = new Gson();

            JsonObject obj = gson.fromJson(response.body().string(), JsonObject.class);
            System.out.println(obj.toString());
            System.out.println("Decrypting...");
            System.out.println(decryptor.decrypt(obj.get("url").getAsString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
