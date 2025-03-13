package org.example.objectApi.TargetsApi;

import org.openapitools.client.ApiException;
import org.openapitools.client.api.TargetingClausesApi;
import org.openapitools.client.model.SponsoredProductsUpdateSponsoredProductsTargetingClausesRequestContent;
import org.openapitools.client.model.SponsoredProductsUpdateSponsoredProductsTargetingClausesResponseContent;

import java.io.IOException;
import java.util.Map;

import static org.example.AuthUtils.CLIENT_ID_HEADER_NAME;
import static org.example.AuthUtils.CLIENT_SECRET_HEADER_NAME;
import static org.example.AuthUtils.PROFILE_ID_HEADER_NAME;
import static org.example.AuthUtils.REFRESH_TOKEN_HEADER_NAME;
import static org.example.AuthUtils.getRefreshedToken;

public class UpdateTargetApi {
    public static SponsoredProductsUpdateSponsoredProductsTargetingClausesResponseContent updateTarget(
            final TargetingClausesApi targetsApi,
            final SponsoredProductsUpdateSponsoredProductsTargetingClausesRequestContent updateRequestContent,
            final Map<String, String> authMap)
            throws IOException, InterruptedException, ApiException {
        final SponsoredProductsUpdateSponsoredProductsTargetingClausesResponseContent updateResponseContent;
        try {
            targetsApi.getApiClient().addDefaultHeader("Authorization",
                    getRefreshedToken(authMap.get(REFRESH_TOKEN_HEADER_NAME), authMap.get(CLIENT_ID_HEADER_NAME), authMap.get(CLIENT_SECRET_HEADER_NAME)));
            updateResponseContent = targetsApi.updateSponsoredProductsTargetingClauses(authMap.get(CLIENT_ID_HEADER_NAME), authMap.get(PROFILE_ID_HEADER_NAME), updateRequestContent, null);
        } catch (final ApiException e) {
            System.out.println("Exception while updating target: " + e.getMessage()
                    + "\n Headers: " + e.getResponseHeaders()
                    + "\n Body: " + e.getResponseBody());
            throw e;
        }

        System.out.println("Update target request: " + updateRequestContent + ", response: " + updateResponseContent);

        return updateResponseContent;
    }
}
