package vn.app.tconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;

import vn.app.tconnect.models.BanPhimModel;
import vn.app.tconnect.models.ChuotModel;
import vn.app.tconnect.models.FlashSaleModel;
import vn.app.tconnect.models.LaptopModel;
import vn.app.tconnect.models.ManHinhModel;
import vn.app.tconnect.models.RecommendedModel;
import vn.app.tconnect.models.TaiNgheModel;

public class CtsanphamActivity extends AppCompatActivity {
    ImageView ctimg;
    TextView name, price,Quantity,descri;
    int totalQuantity =1;
    ImageView addItem,removeItem,addFavorite;
    int tongtien =0;
    Button addtocart;
    Toolbar toolbar;

    ManHinhModel manHinhModel = null;
    BanPhimModel banPhimModel = null;
    ChuotModel chuotModel = null;
    LaptopModel laptopModel = null;
    TaiNgheModel taiNgheModel = null;
    FlashSaleModel flashSaleModel = null;
    RecommendedModel recommendedModel = null;
    FirebaseFirestore firestore;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ctsanpham);
        toolbar = findViewById(R.id.toolbar_ctsp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        addItem = findViewById(R.id.add_item);
        Quantity = findViewById(R.id.quantity);
        removeItem = findViewById(R.id.remove_item);
        addtocart = findViewById(R.id.add_tocart);
        ctimg = findViewById(R.id.ct_img);
        name = findViewById(R.id.ct_name);
        price = findViewById(R.id.ct_price);
        addFavorite= findViewById(R.id.addto_favo);
        descri= findViewById(R.id.desc);

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(totalQuantity < 10) {
                    totalQuantity++;
                    Quantity.setText(String.valueOf(totalQuantity));
                    if(manHinhModel != null) {
                        int price = manHinhModel.getPrice();
                        tongtien += manHinhModel.getPrice();
                    }
                    if(chuotModel != null) {
                        int price = chuotModel.getPrice();
                        tongtien += chuotModel.getPrice();
                    }
                    if(banPhimModel != null) {
                        int price = banPhimModel.getPrice();
                        tongtien += banPhimModel.getPrice();
                    }
                    if(taiNgheModel != null) {
                        int price = taiNgheModel.getPrice();
                        tongtien += taiNgheModel.getPrice();
                    }
                    if(laptopModel != null) {
                        int price = laptopModel.getPrice();
                        tongtien += laptopModel.getPrice();
                    }
                    if(flashSaleModel != null) {
                        int price = flashSaleModel.getPrice();
                        tongtien += flashSaleModel.getPrice();
                    }
                    if(recommendedModel != null) {
                        int price = recommendedModel.getPrice();
                        tongtien += recommendedModel.getPrice();
                    }
                }
            }
        });

        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(totalQuantity > 0) {
                    totalQuantity--;
                    Quantity.setText(String.valueOf(totalQuantity));
                    tongtien = 0;
                    if(manHinhModel != null) {
                        tongtien += manHinhModel.getPrice() * totalQuantity; // Cập nhật giá tiền màn hình
                    }
                    if(chuotModel != null) {
                        tongtien += chuotModel.getPrice() * totalQuantity; // Cập nhật giá tiền màn hình
                    }
                    if(banPhimModel != null) {
                        tongtien += banPhimModel.getPrice() * totalQuantity; // Cập nhật giá tiền màn hình
                    }
                    if(taiNgheModel != null) {
                        tongtien += taiNgheModel.getPrice() * totalQuantity; // Cập nhật giá tiền màn hình
                    }
                    if(laptopModel != null) {
                        tongtien += laptopModel.getPrice() * totalQuantity; // Cập nhật giá tiền màn hình
                    }
                    if(flashSaleModel != null) {
                        tongtien += flashSaleModel.getPrice() * totalQuantity; // Cập nhật giá tiền màn hình
                    }
                    if(recommendedModel != null) {
                        tongtien += recommendedModel.getPrice() * totalQuantity; // Cập nhật giá tiền màn hình
                    }

                }
            }
        });

        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCart();
            }
        });


        final Object object = getIntent().getSerializableExtra("Chitiet");
        if (object instanceof ManHinhModel) {
            manHinhModel = (ManHinhModel) object;
            setupManHinhModel();
        } else if (object instanceof BanPhimModel) {
            banPhimModel = (BanPhimModel) object;
            setupBanPhimModel();
        } else if (object instanceof ChuotModel) {
            chuotModel = (ChuotModel) object;
            setupChuotModel();
        } else if (object instanceof LaptopModel) {
            laptopModel = (LaptopModel) object;
            setupLaptopModel();
        } else if (object instanceof TaiNgheModel) {
            taiNgheModel = (TaiNgheModel) object;
            setupTaiNgheModel();
        }else if (object instanceof FlashSaleModel) {
            flashSaleModel = (FlashSaleModel) object;
            setupFlashSaleModel();
        }else if (object instanceof RecommendedModel) {
            recommendedModel = (RecommendedModel) object;
            setupRecommendedModel();
        }

        addFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToFavorite();
            }
        });
    }

    private void addToFavorite(){
        if (manHinhModel != null) {
            addToFavoriteForModel(manHinhModel);
        } else if (banPhimModel != null) {
            addToFavoriteForModel(banPhimModel);
        } else if (chuotModel != null) {
            addToFavoriteForModel(chuotModel);
        } else if (laptopModel != null) {
            addToFavoriteForModel(laptopModel);
        } else if (taiNgheModel != null) {
            addToFavoriteForModel(taiNgheModel);
        }else if (flashSaleModel != null) {
            addToFavoriteForModel(flashSaleModel);
        }else if (recommendedModel != null) {
            addToFavoriteForModel(recommendedModel);
        }
    }

    private void addToFavoriteForModel(ProductModel productModel) {
        HashMap<String, Object> cartMap = new HashMap<>();
        cartMap.put("productImage", productModel.getImg_url());
        cartMap.put("productName", productModel.getName());
        cartMap.put("productPrice", productModel.getPrice());

        firestore.collection("addFavorite")
                .document(auth.getCurrentUser().getUid())
                .collection("CurrentUser")
                .add(cartMap)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(CtsanphamActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(CtsanphamActivity.this, "Thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void addToCart() {
        // Xử lý thêm vào giỏ hàng tương ứng với mỗi loại sản phẩm
        if (manHinhModel != null) {
            addToCartForModel(manHinhModel);
        } else if (banPhimModel != null) {
            addToCartForModel(banPhimModel);
        } else if (chuotModel != null) {
            addToCartForModel(chuotModel);
        } else if (laptopModel != null) {
            addToCartForModel(laptopModel);
        } else if (taiNgheModel != null) {
            addToCartForModel(taiNgheModel);
        }else if (flashSaleModel != null) {
            addToCartForModel(flashSaleModel);
        }else if (recommendedModel != null) {
            addToCartForModel(recommendedModel);
        }
    }

    private void addToCartForModel(ProductModel productModel) {
        HashMap<String, Object> cartMap = new HashMap<>();
        cartMap.put("productImage", productModel.getImg_url());
        cartMap.put("productName", productModel.getName());
        cartMap.put("productPrice", productModel.getPrice());
        cartMap.put("totalQuantity",Quantity.getText().toString());
        cartMap.put("tongtien",tongtien);

        firestore.collection("addtocart")
                .document(auth.getCurrentUser().getUid())
                .collection("CurrentUser")
                .add(cartMap)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(CtsanphamActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(CtsanphamActivity.this, "Thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    // Các phương thức setup cho từng loại sản phẩm

    private String formatCurrency(int price) {
        // Định dạng giá tiền theo định dạng tiền tệ
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.getDefault());
        String formattedPrice = currencyFormat.format(price);

        return formattedPrice;
    }
    private void setupManHinhModel() {
        if(manHinhModel != null) {
            Glide.with(getApplicationContext()).load(manHinhModel.getImg_url()).into(ctimg);
            name.setText(manHinhModel.getName());
            int intValue = manHinhModel.getPrice();
            String formattedPrice = formatCurrency(intValue);
            price.setText(formattedPrice);
            descri.setText(manHinhModel.getDesc() );
            tongtien = manHinhModel.getPrice() * totalQuantity;
        }
    }

    private void setupBanPhimModel() {
        if(banPhimModel != null)
        {
            Glide.with(getApplicationContext()).load(banPhimModel.getImg_url()).into(ctimg);
            name.setText(banPhimModel.getName());
            int intValue = banPhimModel.getPrice();String formattedPrice = formatCurrency(intValue);
            price.setText(formattedPrice);
            descri.setText(banPhimModel.getDesc() );
            tongtien = banPhimModel.getPrice() * totalQuantity;
        }

    }

    private void setupChuotModel() {
        if(chuotModel != null)
        {
            Glide.with(getApplicationContext()).load(chuotModel.getImg_url()).into(ctimg);
            name.setText(chuotModel.getName());
            int intValue = chuotModel.getPrice();
            String formattedPrice = formatCurrency(intValue);
            price.setText(formattedPrice);
            descri.setText(chuotModel.getDesc() );
            tongtien = chuotModel.getPrice() * totalQuantity;
        }
    }

    private void setupLaptopModel() {
        if(laptopModel != null)
        {
            Glide.with(getApplicationContext()).load(laptopModel.getImg_url()).into(ctimg);
            name.setText(laptopModel.getName());
            int intValue = laptopModel.getPrice();
            String formattedPrice = formatCurrency(intValue);
            price.setText(formattedPrice);
            descri.setText(laptopModel.getDesc() );
            tongtien = laptopModel.getPrice() * totalQuantity;
        }
    }

    private void setupTaiNgheModel() {
        if(taiNgheModel != null)
        {
            Glide.with(getApplicationContext()).load(taiNgheModel.getImg_url()).into(ctimg);
            name.setText(taiNgheModel.getName());
            int intValue = taiNgheModel.getPrice();
            String formattedPrice = formatCurrency(intValue);
            price.setText(formattedPrice);
            descri.setText(taiNgheModel.getDesc() );
            tongtien = taiNgheModel.getPrice() * totalQuantity;
        }
    }
    private void setupFlashSaleModel() {
        if(flashSaleModel != null) {
            Glide.with(getApplicationContext()).load(flashSaleModel.getImg_url()).into(ctimg);
            name.setText(flashSaleModel.getName());
            int intValue = flashSaleModel.getPrice();
            String formattedPrice = formatCurrency(intValue);
            price.setText(formattedPrice);
            descri.setText(flashSaleModel.getDesc() );
            tongtien = flashSaleModel.getPrice() * totalQuantity;
        }
    }
    private void setupRecommendedModel() {
        if(recommendedModel != null) {
            Glide.with(getApplicationContext()).load(recommendedModel.getImg_url()).into(ctimg);
            name.setText(recommendedModel.getName());
            int intValue = recommendedModel.getPrice();
            String formattedPrice = formatCurrency(intValue);
            price.setText(formattedPrice);
            descri.setText(recommendedModel.getDesc() );
            tongtien = recommendedModel.getPrice() * totalQuantity;
        }
    }
}
