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

package com.google.android.apps.iosched;

public class Config {
    // OAuth 2.0 related config
	/*
	 * Google+ configuration
	 */
//    public static final String APP_NAME = "Google-IO-Android-App";
//    public static final String API_KEY = "AIzaSyCXjH2cfTuClN0OTbJpF1XnHbhdPQ6wg4Y"; // from the APIs console
//    public static final String CLIENT_ID = "237695054204.apps.googleusercontent.com"; // from the APIs console

    
    public static final String APP_NAME = "GDGDevFest-Android-App";
    public static final String API_KEY = "AIzaSyCsAij0bSMlGHdta3snhfxD4rAOw9WeSDg"; // from the APIs console
    public static final String CLIENT_ID = "903246180582.apps.googleusercontent.com"; // from the APIs console

    private static final String BASE_URL = "http://www.devfest.at/json";
     
    public static final String GET_ALL_SESSIONS_URL      = BASE_URL + "/sessions";
    public static final String GET_ALL_SPEAKERS_URL      = BASE_URL + "/speakers";
    public static final String GET_ALL_ANNOUNCEMENTS_URL = BASE_URL + "/news";
    public static final String GET_ALL_ROOMS_URL         = BASE_URL + "/rooms";
    public static final String GET_COMMON_SLOTS_URL      = BASE_URL + "/slots";
    public static final String GET_ALL_TRACKS_URL        = BASE_URL + "/tracks";
    public static final String GET_ALL_SUGGESTS_URL      = BASE_URL + "/suggests";
    public static final String GET_SANDBOX_URL           = BASE_URL + "/sponsors";
    
    //public static final String BASE_SESSION_URL      =  "https://developers.google.com/events/io/sessions/";
    public static final String BASE_SESSION_URL      =  "http://www.devfest.at/session/";
    
    

    // YouTube API config
    public static final String YOUTUBE_API_KEY = "API_KEY";
    // YouTube share URL
    public static final String YOUTUBE_SHARE_URL_PREFIX = "http://youtu.be/";

    // Livestream captions config
    public static final String PRIMARY_LIVESTREAM_CAPTIONS_URL = "TODO";
    public static final String SECONDARY_LIVESTREAM_CAPTIONS_URL = "TODO";
    public static final String PRIMARY_LIVESTREAM_TRACK = "android";
    public static final String SECONDARY_LIVESTREAM_TRACK = "chrome";

    // GCM config
    public static final String GCM_SERVER_URL = "http://2012.mkupriyanov.appspot.com";
    public static final String GCM_SENDER_ID = "903246180582"; // project ID from the APIs console

	
	public static final boolean FEATURE_MAP_ENABLED = false;
	public static final boolean FEATURE_SESSION_URL_ENABLED = true;
	public static final boolean FEATURE_PLUSONE_ENABLED = true;
	
	public static final String GOOGLE_ANALYTICS = "UA-21611117-5";
	
	
	


}
