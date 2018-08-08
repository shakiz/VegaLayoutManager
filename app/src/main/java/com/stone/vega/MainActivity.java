package com.stone.vega;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.stone.vega.library.VegaLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<StockEntity> dataList = new ArrayList<>();
    private int redColor, greenColor;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View positionView = findViewById(R.id.main_position_view);
        boolean immerse = Utils.immerseStatusBar(this);
        boolean darkMode = Utils.setDarkMode(this);
        if (immerse) {
            ViewGroup.LayoutParams lp = positionView.getLayoutParams();
            lp.height = Utils.getStatusBarHeight(this);
            positionView.setLayoutParams(lp);
            if (!darkMode) {
                positionView.setBackgroundColor(Color.BLACK);
            }
        } else {
            positionView.setVisibility(View.GONE);
        }

        // 2. toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // 3. recyclerView数据填充
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);
        recyclerView.setLayoutManager(new VegaLayoutManager());
        adapter = getAdapter();
        recyclerView.setAdapter(adapter);

        redColor = getResources().getColor(R.color.red);
        greenColor = getResources().getColor(R.color.green);

        prepareDataList();
        adapter.notifyDataSetChanged();
    }

    private void prepareDataList() {
        for (int i = 0; i < 1; i++) {
            dataList.add(new StockEntity("ASUS 8Gb RAM", 100f, "ASUS"));
            dataList.add(new StockEntity("ASUS BM250 Motherboard .", 158.73f, "ASUS"));
            dataList.add(new StockEntity("Dell E1916HV 18.5\" LED Monitor.", 80f, "DELL"));
            dataList.add(new StockEntity("Dell P2417H 24\" LED Monitor.", 230f,"DELL"));
            dataList.add(new StockEntity("Dell U2414H 23.8\"", 350f,"DELL"));
            dataList.add(new StockEntity("Dell U2518D UltraSharp 25\"", 460f,"DELL"));
            dataList.add(new StockEntity("ASUS PRIME Z370-P LGA1151", 190f,"ASUS"));
            dataList.add(new StockEntity("Asus TUF Z370-PLUS", 220f,"ASUS"));
            dataList.add(new StockEntity("Asus Prime Z370-A", 237.92f,"ASUS"));
            dataList.add(new StockEntity("ASUS H61M-K.", 90.47f,"ASUS"));
            dataList.add(new StockEntity("Oracle Inc.", 48.03f,"ORACLE"));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:// 点击返回图标事件
                dataList.remove(0);
                adapter.notifyItemRemoved(0);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private RecyclerView.Adapter getAdapter() {
        final LayoutInflater inflater = LayoutInflater.from(this);
        RecyclerView.Adapter adapter = new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = inflater.inflate(R.layout.recycler_item, parent, false);
                return new MyViewHolder(view);
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                MyViewHolder myHolder = (MyViewHolder) holder;
                myHolder.bindData(dataList.get(position));
            }

            @Override
            public int getItemCount() {
                return dataList.size();
            }
        };
        return adapter;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nameTv;
        TextView currentPriceTv;
        ImageView trendFlagIv;
        TextView grossTv;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.nameTv = (TextView) itemView.findViewById(R.id.item_name_tv);
            this.currentPriceTv = (TextView) itemView.findViewById(R.id.item_current_price);
            this.trendFlagIv = (ImageView) itemView.findViewById(R.id.item_trend_flag);
            this.grossTv = (TextView) itemView.findViewById(R.id.item_gross);
        }

        public void bindData(StockEntity stockEntity) {
            nameTv.setText(stockEntity.getName());
            currentPriceTv.setText("$" + stockEntity.getPrice());
            grossTv.setText(stockEntity.getGross());
        }
    }
}
