package com.lap.bellapp.bellapp_android.ui.presenters.Account;

import com.lap.bellapp.bellapp_android.ui.model.UserDetail;
import com.lap.bellapp.bellapp_android.ui.presenters.Presenter;
import com.lap.bellapp.bellapp_android.ui.view.AccountView;

/**
 * Created by juangarcia on 12/28/15.
 */
public interface AccountPresenter extends Presenter{
    public void loadAccountDetails(int userId);
    public void configureAccountView(AccountView accountView);
    public void updateAccountDetails(UserDetail userDetails);
}
