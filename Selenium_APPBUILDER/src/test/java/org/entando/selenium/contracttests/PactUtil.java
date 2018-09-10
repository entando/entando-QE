package org.entando.selenium.contracttests;

import au.com.dius.pact.consumer.dsl.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.regex.Pattern;

public class PactUtil {

    public static final String MATCH_ANY_HTTP_VERB = "(GET|HEAD|POST|DELETE|TRACE|OPTIONS|PATCH|PUT\\,\\s){1,8}";

//    public static PactDslJsonBody toDslJsonBody(String json) {
//        JSONObject jsonObject = new JSONObject(json);
//        PactDslJsonBody dslJsonBody = new PactDslJsonBody();
//        copyStructureInto(jsonObject, dslJsonBody);
//        JSONObject targetJsonObject = (JSONObject) dslJsonBody.getBody();
//        for (String name : jsonObject.keySet()) {
//            targetJsonObject.put(name,jsonObject.get(name));
//        }
//        System.out.println(dslJsonBody.toString());
//
//        return dslJsonBody;
//    }


    private static void copyStructureInto(JSONObject jsonObject, PactDslJsonBody dslJsonBody) {
        for (String string : jsonObject.keySet()) {
            copyPropertyTypeNamed(jsonObject, dslJsonBody, string);
        }
        dslJsonBody.closeObject();
    }

    private static void copyPropertyTypeNamed(JSONObject jsonObject, PactDslJsonBody dslJsonBody, String string) {
        Object o = jsonObject.get(string);
        if (o instanceof Number) {
            dslJsonBody.numberType(string, (Number) o);
        } else if (o instanceof Boolean) {
            dslJsonBody.booleanType(string, (Boolean) o);
        } else if (o instanceof String) {
            //TODO check for dates/ip addresses and what not
            dslJsonBody.stringType(string, (String) o);
        } else if (o instanceof JSONObject) {
            copyStructureInto((JSONObject) o, dslJsonBody.object(string));
        } else if (o instanceof JSONArray) {
            JSONArray arr = (JSONArray) o;
            if (arr.length() > 0) {
                if (arr.get(0) instanceof JSONObject) {
                    PactDslJsonBody jsonBodyToMatch = dslJsonBody.eachLike(string, arr.length());
                    copyStructureInto(arr.getJSONObject(0), jsonBodyToMatch);
                    PactDslJsonArray dslJsonArray = (PactDslJsonArray) jsonBodyToMatch.closeObject();
                    dslJsonArray.closeArray();
                }else if(arr.get(0) instanceof Number){
                    dslJsonBody.array(string).numberType((Number) arr.get(0));
                }else if(arr.get(0) instanceof String){
                    dslJsonBody.array(string).stringType((String) arr.get(0));
                }else if(arr.get(0) instanceof Boolean){
                    dslJsonBody.array(string).booleanType((Boolean) arr.get(0));
                }
            }
        }
    }
    public static PactDslResponse buildGetProfileTypes(PactDslResponse builder) {
        PactDslRequestWithPath optionsRequest = builder

                .uponReceiving("The ProfileTypes OPTIONS Interaction")
                .path("/entando/api/profileTypes")
                .method("OPTIONS")
                .matchQuery("page", "\\d+")
                .matchQuery("pageSize", "\\d+");
        PactDslResponse optionsResponse = optionsResponse(optionsRequest);
        PactDslRequestWithPath request = optionsResponse
                .uponReceiving("The ProfileTypes GET Interaction")
                .path("/entando/api/profileTypes")
                .method("GET")
                .matchQuery("page", "\\d+")
                .matchQuery("pageSize", "\\d+");
        return standardResponse(request, "{\"payload\":[{\"code\":\"PFL\",\"name\":\"Default user profile\",\"status\":\"0\"},{\"code\":\"1DF\",\"name\":\"1SeleniumTest_DontTouch\",\"status\":\"0\"}],\"errors\":[],\"metaData\":{\"page\":1,\"pageSize\":10,\"lastPage\":1,\"totalItems\":1,\"sort\":\"name\",\"direction\":\"ASC\",\"filters\":[],\"additionalParams\":{}}}");
    }

