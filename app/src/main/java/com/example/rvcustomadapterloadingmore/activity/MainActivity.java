package com.example.rvcustomadapterloadingmore.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.rvcustomadapterloadingmore.R;
import com.example.rvcustomadapterloadingmore.adapter.CustomAdapter;
import com.example.rvcustomadapterloadingmore.listener.OnBottomReachedListener;
import com.example.rvcustomadapterloadingmore.model.Member;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private RecyclerView recyclerView;
    private CustomAdapter adapter;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        ArrayList<Member> members = prepareMemberList();
        refreshAdapter(members);
    }


    private void initViews() {
        context = this;
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 1));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // Interface siz ohirgi elementni aniqlab yangi elementlar qo`shish, tayyor function
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int totalItemCount = layoutManager.getItemCount();
                int lastVisible = layoutManager.findLastVisibleItemPosition();

                boolean endHasBeenReached = lastVisible + 5 >= totalItemCount;
                if (totalItemCount > 0 && endHasBeenReached){
                    adapter.addMemberList(prepareAddMember());
                }
            }
        });

    }


    private void refreshAdapter(ArrayList<Member> members) {

        adapter = new CustomAdapter(context, members);

/*        adapter = new CustomAdapter(context, members, new OnBottomReachedListener() {
            @Override
            public void onBottomReached(int position) {
                Log.d("@@@", "@@position: " + position);
                if (count < 3) {
                    //recyclerView.getLayoutManager().scrollToPosition(position);  // RecyclerViewni birdaniga aytilgan positionga o`tkazadi.
                    //recyclerView.smoothScrollToPosition(position);  // RecyclerViewni bitta-bitadan aytilgan positionga o`tkazadi.
                    adapter.addMemberList(prepareAddMember());
                    recyclerView.post(new Runnable() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                        }
                    });
                    count++;
                }
            }
        });

 */

        recyclerView.setAdapter(adapter);
    }


    private ArrayList<Member> prepareMemberList() {
        ArrayList<Member> members = new ArrayList<>();
        members.add(new Member()); // For Header
        for (int i = 1; i < 31; i++) {
            if (i == 4 || i == 10 || i == 13 || i == 16 || i == 19 || i == 22 || i == 25 || i == 29) {
                members.add(new Member(i + ")Selena", i + ")Gomez", R.drawable.selena, false));
            } else {
                members.add(new Member(i + ")Michael", i + ")Jackson", R.drawable.michael, true));
            }
        }
        members.add(new Member()); // For Footer
        return members;
    }


    private ArrayList<Member> prepareAddMember() {
        ArrayList<Member> members = new ArrayList<>();
        members.add(new Member()); // For Header
        for (int i = 1; i < 21; i++) {
            if (i == 4 || i == 7 || i == 12 || i == 16 || i == 19) {
                members.add(new Member(i + ")Amanda", i + ")Nunes", R.drawable.amanda, false));
            } else {
                members.add(new Member(i + ")Jone", i + ")Uik", R.drawable.jone, true));
            }
        }
        members.add(new Member()); // For Footer
        return members;
    }


}
