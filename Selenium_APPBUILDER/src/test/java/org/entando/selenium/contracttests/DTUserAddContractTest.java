/*
Copyright 2015-Present Entando Inc. (http://www.entando.com) All rights reserved.
This library is free software; you can redistribute it and/or modify it under
the terms of the GNU Lesser General Public License as published by the Free
Software Foundation; either version 2.1 of the License, or (at your option)
any later version.
This library is distributed in the hope that it will be useful, but WITHOUT
ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
details.
 */
package org.entando.selenium.contracttests;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.model.RequestResponsePact;
import org.apache.http.entity.ContentType;
import org.entando.selenium.pages.*;
import org.entando.selenium.utils.UsersTestBase;
import org.entando.selenium.utils.Utils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * This class perform a test to add a user
 * 
 * @version 1.01
 */
@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "UserAddProvider", port = "8080")
public class DTUserAddContractTest extends UsersTestBase {
    private Random generator = new Random();
    private int randomNumber = generator.nextInt(9999);
    private String username = "1SLNM_TEST_" + randomNumber;
    /*
        Pages used on this test
    */
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTUsersPage dTUsersPage;

    @Autowired
    public DTUserAddPage dTUserAddPage;

    @Autowired
    public DTUserProfileTypePage dtUserProfileTypePage;

    @Autowired
    public DTUserProfileTypeAddPage dtUserProfileTypeAddPage;