    public static PactDslResponse buildGetUsers(PactDslResponse builder, int page, int pageSize) {
        PactDslRequestWithPath optionsRequest = builder.uponReceiving("The User Query OPTIONS Interaction")
                .path("/entando/api/users")
                .method("OPTIONS")
                .matchQuery("page", "\\d+", "" + page)
                .matchQuery("pageSize", "\\d+", ""+pageSize);
        PactDslResponse optionsResponse = optionsResponse(optionsRequest);
        PactDslRequestWithPath request = optionsResponse.uponReceiving("The User Query GET Interaction")
                .path("/entando/api/users")
                .method("GET")
                .matchQuery("page", "\\d+", "" + page)
                .matchQuery("pageSize",  "\\d+", ""+pageSize);
        return standardResponse(request, "{\"payload\":[{\"username\":\"UNIMPORTANT\",\"registration\":\"2018-08-31 00:00:00\",\"lastLogin\":null,\"lastPasswordChange\":null,\"status\":\"active\",\"accountNotExpired\":true,\"credentialsNotExpired\":true,\"profileType\":null,\"profileAttributes\":{},\"maxMonthsSinceLastAccess\":-1,\"maxMonthsSinceLastPasswordChange\":-1}],\"errors\":[],\"metaData\":{\"page\":1,\"pageSize\":1,\"lastPage\":1,\"totalItems\":1,\"sort\":\"username\",\"direction\":\"ASC\",\"filters\":[],\"additionalParams\":{}}}");
    }

