package appkite.jordiguzman.com.astronomyapp.iss.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import appkite.jordiguzman.com.astronomyapp.R;
import appkite.jordiguzman.com.astronomyapp.iss.adapter.AdapterAstronaut;
import appkite.jordiguzman.com.astronomyapp.iss.model.Astronaut;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AstronautsActivity extends AppCompatActivity {

    //String url= "http://www.howmanypeopleareinspacerightnow.com/peopleinspace.json";
    @BindView(R.id.rv_iss)
    RecyclerView rv_iss_astronaut;


    public static ArrayList<Astronaut> spaceAstronauts = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iss);
        ButterKnife.bind(this);


        populateRecyclerview();


    }

    public void populateRecyclerview() {
        AdapterAstronaut adapterAstronaut = new AdapterAstronaut(spaceAstronauts, this);
        rv_iss_astronaut.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        rv_iss_astronaut.setHasFixedSize(true);
        rv_iss_astronaut.setAdapter(adapterAstronaut);
    }







}
