package com.dat.docbaotonghop;

import android.os.Handler;
import android.os.Looper;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * RssParser - Fetch + Parse RSS XML từ VnExpress trên background thread.
 * Kết quả trả về qua Callback trên UI thread.
 */
public class RssParser {

    public interface RssCallback {
        void onSuccess(ArrayList<ItemBaiBao> dsBaiBao);
        void onError(String errorMessage);
    }

    private static final String RSS_URL = "https://vnexpress.net/rss/the-gioi.rss";
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    public void fetch(RssCallback callback) {
        new Thread(() -> {
            try {
                ArrayList<ItemBaiBao> result = downloadAndParse(RSS_URL);
                mainHandler.post(() -> callback.onSuccess(result));
            } catch (Exception e) {
                mainHandler.post(() -> callback.onError(e.getMessage()));
            }
        }).start();
    }

    private ArrayList<ItemBaiBao> downloadAndParse(String urlString)
            throws IOException, XmlPullParserException {

        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("User-Agent",
                "Mozilla/5.0 (Android) DocBaoTongHop/1.0");
        conn.setConnectTimeout(10000);
        conn.setReadTimeout(10000);
        conn.connect();

        InputStream stream = conn.getInputStream();
        try {
            return parseRss(stream);
        } finally {
            stream.close();
            conn.disconnect();
        }
    }

    private ArrayList<ItemBaiBao> parseRss(InputStream stream)
            throws XmlPullParserException, IOException {

        ArrayList<ItemBaiBao> list = new ArrayList<>();
        XmlPullParser parser = Xml.newPullParser();
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        parser.setInput(stream, null);

        int event = parser.getEventType();
        ItemBaiBao currentItem = null;
        boolean insideItem = false;

        while (event != XmlPullParser.END_DOCUMENT) {
            String tag = parser.getName();

            if (event == XmlPullParser.START_TAG) {
                if ("item".equals(tag)) {
                    insideItem = true;
                    currentItem = new ItemBaiBao();
                } else if (insideItem) {
                    if ("title".equals(tag)) {
                        currentItem.setTieuDeBaiBao(readText(parser));
                    } else if ("pubDate".equals(tag)) {
                        String raw = readText(parser);
                        currentItem.setPubDateMillis(parseDateMillis(raw));
                        currentItem.setThoiDiemDangTin(formatDate(raw));
                    } else if ("link".equals(tag)) {
                        currentItem.setDuongDan(readText(parser));
                    } else if ("description".equals(tag)) {
                        // CDATA chứa HTML: <a href="..."><img src="URL_ANH"></a>...
                        String descHtml = readText(parser);
                        String imgUrl = extractImgSrc(descHtml);
                        if (imgUrl != null && !imgUrl.isEmpty()) {
                            currentItem.setUrlAnhDaiDien(imgUrl);
                        }
                    } else if ("enclosure".equals(tag)) {
                        // Fallback nếu description không có ảnh
                        String imgUrl = parser.getAttributeValue(null, "url");
                        if (imgUrl == null || imgUrl.isEmpty()) {
                            imgUrl = parser.getAttributeValue("", "url");
                        }
                        if (imgUrl != null && !imgUrl.isEmpty()
                                && (currentItem.getUrlAnhDaiDien() == null
                                || currentItem.getUrlAnhDaiDien().isEmpty())) {
                            currentItem.setUrlAnhDaiDien(imgUrl);
                        }
                    } else {
                        skipTag(parser);
                    }
                }
            } else if (event == XmlPullParser.END_TAG) {
                if ("item".equals(tag) && insideItem && currentItem != null) {
                    list.add(currentItem);
                    insideItem = false;
                    currentItem = null;
                }
            }

            event = parser.next();
        }

        return list;
    }

    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        StringBuilder sb = new StringBuilder();
        int event = parser.next();
        while (event != XmlPullParser.END_TAG) {
            if (event == XmlPullParser.TEXT || event == XmlPullParser.CDSECT) {
                sb.append(parser.getText());
            }
            event = parser.next();
        }
        return sb.toString().trim();
    }

    private void skipTag(XmlPullParser parser) throws IOException, XmlPullParserException {
        int depth = 1;
        while (depth > 0) {
            int event = parser.next();
            if (event == XmlPullParser.START_TAG) depth++;
            else if (event == XmlPullParser.END_TAG) depth--;
            else if (event == XmlPullParser.END_DOCUMENT) break;
        }
    }

    private String extractImgSrc(String html) {
        if (html == null || html.isEmpty()) return null;
        int idx = html.indexOf("src=\"");
        if (idx == -1) {
            idx = html.indexOf("src='");
            if (idx == -1) return null;
        }
        int start = idx + 5;
        char closeQuote = html.charAt(idx + 4) == '"' ? '"' : '\'';
        int end = html.indexOf(closeQuote, start);
        if (end == -1) return null;
        return html.substring(start, end);
    }

    // ─── Xử lý ngày giờ ───────────────────────────────────────────────────────

    /**
     * Parse pubDate thành timestamp milliseconds để lọc theo thời gian.
     * Input: "Sat, 28 Mar 2026 18:02:47 +0700"
     */
    private long parseDateMillis(String pubDate) {
        if (pubDate == null || pubDate.isEmpty()) return 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(
                    "EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
            Date date = sdf.parse(pubDate.trim());
            return date != null ? date.getTime() : 0;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Định dạng ngày giờ sang tiếng Việt đầy đủ.
     * Input:  "Sat, 28 Mar 2026 18:02:47 +0700"
     * Output: "Thứ Bảy, 28/03/2026 lúc 18:02"
     */
    private String formatDate(String pubDate) {
        if (pubDate == null || pubDate.isEmpty()) return "";
        try {
            String[] parts = pubDate.trim().split("\\s+");
            // parts[0]=Sat, parts[1]=28, parts[2]=Mar, parts[3]=2026, parts[4]=18:02:47
            String dayName  = convertDayName(parts[0].replace(",", ""));
            String day      = parts[1];
            String month    = convertMonth(parts[2]);
            String year     = parts[3];
            String time     = parts[4].substring(0, 5); // "18:02"

            return dayName + ", " + day + "/" + month + "/" + year + " lúc " + time;
        } catch (Exception e) {
            return pubDate;
        }
    }

    private String convertDayName(String d) {
        switch (d) {
            case "Mon": return "Thứ Hai";
            case "Tue": return "Thứ Ba";
            case "Wed": return "Thứ Tư";
            case "Thu": return "Thứ Năm";
            case "Fri": return "Thứ Sáu";
            case "Sat": return "Thứ Bảy";
            case "Sun": return "Chủ Nhật";
            default:    return d;
        }
    }

    private String convertMonth(String m) {
        switch (m) {
            case "Jan": return "01"; case "Feb": return "02";
            case "Mar": return "03"; case "Apr": return "04";
            case "May": return "05"; case "Jun": return "06";
            case "Jul": return "07"; case "Aug": return "08";
            case "Sep": return "09"; case "Oct": return "10";
            case "Nov": return "11"; case "Dec": return "12";
            default: return "00";
        }
    }
}
