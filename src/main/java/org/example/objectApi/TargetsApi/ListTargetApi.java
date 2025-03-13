package org.example.objectApi.TargetsApi;

import org.openapitools.client.ApiException;
import org.openapitools.client.api.TargetingClausesApi;
import org.openapitools.client.model.SponsoredProductsListSponsoredProductsTargetingClausesRequestContent;
import org.openapitools.client.model.SponsoredProductsListSponsoredProductsTargetingClausesResponseContent;

import java.io.IOException;
import java.util.Map;

import static org.example.AuthUtils.CLIENT_ID_HEADER_NAME;
import static org.example.AuthUtils.CLIENT_SECRET_HEADER_NAME;
import static org.example.AuthUtils.PROFILE_ID_HEADER_NAME;
import static org.example.AuthUtils.REFRESH_TOKEN_HEADER_NAME;
import static org.example.AuthUtils.getRefreshedToken;

public class ListTargetApi {
    public static SponsoredProductsListSponsoredProductsTargetingClausesResponseContent listTarget(
            final TargetingClausesApi targetsApi,
            final SponsoredProductsListSponsoredProductsTargetingClausesRequestContent listRequestContent,
            final Map<String, String> authMap)
            throws IOException, InterruptedException, ApiException {
        final SponsoredProductsListSponsoredProductsTargetingClausesResponseContent listResponseContent;
        try {
            targetsApi.getApiClient().addDefaultHeader("Authorization",
                    getRefreshedToken(authMap.get(REFRESH_TOKEN_HEADER_NAME), authMap.get(CLIENT_ID_HEADER_NAME), authMap.get(CLIENT_SECRET_HEADER_NAME)));
            listResponseContent = targetsApi.listSponsoredProductsTargetingClauses(authMap.get(CLIENT_ID_HEADER_NAME), authMap.get(PROFILE_ID_HEADER_NAME), listRequestContent);
        } catch (final ApiException e) {
            System.out.println("Exception while listing target: " + e.getMessage()
                    + "\n Headers: " + e.getResponseHeaders()
                    + "\n Body: " + e.getResponseBody());
            throw e;
        }

        System.out.println("List targets request: " + listRequestContent + ", response: " + listResponseContent);

        return listResponseContent;
    }
}
