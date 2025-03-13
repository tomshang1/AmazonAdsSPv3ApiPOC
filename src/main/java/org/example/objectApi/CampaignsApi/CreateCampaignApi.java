package org.example.objectApi.CampaignsApi;

import org.openapitools.client.ApiException;
import org.openapitools.client.api.CampaignsApi;
import org.openapitools.client.model.SponsoredProductsCampaignMutationSuccessResponseItem;
import org.openapitools.client.model.SponsoredProductsCreateSponsoredProductsCampaignsRequestContent;
import org.openapitools.client.model.SponsoredProductsCreateSponsoredProductsCampaignsResponseContent;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import static org.example.AuthUtils.CLIENT_ID_HEADER_NAME;
import static org.example.AuthUtils.CLIENT_SECRET_HEADER_NAME;
import static org.example.AuthUtils.PROFILE_ID_HEADER_NAME;
import static org.example.AuthUtils.REFRESH_TOKEN_HEADER_NAME;
import static org.example.AuthUtils.getRefreshedToken;
import static org.junit.Assert.assertNotNull;

public class CreateCampaignApi {
    public static String createCampaign(final CampaignsApi campaignsApi,
                                        final SponsoredProductsCreateSponsoredProductsCampaignsRequestContent createRequestContent,
                                        final Map<String, String> authMap)
            throws IOException, InterruptedException, ApiException {
        final SponsoredProductsCreateSponsoredProductsCampaignsResponseContent createResponseContent;
        try {
            campaignsApi.getApiClient().addDefaultHeader("Authorization",
                    getRefreshedToken(authMap.get(REFRESH_TOKEN_HEADER_NAME), authMap.get(CLIENT_ID_HEADER_NAME), authMap.get(CLIENT_SECRET_HEADER_NAME)));
            createResponseContent = campaignsApi.createSponsoredProductsCampaigns(
                    authMap.get(CLIENT_ID_HEADER_NAME), authMap.get(PROFILE_ID_HEADER_NAME), createRequestContent, null);
        } catch (final ApiException e) {
            System.out.println("Exception while creating campaign: " + e.getMessage()
                    + "\n Headers: " + e.getResponseHeaders()
                    + "\n Body: " + e.getResponseBody());
            throw e;
        }

        System.out.println("Create Campaigns request: " + createRequestContent + ", response: " + createResponseContent);
        final String campaignId = Optional.ofNullable(createResponseContent.getCampaigns().getSuccess())
                .map(list -> list.get(0))
                .map(SponsoredProductsCampaignMutationSuccessResponseItem::getCampaignId)
                .orElse(null);
        assertNotNull(campaignId);

        return campaignId;
    }
}