    @Pact(provider="UserAddProvider", consumer="UserAddConsumer")
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        Headers standardResponseHeaders = new Headers("Date", "Mon, 03 Sep 2018 06:37:56 GMT")
                .and("Allow", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH")
                .and("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")
                .and("Access-Control-Allow-Origin", "*")
                .and("Access-Control-Max-Age", "3600")
                .and("Connection", "keep-alive")
                .and("Access-Control-Allow-Headers", "Content-Type, Authorization");
        return builder
                .uponReceiving("The Login  interaction")
                .path("/entando/OAuth2/access_token")
                .method("POST")
                .matchHeader("Content-Type","application\\/x-www-form-urlencoded")
                .matchHeader("Accept","\\*\\/\\*")
                .body("username=admin&password=adminadmin&grant_type=password&client_id=true&client_secret=true")
                .willRespondWith()
                .status(200)
                .headers(
                        new Headers("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")
                        .and("Content-Type","application/json;charset=UTF-8")
                        .and("Access-Control-Allow-Origin", "*")
                        .and("Set-Cookie","JSESSIONID=082F7mzH9gxErT4EnT9L7PL6PLwgarDsQmK5Pov9.a006c24d3c59; path=/entando")
                        .and("Access-Control-Allow-Headers","Content-Type, Authorization")
                )
                .body("{\"access_token\":\"564e2bd3f55363e6d1fc0f53b0580bb3\",\"refresh_token\":\"c007499217281691532aa71b530e3fad\",\"expires_in\":3600}")

                .uponReceiving("The User Query Interaction")
                .path("/entando/api/users")
                .method("OPTIONS")
                .matchQuery("page","1")
                .matchQuery("pageSize","1")
                .willRespondWith()
                .status(200)
                .body("","text/plain")
                .headers(standardResponseHeaders)


                .uponReceiving("The User Query Interaction")
                .path("/entando/api/users")
                .method("GET")
                .matchQuery("page","1")
                .matchQuery("pageSize","1")
                .matchHeader("accept", "\\*\\/\\*")
                .willRespondWith()
                .status(200)
                .body("{\"payload\":[{\"username\":\"" + this.username + "\",\"registration\":\"2018-08-31 00:00:00\",\"lastLogin\":null,\"lastPasswordChange\":null,\"status\":\"active\",\"accountNotExpired\":true,\"credentialsNotExpired\":true,\"profileType\":null,\"profileAttributes\":{},\"maxMonthsSinceLastAccess\":-1,\"maxMonthsSinceLastPasswordChange\":-1}],\"errors\":[],\"metaData\":{\"page\":1,\"pageSize\":1,\"lastPage\":2,\"totalItems\":2,\"sort\":\"username\",\"direction\":\"ASC\",\"filters\":[],\"additionalParams\":{}}}"
                        ,ContentType.APPLICATION_JSON)
                .headers(standardResponseHeaders)


                .uponReceiving("The Page Query Interaction")
                .path(	"/entando/api/pages/search")
                .method("OPTIONS")
                .matchQuery("page","1")
                .matchQuery("pageSize","5")
                .matchQuery("sort","lastModified")
                .matchQuery("direction", "DESC")
                .willRespondWith()
                .headers(standardResponseHeaders)
                .status(200)
                .body("")

                .uponReceiving("The Page Query Interaction")
                .path("/entando/api/pages/search")
                .method("GET")
                .matchQuery("page","1")
                .matchQuery("pageSize","5")
                .matchQuery("sort","lastModified")
                .matchQuery("direction", "DESC")
                .matchHeader("accept", "\\*\\/\\*")
                .willRespondWith()
                .status(200)
                .body("{\"payload\":[{\"code\":\"homepage\",\"status\":\"published\",\"displayedInMenu\":true,\"pageModel\":\"home\",\"charset\":\"utf8\",\"contentType\":\"text/html\",\"parentCode\":\"homepage\",\"seo\":false,\"titles\":{\"en\":\"Home\",\"it\":\"Home\"},\"fullTitles\":{\"en\":\"Home\",\"it\":\"Home\"},\"ownerGroup\":\"free\",\"joinGroups\":[],\"children\":[\"service\"],\"position\":-1,\"numWidget\":0,\"lastModified\":\"2017-02-18 00:12:24\",\"fullPath\":\"homepage\",\"token\":null},{\"code\":\"errorpage\",\"status\":\"published\",\"displayedInMenu\":true,\"pageModel\":\"service\",\"charset\":\"utf8\",\"contentType\":\"text/html\",\"parentCode\":\"service\",\"seo\":false,\"titles\":{\"en\":\"Error page\",\"it\":\"Pagina di errore\"},\"fullTitles\":{\"en\":\"Home / Service / Error page\",\"it\":\"Home / Pagine di Servizio / Pagina di errore\"},\"ownerGroup\":\"free\",\"joinGroups\":[],\"children\":[],\"position\":2,\"numWidget\":0,\"lastModified\":\"2017-02-17 21:11:54\",\"fullPath\":\"homepage/service/errorpage\",\"token\":null},{\"code\":\"notfound\",\"status\":\"published\",\"displayedInMenu\":true,\"pageModel\":\"service\",\"charset\":\"utf8\",\"contentType\":\"text/html\",\"parentCode\":\"service\",\"seo\":false,\"titles\":{\"en\":\"Page not found\",\"it\":\"Pagina non trovata\"},\"fullTitles\":{\"en\":\"Home / Service / Page not found\",\"it\":\"Home / Pagine di Servizio / Pagina non trovata\"},\"ownerGroup\":\"free\",\"joinGroups\":[],\"children\":[],\"position\":1,\"numWidget\":0,\"lastModified\":\"2017-02-17 16:37:10\",\"fullPath\":\"homepage/service/notfound\",\"token\":null},{\"code\":\"login\",\"status\":\"published\",\"displayedInMenu\":true,\"pageModel\":\"service\",\"charset\":\"utf8\",\"contentType\":\"text/html\",\"parentCode\":\"service\",\"seo\":false,\"titles\":{\"en\":\"Login\",\"it\":\"Pagina di login\"},\"fullTitles\":{\"en\":\"Home / Service / Login\",\"it\":\"Home / Pagine di Servizio / Pagina di login\"},\"ownerGroup\":\"free\",\"joinGroups\":[],\"children\":[],\"position\":3,\"numWidget\":0,\"lastModified\":\"2017-02-17 15:32:34\",\"fullPath\":\"homepage/service/login\",\"token\":null},{\"code\":\"service\",\"status\":\"published\",\"displayedInMenu\":false,\"pageModel\":\"service\",\"charset\":\"utf8\",\"contentType\":\"text/html\",\"parentCode\":\"homepage\",\"seo\":false,\"titles\":{\"en\":\"Service\",\"it\":\"Pagine di Servizio\"},\"fullTitles\":{\"en\":\"Home / Service\",\"it\":\"Home / Pagine di Servizio\"},\"ownerGroup\":\"free\",\"joinGroups\":[],\"children\":[\"notfound\",\"errorpage\",\"login\"],\"position\":1,\"numWidget\":0,\"lastModified\":\"2017-02-17 13:06:24\",\"fullPath\":\"homepage/service\",\"token\":null}],\"errors\":[],\"metaData\":{\"page\":1,\"pageSize\":5,\"lastPage\":1,\"totalItems\":5,\"sort\":\"lastModified\",\"direction\":\"DESC\",\"additionalParams\":{},\"pageCodeToken\":null}}"
                    ,ContentType.APPLICATION_JSON)
                .headers(standardResponseHeaders)



                .uponReceiving("The Page Status OPTIONS Interaction")
                .path(	"/entando/api/dashboard/pageStatus")
                .method("OPTIONS")
                .willRespondWith()
                .status(200)
                .body("")
                .headers(standardResponseHeaders)

                .uponReceiving("The Page Status GET Interaction")
                .path(	"/entando/api/dashboard/pageStatus")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body("{\"payload\":{\"published\":5,\"unpublished\":0,\"draft\":0,\"lastUpdate\":\"2017-02-18 00:12:24\"},\"errors\":[],\"metaData\":{}}"
                        ,ContentType.APPLICATION_JSON)
                .headers(standardResponseHeaders)


                .uponReceiving("The Widgets OPTIONS Interaction")
                .path(	"/entando/api/widgets")
                .method("OPTIONS")
                .matchQuery("page","1")
                .matchQuery("pageSize","1")
                .willRespondWith()
                .status(200)
                .body("")
                .headers(standardResponseHeaders)

                .uponReceiving("The Widgets GET Interaction")
                .path(	"/entando/api/widgets")
                .method("GET")
                .matchQuery("page","1")
                .matchQuery("pageSize","1")
                .willRespondWith()
                .status(200)
                .body("{\"payload\":[{\"code\":\"bpm-case-actions\",\"used\":0,\"titles\":{\"en\":\"PAM-Case actions\",\"it\":\"Azioni caso PAM\"},\"typology\":\"jpkiebpm\",\"group\":null,\"pluginCode\":\"jpkiebpm\",\"pluginDesc\":\"Entando Red Hat PAM Connector\",\"guiFragments\":[],\"hasConfig\":true}],\"errors\":[],\"metaData\":{\"page\":1,\"pageSize\":1,\"lastPage\":22,\"totalItems\":22,\"sort\":\"code\",\"direction\":\"ASC\",\"filters\":[],\"additionalParams\":{}}}"
                        , ContentType.APPLICATION_JSON)
                .headers(standardResponseHeaders)


                .uponReceiving("The Groups OPTIONS Interaction")
                .path(	"/entando/api/groups")
                .method("OPTIONS")
                .matchQuery("page","1")
                .matchQuery("pageSize","1")
                .willRespondWith()
                .status(200)
                .body("")
                .headers(standardResponseHeaders)

                .uponReceiving("The Groups GET Interaction")
                .path(	"/entando/api/groups")
                .method("GET")
                .matchQuery("page","1")
                .matchQuery("pageSize","1")
                .willRespondWith()
                .status(200)
                .body("{\"payload\":[{\"code\":\"1slnm_test_8645\",\"name\":\"1SLNM_TEST_8645\"}],\"errors\":[],\"metaData\":{\"page\":1,\"pageSize\":1,\"lastPage\":7,\"totalItems\":7,\"sort\":\"code\",\"direction\":\"ASC\",\"filters\":[],\"additionalParams\":{}}}"
                        ,ContentType.APPLICATION_JSON)
                .headers(standardResponseHeaders)


                .uponReceiving("The Page Models OPTIONS Interaction")
                .path(	"/entando/api/pageModels")
                .method("OPTIONS")
                .matchQuery("page","1")
                .matchQuery("pageSize","1")
                .willRespondWith()
                .status(200)
                .body("")
                .headers(standardResponseHeaders)

                .uponReceiving("The Page Models GET Interaction")
                .path(	"/entando/api/pageModels")
                .method("GET")
                .matchQuery("page","1")
                .matchQuery("pageSize","1")
                .willRespondWith()
                .status(200)
                .body( "{\"payload\":[{\"code\":\"1SLNM_TEST_4883\",\"descr\":\"1SLNM_TEST_4883\",\"mainFrame\":-1,\"pluginCode\":null,\"template\":\"<>\",\"configuration\":{\"frames\":[{\"pos\":0,\"descr\":\"SeleniumCell\",\"mainFrame\":false,\"defaultWidget\":null,\"sketch\":null}]}}],\"errors\":[],\"metaData\":{\"page\":1,\"pageSize\":1,\"lastPage\":4,\"totalItems\":4,\"sort\":\"code\",\"direction\":\"ASC\",\"filters\":[],\"additionalParams\":{}}}"
                        ,ContentType.APPLICATION_JSON)
                .headers(standardResponseHeaders)

                .uponReceiving("The Languages OPTIONS Interaction")
                .path(	"/entando/api/languages")
                .method("OPTIONS")
                .matchQuery("page","1")
                .matchQuery("pageSize","0")
                .willRespondWith()
                .status(200)
                .body("")
                .headers(standardResponseHeaders)

                .uponReceiving("The Languages GET Interaction")
                .path(	"/entando/api/languages")
                .method("GET")
                .matchQuery("page","1")
                .matchQuery("pageSize","0")
                .willRespondWith()
                .status(200)
                .body("{\"payload\":[{\"code\":\"aa\",\"description\":\"Afar\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ab\",\"description\":\"Abkhazian\",\"isActive\":false,\"isDefault\":false},{\"code\":\"af\",\"description\":\"Afrikaans\",\"isActive\":false,\"isDefault\":false},{\"code\":\"am\",\"description\":\"Amharic\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ar\",\"description\":\"Arabic\",\"isActive\":false,\"isDefault\":false},{\"code\":\"as\",\"description\":\"Assamese\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ay\",\"description\":\"Aymara\",\"isActive\":false,\"isDefault\":false},{\"code\":\"az\",\"description\":\"Azerbaijani\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ba\",\"description\":\"Bashkir\",\"isActive\":false,\"isDefault\":false},{\"code\":\"be\",\"description\":\"Byelorussian\",\"isActive\":false,\"isDefault\":false},{\"code\":\"bg\",\"description\":\"Bulgarian\",\"isActive\":false,\"isDefault\":false},{\"code\":\"bh\",\"description\":\"Bihari\",\"isActive\":false,\"isDefault\":false},{\"code\":\"bi\",\"description\":\"Bislama\",\"isActive\":false,\"isDefault\":false},{\"code\":\"bn\",\"description\":\"Bengali; Bangla\",\"isActive\":false,\"isDefault\":false},{\"code\":\"bo\",\"description\":\"Tibetan\",\"isActive\":false,\"isDefault\":false},{\"code\":\"br\",\"description\":\"Breton\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ca\",\"description\":\"Catalan\",\"isActive\":false,\"isDefault\":false},{\"code\":\"co\",\"description\":\"Corsican\",\"isActive\":false,\"isDefault\":false},{\"code\":\"cs\",\"description\":\"Czech\",\"isActive\":false,\"isDefault\":false},{\"code\":\"cy\",\"description\":\"Welsh\",\"isActive\":false,\"isDefault\":false},{\"code\":\"da\",\"description\":\"Danish\",\"isActive\":false,\"isDefault\":false},{\"code\":\"de\",\"description\":\"German\",\"isActive\":false,\"isDefault\":false},{\"code\":\"dz\",\"description\":\"Bhutani\",\"isActive\":false,\"isDefault\":false},{\"code\":\"el\",\"description\":\"Greek\",\"isActive\":false,\"isDefault\":false},{\"code\":\"en\",\"description\":\"English\",\"isActive\":true,\"isDefault\":true},{\"code\":\"eo\",\"description\":\"Esperanto\",\"isActive\":false,\"isDefault\":false},{\"code\":\"es\",\"description\":\"Spanish\",\"isActive\":false,\"isDefault\":false},{\"code\":\"et\",\"description\":\"Estonian\",\"isActive\":false,\"isDefault\":false},{\"code\":\"eu\",\"description\":\"Basque\",\"isActive\":false,\"isDefault\":false},{\"code\":\"fa\",\"description\":\"Persian\",\"isActive\":false,\"isDefault\":false},{\"code\":\"fi\",\"description\":\"Finnish\",\"isActive\":false,\"isDefault\":false},{\"code\":\"fj\",\"description\":\"Fiji\",\"isActive\":false,\"isDefault\":false},{\"code\":\"fo\",\"description\":\"Faroese\",\"isActive\":false,\"isDefault\":false},{\"code\":\"fr\",\"description\":\"French\",\"isActive\":false,\"isDefault\":false},{\"code\":\"fy\",\"description\":\"Frisian\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ga\",\"description\":\"Irish\",\"isActive\":false,\"isDefault\":false},{\"code\":\"gd\",\"description\":\"Scots Gaelic\",\"isActive\":false,\"isDefault\":false},{\"code\":\"gl\",\"description\":\"Galician\",\"isActive\":false,\"isDefault\":false},{\"code\":\"gn\",\"description\":\"Guarani\",\"isActive\":false,\"isDefault\":false},{\"code\":\"gu\",\"description\":\"Gujarati\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ha\",\"description\":\"Hausa\",\"isActive\":false,\"isDefault\":false},{\"code\":\"he\",\"description\":\"Hebrew (formerly iw)\",\"isActive\":false,\"isDefault\":false},{\"code\":\"hi\",\"description\":\"Hindi\",\"isActive\":false,\"isDefault\":false},{\"code\":\"hr\",\"description\":\"Croatian\",\"isActive\":false,\"isDefault\":false},{\"code\":\"hu\",\"description\":\"Hungarian\",\"isActive\":false,\"isDefault\":false},{\"code\":\"hy\",\"description\":\"Armenian\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ia\",\"description\":\"Interlingua\",\"isActive\":false,\"isDefault\":false},{\"code\":\"id\",\"description\":\"Indonesian (formerly in)\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ie\",\"description\":\"Interlingue\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ik\",\"description\":\"Inupiak\",\"isActive\":false,\"isDefault\":false},{\"code\":\"is\",\"description\":\"Icelandic\",\"isActive\":false,\"isDefault\":false},{\"code\":\"it\",\"description\":\"Italian\",\"isActive\":true,\"isDefault\":false},{\"code\":\"iu\",\"description\":\"Inuktitut\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ja\",\"description\":\"Japanese\",\"isActive\":false,\"isDefault\":false},{\"code\":\"jw\",\"description\":\"Javanese\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ka\",\"description\":\"Georgian\",\"isActive\":false,\"isDefault\":false},{\"code\":\"kk\",\"description\":\"Kazakh\",\"isActive\":false,\"isDefault\":false},{\"code\":\"kl\",\"description\":\"Greenlandic\",\"isActive\":false,\"isDefault\":false},{\"code\":\"km\",\"description\":\"Cambodian\",\"isActive\":false,\"isDefault\":false},{\"code\":\"kn\",\"description\":\"Kannada\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ko\",\"description\":\"Korean\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ks\",\"description\":\"Kashmiri\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ku\",\"description\":\"Kurdish\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ky\",\"description\":\"Kirghiz\",\"isActive\":false,\"isDefault\":false},{\"code\":\"la\",\"description\":\"Latin\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ln\",\"description\":\"Lingala\",\"isActive\":false,\"isDefault\":false},{\"code\":\"lo\",\"description\":\"Laothian\",\"isActive\":false,\"isDefault\":false},{\"code\":\"lt\",\"description\":\"Lithuanian\",\"isActive\":false,\"isDefault\":false},{\"code\":\"lv\",\"description\":\"Latvian, Lettish\",\"isActive\":false,\"isDefault\":false},{\"code\":\"mg\",\"description\":\"Malagasy\",\"isActive\":false,\"isDefault\":false},{\"code\":\"mi\",\"description\":\"Maori\",\"isActive\":false,\"isDefault\":false},{\"code\":\"mk\",\"description\":\"Macedonian\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ml\",\"description\":\"Malayalam\",\"isActive\":false,\"isDefault\":false},{\"code\":\"mn\",\"description\":\"Mongolian\",\"isActive\":false,\"isDefault\":false},{\"code\":\"mo\",\"description\":\"Moldavian\",\"isActive\":false,\"isDefault\":false},{\"code\":\"mr\",\"description\":\"Marathi\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ms\",\"description\":\"Malay\",\"isActive\":false,\"isDefault\":false},{\"code\":\"mt\",\"description\":\"Maltese\",\"isActive\":false,\"isDefault\":false},{\"code\":\"my\",\"description\":\"Burmese\",\"isActive\":false,\"isDefault\":false},{\"code\":\"na\",\"description\":\"Nauru\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ne\",\"description\":\"Nepali\",\"isActive\":false,\"isDefault\":false},{\"code\":\"nl\",\"description\":\"Dutch\",\"isActive\":false,\"isDefault\":false},{\"code\":\"no\",\"description\":\"Norwegian\",\"isActive\":false,\"isDefault\":false},{\"code\":\"oc\",\"description\":\"Occitan\",\"isActive\":false,\"isDefault\":false},{\"code\":\"om\",\"description\":\"(Afan) Oromo\",\"isActive\":false,\"isDefault\":false},{\"code\":\"or\",\"description\":\"Oriya\",\"isActive\":false,\"isDefault\":false},{\"code\":\"pa\",\"description\":\"Punjabi\",\"isActive\":false,\"isDefault\":false},{\"code\":\"pl\",\"description\":\"Polish\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ps\",\"description\":\"Pashto, Pushto\",\"isActive\":false,\"isDefault\":false},{\"code\":\"pt\",\"description\":\"Portuguese\",\"isActive\":false,\"isDefault\":false},{\"code\":\"qu\",\"description\":\"Quechua\",\"isActive\":false,\"isDefault\":false},{\"code\":\"rm\",\"description\":\"Rhaeto-Romance\",\"isActive\":false,\"isDefault\":false},{\"code\":\"rn\",\"description\":\"Kirundi\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ro\",\"description\":\"Romanian\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ru\",\"description\":\"Russian\",\"isActive\":false,\"isDefault\":false},{\"code\":\"rw\",\"description\":\"Kinyarwanda\",\"isActive\":false,\"isDefault\":false},{\"code\":\"sa\",\"description\":\"Sanskrit\",\"isActive\":false,\"isDefault\":false},{\"code\":\"sd\",\"description\":\"Sindhi\",\"isActive\":false,\"isDefault\":false},{\"code\":\"sg\",\"description\":\"Sangho\",\"isActive\":false,\"isDefault\":false},{\"code\":\"sh\",\"description\":\"Serbo-Croatian\",\"isActive\":false,\"isDefault\":false},{\"code\":\"si\",\"description\":\"Sinhalese\",\"isActive\":false,\"isDefault\":false},{\"code\":\"sk\",\"description\":\"Slovak\",\"isActive\":false,\"isDefault\":false},{\"code\":\"sl\",\"description\":\"Slovenian\",\"isActive\":false,\"isDefault\":false},{\"code\":\"sm\",\"description\":\"Samoan\",\"isActive\":false,\"isDefault\":false},{\"code\":\"sn\",\"description\":\"Shona\",\"isActive\":false,\"isDefault\":false},{\"code\":\"so\",\"description\":\"Somali\",\"isActive\":false,\"isDefault\":false},{\"code\":\"sq\",\"description\":\"Albanian\",\"isActive\":false,\"isDefault\":false},{\"code\":\"sr\",\"description\":\"Serbian\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ss\",\"description\":\"Siswati\",\"isActive\":false,\"isDefault\":false},{\"code\":\"st\",\"description\":\"Sesotho\",\"isActive\":false,\"isDefault\":false},{\"code\":\"su\",\"description\":\"Sundanese\",\"isActive\":false,\"isDefault\":false},{\"code\":\"sv\",\"description\":\"Swedish\",\"isActive\":false,\"isDefault\":false},{\"code\":\"sw\",\"description\":\"Swahili\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ta\",\"description\":\"Tamil\",\"isActive\":false,\"isDefault\":false},{\"code\":\"te\",\"description\":\"Telugu\",\"isActive\":false,\"isDefault\":false},{\"code\":\"tg\",\"description\":\"Tajik\",\"isActive\":false,\"isDefault\":false},{\"code\":\"th\",\"description\":\"Thai\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ti\",\"description\":\"Tigrinya\",\"isActive\":false,\"isDefault\":false},{\"code\":\"tk\",\"description\":\"Turkmen\",\"isActive\":false,\"isDefault\":false},{\"code\":\"tl\",\"description\":\"Tagalog\",\"isActive\":false,\"isDefault\":false},{\"code\":\"tn\",\"description\":\"Setswana\",\"isActive\":false,\"isDefault\":false},{\"code\":\"to\",\"description\":\"Tonga\",\"isActive\":false,\"isDefault\":false},{\"code\":\"tr\",\"description\":\"Turkish\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ts\",\"description\":\"Tsonga\",\"isActive\":false,\"isDefault\":false},{\"code\":\"tt\",\"description\":\"Tatar\",\"isActive\":false,\"isDefault\":false},{\"code\":\"tw\",\"description\":\"Twi\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ug\",\"description\":\"Uighur\",\"isActive\":false,\"isDefault\":false},{\"code\":\"uk\",\"description\":\"Ukrainian\",\"isActive\":false,\"isDefault\":false},{\"code\":\"ur\",\"description\":\"Urdu\",\"isActive\":false,\"isDefault\":false},{\"code\":\"uz\",\"description\":\"Uzbek\",\"isActive\":false,\"isDefault\":false},{\"code\":\"vi\",\"description\":\"Vietnamese\",\"isActive\":false,\"isDefault\":false},{\"code\":\"vo\",\"description\":\"Volapuk\",\"isActive\":false,\"isDefault\":false},{\"code\":\"wo\",\"description\":\"Wolof\",\"isActive\":false,\"isDefault\":false},{\"code\":\"xh\",\"description\":\"Xhosa\",\"isActive\":false,\"isDefault\":false},{\"code\":\"yi\",\"description\":\"Yiddish (formerly ji)\",\"isActive\":false,\"isDefault\":false},{\"code\":\"yo\",\"description\":\"Yoruba\",\"isActive\":false,\"isDefault\":false},{\"code\":\"za\",\"description\":\"Zhuang\",\"isActive\":false,\"isDefault\":false},{\"code\":\"zh\",\"description\":\"Chinese - Traditional\",\"isActive\":false,\"isDefault\":false},{\"code\":\"zhs\",\"description\":\"Chinese - Simplified\",\"isActive\":false,\"isDefault\":false},{\"code\":\"zu\",\"description\":\"Zulu\",\"isActive\":false,\"isDefault\":false}],\"errors\":[],\"metaData\":{\"page\":1,\"pageSize\":0,\"lastPage\":1,\"totalItems\":140,\"sort\":\"code\",\"direction\":\"ASC\",\"filters\":[],\"additionalParams\":{}}}"
                    ,ContentType.APPLICATION_JSON)
                .headers(standardResponseHeaders)

                .uponReceiving("The User Query OPTIONS Interaction")
                .path("/entando/api/users")
                .method("OPTIONS")
                .matchQuery("page","1")
                .matchQuery("pageSize","10")
                .willRespondWith()
                .status(200)
                .body("","text/plain")
                .headers(standardResponseHeaders)



                .uponReceiving("The User Query GET Interaction")
                .path("/entando/api/users")
                .method("GET")
                .matchQuery("page","1")
                .matchQuery("pageSize","10")
                .matchHeader("accept", "\\*\\/\\*")
                .willRespondWith()
                .status(200)
                .body("{\"payload\":[{\"username\":\"" + this.username + "\",\"registration\":\"2018-08-31 00:00:00\",\"lastLogin\":null,\"lastPasswordChange\":null,\"status\":\"active\",\"accountNotExpired\":true,\"credentialsNotExpired\":true,\"profileType\":null,\"profileAttributes\":{},\"maxMonthsSinceLastAccess\":-1,\"maxMonthsSinceLastPasswordChange\":-1}],\"errors\":[],\"metaData\":{\"page\":1,\"pageSize\":1,\"lastPage\":2,\"totalItems\":2,\"sort\":\"username\",\"direction\":\"ASC\",\"filters\":[],\"additionalParams\":{}}}"
                        ,ContentType.APPLICATION_JSON)
                .headers(standardResponseHeaders)



                .uponReceiving("The ProfileTypes OPTIONS Interaction")
                .path(	"/entando/api/profileTypes")
                .method("OPTIONS")
                .matchQuery("page","1")
                .matchQuery("pageSize","0")
                .willRespondWith()
                .status(200)
                .body("")
                .headers(standardResponseHeaders)

                .uponReceiving("The ProfileTypes GET Interaction")
                .path(	"/entando/api/profileTypes")
                .method("GET")
                .matchQuery("page","1")
                .matchQuery("pageSize","0")
                .willRespondWith()
                .status(200)
                .body("{\"payload\":[{\"code\":\"PFL\",\"name\":\"Default user profile\",\"status\":\"0\"},{\"code\":\"1DF\",\"name\":\"1SeleniumTest_DontTouch\",\"status\":\"0\"}],\"errors\":[],\"metaData\":{\"page\":1,\"pageSize\":10,\"lastPage\":1,\"totalItems\":1,\"sort\":\"name\",\"direction\":\"ASC\",\"filters\":[],\"additionalParams\":{}}}"
                        ,ContentType.APPLICATION_JSON)
                .headers(standardResponseHeaders)

                .uponReceiving("The ProfileTypes OPTIONS Interaction")
                .path(	"/entando/api/profileTypes")
                .method("OPTIONS")
                .matchQuery("page","1")
                .matchQuery("pageSize","10")
                .willRespondWith()
                .status(200)
                .body("")
                .headers(standardResponseHeaders)

                .uponReceiving("The ProfileTypes GET Interaction")
                .path(	"/entando/api/profileTypes")
                .method("GET")
                .matchQuery("page","1")
                .matchQuery("pageSize","10")
                .willRespondWith()
                .status(200)
                .body("{\"payload\":[{\"code\":\"PFL\",\"name\":\"Default user profile\",\"status\":\"0\"},{\"code\":\"1DF\",\"name\":\"1SeleniumTest_DontTouch\",\"status\":\"0\"}],\"errors\":[],\"metaData\":{\"page\":1,\"pageSize\":10,\"lastPage\":1,\"totalItems\":1,\"sort\":\"name\",\"direction\":\"ASC\",\"filters\":[],\"additionalParams\":{}}}"
                        ,ContentType.APPLICATION_JSON)
                .headers(standardResponseHeaders)


                .uponReceiving("The User Add OPTIONS Interaction")
                .path("/entando/api/users/")
                .method("OPTIONS")
                .headers("Access-control-request-method","POST")
                .body("")
                .willRespondWith()
                .status(200)
                .body("","text/plain")
                .headers(standardResponseHeaders)


                .uponReceiving("The User Add POST Interaction")
                .path("/entando/api/users/")
                .method("POST")
                .willRespondWith()
                .status(200)
                .body( "{\"payload\":{\"username\":\"1SLNM_TEST_2354\",\"registration\":\"2018-09-03 00:00:00\",\"lastLogin\":null,\"lastPasswordChange\":null,\"status\":\"active\",\"accountNotExpired\":true,\"credentialsNotExpired\":true,\"profileType\":null,\"profileAttributes\":{},\"maxMonthsSinceLastAccess\":-1,\"maxMonthsSinceLastPasswordChange\":-1},\"errors\":[],\"metaData\":{}}"
                        ,ContentType.APPLICATION_JSON)
                .headers(standardResponseHeaders)

                .uponReceiving("The User Query2 Interaction")
                .path("/entando/api/users")
                .method("OPTIONS")
                .matchQuery("page","1")
                .matchQuery("pageSize","1")
                .willRespondWith()
                .status(200)
                .body("","text/plain")
                .headers(standardResponseHeaders)


                .uponReceiving("The User Query2 Interaction")
                .path("/entando/api/users")
                .method("GET")
                .matchQuery("page","1")
                .matchQuery("pageSize","1")
                .matchHeader("accept", "\\*\\/\\*")
                .willRespondWith()
                .status(200)
                .body("{\"payload\":[{\"username\":\"" + this.username + "\",\"registration\":\"2018-08-31 00:00:00\",\"lastLogin\":null,\"lastPasswordChange\":null,\"status\":\"active\",\"accountNotExpired\":true,\"credentialsNotExpired\":true,\"profileType\":null,\"profileAttributes\":{},\"maxMonthsSinceLastAccess\":-1,\"maxMonthsSinceLastPasswordChange\":-1}],\"errors\":[],\"metaData\":{\"page\":1,\"pageSize\":1,\"lastPage\":2,\"totalItems\":2,\"sort\":\"username\",\"direction\":\"ASC\",\"filters\":[],\"additionalParams\":{}}}"
                        ,ContentType.APPLICATION_JSON)
                .headers(standardResponseHeaders)


                .uponReceiving("The User Delete OPTIONS Interaction")
                .path("/entando/api/users/" + this.username)
                .method("OPTIONS")
                .headers("Access-control-request-method","DELETE")
                .willRespondWith()
                .status(200)
                .body("","text/plain")
                .headers(standardResponseHeaders)


                .uponReceiving("The User Delete DELETE Interaction")
                .path("/entando/api/users/"+ this.username)
                .method("DELETE")
                .willRespondWith()
                .status(200)
                .body("{\"payload\":{\"code\":\""+this.username+"\"},\"errors\":[],\"metaData\":{}}"
                        ,ContentType.APPLICATION_JSON)
                .headers(standardResponseHeaders)





                .toPact();
    }
    /*
        Test
    */
    @Test
    public void runTest() throws InterruptedException {
        /*
            Parameters
        */
        //Link menù buttons

        //Final page title
        String pageTitle = "Add";
        
        //Usernames to set
        String falseUsername = "1SLNM_TEST-*-" + randomNumber;
                
        //Passwords to set
        String password = Double.toString(Math.pow(10, super.minPasswordLength-1) + randomNumber);
        String falsePassword = Integer.toString((int)Math.round(Math.pow(10, super.minPasswordLength-2) + randomNumber));
        String falseConfirmPassword = Integer.toString((int)Math.round(Math.pow(10, super.minPasswordLength-3) + randomNumber));
        
        //Status string
        String statusString = " Active";


        /*
            Actions and asserts
        */
        //Login
        login();
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLinkWithSleep("User Management", "Users");
        Utils.waitUntilIsVisible(driver, dTUsersPage.getAddButton());
        
        dTUsersPage.getAddButton().click();

        Utils.waitUntilIsVisible(driver, dTUserAddPage.getPageTitle());

        //Asserts the PAGE TITLE is the expected one
        Assert.assertEquals(pageTitle, dTUserAddPage.getPageTitle().getText());

        //Asserts the presence of the HELP button
        dTUserAddPage.getHelp().click();
        Utils.waitUntilIsVisible(driver, dTUserAddPage.getTooltip());
        Assert.assertTrue(dTUserAddPage.getTooltip().isDisplayed());

        //Verify "field required" warning
        dTUserAddPage.setUsernameField(falseUsername);
        dTUserAddPage.setPasswordField(falsePassword);
        dTUserAddPage.setPasswordConfirmField(falseConfirmPassword);
        dTUserAddPage.getProfileType().click();
        dTUserAddPage.getUsernameField().click();
        Logger.getGlobal().info(falsePassword);
        Assert.assertTrue(dTUserAddPage.getUsernameFieldError().isDisplayed());
        Assert.assertTrue(dTUserAddPage.getPasswordFieldError().isDisplayed());
        Assert.assertTrue(dTUserAddPage.getPasswordConfirmFieldError().isDisplayed());
        Assert.assertTrue(dTUserAddPage.getProfileTypeError().isDisplayed());

        //Verify Switch
        Assert.assertFalse(dTUserAddPage.getStatusSwitch().isOn());

        //Verify Save buttons are disable
        Assert.assertFalse(dTUserAddPage.getSaveButton().isEnabled());



        //Compilation of the page
        dTUserAddPage.setUsernameField(this.username);
        dTUserAddPage.setPasswordField(password);
        dTUserAddPage.setPasswordConfirmField(password);
        dTUserAddPage.getProfileTypeSelect().selectByVisibleText(profileType);
        if(super.status){
            dTUserAddPage.getStatusSwitch().setOn();
        }else{
            dTUserAddPage.getStatusSwitch().setOff();
        }



        //Save and come back to the Users list
        Assert.assertTrue(dTUserAddPage.getSaveButton().isEnabled());
        dTUserAddPage.getSaveButton().click();

//        Utils.waitUntilIsPresent(driver, dTUsersPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTUsersPage.spinnerTag);


        //Assert the presence of the created user in the Users table
        List<WebElement> createdUser = dTUsersPage.getTable().findRowList(this.username, super.usersTableHeaderTitles.get(0));
        Assert.assertFalse(createdUser.isEmpty());

        //Verify "Status" is "Active"
        WebElement cell = dTUsersPage.getTable().getCell(this.username, super.usersTableHeaderTitles.get(0), super.usersTableHeaderTitles.get(3));
        Assert.assertEquals(statusString, cell.getText());

        //Delete the created user
        Assert.assertTrue(deleteUser(dTUsersPage, this.username));
        
        /** Debug code **/
        Logger.getGlobal().info("TEST CONCLUSO");
        if(Logger.getGlobal().getLevel() == Level.INFO){
//            sleep(SLEEPTIME);
        }
        /** End Debug code**/
        
    }

}//end class
