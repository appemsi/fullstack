package ma.emsi.contact_2;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterfacee {


    @GET("contacts")
    Call<ArrayList<Contact>> getContacts();
}
