package com.github.scribejava.apis;

import com.github.scribejava.apis.service.MicrosoftAzureActiveDirectoryService;
import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.httpclient.HttpClient;
import com.github.scribejava.core.httpclient.HttpClientConfig;
import com.github.scribejava.core.oauth2.clientauthentication.ClientAuthentication;
import com.github.scribejava.core.oauth2.clientauthentication.RequestBodyAuthenticationScheme;
import java.io.OutputStream;

/**
 * Microsoft Azure Active Directory Api
 *
 * @see <a href="https://docs.microsoft.com/en-us/azure/active-directory/develop/active-directory-protocols-oauth-code">
 * Understand the OAuth 2.0 authorization code flow in Azure AD | Microsoft Docs</a>
 * @see <a
 * href="https://docs.microsoft.com/en-us/azure/active-directory/develop/active-directory-devquickstarts-webapp-java">
 * Azure AD Java web app Getting Started | Microsoft Docs</a>
 * @see <a href="https://msdn.microsoft.com/en-us/library/azure/ad/graph/api/signed-in-user-operations">
 * Azure AD Graph API Operations on the Signed-in User</a>
 * @see <a href="https://portal.azure.com">https://portal.azure.com</a>
 */
public class MicrosoftAzureActiveDirectoryApi extends DefaultApi20 {

    private static final String MSFT_GRAPH_URL = "https://graph.windows.net";

    private static final String MSFT_LOGIN_URL = "https://login.microsoftonline.com";
    private static final String SLASH = "/";
    private static final String COMMON = "common";
    private static final String TOKEN_URI = "oauth2/token";
    private static final String AUTH_URI = "oauth2/authorize?resource=" + MSFT_GRAPH_URL;

    private static class InstanceHolder {

        private static final MicrosoftAzureActiveDirectoryApi INSTANCE = new MicrosoftAzureActiveDirectoryApi();
    }

    public static MicrosoftAzureActiveDirectoryApi instance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public String getAccessTokenEndpoint() {
        return MSFT_LOGIN_URL + SLASH + COMMON + SLASH + TOKEN_URI;
    }

    @Override
    protected String getAuthorizationBaseUrl() {
        return MSFT_LOGIN_URL + SLASH + COMMON + SLASH + AUTH_URI;
    }

    @Override
    public MicrosoftAzureActiveDirectoryService createService(String apiKey, String apiSecret, String callback,
            String scope, OutputStream debugStream, String state, String responseType, String userAgent,
            HttpClientConfig httpClientConfig, HttpClient httpClient) {
        return new MicrosoftAzureActiveDirectoryService(this, apiKey, apiSecret, callback, scope, state, responseType,
                userAgent, httpClientConfig, httpClient);
    }

    @Override
    public ClientAuthentication getClientAuthentication() {
        return RequestBodyAuthenticationScheme.instance();
    }
}
