package org.example.objectApi.TargetsApi;

import org.openapitools.client.ApiException;
import org.openapitools.client.api.TargetingClausesApi;
import org.openapitools.client.model.SponsoredProductsDeleteSponsoredProductsTargetingClausesRequestContent;
import org.openapitools.client.model.SponsoredProductsDeleteSponsoredProductsTargetingClausesResponseContent;

import java.io.IOException;
import java.util.Map;

import static org.example.AuthUtils.CLIENT_ID_HEADER_NAME;
import static org.example.AuthUtils.CLIENT_SECRET_HEADER_NAME;
import static org.example.AuthUtils.PROFILE_ID_HEADER_NAME;
import static org.example.AuthUtils.REFRESH_TOKEN_HEADER_NAME;
import static org.example.AuthUtils.getRefreshedToken;

public class DeleteTargetApi {
    public static SponsoredProductsDeleteSponsoredProductsTargetingClausesResponseContent deleteTarget(
            final TargetingClausesApi targetsApi,
            final SponsoredProductsDeleteSponsoredProductsTargetingClausesRequestContent deleteRequestContent,
            final Map<String, String> authMap)
            throws IOException, InterruptedException, ApiException {
        final SponsoredProductsDeleteSponsoredProductsTargetingClausesResponseContent deleteResponseContent;
        try {
            targetsApi.getApiClient().addDefaultHeader("Authorization",
                    getRefreshedToken(authMap.get(REFRESH_TOKEN_HEADER_NAME), authMap.get(CLIENT_ID_HEADER_NAME), authMap.get(CLIENT_SECRET_HEADER_NAME)));
            deleteResponseContent = targetsApi.deleteSponsoredProductsTargetingClauses(authMap.get(CLIENT_ID_HEADER_NAME), authMap.get(PROFILE_ID_HEADER_NAME), deleteRequestContent);
        } catch (final ApiException e) {
            System.out.println("Exception while deleting target: " + e.getMessage()
                    + "\n Headers: " + e.getResponseHeaders()
                    + "\n Body: " + e.getResponseBody());
            throw e;
        }

        System.out.println("Delete targets request: " + deleteRequestContent + ", response: " + deleteResponseContent);

        return deleteResponseContent;
    }
}
