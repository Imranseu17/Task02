package com.example.task_02.presenter;

import com.example.task_02.Models.Data;
import com.example.task_02.Models.User;
import com.example.task_02.services.APIClient;
import com.example.task_02.services.APIErrors;
import com.example.task_02.utils.ErrorCode;
import com.example.task_02.utils.UserStatus;
import com.example.task_02.view.UserView;

import java.io.IOException;
import java.net.SocketTimeoutException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

public class UserPresenter {

    private UserView mViewInterface;
    private APIClient mApiClient;

    public UserPresenter(UserView view) {
        this.mViewInterface = view;

        if (this.mApiClient == null) {
            this.mApiClient = new APIClient();
        }
    }

    public void getUsers() {

        mApiClient.getAPI()
                .getUsers()
                .enqueue(new Callback<Data>() {
                    @Override
                    public void onResponse(Call<Data> call, Response<Data> response) {


                        if (response.isSuccessful()) {
                            Data data = response.body();
                            if (data != null) {
                                mViewInterface.onSuccess(data);
                            } else {
                                mViewInterface.onError("Error fetching data", UserStatus.USERS_ERROR.getCode());
                            }
                        } else getErrorMessage(response.code(), response.errorBody());


                    }

                    @Override
                    public void onFailure(Call<Data> call, Throwable e) {
                        e.printStackTrace();
                        if (e instanceof HttpException) {
                            int code = ((HttpException) e).response().code();
                            ResponseBody responseBody = ((HttpException) e).response().errorBody();
                            getErrorMessage(code, responseBody);

                        } else if (e instanceof SocketTimeoutException) {

                            mViewInterface.onError("Server connection error", UserStatus.USERS_ERROR.getCode());
                        } else if (e instanceof IOException) {
                            mViewInterface.onError("IOException", UserStatus.USERS_ERROR.getCode());
                        } else {
                            mViewInterface.onError("Unknown exception", UserStatus.USERS_ERROR.getCode());
                        }
                    }
                });
    }





    private void getErrorMessage(int code, ResponseBody responseBody) {
        ErrorCode errorCode = ErrorCode.getByCode(code);

        if(errorCode != null){
            switch (errorCode) {
                case ERRORCODE500:
                    mViewInterface.onError(APIErrors.get500ErrorMessage(responseBody), UserStatus.USERS_ERROR.getCode());
                    break;
                case ERRORCODE400:
                    mViewInterface.onError(APIErrors.get500ErrorMessage(responseBody), UserStatus.USERS_ERROR.getCode());
                    break;
                default:
                    mViewInterface.onError(APIErrors.getErrorMessage(responseBody), UserStatus.USERS_ERROR.getCode());
            }


        }else{

            mViewInterface.onError("Error occurred Please try again",code);

        }


    }
}
