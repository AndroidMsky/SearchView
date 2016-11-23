package com.example.liangmutian.searchview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.liangmutian.searchview.swipetoloadlayout.BaseRecyclerAdapter;
import com.example.liangmutian.searchview.swipetoloadlayout.OnLoadMoreListener;
import com.example.liangmutian.searchview.swipetoloadlayout.OnRefreshListener;
import com.example.liangmutian.searchview.swipetoloadlayout.SuperRefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lmt
 * on 16/9/14.
 */
public class CustomerSearchActivity extends Activity implements OnRefreshListener, OnLoadMoreListener {
    private final static int NoThing = 0;
    private final static int Show = 1;
    private final static int Cant_find = 2;

    private SuperRefreshRecyclerView superRecyclerView;
    private CustomerCampanySearchAdapter adapter;
    private List<Customer> customerList = new ArrayList<>();
    private EditText editMobile;
    private RelativeLayout rlCantFind;
    private TextView tvKey;
    private List<Customer> dataList = new ArrayList<>();
    private List<Customer> list2 = new ArrayList<>();//after search
    int customerNum = 0;
    private List<Customer> list = new ArrayList<>();
    private boolean isSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_customer_search);
        initViews();
        initData();
    }



    public void initViews() {

        findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        superRecyclerView = (SuperRefreshRecyclerView) findViewById(R.id.super_recyclerview);
        superRecyclerView.init(new LinearLayoutManager(this), this, this);
        superRecyclerView.setRefreshEnabled(false);
        superRecyclerView.setLoadingMoreEnable(false);
        rlCantFind = (RelativeLayout) findViewById(R.id.rl_cant_find_over);
        editMobile = (EditText) findViewById(R.id.edit_search);
        editMobile.addTextChangedListener(textWatcher);
        tvKey = (TextView) findViewById(R.id.tv_key);


    }

    public void initData() {
        //isSelect=getIntent().getBooleanExtra("isSelect",false);
      //  String customerListJson = getIntent().getStringExtra("customerListJson");
       // list = BasePresenter.gson.fromJson(customerListJson, new TypeToken<List<Customer>>() {}.getType());

        list.add(new Customer("tom 15588881111"));
        list.add(new Customer("tom 15588881112"));
        list.add(new Customer("tom 15588881113"));
        list.add(new Customer("tom 15588881114"));
        list.add(new Customer("tom 15588881115"));
        list.add(new Customer("tom 15588801111"));
        list.add(new Customer("Bom 15588811111"));
        list.add(new Customer("Bom 15588821111"));
        list.add(new Customer("tom 15588831111"));
        list.add(new Customer("CCm 15588841111"));
        list.add(new Customer("CCm 15588851111"));
        list.add(new Customer("CCm 15588861111"));



        if (list != null && list.size() > 0) {
            dataList.addAll(list);
        }
        for (int i = 0; i < dataList.size(); i++) {
            dataList.get(i).name = dataList.get(i).organizationName;
        }
    }

    public void showCustomer(List<Customer> list, String key) {
        if (list == null || list.size() == 0)
            return;
        customerList.clear();
        customerList.addAll(list);
        adapter = new CustomerCampanySearchAdapter(customerList, this, key);
        superRecyclerView.setAdapter(adapter);
        superRecyclerView.showData();

        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, long id) {
                //
            }
        });

    }




    @Override
    public void onLoadMore() {

    }

    @Override
    public void onRefresh() {

    }


    public void changeStates(int states) {

        switch (states) {
            case NoThing:
                rlCantFind.setVisibility(View.INVISIBLE);
                superRecyclerView.setVisibility(View.INVISIBLE);
                break;
            case Show:
                rlCantFind.setVisibility(View.INVISIBLE);
                superRecyclerView.setVisibility(View.VISIBLE);
                break;
            case Cant_find:
                rlCantFind.setVisibility(View.VISIBLE);
                superRecyclerView.setVisibility(View.INVISIBLE);
                break;


        }

    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (!TextUtils.isEmpty(charSequence)) {

                searchByTel(charSequence);
                if (customerNum == 0) {
                    changeStates(Cant_find);
                    tvKey.setText(charSequence + "");
                } else {
                    changeStates(Show);
                }
                showCustomer(list2, charSequence + "");

            } else {

                changeStates(NoThing);

            }
        }


        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private List<Customer> searchByTel(CharSequence key) {
        list2.clear();
        customerNum = 0;
        for (int i = 0; i < dataList.size(); i++) {

            if (dataList.get(i).name.contains(key)) {
                customerNum++;
                list2.add(dataList.get(i));
            }

        }

        return list2;
    }


    public static void into(Activity context) {
        Intent intent = new Intent(context, CustomerSearchActivity.class);
        context.startActivity(intent);
    }

}


