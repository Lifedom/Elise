package site.zido.elise.utils;

import site.zido.elise.Request;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Url utils.
 *
 * @author zido
 */
public class UrlUtils {
    private final static String QUERY_START_CHARACTER = "?";

    /**
     * 规范url
     *
     * @param url   url
     * @param refer refer
     * @return 规范化后的url string
     */
    public static String canonicalizeUrl(String url, String refer) {
        URL base;
        try {
            try {
                base = new URL(refer);
            } catch (MalformedURLException e) {
                // the base is unsuitable, but the attribute may be abs on its own, so try that
                URL abs = new URL(refer);
                return abs.toExternalForm();
            }
            // workaround: java resolves '//path/file + ?foo' to '//path/?foo', not '//path/file?foo' as desired
            if (url.startsWith(QUERY_START_CHARACTER)) {
                url = base.getPath() + url;
            }
            URL abs = new URL(base, url);
            return abs.toExternalForm();
        } catch (MalformedURLException e) {
            return "";
        }
    }

    /**
     * Convert to requests list.
     *
     * @param urls the urls
     * @return the list
     */
    public static List<Request> convertToRequests(Collection<String> urls) {
        List<Request> requestList = new ArrayList<Request>(urls.size());
        for (String url : urls) {
            requestList.add(new Request(url));
        }
        return requestList;
    }

    /**
     * Fix illegal character in url string.
     *
     * @param url the url
     * @return the string
     */
    public static String fixIllegalCharacterInUrl(String url) {
        return url.replace(" ", "%20").replaceAll("#+", "#");
    }

    private static Pattern patternForProtocal = Pattern.compile("[\\w]+://");

    /**
     * Remove protocol string.
     *
     * @param url the url
     * @return the string
     */
    public static String removeProtocol(String url) {
        return patternForProtocal.matcher(url).replaceAll("");
    }

    /**
     * Gets domain.
     *
     * @param url the url
     * @return the domain
     */
    public static String getDomain(String url) {
        String domain = removeProtocol(url);
        int i = domain.indexOf("/", 1);
        if (i > 0) {
            domain = domain.substring(0, i);
        }
        return removePort(domain);
    }

    /**
     * Remove port string.
     *
     * @param domain the domain
     * @return the string
     */
    public static String removePort(String domain) {
        int portIndex = domain.indexOf(":");
        if (portIndex != -1) {
            return domain.substring(0, portIndex);
        } else {
            return domain;
        }
    }

    private static final Pattern PATTERN_FOR_CHARSET = Pattern.compile("charset\\s*=\\s*['\"]*([^\\s;'\"]*)", Pattern.CASE_INSENSITIVE);

    /**
     * Gets charset.
     *
     * @param contentType the content type
     * @return the charset
     */
    public static String getCharset(String contentType) {
        Matcher matcher = PATTERN_FOR_CHARSET.matcher(contentType);
        if (matcher.find()) {
            String charset = matcher.group(1);
            if (Charset.isSupported(charset)) {
                return charset;
            }
        }
        return null;
    }
}
