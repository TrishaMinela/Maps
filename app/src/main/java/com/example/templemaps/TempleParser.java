import android.util.Xml;

import com.example.templemaps.Temple;

import org.xmlpull.v1.XmlPullParser;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TempleParser {

    public static List<Temple> parse(InputStream inputStream) throws Exception {
        XmlPullParser parser = Xml.newPullParser();
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        parser.setInput(inputStream, null);

        List<Temple> temples = new ArrayList<>();
        Temple currentTemple = null;
        int eventType = parser.getEventType();

        while (eventType != XmlPullParser.END_DOCUMENT) {
            String tagName = parser.getName();

            switch (eventType) {
                case XmlPullParser.START_TAG:
                    if ("temple".equals(tagName)) {
                        currentTemple = new Temple();
                    } else if (currentTemple != null) {
                        switch (tagName) {
                            case "name":
                                currentTemple.setName(parser.nextText());
                                break;
                            case "latitude":
                                double latitude = Double.parseDouble(parser.nextText());
                                currentTemple.setLatitude(latitude);
                                break;
                            case "longitude":
                                double longitude = Double.parseDouble(parser.nextText());
                                currentTemple.setLongitude(longitude);
                                break;
                            case "announcement":
                                currentTemple.setAnnouncement(parser.nextText());
                                break;
                            case "groundbreaking":
                                currentTemple.setGroundbreaking(parser.nextText());
                                break;
                            case "dedication":
                                currentTemple.setDedication(parser.nextText());
                                break;
                            case "rededication":
                                currentTemple.setRededication(parser.nextText());
                                break;
                            case "imageResource":
                                currentTemple.setImageResource(parser.nextText());
                                break;
                        }
                    }
                    break;

                case XmlPullParser.END_TAG:
                    if ("temple".equals(tagName) && currentTemple != null) {
                        temples.add(currentTemple);
                        currentTemple = null;
                    }
                    break;
            }

            eventType = parser.next();
        }

        return temples;
    }
}
