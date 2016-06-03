package com.thedeveloperworldisyours.rotatescreenexpresso;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest

/**
 * Created by javierg on 02/06/16.
 */
public class RouteTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void checkInitialCount() {
        onView(withId(R.id.activity_main_count_text_view))
                .check(matches(withText("0")));
    }

    @Test
    public void increment() {
        onView(withId(R.id.activity_main_increment_button))
                .check(matches(withText(R.string.activity_main_increment)))
                .perform(click())
                .perform(click())
                .perform(click());
        onView(withId(R.id.activity_main_count_text_view))
                .check(matches(withText("3")));
    }

    @Test
    public void incrementTwiceAndRotateScreen() {
        onView(withId(R.id.activity_main_increment_button))
                .check(matches(withText(R.string.activity_main_increment)))
                .perform(click())
                .perform(click())
                .perform(click())
                .perform(click())
                .perform(click());
        onView(withId(R.id.activity_main_count_text_view))
                .check(matches(withText("5")));

        rotateScreen();

        onView(withId(R.id.activity_main_count_text_view))
                .check(matches(withText("5")));
    }

    @Test
    public void noIncrementRotateScreen() {
        rotateScreen();
        onView(withId(R.id.activity_main_count_text_view))
                .check(matches(withText("0")));
    }

    private void rotateScreen() {
        Context context = InstrumentationRegistry.getTargetContext();
        int orientation = context.getResources().getConfiguration().orientation;

        Activity activity = activityRule.getActivity();
        activity.setRequestedOrientation(
                (orientation == Configuration.ORIENTATION_PORTRAIT) ?
                        ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE : ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
}
