package com.example.uitask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uitask.Adapter.FriendsAdapter;
import com.example.uitask.Adapter.RedeemAdapter;
import com.example.uitask.Adapter.InvitedFriendsAdapter;
import com.example.uitask.Model.Friend;
import com.example.uitask.Model.RedeemDetails;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FrndsInviteInterface{

    private RecyclerView mRcredeemtion;
    private  RecyclerView mRcinvitedFriends;
    public ArrayList<RedeemDetails> redeemDetails;
    public  FriendsAdapter friendsAdapter;
    private TextView inviteCount;

    public String[]  brandNames= { "big bazaar", "life style" };
    public String[]  brandPoints = { "200 pts" , "100 pts" };
    public int[] brandImages = { R.drawable.bb , R.drawable.ls };
    public int sizeRedeem = brandImages.length;

    public int[] invitedDP = {R.drawable.dp1 , R.drawable.dp2 , R.drawable.dp3 , R.drawable.dp4};
    public String[] frndNames= { "Diana" ,"Merlin" , "Mike" , "Jordan", "Karan", "Diana" , "Merlin" , "Mike" , "jordan"};
    public String[] frndFrom = {"Contacts" , "Instagram" , "Facebook" ,"Contacts" , "Instagram" , "Facebook" , "Contacts" , "Instagram" , "Facebook"};
    public  int[] frndDp = {R.drawable.dp5 , R.drawable.dp6 , R.drawable.dp7 , R.drawable.dp8 , R.drawable.dp1 , R.drawable.dp5 , R.drawable.dp6 , R.drawable.dp7 , R.drawable.dp8 };

    public ArrayList<Friend> friends;
    public  ArrayList<Friend> invitedFriends;
    public  InvitedFriendsAdapter invitedFriendsAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inviteCount = findViewById(R.id.tv_inviteCount_home);

        ImageView addFrnd = findViewById(R.id.iv_add_friend_home);
        addFrnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBtmSht();
            }
        });

        mRcredeemtion = findViewById(R.id.rcv_redeem_home);
        mRcredeemtion.setLayoutManager(new LinearLayoutManager(MainActivity.this , LinearLayoutManager.VERTICAL, false));
         redeemDetails = new ArrayList<>();
        for(int i = 0;i<sizeRedeem ; i++){
            RedeemDetails redeemDetail = new RedeemDetails(brandImages[i] , brandNames[i] , brandPoints[i]);
            redeemDetails.add(redeemDetail);
        }
        RedeemAdapter redeemAdapter = new RedeemAdapter(MainActivity.this , redeemDetails);
        mRcredeemtion.setAdapter(redeemAdapter);


        invitedFriends = new ArrayList<>();
        for( int i=0;i<invitedDP.length;i++){
            Friend friend = new Friend(frndNames[i], frndFrom[i], invitedDP[i]);
            invitedFriends.add(friend);
        }
        mRcinvitedFriends = findViewById(R.id.rcv_invited_friends);
        mRcinvitedFriends.setLayoutManager(new LinearLayoutManager(MainActivity.this , LinearLayoutManager.HORIZONTAL, false));
         invitedFriendsAdapter = new InvitedFriendsAdapter(MainActivity.this , invitedFriends);
        mRcinvitedFriends.setAdapter(invitedFriendsAdapter);
        int len = invitedFriends.size();
        inviteCount.setText("Total invite :" + String.valueOf(len) );



        friends = new ArrayList<>();
        for( int i=0;i<frndDp.length;i++){
            Friend friend = new Friend(frndNames[i] , frndFrom[i] , frndDp[i]);
            friends.add(friend);
        }
        friendsAdapter = new FriendsAdapter(this , friends , this );

    }

    private void showBtmSht() {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this , R.style.CustomBottomSheetDialogTheme);
        bottomSheetDialog.setContentView(R.layout.buttom_sheet_layout);

        EditText mEtsearch = bottomSheetDialog.findViewById(R.id.et_search_bmst);
        assert mEtsearch != null;
        mEtsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = editable.toString();
                ArrayList<Friend> filterFrnds = new ArrayList<>();

                for(Friend friend :friends){
                    if(friend.name.toLowerCase().contains(text.toLowerCase()) || friend.from.toLowerCase().contains(text.toLowerCase())){
                        filterFrnds.add(friend);
                    }
                }
                friendsAdapter.filter(filterFrnds);
            }
        });

        RecyclerView mRcfrinds = bottomSheetDialog.findViewById(R.id.rcv_invite_friends_bst);
        assert mRcfrinds != null;
        mRcfrinds.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.VERTICAL , false));
        mRcfrinds.setAdapter(friendsAdapter);

        bottomSheetDialog.show();
    }


    @Override
    public void onInviteClicked(int position) {
        Toast.makeText(this , friends.get(position).name + " is invited" , Toast.LENGTH_LONG).show();
        Friend friend = friends.get(position);
        invitedFriends.add(friend);
        invitedFriendsAdapter.change(invitedFriends);
        friends.remove(position);
        friendsAdapter.filter(friends);
        inviteCount.setText("Total invites : " + String.valueOf(invitedFriends.size()));
    }
}