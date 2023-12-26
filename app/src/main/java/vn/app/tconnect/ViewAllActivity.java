package vn.app.tconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import vn.app.tconnect.adapter.ViewAllAdapter;
import vn.app.tconnect.models.ChuotModel;
import vn.app.tconnect.models.ViewAllModel;

public class ViewAllActivity extends AppCompatActivity {
    List<ViewAllModel> viewAllModelList;
    RecyclerView recyclerView;
    ViewAllAdapter viewAllAdapter;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        firestore= FirebaseFirestore.getInstance();
        String type= getIntent().getStringExtra("type");
        recyclerView= findViewById(R.id.rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewAllAdapter= new ViewAllAdapter(this, viewAllModelList);
        viewAllModelList = new ArrayList<>();
        recyclerView.setAdapter(viewAllAdapter);

        /*if(type!=null && type.equalsIgnoreCase("Laptop")){
            firestore.collection("AllProduct").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();
                    }
                }
            });
        }*/
        firestore.collection("AllProduct")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                ViewAllModel viewAllModel = document.toObject(ViewAllModel.class);
                                viewAllModelList.add(viewAllModel);
                                viewAllAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getParent(),"Error"+task.getException(),Toast.LENGTH_SHORT);

                        }
                    }
                });

    }
}