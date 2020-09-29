package util;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.example.LifestyleApp.GoalManager.GoalManagerFragment;
import com.example.LifestyleApp.ItemDetail.ItemDetailFragment;
import com.example.LifestyleApp.UserInfo.User;

public class GetFragmentUtil {
    GoalManagerFragment mGoalFragment;


    public Fragment getFragment(String detail, Bundle bundle, User user, String goalWeight){
        if(detail.equals("Hikes")){

        }else if(detail.equals("Goal")){
            mGoalFragment = new GoalManagerFragment();
            Bundle bundle1 = new Bundle();
            bundle1.putSerializable("user", user);
            mGoalFragment.setArguments(bundle1);
            //fragment.sendGoalWeight(goalWeight);
            return mGoalFragment;

        }

        Fragment fragment = new ItemDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public void passGoalWeight(String data) {

//        System.out.println(data);
        mGoalFragment.sendGoalWeight(data);
    }
}
