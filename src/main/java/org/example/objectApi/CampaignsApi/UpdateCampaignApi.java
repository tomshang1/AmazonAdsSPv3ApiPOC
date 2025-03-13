package org.example.objectApi.CampaignsApi;

import org.openapitools.client.ApiException;
import org.openapitools.client.api.CampaignsApi;
import org.openapitools.client.model.SponsoredProductsUpdateSponsoredProductsCampaignsRequestContent;
import org.openapitools.client.model.SponsoredProductsUpdateSponsoredProductsCampaignsResponseContent;

import java.io.IOException;
import java.util.Map;

import static org.example.AuthUtils.CLIENT_ID_HEADER_NAME;
import static org.example.AuthUtils.CLIENT_SECRET_HEADER_NAME;
import static org.example.AuthUtils.PROFILE_ID_HEADER_NAME;
import static org.example.AuthUtils.REFRESH_TOKEN_HEADER_NAME;
import static org.example.AuthUtils.getRefreshedToken;

public class UpdateCampaignApi {
    public static SponsoredProductsUpdateSponsoredProductsCampaignsResponseContent updateCampaign(
            final CampaignsApi campaignsApi,
            final SponsoredProductsUpdateSponsoredProductsCampaignsRequestContent updateRequestContent,
            final Map<String, String> authMap)
            throws IOException, InterruptedException, ApiException {
        final SponsoredProductsUpdateSponsoredProductsCampaignsResponseContent updateResponseContent;
        try {
            campaignsApi.getApiClient().addDefaultHeader("Authorization",
                    getRefreshedToken(authMap.get(REFRESH_TOKEN_HEADER_NAME), authMap.get(CLIENT_ID_HEADER_NAME), authMap.get(CLIENT_SECRET_HEADER_NAME)));
            updateResponseContent = campaignsApi.updateSponsoredProductsCampaigns(
                    authMap.get(CLIENT_ID_HEADER_NAME), authMap.get(PROFILE_ID_HEADER_NAME), updateRequestContent, null);
        } catch (final ApiException e) {
            System.out.println("Exception while updating campaign: " + e.getMessage()
                    + "\n Headers: " + e.getResponseHeaders()
                    + "\n Body: " + e.getResponseBody());
            throw e;
        }

        System.out.println("Update Campaigns request: " + updateRequestContent + ", response: " + updateResponseContent);

        return updateResponseContent;
    }
}
