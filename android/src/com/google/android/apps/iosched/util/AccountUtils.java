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

package com.google.android.apps.iosched.util;

import com.google.android.apps.iosched.calendar.SessionCalendarService;
import com.google.android.apps.iosched.provider.ScheduleContract;
import com.google.android.apps.iosched.ui.AccountActivity;
import com.google.android.gcm.GCMRegistrar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.widget.Toast;
import at.devfest.Setup;
import at.devfest.app.R;

import static com.google.android.apps.iosched.util.LogUtils.LOGE;
import static com.google.android.apps.iosched.util.LogUtils.LOGI;
import static com.google.android.apps.iosched.util.LogUtils.makeLogTag;

/**
 * An assortment of authentication and login helper utilities.
 */
public class AccountUtils {
    private static final String TAG = makeLogTag(AccountUtils.class);

    private static final String PREF_CHOSEN_ACCOUNT = "chosen_account";

    public static boolean isAuthenticated(final Context context) {
        return !TextUtils.isEmpty(getChosenAccountName(context));
    }

    public static String getChosenAccountName(final Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getString(PREF_CHOSEN_ACCOUNT, null);
    }

    public static void setChosenAccountName(final Context context, final String accountName) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
                        sp.edit().putString(PREF_CHOSEN_ACCOUNT, accountName).commit();
    }

    public static void signOut(final Context context) {
        // Clear out all Google I/O-created sessions from Calendar
        if (UIUtils.hasICS()) {
            LOGI(TAG, "Clearing all sessions from Google Calendar using SessionCalendarService.");
            Toast.makeText(context, R.string.toast_deleting_sessions_from_calendar,
                    Toast.LENGTH_LONG).show();
            context.startService(
                    new Intent(SessionCalendarService.ACTION_CLEAR_ALL_SESSIONS_CALENDAR)
                            .setClass(context, Setup.getSessionCalendarServiceClass())
                            .putExtra(SessionCalendarService.EXTRA_ACCOUNT_NAME,
                                    getChosenAccountName(context)));
        }

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().clear().commit();
        context.getContentResolver().delete(ScheduleContract.BASE_CONTENT_URI, null, null);
        GCMRegistrar.unregister(context);
    }

    public static void startAuthenticationFlow(final Context context, final Intent finishIntent) {
        Intent loginFlowIntent = new Intent(context, Setup.AccountActivityClass);
        loginFlowIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        loginFlowIntent.putExtra(AccountActivity.EXTRA_FINISH_INTENT, finishIntent);
        context.startActivity(loginFlowIntent);
    }
}
