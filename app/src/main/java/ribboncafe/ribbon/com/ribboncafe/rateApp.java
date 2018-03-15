package ribboncafe.ribbon.com.ribboncafe;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.stepstone.apprating.AppRatingDialog;
import com.stepstone.apprating.listener.RatingDialogListener;

import java.util.Arrays;

import Commonpack.Common;
import Database.Database;
import Model.Rating;

public class rateApp extends AppCompatActivity implements RatingDialogListener {

    DatabaseReference ratingTbl;
    FirebaseDatabase database;
    DatabaseReference foods;
    FloatingActionButton btncart;
    FirebaseAuth user=FirebaseAuth.getInstance();
    String email=user.getCurrentUser().getEmail();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_app);

        database= FirebaseDatabase.getInstance();
        foods=database.getReference("Foods");
        ratingTbl=database.getReference("Rating");

        ratingBar=(RatingBar)findViewById(R.id.ratingBox);
        getRatingFood(foodId);
    }

    public void showRatingDialog(){
        new AppRatingDialog().Builder()
                .setPositiveButtonText("submit")
                .setNegativeButtonText("cancel")
                .setNoteDescriptions(Arrays.asList("Very Bad","Not Good","Quite Ok","Very Good","Excellent"))
                .setDefaultRating(0)
                .setTitle("Rate this Food")
                .setDescription("Please select some stars and give some feedback")
                .setTitleTextColor(R.color.colorPrimary)
                .setDescriptionTextColor(R.color.colorPrimary)
                .setHint("Please write your comment here")
                .setHintTextColor(R.color.colorAccent)
                .setCommentTextColor(android.R.color.white)
                .setCommentbackgroundColor(R.color.colorPrimary)
                .setWindowAnimation(R.style.RatingDialogFadeAnim)
                .create(rateApp.this)
                .show();
    }

    @Override
    public void onPositiveButtonClicked(int i, String s) {

        final Rating rating=new Rating(email,foodId,String.valueOf(value),comments);

        ratingTbl.child(email).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(email).exists()){
                    ratingTbl.child(email).removeValue();
                }
                else{
                    ratingTbl.child(email).setValue(rating);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        })

    }

    @Override
    public void onNegativeButtonClicked() {

    }
    public void getRatingFood(String foodId){
        com.google.firebase.database.Query foodRating= ratingTbl.orderByChild("foodId").equalTo(foodId);
        foodRating.addValueEventListener(new ValueEventListener() {

            int count=0,sum=0;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot:dataSnapshot.getChildren()){
                    Rating item=postSnapshot.getValue(Rating.class);
                    sum+=Integer.parseInt(item.getRateValue());
                    count++;
                }
                float average=sum/count;
                ratingBar.setRating(average);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
