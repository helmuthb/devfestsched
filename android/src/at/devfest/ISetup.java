package at.devfest;


public interface ISetup {

	public static String EVENT_PACKAGE_NAME = "at.devfest.app"; // "com.google.android.apps.iosched"

	/*
	 * Define Activities listed in Manifest + called vie Intents
	 */

	public static final Class<?> HomeActivityClass = at.devfest.app.ui.HomeActivity.class;

	public static final Class<?> AccountActivityClass = at.devfest.app.ui.AccountActivity.class;

//	public static final Class<?> SessionLivestreamActivityClass = at.devfest.app.ui.SessionLivestreamActivity.class;
	public static final Class<?> SessionAlarmServiceClass = at.devfest.app.calendar.SessionAlarmService.class;
//	public static final Class<?> SessionCalendarServiceClass = at.devfest.app.calendar.SessionCalendarService.class;

	public static final Class<?> SocialStreamActivityClass = at.devfest.app.ui.SocialStreamActivity.class;

//	public static final Class<?> GoogleTVSessionLivestreamActivityClass = at.devfest.app.ui.gtv.GoogleTVSessionLivestreamActivity.class;

	public static final Class<?> BeamActivityClass = at.devfest.app.ui.BeamActivity.class;

	public static final Class<?> MapActivityClass = at.devfest.app.ui.phone.MapActivity.class;
	public static final Class<?> SessionDetailActivityClass = at.devfest.app.ui.phone.SessionDetailActivity.class;
	public static final Class<?> SessionsActivityClass = at.devfest.app.ui.phone.SessionsActivity.class;
	public static final Class<?> TrackDetailActivityClass = at.devfest.app.ui.phone.TrackDetailActivity.class;
	public static final Class<?> VendorDetailActivityClass = at.devfest.app.ui.phone.VendorDetailActivity.class;

//	public static final Class<?> MapMultiPaneActivityClass = at.devfest.app.ui.tablet.MapMultiPaneActivity.class;
//	public static final Class<?> SessionsVendorsMultiPaneActivityClass = at.devfest.app.ui.tablet.SessionsVendorsMultiPaneActivity.class;

	
}