    public static PactDslResponse buildGetLanguages(PactDslResponse builder) {
        PactDslRequestWithPath optionsRequest = builder.uponReceiving("The Languages OPTIONS Interaction")
                .path("/entando/api/languages")
                .method("OPTIONS")
                .matchQuery("page", "1")
                .matchQuery("pageSize", "0");
        PactDslResponse optionsResponse = optionsResponse(optionsRequest);
        PactDslRequestWithPath request = optionsResponse.uponReceiving("The Languages GET Interaction")
                .path("/entando/api/languages")
                .method("GET")
                .matchQuery("page", "1")
                .matchQuery("pageSize", "0");
        return standardResponse(request, "{\"payload\":[{\"code\":\"aa\",\"description\":\"Afar\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ab\",\"description\":\"Abkhazian\",\"isActive\":false,\"isDefault\":false},{\"code\":\"af\",\"description\":\"Afrikaans\",\"isActive\":false,\"isDefault\":false},{\"code\":\"am\",\"description\":\"Amharic\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ar\",\"description\":\"Arabic\",\"isActive\":false,\"isDefault\":false},{\"code\":\"as\",\"description\":\"Assamese\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ay\",\"description\":\"Aymara\",\"isActive\":false,\"isDefault\":false},{\"code\":\"az\",\"description\":\"Azerbaijani\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ba\",\"description\":\"Bashkir\",\"isActive\":false,\"isDefault\":false},{\"code\":\"be\",\"description\":\"Byelorussian\",\"isActive\":false,\"isDefault\":false},{\"code\":\"bg\",\"description\":\"Bulgarian\",\"isActive\":false,\"isDefault\":false},{\"code\":\"bh\",\"description\":\"Bihari\",\"isActive\":false,\"isDefault\":false},{\"code\":\"bi\",\"description\":\"Bislama\",\"isActive\":false,\"isDefault\":false},{\"code\":\"bn\",\"description\":\"Bengali; Bangla\",\"isActive\":false,\"isDefault\":false},{\"code\":\"bo\",\"description\":\"Tibetan\",\"isActive\":false,\"isDefault\":false},{\"code\":\"br\",\"description\":\"Breton\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ca\",\"description\":\"Catalan\",\"isActive\":false,\"isDefault\":false},{\"code\":\"co\",\"description\":\"Corsican\",\"isActive\":false,\"isDefault\":false},{\"code\":\"cs\",\"description\":\"Czech\",\"isActive\":false,\"isDefault\":false},{\"code\":\"cy\",\"description\":\"Welsh\",\"isActive\":false,\"isDefault\":false},{\"code\":\"da\",\"description\":\"Danish\",\"isActive\":false,\"isDefault\":false},{\"code\":\"de\",\"description\":\"German\",\"isActive\":false,\"isDefault\":false},{\"code\":\"dz\",\"description\":\"Bhutani\",\"isActive\":false,\"isDefault\":false},{\"code\":\"el\",\"description\":\"Greek\",\"isActive\":false,\"isDefault\":false},{\"code\":\"en\",\"description\":\"English\",\"isActive\":true,\"isDefault\":true},{\"code\":\"eo\",\"description\":\"Esperanto\",\"isActive\":false,\"isDefault\":false},{\"code\":\"es\",\"description\":\"Spanish\",\"isActive\":false,\"isDefault\":false},{\"code\":\"et\",\"description\":\"Estonian\",\"isActive\":false,\"isDefault\":false},{\"code\":\"eu\",\"description\":\"Basque\",\"isActive\":false,\"isDefault\":false},{\"code\":\"fa\",\"description\":\"Persian\",\"isActive\":false,\"isDefault\":false},{\"code\":\"fi\",\"description\":\"Finnish\",\"isActive\":false,\"isDefault\":false},{\"code\":\"fj\",\"description\":\"Fiji\",\"isActive\":false,\"isDefault\":false},{\"code\":\"fo\",\"description\":\"Faroese\",\"isActive\":false,\"isDefault\":false},{\"code\":\"fr\",\"description\":\"French\",\"isActive\":false,\"isDefault\":false},{\"code\":\"fy\",\"description\":\"Frisian\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ga\",\"description\":\"Irish\",\"isActive\":false,\"isDefault\":false},{\"code\":\"gd\",\"description\":\"Scots Gaelic\",\"isActive\":false,\"isDefault\":false},{\"code\":\"gl\",\"description\":\"Galician\",\"isActive\":false,\"isDefault\":false},{\"code\":\"gn\",\"description\":\"Guarani\",\"isActive\":false,\"isDefault\":false},{\"code\":\"gu\",\"description\":\"Gujarati\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ha\",\"description\":\"Hausa\",\"isActive\":false,\"isDefault\":false},{\"code\":\"he\",\"description\":\"Hebrew (formerly iw)\",\"isActive\":false,\"isDefault\":false},{\"code\":\"hi\",\"description\":\"Hindi\",\"isActive\":false,\"isDefault\":false},{\"code\":\"hr\",\"description\":\"Croatian\",\"isActive\":false,\"isDefault\":false},{\"code\":\"hu\",\"description\":\"Hungarian\",\"isActive\":false,\"isDefault\":false},{\"code\":\"hy\",\"description\":\"Armenian\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ia\",\"description\":\"Interlingua\",\"isActive\":false,\"isDefault\":false},{\"code\":\"id\",\"description\":\"Indonesian (formerly in)\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ie\",\"description\":\"Interlingue\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ik\",\"description\":\"Inupiak\",\"isActive\":false,\"isDefault\":false},{\"code\":\"is\",\"description\":\"Icelandic\",\"isActive\":false,\"isDefault\":false},{\"code\":\"it\",\"description\":\"Italian\",\"isActive\":true,\"isDefault\":false},{\"code\":\"iu\",\"description\":\"Inuktitut\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ja\",\"description\":\"Japanese\",\"isActive\":false,\"isDefault\":false},{\"code\":\"jw\",\"description\":\"Javanese\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ka\",\"description\":\"Georgian\",\"isActive\":false,\"isDefault\":false},{\"code\":\"kk\",\"description\":\"Kazakh\",\"isActive\":false,\"isDefault\":false},{\"code\":\"kl\",\"description\":\"Greenlandic\",\"isActive\":false,\"isDefault\":false},{\"code\":\"km\",\"description\":\"Cambodian\",\"isActive\":false,\"isDefault\":false},{\"code\":\"kn\",\"description\":\"Kannada\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ko\",\"description\":\"Korean\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ks\",\"description\":\"Kashmiri\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ku\",\"description\":\"Kurdish\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ky\",\"description\":\"Kirghiz\",\"isActive\":false,\"isDefault\":false},{\"code\":\"la\",\"description\":\"Latin\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ln\",\"description\":\"Lingala\",\"isActive\":false,\"isDefault\":false},{\"code\":\"lo\",\"description\":\"Laothian\",\"isActive\":false,\"isDefault\":false},{\"code\":\"lt\",\"description\":\"Lithuanian\",\"isActive\":false,\"isDefault\":false},{\"code\":\"lv\",\"description\":\"Latvian, Lettish\",\"isActive\":false,\"isDefault\":false},{\"code\":\"mg\",\"description\":\"Malagasy\",\"isActive\":false,\"isDefault\":false},{\"code\":\"mi\",\"description\":\"Maori\",\"isActive\":false,\"isDefault\":false},{\"code\":\"mk\",\"description\":\"Macedonian\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ml\",\"description\":\"Malayalam\",\"isActive\":false,\"isDefault\":false},{\"code\":\"mn\",\"description\":\"Mongolian\",\"isActive\":false,\"isDefault\":false},{\"code\":\"mo\",\"description\":\"Moldavian\",\"isActive\":false,\"isDefault\":false},{\"code\":\"mr\",\"description\":\"Marathi\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ms\",\"description\":\"Malay\",\"isActive\":false,\"isDefault\":false},{\"code\":\"mt\",\"description\":\"Maltese\",\"isActive\":false,\"isDefault\":false},{\"code\":\"my\",\"description\":\"Burmese\",\"isActive\":false,\"isDefault\":false},{\"code\":\"na\",\"description\":\"Nauru\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ne\",\"description\":\"Nepali\",\"isActive\":false,\"isDefault\":false},{\"code\":\"nl\",\"description\":\"Dutch\",\"isActive\":false,\"isDefault\":false},{\"code\":\"no\",\"description\":\"Norwegian\",\"isActive\":false,\"isDefault\":false},{\"code\":\"oc\",\"description\":\"Occitan\",\"isActive\":false,\"isDefault\":false},{\"code\":\"om\",\"description\":\"(Afan) Oromo\",\"isActive\":false,\"isDefault\":false},{\"code\":\"or\",\"description\":\"Oriya\",\"isActive\":false,\"isDefault\":false},{\"code\":\"pa\",\"description\":\"Punjabi\",\"isActive\":false,\"isDefault\":false},{\"code\":\"pl\",\"description\":\"Polish\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ps\",\"description\":\"Pashto, Pushto\",\"isActive\":false,\"isDefault\":false},{\"code\":\"pt\",\"description\":\"Portuguese\",\"isActive\":false,\"isDefault\":false},{\"code\":\"qu\",\"description\":\"Quechua\",\"isActive\":false,\"isDefault\":false},{\"code\":\"rm\",\"description\":\"Rhaeto-Romance\",\"isActive\":false,\"isDefault\":false},{\"code\":\"rn\",\"description\":\"Kirundi\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ro\",\"description\":\"Romanian\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ru\",\"description\":\"Russian\",\"isActive\":false,\"isDefault\":false},{\"code\":\"rw\",\"description\":\"Kinyarwanda\",\"isActive\":false,\"isDefault\":false},{\"code\":\"sa\",\"description\":\"Sanskrit\",\"isActive\":false,\"isDefault\":false},{\"code\":\"sd\",\"description\":\"Sindhi\",\"isActive\":false,\"isDefault\":false},{\"code\":\"sg\",\"description\":\"Sangho\",\"isActive\":false,\"isDefault\":false},{\"code\":\"sh\",\"description\":\"Serbo-Croatian\",\"isActive\":false,\"isDefault\":false},{\"code\":\"si\",\"description\":\"Sinhalese\",\"isActive\":false,\"isDefault\":false},{\"code\":\"sk\",\"description\":\"Slovak\",\"isActive\":false,\"isDefault\":false},{\"code\":\"sl\",\"description\":\"Slovenian\",\"isActive\":false,\"isDefault\":false},{\"code\":\"sm\",\"description\":\"Samoan\",\"isActive\":false,\"isDefault\":false},{\"code\":\"sn\",\"description\":\"Shona\",\"isActive\":false,\"isDefault\":false},{\"code\":\"so\",\"description\":\"Somali\",\"isActive\":false,\"isDefault\":false},{\"code\":\"sq\",\"description\":\"Albanian\",\"isActive\":false,\"isDefault\":false},{\"code\":\"sr\",\"description\":\"Serbian\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ss\",\"description\":\"Siswati\",\"isActive\":false,\"isDefault\":false},{\"code\":\"st\",\"description\":\"Sesotho\",\"isActive\":false,\"isDefault\":false},{\"code\":\"su\",\"description\":\"Sundanese\",\"isActive\":false,\"isDefault\":false},{\"code\":\"sv\",\"description\":\"Swedish\",\"isActive\":false,\"isDefault\":false},{\"code\":\"sw\",\"description\":\"Swahili\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ta\",\"description\":\"Tamil\",\"isActive\":false,\"isDefault\":false},{\"code\":\"te\",\"description\":\"Telugu\",\"isActive\":false,\"isDefault\":false},{\"code\":\"tg\",\"description\":\"Tajik\",\"isActive\":false,\"isDefault\":false},{\"code\":\"th\",\"description\":\"Thai\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ti\",\"description\":\"Tigrinya\",\"isActive\":false,\"isDefault\":false},{\"code\":\"tk\",\"description\":\"Turkmen\",\"isActive\":false,\"isDefault\":false},{\"code\":\"tl\",\"description\":\"Tagalog\",\"isActive\":false,\"isDefault\":false},{\"code\":\"tn\",\"description\":\"Setswana\",\"isActive\":false,\"isDefault\":false},{\"code\":\"to\",\"description\":\"Tonga\",\"isActive\":false,\"isDefault\":false},{\"code\":\"tr\",\"description\":\"Turkish\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ts\",\"description\":\"Tsonga\",\"isActive\":false,\"isDefault\":false},{\"code\":\"tt\",\"description\":\"Tatar\",\"isActive\":false,\"isDefault\":false},{\"code\":\"tw\",\"description\":\"Twi\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ug\",\"description\":\"Uighur\",\"isActive\":false,\"isDefault\":false},{\"code\":\"uk\",\"description\":\"Ukrainian\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ur\",\"description\":\"Urdu\",\"isActive\":false,\"isDefault\":false},{\"code\":\"uz\",\"description\":\"Uzbek\",\"isActive\":false,\"isDefault\":false},{\"code\":\"vi\",\"description\":\"Vietnamese\",\"isActive\":false,\"isDefault\":false},{\"code\":\"vo\",\"description\":\"Volapuk\",\"isActive\":false,\"isDefault\":false},{\"code\":\"wo\",\"description\":\"Wolof\",\"isActive\":false,\"isDefault\":false},{\"code\":\"xh\",\"description\":\"Xhosa\",\"isActive\":false,\"isDefault\":false},{\"code\":\"yi\",\"description\":\"Yiddish (formerly ji)\",\"isActive\":false,\"isDefault\":false},{\"code\":\"yo\",\"description\":\"Yoruba\",\"isActive\":false,\"isDefault\":false},{\"code\":\"za\",\"description\":\"Zhuang\",\"isActive\":false,\"isDefault\":false},{\"code\":\"zh\",\"description\":\"Chinese - Traditional\",\"isActive\":false,\"isDefault\":false},{\"code\":\"zhs\",\"description\":\"Chinese - Simplified\",\"isActive\":false,\"isDefault\":false},{\"code\":\"zu\",\"description\":\"Zulu\",\"isActive\":false,\"isDefault\":false}],\"errors\":[],\"metaData\":{\"page\":1,\"pageSize\":0,\"lastPage\":1,\"totalItems\":140,\"sort\":\"code\",\"direction\":\"ASC\",\"filters\":[],\"additionalParams\":{}}}");
    }

