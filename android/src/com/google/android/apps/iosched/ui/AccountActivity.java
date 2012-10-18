/*
 * Copyright 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.apps.iosched.ui;

import com.google.android.apps.iosched.provider.ScheduleContract;
import com.google.android.apps.iosched.util.AccountUtils;
import com.google.api.client.googleapis.extensions.android2.auth.GoogleAccountManager;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.app.SherlockListFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import at.devfest.app.R;

import java.util.Arrays;
import java.util.List;

import static com.google.android.apps.iosched.util.LogUtils.makeLogTag;

/**
 * The first activity most users see. This wizard-like activity first presents an account
 * selection fragment ({@link ChooseAccountFragment}), and then an authentication progress fragment
 * ({@link AuthProgressFragment}).
 */
public class AccountActivity extends SherlockFragmentActivity {

    private static final String TAG = makeLogTag(AccountActivity.class);

    public static final String EXTRA_FINISH_INTENT
            = "com.google.android.iosched.extra.FINISH_INTENT";

    private static final int REQUEST_AUTHENTICATE = 100;

    private final Handler mHandler = new Handler();

    private Account mChosenAccount;
    private Intent mFinishIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_account);

        if (getIntent().hasExtra(EXTRA_FINISH_INTENT)) {
            mFinishIntent = getIntent().getParcelableExtra(EXTRA_FINISH_INTENT);
        }

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, new ChooseAccountFragment(), "choose_account")
                    .commit();
        }
    }
        
    public void onAccountChosen() {
        ContentResolver.setIsSyncable(mChosenAccount, ScheduleContract.CONTENT_AUTHORITY, 1);

        if (mFinishIntent != null) {
            mFinishIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            mFinishIntent.setAction(Intent.ACTION_MAIN);
            mFinishIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(mFinishIntent);
        }

        finish();
    }

    /**
     * A fragment that presents the user with a list of accounts to choose from. Once an account is
     * selected, we move on to the login progress fragment ({@link AuthProgressFragment}).
     */
    public static class ChooseAccountFragment extends SherlockListFragment {
        public ChooseAccountFragment() {
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);
        }

        @Override
        public void onResume() {
            super.onResume();
            reloadAccountList();
        }

        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            inflater.inflate(R.menu.add_account, menu);
            super.onCreateOptionsMenu(menu, inflater);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            if (item.getItemId() == R.id.menu_add_account) {
                Intent addAccountIntent = new Intent(Settings.ACTION_ADD_ACCOUNT);
                addAccountIntent.putExtra(Settings.EXTRA_AUTHORITIES,
                        new String[]{ScheduleContract.CONTENT_AUTHORITY});
                startActivity(addAccountIntent);
                return true;
            }
            return super.onOptionsItemSelected(item);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            ViewGroup rootView = (ViewGroup) inflater.inflate(
                    R.layout.fragment_login_choose_account, container, false);
            TextView descriptionView = (TextView) rootView.findViewById(R.id.choose_account_intro);
            descriptionView.setText(Html.fromHtml(getString(R.string.description_choose_account)));
            return rootView;
        }

        private AccountListAdapter mAccountListAdapter;

        private void reloadAccountList() {
            if (mAccountListAdapter != null) {
                mAccountListAdapter = null;
            }

            AccountManager am = AccountManager.get(getActivity());
            Account[] accounts = am.getAccountsByType(GoogleAccountManager.ACCOUNT_TYPE);
            mAccountListAdapter = new AccountListAdapter(getActivity(), Arrays.asList(accounts));
            setListAdapter(mAccountListAdapter);
        }

        private class AccountListAdapter extends ArrayAdapter<Account> {
            private static final int LAYOUT_RESOURCE = android.R.layout.simple_list_item_1;

            public AccountListAdapter(Context context, List<Account> accounts) {
                super(context, LAYOUT_RESOURCE, accounts);
            }

            private class ViewHolder {
                TextView text1;
            }

            public View getView(int position, View convertView, ViewGroup parent) {
                ViewHolder holder;

                if (convertView == null) {
                    convertView = getLayoutInflater(null).inflate(LAYOUT_RESOURCE, null);

                    holder = new ViewHolder();
                    holder.text1 = (TextView) convertView.findViewById(android.R.id.text1);

                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }

                final Account account = getItem(position);

                if (account != null) {
                    holder.text1.setText(account.name);
                } else {
                    holder.text1.setText("");
                }

                return convertView;
            }
        }

        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {
            AccountActivity activity = (AccountActivity) getActivity();

            activity.mChosenAccount = mAccountListAdapter.getItem(position);
            AccountUtils.setChosenAccountName(activity, activity.mChosenAccount.name);
            activity.onAccountChosen();
        }
    }
}
