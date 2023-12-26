package vn.app.tconnect.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import vn.app.tconnect.CtsanphamActivity;
import vn.app.tconnect.R;
import vn.app.tconnect.models.FlashSaleModel;

public class SaleAdapter extends RecyclerView.Adapter<SaleAdapter.ViewHolder> {

    private Context context;
    private List<FlashSaleModel> flashSaleModelList;

    public SaleAdapter(Context context, List<FlashSaleModel> flashSaleModelList) {
        this.context = context;
        this.flashSaleModelList = flashSaleModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.flashsale_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(flashSaleModelList.get(position).getImg_url()).into(holder.popImg);
        holder.name.setText(flashSaleModelList.get(position).getName());
        int priceValue = flashSaleModelList.get(position).getPrice();
        String formattedPrice = formatCurrency(priceValue);
        holder.price.setText(formattedPrice);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CtsanphamActivity.class);
                intent.putExtra("Chitiet",flashSaleModelList.get(position));
                context.startActivity(intent);
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
        return flashSaleModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView popImg;
        TextView name,price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            popImg=itemView.findViewById(R.id.pop_img);
            name= itemView.findViewById(R.id.FS_name);
            price= itemView.findViewById(R.id.FS_price);
        }
    }
}
