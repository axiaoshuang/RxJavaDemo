package com.win.rxjavademo.ui;


import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.win.rxjavademo.R;
import com.win.rxjavademo.base.BaseActivity;
import com.win.rxjavademo.net.ApiInfo;
import retrofit2.Response;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends BaseActivity {
    private CompositeSubscription mCompositeSubscription;
    @BindView(R.id.textInfo)
    TextView textInfo;

    @Override
    public int getView() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        mCompositeSubscription=new CompositeSubscription();
    }

    private void getContent() {
                ApiInfo.getmService().getContent(1).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Response<String> stringResponse) {
                        textInfo.setText(stringResponse.body());
                    }
                });
    }

    @OnClick(R.id.getInfo)
    public void getInfo(){
        getContent();
    }
}
