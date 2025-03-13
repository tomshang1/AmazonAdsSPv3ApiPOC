package org.example.objectUtils;

import org.openapitools.client.model.SponsoredProductsCreateAdGroup;
import org.openapitools.client.model.SponsoredProductsCreateOrUpdateEntityState;
import org.openapitools.client.model.SponsoredProductsCreateSponsoredProductsAdGroupsRequestContent;

import java.util.List;

import static org.example.CommonUtils.generateName;

public class SPAdGroupUtils {
    public static SponsoredProductsCreateSponsoredProductsAdGroupsRequestContent buildCreateSPAdGroupsRequestContent(
            final String campaignId
    ) {
        final SponsoredProductsCreateSponsoredProductsAdGroupsRequestContent requestContent
                = new SponsoredProductsCreateSponsoredProductsAdGroupsRequestContent();
        requestContent.setAdGroups(List.of(buildSponsoredProductsCreateAdGroup(campaignId)));
        return requestContent;
    }

    private static SponsoredProductsCreateAdGroup buildSponsoredProductsCreateAdGroup(final String campaignId) {
        final SponsoredProductsCreateAdGroup adGroup = new SponsoredProductsCreateAdGroup();
        adGroup.setCampaignId(campaignId);
        adGroup.setName(generateName("SP_ADGROUP"));
        adGroup.setState(SponsoredProductsCreateOrUpdateEntityState.PAUSED);
        adGroup.setDefaultBid(100d);
        return adGroup;
    }
}
