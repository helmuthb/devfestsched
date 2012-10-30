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

import com.google.api.client.googleapis.extensions.android2.auth.GoogleAccountManager;

import android.accounts.AccountManager;
import android.content.Context;
import android.text.TextUtils;
import static com.google.android.apps.iosched.util.LogUtils.makeLogTag;

/**
 * An assortment of authentication and login helper utilities.
 */
public class AccountUtils {
    private static final String TAG = makeLogTag(AccountUtils.class);

    public static boolean isAuthenticated(final Context context) {
        return !TextUtils.isEmpty(getChosenAccountName(context));
    }

    public static String getChosenAccountName(final Context context) {
        return AccountManager.get(context).getAccountsByType(GoogleAccountManager.ACCOUNT_TYPE)[0].name;
    }
}
