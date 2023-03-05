package telegram.bot.nure.botphonenumberprodject.config;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import telegram.bot.nure.botphonenumberprodject.client.HttpClient;
import telegram.bot.nure.botphonenumberprodject.client.HttpClientImpl;

@Configuration
public class ClintConfig {

    @Bean
    HttpClient httpClientImpl(){
        return new HttpClientImpl(new Gson(),new OkHttpClient());
    }
}