    public static PactDslResponse buildGetPageModels(PactDslResponse builder) {
        PactDslRequestWithPath optionsRequest = builder.uponReceiving("The Page Models OPTIONS Interaction")
                .path("/entando/api/pageModels")
                .method("OPTIONS")
                .matchQuery("page", "1")
                .matchQuery("pageSize", "1");
        PactDslResponse optionsResponse = optionsResponse(optionsRequest);
        PactDslRequestWithPath request = optionsResponse.uponReceiving("The Page Models GET Interaction")
                .path("/entando/api/pageModels")
                .method("GET")
                .matchQuery("page", "1")
                .matchQuery("pageSize", "1");
        return standardResponse(request, "{\"payload\":[{\"code\":\"1SLNM_TEST_4883\",\"descr\":\"1SLNM_TEST_4883\",\"mainFrame\":-1,\"pluginCode\":null,\"template\":\"<>\",\"configuration\":{\"frames\":[{\"pos\":0,\"descr\":\"SeleniumCell\",\"mainFrame\":false,\"defaultWidget\":null,\"sketch\":null}]}}],\"errors\":[],\"metaData\":{\"page\":1,\"pageSize\":1,\"lastPage\":4,\"totalItems\":4,\"sort\":\"code\",\"direction\":\"ASC\",\"filters\":[],\"additionalParams\":{}}}");
    }

