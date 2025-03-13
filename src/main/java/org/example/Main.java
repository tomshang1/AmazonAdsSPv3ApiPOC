package org.example;
import org.apache.commons.lang3.StringUtils;
import org.example.objectApi.AdGroupsApiService;
import org.example.objectApi.CampaignsApiService;
import org.example.objectApi.TargetsApiService;
import org.openapitools.client.ApiException;
import org.openapitools.client.model.SponsoredProductsDeleteSponsoredProductsCampaignsResponseContent;
import org.openapitools.client.model.SponsoredProductsDeleteSponsoredProductsTargetingClausesResponseContent;
import org.openapitools.client.model.SponsoredProductsEntityState;
import org.openapitools.client.model.SponsoredProductsListSponsoredProductsCampaignsResponseContent;
import org.openapitools.client.model.SponsoredProductsListSponsoredProductsTargetingClausesResponseContent;
import org.openapitools.client.model.SponsoredProductsUpdateSponsoredProductsCampaignsResponseContent;
import org.openapitools.client.model.SponsoredProductsUpdateSponsoredProductsTargetingClausesResponseContent;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import static org.example.AuthUtils.CLIENT_ID_HEADER_NAME;
import static org.example.AuthUtils.CLIENT_SECRET_HEADER_NAME;
import static org.example.AuthUtils.PROFILE_ID_HEADER_NAME;
import static org.example.AuthUtils.REFRESH_TOKEN_HEADER_NAME;
import static org.example.AuthUtils.getAccessTokenMap;
import static org.example.AuthUtils.getAdGroupsApi;
import static org.example.AuthUtils.getCampaignsApi;
import static org.example.AuthUtils.getProfileId;
import static org.example.AuthUtils.getRefreshedToken;
import static org.example.AuthUtils.getTargetsApi;
import static org.example.objectUtils.SPAdGroupUtils.buildCreateSPAdGroupsRequestContent;
import static org.example.objectUtils.SPCampaignUtils.buildCreateSPCampaignsRequestContent;
import static org.example.objectUtils.SPCampaignUtils.buildDeleteSPCampaignsRequestContent;
import static org.example.objectUtils.SPCampaignUtils.buildListSPCampaignsRequestContentIdFilter;
import static org.example.objectUtils.SPCampaignUtils.buildUpdateSPCampaignsRequestContent;
import static org.example.objectUtils.SPTargetUtils.buildCreateSPTargetsRequestContent;
import static org.example.objectUtils.SPTargetUtils.buildDeleteSPTargetsRequestContent;
import static org.example.objectUtils.SPTargetUtils.buildListSPTargetsRequestContentIdFilter;
import static org.example.objectUtils.SPTargetUtils.buildUpdateSPTargetsRequestContent;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Main {
    // TO BE SET BY USER
    private final static String AUTH_CODE = null; // Change with new auth code to fetch profileId or refresh token
    private final static String PROFILE_ID_SCOPE = null; // Set this to fetched profileId! Don't need to refresh this
    public final static String REFRESH_TOKEN = null; // Set this with fetched refresh token! Don't need to refresh this
    private final static String CLIENT_ID = null; // Get from LWA account
    private final static String CLIENT_SECRET = null; // Get from LWA account
    // END

    private static final Map<String, String> AUTH_MAP = Map.of(
            CLIENT_ID_HEADER_NAME, CLIENT_ID,
            CLIENT_SECRET_HEADER_NAME, CLIENT_SECRET,
            PROFILE_ID_HEADER_NAME, PROFILE_ID_SCOPE,
            REFRESH_TOKEN_HEADER_NAME, REFRESH_TOKEN
    );

    private static final CampaignsApiService campaignsApiService = new CampaignsApiService(getCampaignsApi(), AUTH_MAP);
    private static final AdGroupsApiService adGroupsApiService = new AdGroupsApiService(getAdGroupsApi(), AUTH_MAP);
    private static final TargetsApiService targetsApiService = new TargetsApiService(getTargetsApi(), AUTH_MAP);

    private static void testCampaignApiFunctionality() throws IOException, InterruptedException, ApiException {
        // Create Campaign with paused state
        final String campaignId = campaignsApiService.createCampaign(buildCreateSPCampaignsRequestContent());

        // Query Campaign
        final SponsoredProductsListSponsoredProductsCampaignsResponseContent listResponseContent
                = campaignsApiService.listCampaign(buildListSPCampaignsRequestContentIdFilter(campaignId));
        assertEquals(1, Objects.requireNonNull(listResponseContent.getCampaigns()).size());
        assertEquals(SponsoredProductsEntityState.PAUSED, Objects.requireNonNull(listResponseContent.getCampaigns()).get(0).getState());

        // Update Campaign with enabled state
        final SponsoredProductsUpdateSponsoredProductsCampaignsResponseContent updateResponseContent
                = campaignsApiService.updateCampaign(buildUpdateSPCampaignsRequestContent(campaignId));
        assertNotNull(updateResponseContent.getCampaigns().getSuccess());
        assertEquals(1, updateResponseContent.getCampaigns().getSuccess().size());

        // Query Campaign
        final SponsoredProductsListSponsoredProductsCampaignsResponseContent updatedListResponseContent
                = campaignsApiService.listCampaign(buildListSPCampaignsRequestContentIdFilter(campaignId));
        assertEquals(1, Objects.requireNonNull(updatedListResponseContent.getCampaigns()).size());
        assertEquals(SponsoredProductsEntityState.ENABLED, Objects.requireNonNull(updatedListResponseContent.getCampaigns()).get(0).getState());

        // Delete Campaign with archived state
        final SponsoredProductsDeleteSponsoredProductsCampaignsResponseContent deleteResponseContent
                = campaignsApiService.deleteCampaign(buildDeleteSPCampaignsRequestContent(campaignId));
        assertNotNull(deleteResponseContent.getCampaigns().getSuccess());
        assertEquals(1, deleteResponseContent.getCampaigns().getSuccess().size());

        // Query Campaign
        final SponsoredProductsListSponsoredProductsCampaignsResponseContent deletedListResponseContent
                = campaignsApiService.listCampaign(buildListSPCampaignsRequestContentIdFilter(campaignId));
        assertEquals(1, Objects.requireNonNull(updatedListResponseContent.getCampaigns()).size());
        assertEquals(SponsoredProductsEntityState.ARCHIVED, Objects.requireNonNull(deletedListResponseContent.getCampaigns()).get(0).getState());
    }

    private static void testTargetApiFunctionality() throws IOException, InterruptedException, ApiException {
        // Create Parent Campaign
        final String campaignId = campaignsApiService.createCampaign(buildCreateSPCampaignsRequestContent());

        // Create Parent AdGroup using Parent Campaign
        final String adGroupId = adGroupsApiService.createAdGroup(buildCreateSPAdGroupsRequestContent(campaignId));

        // Create Target with paused state
        final String targetId = targetsApiService.createTarget(buildCreateSPTargetsRequestContent(campaignId, adGroupId));

        // Query Target
        final SponsoredProductsListSponsoredProductsTargetingClausesResponseContent listResponseContent
                = targetsApiService.listTarget(buildListSPTargetsRequestContentIdFilter(targetId));
        assertEquals(1, Objects.requireNonNull(listResponseContent.getTargetingClauses()).size());
        assertEquals(SponsoredProductsEntityState.PAUSED, Objects.requireNonNull(listResponseContent.getTargetingClauses()).get(0).getState());

        // Update Target with enabled state
        final SponsoredProductsUpdateSponsoredProductsTargetingClausesResponseContent updateResponseContent
                = targetsApiService.updateTarget(buildUpdateSPTargetsRequestContent(targetId));
        assertNotNull(updateResponseContent.getTargetingClauses().getSuccess());
        assertEquals(1, updateResponseContent.getTargetingClauses().getSuccess().size());

        // Query Target
        final SponsoredProductsListSponsoredProductsTargetingClausesResponseContent updatedListResponseContent
                = targetsApiService.listTarget(buildListSPTargetsRequestContentIdFilter(targetId));
        assertEquals(1, Objects.requireNonNull(updatedListResponseContent.getTargetingClauses()).size());
        assertEquals(SponsoredProductsEntityState.ENABLED, Objects.requireNonNull(updatedListResponseContent.getTargetingClauses()).get(0).getState());

        // Delete Target with archived state
        final SponsoredProductsDeleteSponsoredProductsTargetingClausesResponseContent deleteResponseContent
                = targetsApiService.deleteTarget(buildDeleteSPTargetsRequestContent(targetId));
        assertNotNull(deleteResponseContent.getTargetingClauses().getSuccess());
        assertEquals(1, deleteResponseContent.getTargetingClauses().getSuccess().size());

        // Query Target
        final SponsoredProductsListSponsoredProductsTargetingClausesResponseContent deletedListResponseContent
                = targetsApiService.listTarget(buildListSPTargetsRequestContentIdFilter(targetId));
        assertEquals(1, Objects.requireNonNull(deletedListResponseContent.getTargetingClauses()).size());
        assertEquals(SponsoredProductsEntityState.ARCHIVED, Objects.requireNonNull(deletedListResponseContent.getTargetingClauses()).get(0).getState());

        campaignsApiService.deleteCampaign(buildDeleteSPCampaignsRequestContent(campaignId));
    }

    public static void main(String[] args) throws IOException, InterruptedException, ApiException {
        if (StringUtils.isBlank(REFRESH_TOKEN)) {
            final Map<String, String> tokenMap = getAccessTokenMap(AUTH_CODE, CLIENT_ID, CLIENT_SECRET);
            System.out.println("PLEASE ADD VALUE TO REFRESH_TOKEN and try again: " + tokenMap.get(REFRESH_TOKEN_HEADER_NAME));
            return; // if refreshToken is not set, fetch it, print it to console, and return (since auth code can only be used once)
        }

        if (StringUtils.isBlank(PROFILE_ID_SCOPE)) {
            final String profileId = getProfileId(getRefreshedToken(REFRESH_TOKEN, CLIENT_ID, CLIENT_SECRET), CLIENT_ID);
            System.out.println("PLEASE ADD VALUE TO PROFILE_ID_SCOPE and try again: " + profileId);
            return; // if profileId is not set, fetch it, print it to console, and return (since auth code can only be used once)
        }

        testCampaignApiFunctionality();

        testTargetApiFunctionality();
    }
}