package com.example.louisompusunggu.demospinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Person> persons;
    private RVAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // This method creates an ArrayList that has three Person objects
        // Checkout the project associated with this tutorial on Github if
        // you want to use the same images.
        persons = new ArrayList<>();

        initializeData();

        adapter = new RVAdapter(persons);
        mRecyclerView.setAdapter(adapter);
    }

    private void initializeData(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("url")
                .build();

        OutboundService outboundService = retrofit.create(OutboundService.class);
        Call<ResponseBody> call = outboundService.listAccount("f5c3792e-9609-42df-bf90-454fae446e4a");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody responseBody = response.body();
                try{
                    JSONObject jsonObject = new JSONObject(responseBody.string());
                    JSONArray resultDataJson = jsonObject.getJSONArray("resultData");
                    setAccountData(resultDataJson);
                }catch(Exception e){
                    Log.e("CATCH",e.getLocalizedMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("FAIL","FAIL");
            }
        });
    }

    public void setAccountData(JSONArray jsonArray){
        try{
            for(int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject objects = jsonArray.getJSONObject(i);
                persons.add(new Person(objects.getString("type"), objects.getString("balance"), R.drawable.emma));
            }
        }catch(Exception e){
            Log.e("SET_ACCOUNT_DATA",e.getLocalizedMessage());
        }
        adapter.notifyDataSetChanged();
    }

}