    public static PactDslResponse buildGetGroups(PactDslResponse builder) {
        PactDslRequestWithPath optionsRequest = builder.uponReceiving("The Groups OPTIONS Interaction")
                .path("/entando/api/groups")
                .method("OPTIONS")
                .matchQuery("page", "1")
                .matchQuery("pageSize", "1");
        PactDslResponse optionsResponse = optionsResponse(optionsRequest);
        PactDslRequestWithPath request = optionsResponse.uponReceiving("The Groups GET Interaction")
                .path("/entando/api/groups")
                .method("GET")
                .matchQuery("page", "1")
                .matchQuery("pageSize", "1");
        String json = "{\"payload\":[{\"code\":\"1slnm_test_8645\",\"name\":\"1SLNM_TEST_8645\"}],\"errors\":[],\"metaData\":{\"page\":1,\"pageSize\":1,\"lastPage\":7,\"totalItems\":7,\"sort\":\"code\",\"direction\":\"ASC\",\"filters\":[],\"additionalParams\":{}}}";
        return standardResponse(request, json);
    }

    public static PactDslResponse standardResponse(PactDslRequestWithPath request, String json) {
        return addStandardHeaders(request
                .willRespondWith()
                .status(200)
                .body(toDslJsonBody(json)));
    }

    private static JSONObject toDslJsonBody(String json) {
        return new JSONObject(json);
    }

