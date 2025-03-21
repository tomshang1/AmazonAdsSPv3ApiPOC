package org.example.objectUtils;

import org.openapitools.client.model.SponsoredProductsAudienceSegment;
import org.openapitools.client.model.SponsoredProductsAudienceSegmentType;
import org.openapitools.client.model.SponsoredProductsCreateCampaign;
import org.openapitools.client.model.SponsoredProductsCreateOrUpdateBudget;
import org.openapitools.client.model.SponsoredProductsCreateOrUpdateBudgetType;
import org.openapitools.client.model.SponsoredProductsCreateOrUpdateDynamicBidding;
import org.openapitools.client.model.SponsoredProductsCreateOrUpdateEntityState;
import org.openapitools.client.model.SponsoredProductsCreateSponsoredProductsCampaignsRequestContent;
import org.openapitools.client.model.SponsoredProductsDeleteSponsoredProductsCampaignsRequestContent;
import org.openapitools.client.model.SponsoredProductsEntityState;
import org.openapitools.client.model.SponsoredProductsEntityStateFilter;
import org.openapitools.client.model.SponsoredProductsListSponsoredProductsCampaignsRequestContent;
import org.openapitools.client.model.SponsoredProductsObjectIdFilter;
import org.openapitools.client.model.SponsoredProductsShopperCohortBidding;
import org.openapitools.client.model.SponsoredProductsShopperCohortType;
import org.openapitools.client.model.SponsoredProductsTargetingType;
import org.openapitools.client.model.SponsoredProductsUpdateCampaign;
import org.openapitools.client.model.SponsoredProductsUpdateSponsoredProductsCampaignsRequestContent;

import java.util.List;

import static org.example.CommonUtils.generateName;

public class SPCampaignUtils {

    public static SponsoredProductsCreateSponsoredProductsCampaignsRequestContent buildCreateSPCampaignsRequestContent() {
        final SponsoredProductsCreateSponsoredProductsCampaignsRequestContent requestContent
                = new SponsoredProductsCreateSponsoredProductsCampaignsRequestContent();
        requestContent.addCampaignsItem(createSponsoredProductsCreateCampaign());

        return requestContent;
    }

    private static SponsoredProductsCreateCampaign createSponsoredProductsCreateCampaign() {
        final SponsoredProductsCreateCampaign createCampaign = new SponsoredProductsCreateCampaign();
        createCampaign.setName(generateName("SP_CAMPAIGN"));
        createCampaign.setState(SponsoredProductsCreateOrUpdateEntityState.PAUSED);
        createCampaign.setTargetingType(SponsoredProductsTargetingType.MANUAL);
        createCampaign.setBudget(createSponsoredProductsCreateOrUpdateBudget());
        // ADDING SHOPPER COHORT FEATURE
        // Commenting for now as it may cause issues in beta
        //createCampaign.setDynamicBidding(buildCreateDynamicBidding());
        return createCampaign;
    }

    private static SponsoredProductsCreateOrUpdateDynamicBidding buildCreateDynamicBidding() {
        final SponsoredProductsAudienceSegment audienceSegment = new SponsoredProductsAudienceSegment();
        audienceSegment.setAudienceId("407353587171002826");
        audienceSegment.setAudienceSegmentType(SponsoredProductsAudienceSegmentType.SPONSORED_ADS_AMC);

        final SponsoredProductsShopperCohortBidding shopperCohortBidding = new SponsoredProductsShopperCohortBidding();
        shopperCohortBidding.setShopperCohortType(SponsoredProductsShopperCohortType.AUDIENCE_SEGMENT);
        shopperCohortBidding.setPercentage(10);
        shopperCohortBidding.setAudienceSegments(List.of(audienceSegment));

        final SponsoredProductsCreateOrUpdateDynamicBidding dynamicBidding = new SponsoredProductsCreateOrUpdateDynamicBidding();
        dynamicBidding.setShopperCohortBidding(List.of(shopperCohortBidding));

        return dynamicBidding;
    }

    private static SponsoredProductsCreateOrUpdateBudget createSponsoredProductsCreateOrUpdateBudget() {
        final SponsoredProductsCreateOrUpdateBudget budget = new SponsoredProductsCreateOrUpdateBudget();
        budget.setBudgetType(SponsoredProductsCreateOrUpdateBudgetType.DAILY);
        budget.setBudget(100.0);
        return budget;
    }

    public static SponsoredProductsUpdateSponsoredProductsCampaignsRequestContent buildUpdateSPCampaignsRequestContent(final String campaignId) {
        final SponsoredProductsUpdateSponsoredProductsCampaignsRequestContent requestContent
                = new SponsoredProductsUpdateSponsoredProductsCampaignsRequestContent();
        requestContent.addCampaignsItem(updateSponsoredProductsCreateCampaign(campaignId));

        return requestContent;
    }

    private static SponsoredProductsUpdateCampaign updateSponsoredProductsCreateCampaign(final String campaignId) {
        final SponsoredProductsUpdateCampaign updateCampaign = new SponsoredProductsUpdateCampaign();
        updateCampaign.setCampaignId(campaignId);
        updateCampaign.setState(SponsoredProductsCreateOrUpdateEntityState.ENABLED);
        return updateCampaign;
    }

    public static SponsoredProductsDeleteSponsoredProductsCampaignsRequestContent buildDeleteSPCampaignsRequestContent(final String campaignId) {
        final SponsoredProductsDeleteSponsoredProductsCampaignsRequestContent requestContent
                = new SponsoredProductsDeleteSponsoredProductsCampaignsRequestContent();

        final SponsoredProductsObjectIdFilter idFilter = new SponsoredProductsObjectIdFilter();
        idFilter.addIncludeItem(campaignId);

        requestContent.setCampaignIdFilter(idFilter);

        return requestContent;
    }

    public static SponsoredProductsListSponsoredProductsCampaignsRequestContent buildListSPCampaignsRequestContentIdFilter(final String campaignId) {
        final SponsoredProductsObjectIdFilter idFilter = new SponsoredProductsObjectIdFilter();
        idFilter.addIncludeItem(campaignId);

        final SponsoredProductsListSponsoredProductsCampaignsRequestContent requestContent
                = new SponsoredProductsListSponsoredProductsCampaignsRequestContent();
        requestContent.setCampaignIdFilter(idFilter);
        requestContent.setMaxResults(10);
        requestContent.setIncludeExtendedDataFields(true);

        return requestContent;
    }

    public static SponsoredProductsListSponsoredProductsCampaignsRequestContent buildListSPCampaignsRequestContent() {
        SponsoredProductsEntityStateFilter stateFilter = new SponsoredProductsEntityStateFilter();
        stateFilter.addIncludeItem(SponsoredProductsEntityState.ENABLED);
        stateFilter.addIncludeItem(SponsoredProductsEntityState.PAUSED);

        final SponsoredProductsListSponsoredProductsCampaignsRequestContent requestContent
                = new SponsoredProductsListSponsoredProductsCampaignsRequestContent();
        requestContent.setStateFilter(stateFilter);
        requestContent.setMaxResults(10);
        requestContent.setIncludeExtendedDataFields(true);

        return requestContent;
    }
}
