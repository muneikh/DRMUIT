package com.sample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.sample.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.sample.util.Constant.REWARD_MOVIES;
import static com.sample.util.Constant.REWARD_MUSIC;
import static com.sample.util.Constant.REWARD_NOT_APPLICABLE;
import static com.sample.util.Constant.REWARD_SPORTS;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    @Test
    public void testRewardKids() {
        onView(withId(R.id.rewardKids)).perform(click());
        onView(withId(R.id.tvResponse)).check(matches(withText(REWARD_NOT_APPLICABLE)));
    }

    @Test
    public void testRewardSports() {
        onView(withId(R.id.rewardSports)).perform(click());
        onView(withId(R.id.tvResponse)).check(matches(withText(REWARD_SPORTS)));
    }

    @Test
    public void testRewardMovies() {
        onView(withId(R.id.rewardMovies)).perform(click());
        onView(withId(R.id.tvResponse)).check(matches(withText(REWARD_MOVIES)));
    }

    @Test
    public void testRewardMusic() {
        onView(withId(R.id.rewardMusic)).perform(click());
        onView(withId(R.id.tvResponse)).check(matches(withText(REWARD_MUSIC)));
    }

    @Test
    public void testRewardNews() {
        onView(withId(R.id.rewardNews)).perform(click());
        onView(withId(R.id.tvResponse)).check(matches(withText(REWARD_NOT_APPLICABLE)));
    }
}