    public static PactDslResponse buildGetWidgets(PactDslResponse builder) {
        PactDslRequestWithPath optionsRequest = builder.uponReceiving("The Widgets OPTIONS Interaction")
                .path("/entando/api/widgets")
                .method("OPTIONS")
                .matchQuery("page", "1")
                .matchQuery("pageSize", "1");
        PactDslResponse optionsrResponse = optionsResponse(optionsRequest);

        return standardResponse(optionsrResponse.uponReceiving("The Widgets GET Interaction")
                .path("/entando/api/widgets")
                .method("GET")
                .matchQuery("page", "1")
                .matchQuery("pageSize", "1"), "{\"payload\":[{\"code\":\"bpm-case-actions\",\"used\":0,\"titles\":{\"en\":\"PAM-Case actions\",\"it\":\"Azioni caso PAM\"},\"typology\":\"jpkiebpm\",\"group\":null,\"pluginCode\":\"jpkiebpm\",\"pluginDesc\":\"Entando Red Hat PAM Connector\",\"guiFragments\":[],\"hasConfig\":true}],\"errors\":[],\"metaData\":{\"page\":1,\"pageSize\":1,\"lastPage\":22,\"totalItems\":22,\"sort\":\"code\",\"direction\":\"ASC\",\"filters\":[],\"additionalParams\":{}}}");
    }


