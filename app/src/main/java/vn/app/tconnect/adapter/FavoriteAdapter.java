package vn.app.tconnect.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.widget.Toast;

import vn.app.tconnect.R;
import vn.app.tconnect.models.CartModel;
import vn.app.tconnect.models.FavoriteModel;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    Context context;
    List<FavoriteModel> favoriteModelList;
    FirebaseFirestore firestore;
    FirebaseAuth auth;

    public FavoriteAdapter(Context context, List<FavoriteModel> favoriteModelList) {
        this.context = context;
        this.favoriteModelList = favoriteModelList;
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(favoriteModelList.get(position).getProductImage()).into(holder.popImg);
        holder.name.setText(favoriteModelList.get(position).getProductName());
        int priceValue = favoriteModelList.get(position).getProductPrice();
        String formattedPrice = formatCurrency(priceValue);
        holder.price.setText(formattedPrice);
        holder.deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firestore.collection("addFavorite")
                        .document(auth.getCurrentUser().getUid())
                        .collection("CurrentUser")
                        .document(favoriteModelList.get(position).getDocumentId())
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {
                                    favoriteModelList.remove(favoriteModelList.get(position));
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "Đã xóa khỏi mục yêu thích", Toast.LENGTH_SHORT).show();
                                }else
                                {
                                    Toast.makeText(context, "Lỗi "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    private String formatCurrency(int price) {
        // Định dạng giá tiền theo định dạng tiền tệ
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.getDefault());
        String formattedPrice = currencyFormat.format(price);

        return formattedPrice;
    }


    @Override
    public int getItemCount() {
        return favoriteModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView popImg;
        TextView name,price,deleteItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            popImg=itemView.findViewById(R.id.anhgh1);
            name= itemView.findViewById(R.id.tengh1);
            price= itemView.findViewById(R.id.gia);
            deleteItem = itemView.findViewById(R.id.delete);
        }
    }
}
