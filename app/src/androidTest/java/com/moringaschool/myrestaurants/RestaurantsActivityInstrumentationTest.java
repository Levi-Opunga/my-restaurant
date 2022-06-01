package com.moringaschool.myrestaurants;



import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.not;

import com.moringaschool.myrestaurants.ui.RestaurantsActivity;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RestaurantsActivityInstrumentationTest {
    @Rule
    public ActivityScenarioRule<RestaurantsActivity> activityTestRule =
            new ActivityScenarioRule<>(RestaurantsActivity.class);
    private View activityDecorView;
    @Before
    public void setUp() {
        activityTestRule.getScenario().onActivity(new ActivityScenario.ActivityAction<RestaurantsActivity>() {
            @Override
            public void perform(RestaurantsActivity activity) {
                activityDecorView = activity.getWindow().getDecorView();
            }
        });
    }
    @Test
    public void listItemClickDisplaysToastWithCorrectRestaurant() {
        String restaurantName = "Mi Mero Mole \nServes great: "+"Vegan Food";
        onData(anything())
                .inAdapterView(withId(R.id.listView))
                .atPosition(0)
                .perform(click());
        onView(withText(restaurantName)).inRoot(withDecorView(not(activityDecorView)))
                .check(matches(withText(restaurantName)));
    }
}
