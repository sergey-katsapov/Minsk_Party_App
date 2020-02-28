package katsapov.minskpartyappjava.data.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static katsapov.minskpartyappjava.presentation.utils.Constants.BASE_URL;

public class ApiClient {

    private static Retrofit retrofit = null;

    /**
     * Этот метод возвращает retrofit client instance
     *
     * @return Retrofit object
     */
    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}