    public static PactDslResponse buildGetPageStatus(PactDslResponse builder) {
        PactDslRequestWithPath optionsRequest = builder.uponReceiving("The Page Status OPTIONS Interaction")
                .path("/entando/api/dashboard/pageStatus")
                .method("OPTIONS");
        PactDslResponse optionsResponse = optionsResponse(optionsRequest);
        PactDslRequestWithPath request = optionsResponse

                .uponReceiving("The Page Status GET Interaction")
                .path("/entando/api/dashboard/pageStatus")
                .method("GET");
        return standardResponse(request, "{\"payload\":{\"published\":5,\"unpublished\":0,\"draft\":0,\"lastUpdate\":\"2017-02-18 00:12:24\"},\"errors\":[],\"metaData\":{}}");
    }

    public static PactDslResponse buildGetPages(PactDslResponse builder) {
        PactDslRequestWithPath optionsRequest = builder.uponReceiving("The Page Query Interaction")
                .path("/entando/api/pages/search")
                .method("OPTIONS")
                .matchQuery("page", "1")
                .matchQuery("pageSize", "5")
                .matchQuery("sort", "lastModified")
                .matchQuery("direction", "DESC");
        PactDslResponse optionsResponse = optionsResponse(optionsRequest);
        PactDslRequestWithPath request = optionsResponse.uponReceiving("The Page Query Interaction")
                .path("/entando/api/pages/search")
                .method("GET")
                .matchQuery("page", "1")
                .matchQuery("pageSize", "5")
                .matchQuery("sort", "lastModified")
                .matchQuery("direction", "DESC")
                .matchHeader("accept", "\\*\\/\\*");
        return standardResponse(request, "{\"payload\":[{\"code\":\"homepage\",\"status\":\"published\",\"displayedInMenu\":true,\"pageModel\":\"home\",\"charset\":\"utf8\",\"contentType\":\"text/html\",\"parentCode\":\"homepage\",\"seo\":false,\"titles\":{\"en\":\"Home\",\"it\":\"Home\"},\"fullTitles\":{\"en\":\"Home\",\"it\":\"Home\"},\"ownerGroup\":\"free\",\"joinGroups\":[],\"children\":[\"service\"],\"position\":-1,\"numWidget\":0,\"lastModified\":\"2017-02-18 00:12:24\",\"fullPath\":\"homepage\",\"token\":null},{\"code\":\"errorpage\",\"status\":\"published\",\"displayedInMenu\":true,\"pageModel\":\"service\",\"charset\":\"utf8\",\"contentType\":\"text/html\",\"parentCode\":\"service\",\"seo\":false,\"titles\":{\"en\":\"Error page\",\"it\":\"Pagina di errore\"},\"fullTitles\":{\"en\":\"Home / Service / Error page\",\"it\":\"Home / Pagine di Servizio / Pagina di errore\"},\"ownerGroup\":\"free\",\"joinGroups\":[],\"children\":[],\"position\":2,\"numWidget\":0,\"lastModified\":\"2017-02-17 21:11:54\",\"fullPath\":\"homepage/service/errorpage\",\"token\":null},{\"code\":\"notfound\",\"status\":\"published\",\"displayedInMenu\":true,\"pageModel\":\"service\",\"charset\":\"utf8\",\"contentType\":\"text/html\",\"parentCode\":\"service\",\"seo\":false,\"titles\":{\"en\":\"Page not found\",\"it\":\"Pagina non trovata\"},\"fullTitles\":{\"en\":\"Home / Service / Page not found\",\"it\":\"Home / Pagine di Servizio / Pagina non trovata\"},\"ownerGroup\":\"free\",\"joinGroups\":[],\"children\":[],\"position\":1,\"numWidget\":0,\"lastModified\":\"2017-02-17 16:37:10\",\"fullPath\":\"homepage/service/notfound\",\"token\":null},{\"code\":\"login\",\"status\":\"published\",\"displayedInMenu\":true,\"pageModel\":\"service\",\"charset\":\"utf8\",\"contentType\":\"text/html\",\"parentCode\":\"service\",\"seo\":false,\"titles\":{\"en\":\"Login\",\"it\":\"Pagina di login\"},\"fullTitles\":{\"en\":\"Home / Service / Login\",\"it\":\"Home / Pagine di Servizio / Pagina di login\"},\"ownerGroup\":\"free\",\"joinGroups\":[],\"children\":[],\"position\":3,\"numWidget\":0,\"lastModified\":\"2017-02-17 15:32:34\",\"fullPath\":\"homepage/service/login\",\"token\":null},{\"code\":\"service\",\"status\":\"published\",\"displayedInMenu\":false,\"pageModel\":\"service\",\"charset\":\"utf8\",\"contentType\":\"text/html\",\"parentCode\":\"homepage\",\"seo\":false,\"titles\":{\"en\":\"Service\",\"it\":\"Pagine di Servizio\"},\"fullTitles\":{\"en\":\"Home / Service\",\"it\":\"Home / Pagine di Servizio\"},\"ownerGroup\":\"free\",\"joinGroups\":[],\"children\":[\"notfound\",\"errorpage\",\"login\"],\"position\":1,\"numWidget\":0,\"lastModified\":\"2017-02-17 13:06:24\",\"fullPath\":\"homepage/service\",\"token\":null}],\"errors\":[],\"metaData\":{\"page\":1,\"pageSize\":5,\"lastPage\":1,\"totalItems\":5,\"sort\":\"lastModified\",\"direction\":\"DESC\",\"additionalParams\":{},\"pageCodeToken\":null}}");
    }

