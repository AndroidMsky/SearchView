package com.example.liangmutian.searchview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.liangmutian.searchview.swipetoloadlayout.BaseRecyclerAdapter;
import java.util.List;


/**
 * Created by lmt on 16/7/1.
 */
public class CustomerCampanySearchAdapter extends BaseRecyclerAdapter<BaseRecyclerAdapter.BaseRecyclerViewHolder, Customer> {

    private List<Customer> list;
    private Context mContext;
    private String mkey;


    public CustomerCampanySearchAdapter(List<Customer> list, Context context, String key) {
        super(list);
        mContext = context;
        mkey = key;
        this.list = list;
    }


    @Override
    public BaseRecyclerAdapter.BaseRecyclerViewHolder createViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        return new CustomerHolder(inflater.inflate(R.layout.item_campany_customer, parent, false));
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position, final Customer data) {
        CustomerHolder customerHolder = (CustomerHolder) holder;
        customerHolder.tvName.setText(data.name);
        StringFormatUtil spanStr3 = new StringFormatUtil(mContext, data.name,
                mkey, R.color.blue).fillColor();
        if (spanStr3 != null)
            customerHolder.tvName.setText(spanStr3.getResult());
        else customerHolder.tvName.setText(data.name);
    }

    class CustomerHolder extends BaseRecyclerViewHolder {
        public TextView tvName;

        public CustomerHolder(View itemView) {
            super(itemView);
            tvName = findView(R.id.tv_name);
            findView(R.id.tv_status).setVisibility(View.INVISIBLE);


        }
    }


}
