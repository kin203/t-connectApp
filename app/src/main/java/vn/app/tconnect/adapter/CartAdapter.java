package vn.app.tconnect.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.checker.units.qual.C;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.zip.Inflater;

import android.content.Context;
import android.widget.Toast;

import vn.app.tconnect.R;
import vn.app.tconnect.models.CartModel;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    Context context;
    List<CartModel> cartModelList;
    int totalprice =0;
    FirebaseFirestore firestore;
    FirebaseAuth auth;

    public CartAdapter(Context context, List<CartModel> cartModelList) {
        this.context = context;
        this.cartModelList = cartModelList;
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(cartModelList.get(position).getProductImage()).into(holder.popImg);
        holder.name.setText(cartModelList.get(position).getProductName());
        int priceValue = cartModelList.get(position).getProductPrice();
        String formattedPrice = formatCurrency(priceValue);
        holder.price.setText(formattedPrice);
        String totalQuantity = cartModelList.get(position).getTotalQuantity();
        holder.totalquantity.setText(totalQuantity);
        int totalPriceValue = cartModelList.get(position).getTongtien();
        String formattedToTal = formatCurrencyTotal(totalPriceValue);
        holder.totalprice.setText(formattedToTal);
        holder.deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firestore.collection("addtocart")
                        .document(auth.getCurrentUser().getUid())
                        .collection("CurrentUser")
                        .document(cartModelList.get(position).getDocumentId())
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {
                                    cartModelList.remove(cartModelList.get(position));
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "Đã xóa khỏi giỏ hàng", Toast.LENGTH_SHORT).show();
                                }else
                                {
                                    Toast.makeText(context, "Lỗi "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        int totalprice = 0;
        for (CartModel cartModel : cartModelList) {
            totalprice += cartModel.getTongtien();
        }
        Intent intent = new Intent("MyTotalAmount");
        intent.putExtra("totalAmount", totalprice);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    private String formatCurrency(int price) {
        // Định dạng giá tiền theo định dạng tiền tệ
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.getDefault());
        String formattedPrice = currencyFormat.format(price);

        return formattedPrice;
    }

    private String formatCurrencyTotal(int total) {
        // Định dạng giá tiền theo định dạng tiền tệ
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.getDefault());
        String formattedTotal = currencyFormat.format(total);

        return formattedTotal;
    }


    @Override
    public int getItemCount() {
        return cartModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView popImg;
        TextView name,price,totalquantity,totalprice,deleteItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            popImg=itemView.findViewById(R.id.anhgh1);
            name= itemView.findViewById(R.id.tengh1);
            price= itemView.findViewById(R.id.gia);
            totalquantity= itemView.findViewById(R.id.totalquantity);
            totalprice = itemView.findViewById(R.id.tongtien);
            deleteItem = itemView.findViewById(R.id.delete);
        }
    }
}
