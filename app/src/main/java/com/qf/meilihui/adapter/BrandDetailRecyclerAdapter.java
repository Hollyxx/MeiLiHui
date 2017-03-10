package com.qf.meilihui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qf.meilihui.R;
import com.qf.meilihui.avtivity.SecondDetailsActivity;
import com.qf.meilihui.avtivity.ThirdDetailsActivity;
import com.qf.meilihui.bean.BrandDetailBean;
import com.qf.meilihui.bean.HotProductsOfBrandDetail;
import com.qf.meilihui.bean.HotProductsOfBrandDetailBean;
import com.qf.meilihui.callback.OnRecyclerItemClickListener;
import com.qf.meilihui.uri.Config;

import java.util.List;

/**
 * Created by invoker on 2017/3/8.
 */

public class BrandDetailRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Object> data;
    private boolean isAll = false;
    //    private LoadMoreCallBack callBack;
//    private BrandDetailActivity activity;
//    private boolean isBottom = false;
//    private int page = 1;

    public BrandDetailRecyclerAdapter(Context context, List<Object> data) {
        this.context = context;
        this.data = data;
//        this.callBack = callBack;
    }

//    public void setActivity(BrandDetailActivity activity) {
//        this.activity = activity;
//    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case 0:
                view = LayoutInflater.from(context).inflate(R.layout.type1_recycler_brand_detail, parent, false);
                holder = new FirstViewHolder(view);
                break;
            case 1:
                view = LayoutInflater.from(context).inflate(R.layout.type2_recycler_brand_detail, parent, false);
                holder = new SecondViewHolder(view);
                break;

            case 2:
                view = LayoutInflater.from(context).inflate(R.layout.type2_recycler_brand_detail, parent, false);
                holder = new SecondViewHolder(view);
                break;


        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        Object o = data.get(position);
        switch (getItemViewType(position)) {

            case 0:
                BrandDetailBean.BodyBrandDetailBean.BrandDetailBrandDetailBean detailBean = (BrandDetailBean.BodyBrandDetailBean.BrandDetailBrandDetailBean) o;
                final FirstViewHolder holder1 = (FirstViewHolder) holder;
                holder1.logo_title.setText(detailBean.getBrandName());
                if (detailBean.getBrandStoryText().length() < 1) {
                    holder1.layout.setVisibility(View.GONE);
                } else {
                    holder1.summary.setText(detailBean.getBrandStoryText());
                }
                //TODO 点击小箭头加载全部描述

                holder1.loadMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        holder1.summary.setLines(20);
                        if (isAll) {
                            holder1.summary.setLines(2);
                            holder1.loadMore.setImageResource(R.mipmap.icon_pic_title_arrow_down);
                            isAll = false;
                        } else {
                            isAll = true;
                            holder1.summary.setSingleLine(false);
                            holder1.loadMore.setImageResource(R.mipmap.icon_pic_title_arrow_up);
                        }

                    }
                });

                Glide.with(context).load(detailBean.getLogoUrl()).into(holder1.image_logo);
                Glide.with(context).load(detailBean.getBrandPageImage()).into(holder1.image);
                break;


            case 1:
                SecondViewHolder holder2 = (SecondViewHolder) holder;

                final BrandDetailBean.BodyBrandDetailBean.NewProductBrandDetailBean items = (BrandDetailBean.BodyBrandDetailBean.NewProductBrandDetailBean) data.get(position);

                holder2.title_product.setText(items.getEventName());
                final List<BrandDetailBean.BodyBrandDetailBean.NewProductBrandDetailBean.ProductBrandDetailBean> product = items.getProduct();
                RecyclerView.LayoutManager manage = new LinearLayoutManager(context, OrientationHelper.HORIZONTAL, false);
                BrandDetailRecyclerFirstAdapter adapter = new BrandDetailRecyclerFirstAdapter(context, product);

                //给adapter设置监听器，用于页面跳转
                adapter.setListener(new OnRecyclerItemClickListener() {
                    @Override
                    public void onItemClickListener(int position) {
                        BrandDetailBean.BodyBrandDetailBean.NewProductBrandDetailBean.ProductBrandDetailBean bean = product.get(position);
                        Intent intent = new Intent(context, ThirdDetailsActivity.class);
                        String thirdAddress = Config.TODAY_THIRD_CONTENT + bean.getProductId();
                        String Hot_recommendation = Config.Hot_recommendation + bean.getProductId() + "&categoryId=" + bean.getEventId();
                        intent.putExtra("Hot_recommendation", Hot_recommendation);
                        intent.putExtra("thirdAddress", thirdAddress);
                        intent.putExtra("price", bean.getPrice());
                        intent.putExtra("marketPrice", bean.getMarketPrice());
                        intent.putExtra("name", bean.getBrandName());
                        intent.putExtra("productName", bean.getProductName());
                        context.startActivity(intent);
                    }
                });


                holder2.recyclerView.setAdapter(adapter);
                holder2.recyclerView.setLayoutManager(manage);
                holder2.next_page.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, SecondDetailsActivity.class);
                        intent.putExtra("eventName", items.getEventName());
                        String web = Config.TODAY_SECOND_CONTENT + items.getEventId() + "&pageIndex=1";
                        intent.putExtra("web", web);
                        intent.putExtra("englishName", items.getEventName());
                        context.startActivity(intent);
                    }
                });

                break;
            case 2:
                SecondViewHolder holder3 = (SecondViewHolder) holder;
                HotProductsOfBrandDetailBean products = (HotProductsOfBrandDetailBean) o;
                holder3.title_product.setText(products.getTitle());
                holder3.next_page.setVisibility(View.INVISIBLE);
                final List<HotProductsOfBrandDetail> details = products.getItems();
                BrandDetailRecyclerSecondAdapter adapter1 = new BrandDetailRecyclerSecondAdapter(context, details);
                holder3.recyclerView.setAdapter(adapter1);
                //用于分页加载

                //给adapter设置监听器，用于页面跳转
                adapter1.setListener(new OnRecyclerItemClickListener() {
                    @Override
                    public void onItemClickListener(int position) {
                        HotProductsOfBrandDetail product = details.get(position);
                        Intent intent = new Intent(context, ThirdDetailsActivity.class);
                        String thirdAddress = Config.TODAY_THIRD_CONTENT + product.getProductId();
                        String Hot_recommendation = Config.Hot_recommendation + product.getProductId() + "&categoryId=" + product.getEventId();
                        intent.putExtra("Hot_recommendation", Hot_recommendation);
                        intent.putExtra("thirdAddress", thirdAddress);
                        intent.putExtra("price", product.getPrice());
                        intent.putExtra("marketPrice", product.getMarketPrice());
                        intent.putExtra("name", product.getBrandName());
                        intent.putExtra("productName", product.getProductName());
                        context.startActivity(intent);
                    }
                });

                GridLayoutManager manager = new GridLayoutManager(context, 2, OrientationHelper.VERTICAL, false);
                holder3.recyclerView.setLayoutManager(manager);
