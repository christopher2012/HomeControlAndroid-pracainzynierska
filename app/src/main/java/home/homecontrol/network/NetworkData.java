package home.homecontrol.network;

/**
 * Created by HP on 2015-12-05.
 */
public class NetworkData {
    public static final String ANDROID_COMMAND = "/android?command=";
    public static final String IP_SERVER = "http://192.168.0.";
    public static final String LIGHT = "/light?state=";
    public static final String MOVEMENT = "/pir?state=";
    public static final String SETTINGS = "/settings";
    public static final String OK_MSG = "OK";
    public static final String TEMPERATURE_FEEDS = "http://api.thingspeak.com/channels/72588/feed.json?start=2011-11-11%2010:10:10";

    public static final String CMD_SWITCH_LIGHT = "A";
    public static final String CMD_CHANGE_BRIGHTNESS = "B";
    public static final String CMD_GET_DATA = "D";
    public static final String CMD_AUTO_LIGHT_ON = "C";
    public static final String CMD_SMOKE_ALARM = "E";
    public static final String CMD_MONOXIDE_ALARM = "F";
    public static final String CMD_ALARM = "G";

    static String IP_SET = "";

    public static String getIpSet() {
        return IP_SET;
    }

    public static void setIpSet(String ipSet) {
        IP_SET = ipSet;
    }

    public static String getIpServer() {
        return IP_SERVER + IP_SET;
    }

}
