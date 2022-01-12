package com.example.rvcustomadapterloadingmore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rvcustomadapterloadingmore.R;
import com.example.rvcustomadapterloadingmore.listener.OnBottomReachedListener;
import com.example.rvcustomadapterloadingmore.model.Member;

import java.util.ArrayList;


public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM_HEADER = 0;
    private static final int TYPE_ITEM_YES_VIEW = 1;
    private static final int TYPE_ITEM_NOT_VIEW = 2;
    private static final int TYPE_ITEM_FOOTER = 3;

    private Context context;
    private ArrayList<Member> members;
    private OnBottomReachedListener listener;

    public CustomAdapter(Context context, ArrayList<Member> members, OnBottomReachedListener listener) {
        this.context = context;
        this.members = members;
        this.listener = listener;
    }


    public CustomAdapter(Context context, ArrayList<Member> members) {   // Interface siz ohirgi elementni aniqlab yangi elementlar qo`shish uchun Constructor
        this.context = context;
        this.members = members;
    }


    @Override
    public int getItemViewType(int position) {
        if (isHeader(position)) return TYPE_ITEM_HEADER;
        if (isFooter(position)) return TYPE_ITEM_FOOTER;

        Member member = members.get(position);
        if (member.isAvailable()) return TYPE_ITEM_YES_VIEW;

        return TYPE_ITEM_NOT_VIEW;
    }


    public boolean isHeader(int position) {
        return position == 0;
    }


    public boolean isFooter(int position) {
        return position == (members.size() - 1);
    }


    public void addMemberList(ArrayList<Member> memberList){
        members.addAll(memberList);
        notifyDataSetChanged();   // RecyclerView ni qayta yuklash.
    }


    @Override
    public int getItemCount() {
        return members.size();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM_HEADER) {
            View viewHeader = LayoutInflater.from(context).inflate(R.layout.item_custom_layout_header, parent, false);
            return new MemberViewHeaderHolder(viewHeader);
        } else if (viewType == TYPE_ITEM_YES_VIEW) {
            View viewYes = LayoutInflater.from(context).inflate(R.layout.item_custom_layout_yes, parent, false);
            return new MemberViewYesHolder(viewYes);
        } else if (viewType == TYPE_ITEM_NOT_VIEW) {
            View viewNot = LayoutInflater.from(context).inflate(R.layout.item_custom_layout_not, parent, false);
            return new MemberViewNotHolder(viewNot);
        }

        View viewFooter = LayoutInflater.from(context).inflate(R.layout.item_custom_layout_footer, parent, false);
        return new MemberViewFooterHolder(viewFooter);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (position == members.size() - 1){
            listener.onBottomReached(position);
        }

        if (isHeader(position) || isFooter(position)) return;

        Member member = members.get(position);

        if (holder instanceof MemberViewYesHolder) {
            TextView firstN = ((MemberViewYesHolder) holder).firstName;
            TextView lastN = ((MemberViewYesHolder) holder).lastName;
            ImageView image = ((MemberViewYesHolder) holder).image;

            firstN.setText(member.getFirstName());
            lastN.setText(member.getLastName());
            image.setImageResource(member.getImage());
        }

        if (holder instanceof MemberViewNotHolder) {
            TextView firstN = ((MemberViewNotHolder) holder).firstName;
            TextView lastN = ((MemberViewNotHolder) holder).lastName;
            ImageView image = ((MemberViewNotHolder) holder).image;

            firstN.setText(member.getFirstName());
            lastN.setText(member.getLastName());
            image.setImageResource(member.getImage());
        }

    }


    private class MemberViewHeaderHolder extends RecyclerView.ViewHolder {
        public View view;

        public MemberViewHeaderHolder(View v) {
            super(v);
            this.view = v;
            // Add your UI Components here
        }
    }


    private class MemberViewYesHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView firstName;
        public TextView lastName;
        public ImageView image;

        public MemberViewYesHolder(View v) {
            super(v);
            this.view = v;
            // Add your UI Components here
            firstName = view.findViewById(R.id.first_name_yes);
            lastName = view.findViewById(R.id.last_name_yes);
            image = view.findViewById(R.id.image_view_yes);
        }
    }


    private class MemberViewNotHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView firstName;
        public TextView lastName;
        public ImageView image;

        public MemberViewNotHolder(View v) {
            super(v);
            this.view = v;
            // Add your UI Components here
            firstName = view.findViewById(R.id.first_name_not);
            lastName = view.findViewById(R.id.last_name_not);
            image = view.findViewById(R.id.image_view_not);
        }
    }


    private class MemberViewFooterHolder extends RecyclerView.ViewHolder {
        public View view;

        public MemberViewFooterHolder(View v) {
            super(v);
            this.view = v;
            // Add your UI Components here
        }
    }


}
