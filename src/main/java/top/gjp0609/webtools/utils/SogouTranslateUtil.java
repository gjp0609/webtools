package top.gjp0609.webtools.utils;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 调用搜狗翻译api<a href="http://deepi.sogou.com/">http://deepi.sogou.com/</a>
 */
public class SogouTranslateUtil {


    private static final String PID = "***";
    private static final String SECRET_KEY = "***";

    public static String translate(String q) {
        return translate(q, Language.zh_CHS);
    }

    public static String translate(String q, Language language) {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://fanyi.sogou.com/reventondc/api/sogouTranslate");
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        httpPost.setHeader("Accept", "application/json");
        String salt = UUID.randomUUID().toString();
        String md5Param = PID + q + salt + SECRET_KEY;
        String sign = MD5Util.MD5Encode(md5Param);
        List<NameValuePair> params = new ArrayList<>(6);
        params.add(new BasicNameValuePair("q", q));
        params.add(new BasicNameValuePair("from", "auto"));
        params.add(new BasicNameValuePair("to", language.getCode()));
        params.add(new BasicNameValuePair("pid", PID));
        params.add(new BasicNameValuePair("salt", salt));
        params.add(new BasicNameValuePair("sign", sign));

        System.err.println(salt);
        System.err.println(md5Param);
        System.err.println(sign);

        String translation = null;
        try {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Consts.UTF_8);
            httpPost.setEntity(entity);
            CloseableHttpResponse response = client.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                translation = EntityUtils.toString(responseEntity);
            }
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return translation;
    }

    public static void main(String[] args) {
        String qwe = translate("1", Language.ja);
        System.out.println(qwe);
    }


    public enum Language {
        ar("ar", "阿拉伯语", "Arabic"),
        et("et", "爱沙尼亚语", "Estonian"),
        bg("bg", "保加利亚语", "Bulgarian"),
        pl("pl", "波兰语", "Polish"),
        ko("ko", "韩语", "Korean"),
        bs_Latn("bs-Latn", "波斯尼亚语", "Bosnian (Latin)"),
        fa("fa", "波斯语", "Persian"),
        mww("mww", "白苗文", "Hmong Daw"),
        da("da", "丹麦语", "Danish"),
        de("de", "德语", "German"),
        ru("ru", "俄语", "Russian"),
        fr("fr", "法语", "French"),
        fi("fi", "芬兰语", "Finnish"),
        tlh_Qaak("tlh-Qaak", "克林贡语(piqaD)", "Klingon (pIqaD)"),
        tlh("tlh", "克林贡语", "Klingon"),
        hr("hr", "克罗地亚语", "Croatian"),
        otq("otq", "克雷塔罗奥托米语", "Querétaro Otomi"),
        ca("ca", "加泰隆语", "加泰罗尼亚 Catalan"),
        cs("cs", "捷克语", "Czech"),
        ro("ro", "罗马尼亚语", "Romanian"),
        lv("lv", "拉脱维亚语", "Latvian"),
        ht("ht", "海地克里奥尔语", "Haitian Creole"),
        lt("lt", "立陶宛语", "Lithuanian"),
        nl("nl", "荷兰语", "Dutch"),
        ms("ms", "马来语", "Malay"),
        mt("mt", "马耳他语", "Maltese"),
        pt("pt", "葡萄牙语", "Portuguese"),
        ja("ja", "日语", "Japanese"),
        sl("sl", "斯洛文尼亚语", "Slovenian"),
        th("th", "泰语", "Thai"),
        tr("tr", "土耳其语", "Turkish"),
        sr_Latn("sr-Latn", "塞尔维亚语(拉丁文)", "Serbian (Latin)"),
        sr_Cyrl("sr-Cyrl", "塞尔维亚语(西里尔文)", "Serbian (Cyrillic)"),
        sk("sk", "斯洛伐克语", "Slovak"),
        sw("sw", "斯瓦希里语", "Kiswahili"),
        af("af", "南非荷兰语", "南非的公用荷兰语"),
        no("no", "挪威语", "Norwegian"),
        en("en", "英语", "English"),
        es("es", "西班牙语", "Spanish"),
        uk("uk", "乌克兰语", "Ukrainian"),
        ur("ur", "乌尔都语", "Urdu"),
        el("el", "希腊语", "Greek"),
        hu("hu", "匈牙利语", "Hungarian"),
        cy("cy", "威尔士语", "Welsh"),
        yua("yua", "尤卡坦玛雅语", "Yucatec Maya"),
        he("he", "希伯来语", "Hebrew"),
        zh_CHS("zh-CHS", "中文", "Chinese Simplified"),
        it("it", "意大利语", "Italian"),
        hi("hi", "印地语", "Hindi"),
        id("id", "印度尼西亚语", "Indonesian"),
        zh_CHT("zh-CHT", "中文繁体", "Chinese Traditional"),
        vi("vi", "越南语", "Vietnamese"),
        sv("sv", "瑞典语", "Swedish"),
        yue("yue", "粤语(繁体)", "Cantonese"),
        fj("fj", "斐济", "fijian"),
        fil("fil", "菲律宾语", "Filipino"),
        sm("sm", "萨摩亚语", "Samoan language"),
        to("to", "汤加语", "lea fakatonga"),
        ty("ty", "塔希提语", "Tahiti language"),
        mg("mg", "马尔加什语", "Malagasy language"),
        bn("bn", "孟加拉语", "Bengali");

        Language(String code, String name, String english) {
            this.code = code;
            this.name = name;
            this.english = english;
        }

        private String code;
        private String name;
        private String english;

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public String getEnglish() {
            return english;
        }
    }
}
