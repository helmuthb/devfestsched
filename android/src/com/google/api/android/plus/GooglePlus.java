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

package com.google.api.android.plus;

import android.content.Context;

/**
 * Stub until the release of <a href="https://developers.google.com/android/google-play-services/">
 * Google Play Services.</a>
 */
public final class GooglePlus {
	public static final int PLUS_ONE_REQUEST_CODE = 1200;
	public static final int REQUEST_CODE_RESOLVE_FAILURE = 1201;
	public static final int REQUEST_CODE_RESOLVE_MISSING_GP = 1202;
			
    public static GooglePlus initialize(Context context, String apiKey, String clientId) {
        return new GooglePlus();
    }
}
