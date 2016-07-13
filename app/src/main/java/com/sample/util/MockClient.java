package com.sample.util;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.sample.model.Eligibility;
import com.sample.model.Reward;

import java.io.IOException;
import java.util.Collections;

import retrofit.client.Client;
import retrofit.client.Request;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;
import timber.log.Timber;

import static com.sample.util.Constant.REWARD_MOVIES;
import static com.sample.util.Constant.REWARD_MUSIC;
import static com.sample.util.Constant.REWARD_NOT_APPLICABLE;
import static com.sample.util.Constant.REWARD_SPORTS;

public class MockClient implements Client {

    private static final String KEY_ACCOUNT_NO = "account";
    private static final String KEY_CHANNEL_ID = "id";

    private static final String PATH_REWARD = "/reward";
    private static final String PATH_ELIGIBILITY = "/eligibility";

    private static final int STATUS = 200;

    @Override
    public Response execute(Request request) throws IOException {
        Uri uri = Uri.parse(request.getUrl());

        if (uri.getPath().equals(PATH_ELIGIBILITY)) {
            return getEligibilityResponse(request, uri);
        }

        if (uri.getPath().equals(PATH_REWARD)) {
            return getRewardResponse(request, uri);
        }

        return contructResponse(request.getUrl(), null);
    }

    @NonNull
    private Response getRewardResponse(Request request, Uri uri) {
        int channelId = ChannelType.INVALID.getCode();
        try {
            channelId = Integer.parseInt(uri.getQueryParameter(KEY_CHANNEL_ID));
        } catch (NumberFormatException e) {
            Timber.e(e.getMessage());
        }

        Reward reward = null;
        // TODO: should have done own bounds checking
        ChannelType type = ChannelType.values()[channelId - 1];

        switch (type) {
            case KIDS:
                reward = new Reward(REWARD_NOT_APPLICABLE);
                break;

            case MOVIES:
                reward = new Reward(REWARD_MOVIES);
                break;

            case MUSIC:
                reward = new Reward(REWARD_MUSIC);
                break;

            case NEWS:
                reward = new Reward(REWARD_NOT_APPLICABLE);
                break;

            case SPORTS:
                reward = new Reward(REWARD_SPORTS);
                break;
        }

        String rewardJson = reward.toJson();
        return contructResponse(request.getUrl(), rewardJson);
    }

    @NonNull
    private Response getEligibilityResponse(Request request, Uri uri) {
        int accountNo = EligibilityType.INVALID_ACCOUNT.getCode();
        try {
            accountNo = Integer.parseInt(uri.getQueryParameter(KEY_ACCOUNT_NO));
        } catch (NumberFormatException e) {
            Timber.e(e.getMessage());
        }

        Eligibility eligibility = null;
        // TODO: should have done own bounds checking
        EligibilityType type = EligibilityType.values()[accountNo - 1];

        /**
         * TODO: Could have implemented a better logic for checking use account number and
         * set some rules for it but for now it's just hard coded integer values.
         */
        switch (type) {
            case CUSTOMER_ELIGIBLE:
                eligibility = new Eligibility(EligibilityType.CUSTOMER_ELIGIBLE.getCode(),
                        "Customer is eligible",
                        "Return relevant rewards according to the customer's portfolio");
                break;

            case CUSTOMER_INELIGIBLE:
                eligibility = new Eligibility(EligibilityType.CUSTOMER_INELIGIBLE.getCode(),
                        "Customer is not eligible",
                        "Return no rewards");
                break;

            case INVALID_ACCOUNT:
                eligibility = new Eligibility(EligibilityType.INVALID_ACCOUNT.getCode(),
                        "The Supplied account number is invalid",
                        "Return no rewards and notify the client that the account number is invalid");
                break;

            case TECHNICAL_FAILURE:
                eligibility = new Eligibility(EligibilityType.TECHNICAL_FAILURE.getCode(),
                        "Service technical failure",
                        "Return no rewards");
                break;
        }

        String eligibilityJson = eligibility.toJson();
        return contructResponse(request.getUrl(), eligibilityJson);
    }

    private Response contructResponse(String url, String json) {
        return new Response(url, STATUS, "", Collections.EMPTY_LIST, new TypedByteArray("application/json", json.getBytes()));
    }

    public Response __testEligibility(Request request) {
        return getEligibilityResponse(request, Uri.parse(request.getUrl()));
    }

    public Response __testReward(Request request) {
        return getRewardResponse(request, Uri.parse(request.getUrl()));
    }
}