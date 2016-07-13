package com.sample;

import com.sample.model.Eligibility;
import com.sample.model.Reward;
import com.sample.util.ChannelType;
import com.sample.util.EligibilityType;
import com.sample.util.MockClient;
import com.sample.util.Utility;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.Collections;

import retrofit.client.Header;
import retrofit.client.Request;
import retrofit.client.Response;

import static com.sample.util.Constant.BASE_URL;
import static com.sample.util.Constant.REWARD_MOVIES;
import static com.sample.util.Constant.REWARD_MUSIC;
import static com.sample.util.Constant.REWARD_NOT_APPLICABLE;
import static com.sample.util.Constant.REWARD_SPORTS;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class ServiceUnitTest {

    /**
     * Not all the test cases are covered by most of them. For instance there could be a test for invalid data and other code in the
     * demo app. Have only written unit tests. Unfortunately, did not get enough time to write integration tests.
     */

    private MockClient mMockClient;

    @Before
    public void setUp() throws Exception {
        mMockClient = new MockClient();
    }

    @Test
    public void testRewardService() throws Exception {
        Reward reward = getReward(ChannelType.KIDS.getCode());
        assertNotNull(reward);
    }

    @Test
    public void testRewardKid() throws Exception {
        Reward reward = getReward(ChannelType.KIDS.getCode());
        assertEquals(reward.getDescription(), REWARD_NOT_APPLICABLE);
    }

    @Test
    public void testRewardSports() throws Exception {
        Reward reward = getReward(ChannelType.SPORTS.getCode());
        assertEquals(reward.getDescription(), REWARD_SPORTS);
    }

    @Test
    public void testRewardMusic() throws Exception {
        Reward reward = getReward(ChannelType.MUSIC.getCode());
        assertEquals(reward.getDescription(), REWARD_MUSIC);
    }

    @Test
    public void testRewardNews() throws Exception {
        Reward reward = getReward(ChannelType.NEWS.getCode());
        assertEquals(reward.getDescription(), REWARD_NOT_APPLICABLE);
    }

    @Test
    public void testRewardMovies() throws Exception {
        Reward reward = getReward(ChannelType.MOVIES.getCode());
        assertEquals(reward.getDescription(), REWARD_MOVIES);
    }

    @Test
    public void testEligibilityService() throws Exception {
        Eligibility eligibility = checkEligibility(EligibilityType.CUSTOMER_ELIGIBLE.getCode());
        assertNotNull(eligibility);
    }

    @Test
    public void testUserEligible() throws Exception {
        Eligibility eligibility = checkEligibility(EligibilityType.CUSTOMER_ELIGIBLE.getCode());
        assertEquals(eligibility.getCode(), EligibilityType.CUSTOMER_ELIGIBLE.getCode());
    }

    @Test
    public void testUserIneligible() throws Exception {
        Eligibility eligibility = checkEligibility(EligibilityType.CUSTOMER_INELIGIBLE.getCode());
        assertEquals(eligibility.getCode(), EligibilityType.CUSTOMER_INELIGIBLE.getCode());
    }

    @Test
    public void testUserInvalidAccount() throws Exception {
        Eligibility eligibility = checkEligibility(EligibilityType.INVALID_ACCOUNT.getCode());
        assertEquals(eligibility.getCode(), EligibilityType.INVALID_ACCOUNT.getCode());
    }

    @Test
    public void testTechnicalFailure() throws Exception {
        Eligibility eligibility = checkEligibility(EligibilityType.TECHNICAL_FAILURE.getCode());
        assertEquals(eligibility.getCode(), EligibilityType.TECHNICAL_FAILURE.getCode());
    }


    private Reward getReward(int rewardId) {
        Response response = mMockClient.__testReward(new Request("GET", BASE_URL + "/reward?id=" + rewardId, Collections.<Header>emptyList(), null));
        String json = Utility.convertTypeInputToString(response.getBody());
        return Reward.fromJson(json);
    }

    private Eligibility checkEligibility(int accountNumber) {
        Response response = mMockClient.__testEligibility(new Request("GET", BASE_URL + "/eligibility?account=" + accountNumber, Collections.<Header>emptyList(), null));
        String json = Utility.convertTypeInputToString(response.getBody());
        return Eligibility.fromJson(json);
    }

}