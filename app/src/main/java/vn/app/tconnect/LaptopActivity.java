package vn.app.tconnect;

import static vn.app.tconnect.MainActivity.redirectActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import vn.app.tconnect.adapter.LaptopAdapter;
import vn.app.tconnect.models.LaptopModel;

public class LaptopActivity extends AppCompatActivity {
    private List<LaptopModel> laptopModelList;
    private LaptopAdapter laptopAdapter;
    private FirebaseFirestore db;
    RecyclerView lapTopRec;
    ImageView home,noti,favorite,cart,footerFavo;
    TextView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laptop);
        db =FirebaseFirestore.getInstance();
        home= findViewById(R.id.footer_home);
        noti = findViewById(R.id.footer_notification);
        favorite = findViewById(R.id.footer_favorite);
        cart= findViewById(R.id.footer_cart);
        lapTopRec=findViewById(R.id.laptop_Rec);
        back=findViewById(R.id.backbutton);
        footerFavo= findViewById(R.id.footer_favorite);

        footerFavo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LaptopActivity.this,FavoriteActivity.class));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(LaptopActivity.this, MainActivity.class);
            }
        });

        lapTopRec.setLayoutManager(new GridLayoutManager(this, 2));
        laptopModelList= new ArrayList<>();
        laptopAdapter= new LaptopAdapter(this,laptopModelList);
        lapTopRec.setAdapter(laptopAdapter);
        db.collection("Laptop")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                LaptopModel laptopModel =document.toObject(LaptopModel.class);
                                laptopModelList.add(laptopModel);
                                laptopAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getParent(),"Error"+task.getException(),Toast.LENGTH_SHORT);

                        }
                    }
                });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LaptopActivity.this,MainActivity.class));
            }
        });

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LaptopActivity.this,CartActivity.class));
            }
        });

        noti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LaptopActivity.this,MainActivity.class));
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LaptopActivity.this,CartActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(LaptopActivity.this, MainActivity.class));
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}