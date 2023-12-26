package vn.app.tconnect;

import static vn.app.tconnect.MainActivity.redirectActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityDiaChiNhan extends AppCompatActivity {
    EditText edtp;
    EditText edq;

    EditText edx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dc_nhanhang);
        edtp = findViewById(R.id.edtThanhPho);
        edq = findViewById(R.id.edtQuan);
        edx  = findViewById(R.id.edtPhuong);
        Button button = findViewById(R.id.btnNhanHang);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityDiaChiNhan.this, ThanhToanActivity.class);
                startActivity(intent);
            }
        });
        edtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showThanhPho();
            }
        });

        edx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showXa();
            }
        });

    }

    private void showThanhPho() {
        PopupMenu popupMenu = new PopupMenu(this,edtp);
        popupMenu.getMenuInflater().inflate(R.menu.menutp,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int id = menuItem.getItemId();
                if(id==R.id.hn){
                    edtp.setText("Hà Nội");
                    edq.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            showQuanHn();
                        }
                    });

                }else if(id==R.id.hy)
                {
                    edtp.setText("Hưng Yên");
                } else if (id==R.id.qn) {
                    edtp.setText("Quảng ninh");
                }else if(id==R.id.nd)
                {
                    edtp.setText("Nam Định");
                    edq.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            showQuanNd();
                        }
                    });

                }
                return false;
            }
        });
        popupMenu.show();
    }
    private void showQuanHn() {
        PopupMenu popupMenu = new PopupMenu(this,edq);
        popupMenu.getMenuInflater().inflate(R.menu.menuquan,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int id = menuItem.getItemId();
                if(id==R.id.hm){
                    edq.setText("Quận Hoàng Mai");
                }else if(id==R.id.hbt)
                {
                    edq.setText("Quận Hai Bà Trưng");
                } else if (id==R.id.cg) {
                    edq.setText("Quận Cầu Giấy");
                }else if(id==R.id.lb)
                {
                    edq.setText("Quận Long Biên");
                }else if(id==R.id.th)
                {
                    edq.setText(("Quận Tây Hồ"));
                }else if(id==R.id.dd)
                {
                    edq.setText(("Quận Đống Đa"));
                }
                return false;
            }
        });
        popupMenu.show();
    }
    private void showQuanNd() {
        PopupMenu popupMenu = new PopupMenu(this,edq);
        popupMenu.getMenuInflater().inflate(R.menu.menuquannd,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int id = menuItem.getItemId();
                if(id==R.id.doson){
                    edq.setText(menuItem.getTitle());
                }else if(id==R.id.hbt)
                {
                    edq.setText(menuItem.getTitle());
                } else if (id==R.id.cg) {
                    edq.setText(menuItem.getTitle());
                }
                return false;
            }
        });
        popupMenu.show();
    }

    private void showXa() {
        PopupMenu popupMenu = new PopupMenu(this,edx);
        popupMenu.getMenuInflater().inflate(R.menu.menuxa,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int id = menuItem.getItemId();
                if(id==R.id.cuadong){
                    edx.setText("Cửa Đông");
                }else if(id==R.id.cuanam)
                {
                    edx.setText("Cửa Nam");
                } else if (id==R.id.hangbai) {
                    edx.setText("Hàng Bài");
                }
                return false;
            }
        });
        popupMenu.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menutp, menu);
        return super.onCreateOptionsMenu(menu);
    }
}