    public static PactDslResponse buildGetAccessToken(PactDslWithProvider builder) {
        return builder
                .uponReceiving("The Login  interaction")
                .path("/entando/OAuth2/access_token")
                .method("POST")
                .matchHeader("Content-Type", "application\\/x-www-form-urlencoded")
                .matchHeader("Accept", "\\*\\/\\*")
                .body("username=admin&password=adminadmin&grant_type=password&client_id=true&client_secret=true")
                .willRespondWith()
                .status(200)
                .matchHeader("Access-Control-Allow-Methods",MATCH_ANY_HTTP_VERB, "GET, POST, PUT, DELETE, OPTIONS")
                .matchHeader("Access-Control-Allow-Origin","\\*. ", "*")
                .matchHeader("Set-Cookie", "JESSIONID.+", "JSESSIONID=082F7mzH9gxErT4EnT9L7PL6PLwgarDsQmK5Pov9.a006c24d3c59; path=/entando")
                .matchHeader("Access-Control-Allow-Headers",".+", "Content-Type, Authorization")
                .body(toDslJsonBody("{\"access_token\":\"564e2bd3f55363e6d1fc0f53b0580bb3\",\"refresh_token\":\"c007499217281691532aa71b530e3fad\",\"expires_in\":3600}"));
    }

    public static void main(String[] args) {
        System.out.println(Pattern.compile("((GET|HEAD|POST|DELETE|TRACE|OPTIONS|PATCH|PUT),){1,8}").matcher("GET, POST, PUT, DELETE, OPTIONS").find());
    }
    private static PactDslResponse addStandardHeaders(PactDslResponse response) {
        response.matchHeader("Allow", MATCH_ANY_HTTP_VERB, "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH")
                .matchHeader("Access-Control-Allow-Methods",MATCH_ANY_HTTP_VERB, "GET, POST, PUT, DELETE, OPTIONS")
                .matchHeader("Access-Control-Allow-Origin", "\\*","*")
                .matchHeader("Access-Control-Max-Age", "3600", "3600")
                .matchHeader("Connection", "keep-alive", "keep-alive")
                .matchHeader("Access-Control-Allow-Headers", ".+", "Content-Type, Authorization");

        return response;
    }

    public static PactDslResponse optionsResponse(PactDslRequestWithPath optionsRequest) {
        return addStandardHeaders(optionsRequest
                .willRespondWith()
                .status(200)
                .body(""));
    }

}
