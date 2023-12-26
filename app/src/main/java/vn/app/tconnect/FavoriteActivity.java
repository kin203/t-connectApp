package vn.app.tconnect;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import vn.app.tconnect.adapter.FavoriteAdapter;
import vn.app.tconnect.models.FavoriteModel;

public class FavoriteActivity extends AppCompatActivity {
    FirebaseFirestore db;
    FirebaseAuth auth;
    RecyclerView recyclerView;
    FavoriteAdapter favoriteAdapter;
    List<FavoriteModel> favoriteModelList;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        recyclerView = findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL, false));
        favoriteModelList = new ArrayList<>();
        favoriteAdapter = new FavoriteAdapter(this,favoriteModelList);
        recyclerView.setAdapter(favoriteAdapter);
        db.collection("addFavorite").document(auth.getCurrentUser().getUid()).collection("CurrentUser").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        String documentId = documentSnapshot.getId();
                        FavoriteModel FavoriteModel = documentSnapshot.toObject(FavoriteModel.class);
                        FavoriteModel.setDocumentId(documentId);
                        favoriteModelList.add(FavoriteModel);
                        favoriteAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }
}
