package com.sample.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.sample.App;
import com.sample.R;
import com.sample.manager.HttpManager;
import com.sample.model.ResponseModel;
import com.sample.service.ApiService;
import com.sample.util.ChannelType;
import com.sample.util.EligibilityType;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.observables.AndroidObservable;
import timber.log.Timber;

public class MainActivity extends BaseActivity {

    @Bind(R.id.tvResponse)
    TextView mResponseTextView;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Inject
    ApiService mApiService;

    @Inject
    HttpManager mHttpManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App) getApplication()).getComponent().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
    }

    private int accountNumber = EligibilityType.CUSTOMER_ELIGIBLE.getCode();

    @OnClick(R.id.rewardKids)
    public void rewardKids() {
        createRequest(accountNumber, ChannelType.KIDS.getCode());
    }

    @OnClick(R.id.rewardMovies)
    public void rewardMovies() {
        createRequest(accountNumber, ChannelType.MOVIES.getCode());
    }

    @OnClick(R.id.rewardMusic)
    public void rewardMusic() {
        createRequest(accountNumber, ChannelType.MUSIC.getCode());
    }

    @OnClick(R.id.rewardNews)
    public void rewardNews() {
        createRequest(accountNumber, ChannelType.NEWS.getCode());
    }

    @OnClick(R.id.rewardSports)
    public void rewardSports() {
        createRequest(accountNumber, ChannelType.SPORTS.getCode());
    }

    private void createRequest(int accountNumber, int channelId) {
        rx.Observable observable = mHttpManager.getRewards(accountNumber, channelId);

        if (observable == null) {
            Toast.makeText(this, "Invalid data", Toast.LENGTH_SHORT).show();
            return;
        }

        AndroidObservable.bindActivity(this, observable)
                .subscribe(new Subscriber<ResponseModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e.getMessage());
                    }

                    @Override
                    public void onNext(ResponseModel responseModel) {
                        updateRewards(responseModel);
                    }
                });
    }

    private void updateRewards(ResponseModel responseModel) {
        if (responseModel == null) {
            Timber.d("unable to get rewards");
            return;
        }

        Timber.d("Eligibility  :: " + responseModel.getEligibility().toJson());
        Timber.d("Reward :: " + responseModel.getReward().toJson());


        if (responseModel.getEligibility().getCode() == EligibilityType.CUSTOMER_ELIGIBLE.getCode()) {
            mResponseTextView.setText(responseModel.getReward().getDescription());
            Timber.d("rewards received :: " + responseModel.getReward().toJson());
        }

    }

}
