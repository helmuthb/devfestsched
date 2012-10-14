package at.devfest;

import java.util.TimeZone;

import android.annotation.TargetApi;
import android.os.Build;

import com.google.android.apps.iosched.util.ParserUtils;

public class Setup implements ISetup {

	public static String CONFERENCE_HASHTAG = "#devfest";

	public static TimeZone CONFERENCE_TIME_ZONE = TimeZone.getTimeZone("Europe/Vienna");
	
	public static long CONFERENCE_START_MILLIS = ParserUtils.parseTime("2012-11-10T09:00:00.000+01:00");
	public static long CONFERENCE_END_MILLIS = ParserUtils.parseTime("2012-11-11T19:00:00.000+01:00");

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public static final Class<?> getMyScheduleWidgetServiceClass() {
		return at.devfest.app.appwidget.MyScheduleWidgetService.class;
	}
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public static final Class<?> getMyScheduleWidgetProviderClass() {
		return at.devfest.app.appwidget.MyScheduleWidgetProvider.class;
	}
	
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	public static final Class<?> getSessionCalendarServiceClass() {
		return at.devfest.app.calendar.SessionCalendarService.class;
	}
	
	

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public static final Class<?> getGoogleTVSessionLivestreamActivityClass() {
		return at.devfest.app.ui.gtv.GoogleTVSessionLivestreamActivity.class;
	}
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public static final Class<?> getSessionLivestreamActivityClass() {
		return at.devfest.app.ui.SessionLivestreamActivity.class;
	}
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public static final Class<?> getMapMultiPaneActivityClass() {
		return at.devfest.app.ui.tablet.MapMultiPaneActivity.class;
	}
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public static final Class<?> getSessionsVendorsMultiPaneActivityClass() {
		return at.devfest.app.ui.tablet.SessionsVendorsMultiPaneActivity.class;
	}
	
}
