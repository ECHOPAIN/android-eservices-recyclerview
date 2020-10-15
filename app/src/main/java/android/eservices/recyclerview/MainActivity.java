package android.eservices.recyclerview;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements GameActionInterface{

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Toolbar toolbar;
    private CoordinatorLayout coordinatorLayout;

    private GameAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        coordinatorLayout = findViewById(R.id.coordinator_layout);
        setupRecyclerView();

    }

    private void setupRecyclerView() {
        //Bind recyclerview and set its adapter.
        recyclerView = findViewById(R.id.my_recyclerview);


        //Linear Layout
        //layoutManager = new LinearLayoutManager(this);

        //Grid layout
        layoutManager = new GridLayoutManager(this,2);


        recyclerView.setLayoutManager(layoutManager);
        adapter = new GameAdapter(this);

        //Use data generator to get data to display.
        adapter.bindGameViewModelList(DataGenerator.generateData());
        recyclerView.setAdapter(adapter);

    }

    public void displaySnackBar(String message) {
        //write a method that displays a snackbar in the coordinator layout with the "message" parameter as content.

        // The view used to make the snackbar.
        // This should be contained within the view hierarchy you want to display the
        // snackbar. Generally it can be the view that was interacted with to trigger
        // the snackbar, such as a button that was clicked, or a card that was swiped.
        View contextView = findViewById(R.id.coordinator_layout);;
        Snackbar.make(contextView, message, Snackbar.LENGTH_SHORT)
                .show();
    }


    //create callback methods for item click
    //Use ressource strings to get the text to display
    @Override
    public void onGameInfoClicked(String gameTitle) {
        //Log.e("MainActivity","On game info clicked " + gameTitle);
        displaySnackBar(getString(R.string.game_info_clicked,gameTitle));
    }

    @Override
    public void onGameClicked(String gameTitle) {
        //Log.e("MainActivity","On game clicked " + gameTitle);
        displaySnackBar(getString(R.string.game_clicked,gameTitle));
    }

}