//                holder3.recyclerView.addOnScrollListener(new LoadMoreRecyclerOnScrollListener(manager));
//                loadMore(holder3.recyclerView);
                break;

        }


    }

//    private void loadMore(RecyclerView recycler) {
//        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//
//                int lastPosition = -1;
//                //当前状态为停止滑动状态SCROLL_STATE_IDLE时
//                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
//                    if (layoutManager instanceof GridLayoutManager) {
//                        //通过LayoutManager找到当前显示的最后的item的position
//                        lastPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
//                    } else if (layoutManager instanceof LinearLayoutManager) {
//                        lastPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
//                    } else if (layoutManager instanceof StaggeredGridLayoutManager) {
//                        //因为StaggeredGridLayoutManager的特殊性可能导致最后显示的item存在多个，所以这里取到的是一个数组
//                        //得到这个数组后再取到数组中position值最大的那个就是最后显示的position值了
//                        int[] lastPositions = new int[((StaggeredGridLayoutManager) layoutManager).getSpanCount()];
//                        ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(lastPositions);
//                        lastPosition = findMax(lastPositions);
//                    }
//
//                    //时判断界面显示的最后item的position是否等于itemCount总数-1也就是最后一个item的position
//                    //如果相等则说明已经滑动到最后了
//                    if (lastPosition == recyclerView.getLayoutManager().getItemCount() - 1) {
//                        isBottom = true;
//                    }
//
//                }
//            }
//
//
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                if (isBottom) {
//                    page++;
//                    activity.updateData(page);
//                }
//            }
//        });
//
//    }
//
//    private int findMax(int[] lastPositions) {
//        int max = lastPositions[0];
//        for (int value : lastPositions) {
//            if (value > max) {
//                max = value;
//            }
//        }
//        return max;
//    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        Object o = data.get(position);
        if (o instanceof BrandDetailBean.BodyBrandDetailBean.BrandDetailBrandDetailBean) {
            return 0;
        } else if (o instanceof HotProductsOfBrandDetailBean) {
            return 2;
        } else {
            return 1;
        }
    }


    class FirstViewHolder extends RecyclerView.ViewHolder {
        TextView logo_title, summary;
        ImageView image_logo;
        ImageView loadMore;
        LinearLayout layout;
        ImageView image;

        public FirstViewHolder(View itemView) {
            super(itemView);
            logo_title = (TextView) itemView.findViewById(R.id.logo_title_type1);
            summary = (TextView) itemView.findViewById(R.id.summary_brand_type1);
            image_logo = (ImageView) itemView.findViewById(R.id.logo_brand_type1);
            layout = (LinearLayout) itemView.findViewById(R.id.layout_summary_brand_type1);
            image = (ImageView) itemView.findViewById(R.id.image_logo_brand_type1);
            loadMore = (ImageView) itemView.findViewById(R.id.load_more_summary);
        }
    }

    class SecondViewHolder extends RecyclerView.ViewHolder {

        TextView title_product;
        ImageView next_page;
        RecyclerView recyclerView;

        public SecondViewHolder(View itemView) {
            super(itemView);

            recyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_type2_brand_detail);
            next_page = (ImageView) itemView.findViewById(R.id.go_type2_brand_detail);
            title_product = (TextView) itemView.findViewById(R.id.title_type2_brand_detail);

        }
    }


}
