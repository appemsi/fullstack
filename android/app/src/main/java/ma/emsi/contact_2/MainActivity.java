package ma.emsi.contact_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Contact> contacts;
    ContactAdapter adapter;
    String phoneNumberToCall;
    private static final int CALL_PHONE_REQUEST_CODE = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData()
    {

        ApiInterfacee apiInterfacee=ApiClient.getApiClient().create(ApiInterfacee.class);
        Call<ArrayList<Contact>> call=apiInterfacee.getContacts();
        call.enqueue(new Callback<ArrayList<Contact>>() {
            @Override
            public void onResponse(Call<ArrayList<Contact>> call, Response<ArrayList<Contact>> response) {
                contacts=response.body();
                adapter=new ContactAdapter(MainActivity.this,contacts);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Contact>> call, Throwable t) {

            }
        });

    }

    public  void addContact(View v)
    {
        Intent i=new Intent(this,AddEditContact.class);
        startActivity(i);
    }

    public void makeCall(String phone)
    {
        // Check if the CALL_PHONE permission is not granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // Request the permission
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, CALL_PHONE_REQUEST_CODE);
            // Store the phone number for later use
            phoneNumberToCall = phone;
        } else {
            // Permission has been granted, proceed with making the call
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + phone));
            startActivity(callIntent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CALL_PHONE_REQUEST_CODE) {
            // Check if the permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, proceed with making the call using the stored phone number
                if (phoneNumberToCall != null) {
                    makeCall(phoneNumberToCall);
                    phoneNumberToCall = null; // Reset the stored phone number
                }
            } else {
                // Permission denied, show a message or handle accordingly
                Toast.makeText(this, "Permission denied to make phone calls", Toast.LENGTH_SHORT).show();
            }
        }
    }
}