package org.example.objectUtils;

import org.openapitools.client.model.SponsoredProductsCreateExpressionType;
import org.openapitools.client.model.SponsoredProductsCreateOrUpdateEntityState;
import org.openapitools.client.model.SponsoredProductsCreateSponsoredProductsTargetingClausesRequestContent;
import org.openapitools.client.model.SponsoredProductsCreateTargetingClause;
import org.openapitools.client.model.SponsoredProductsCreateTargetingExpressionPredicate;
import org.openapitools.client.model.SponsoredProductsCreateTargetingExpressionPredicateType;
import org.openapitools.client.model.SponsoredProductsDeleteSponsoredProductsTargetingClausesRequestContent;
import org.openapitools.client.model.SponsoredProductsEntityState;
import org.openapitools.client.model.SponsoredProductsEntityStateFilter;
import org.openapitools.client.model.SponsoredProductsListSponsoredProductsTargetingClausesRequestContent;
import org.openapitools.client.model.SponsoredProductsObjectIdFilter;
import org.openapitools.client.model.SponsoredProductsUpdateSponsoredProductsTargetingClausesRequestContent;
import org.openapitools.client.model.SponsoredProductsUpdateTargetingClause;

import java.util.List;

public class SPTargetUtils {
    public static SponsoredProductsCreateSponsoredProductsTargetingClausesRequestContent buildCreateSPTargetsRequestContent(
            final String campaignId,
            final String adGroupId
    ) {
        final SponsoredProductsCreateSponsoredProductsTargetingClausesRequestContent requestContent
                = new SponsoredProductsCreateSponsoredProductsTargetingClausesRequestContent();
        requestContent.addTargetingClausesItem(createSponsoredProductsCreateTarget(campaignId, adGroupId));

        return requestContent;
    }

    private static SponsoredProductsCreateTargetingClause createSponsoredProductsCreateTarget(
            final String campaignId,
            final String adGroupId) {
        final SponsoredProductsCreateTargetingClause createTarget = new SponsoredProductsCreateTargetingClause();
        createTarget.setState(SponsoredProductsCreateOrUpdateEntityState.PAUSED);
        createTarget.setBid(null);
        createTarget.setCampaignId(campaignId);
        createTarget.setAdGroupId(adGroupId);
        createTarget.setExpressionType(SponsoredProductsCreateExpressionType.MANUAL);
        createTarget.setExpression(List.of(createTargetingExpressionPredicate()));
        return createTarget;
    }

    private static SponsoredProductsCreateTargetingExpressionPredicate createTargetingExpressionPredicate() {
        final SponsoredProductsCreateTargetingExpressionPredicate predicate = new SponsoredProductsCreateTargetingExpressionPredicate();
        predicate.setType(SponsoredProductsCreateTargetingExpressionPredicateType.KEYWORD_GROUP_SAME_AS);
        predicate.setValue("category");
        return predicate;
    }

    public static SponsoredProductsUpdateSponsoredProductsTargetingClausesRequestContent buildUpdateSPTargetsRequestContent(
            final String targetId) {
        final SponsoredProductsUpdateSponsoredProductsTargetingClausesRequestContent requestContent
                = new SponsoredProductsUpdateSponsoredProductsTargetingClausesRequestContent();
        requestContent.addTargetingClausesItem(buildSponsoredProductsUpdateTarget(targetId));

        return requestContent;
    }

    private static SponsoredProductsUpdateTargetingClause buildSponsoredProductsUpdateTarget(final String targetId) {
        final SponsoredProductsUpdateTargetingClause createTarget = new SponsoredProductsUpdateTargetingClause();
        createTarget.setState(SponsoredProductsCreateOrUpdateEntityState.ENABLED);
        createTarget.setTargetId(targetId);
        return createTarget;
    }

    public static SponsoredProductsDeleteSponsoredProductsTargetingClausesRequestContent buildDeleteSPTargetsRequestContent(
            final String targetId) {
        final SponsoredProductsObjectIdFilter idFilter = new SponsoredProductsObjectIdFilter();
        idFilter.addIncludeItem(targetId);

        final SponsoredProductsDeleteSponsoredProductsTargetingClausesRequestContent requestContent
                = new SponsoredProductsDeleteSponsoredProductsTargetingClausesRequestContent();
        requestContent.setTargetIdFilter(idFilter);

        return requestContent;
    }

    public static SponsoredProductsListSponsoredProductsTargetingClausesRequestContent buildListSPTargetsRequestContentIdFilter(
            final String targetId
    ) {
        SponsoredProductsObjectIdFilter idFilter = new SponsoredProductsObjectIdFilter();
        idFilter.addIncludeItem(targetId);

        final SponsoredProductsListSponsoredProductsTargetingClausesRequestContent requestContent
                = new SponsoredProductsListSponsoredProductsTargetingClausesRequestContent();
        requestContent.setTargetIdFilter(idFilter);
        requestContent.setMaxResults(10);
        requestContent.setIncludeExtendedDataFields(true);

        return requestContent;
    }

    public static SponsoredProductsListSponsoredProductsTargetingClausesRequestContent buildListSPTargetsRequestContentStateFilter() {
        SponsoredProductsEntityStateFilter stateFilter = new SponsoredProductsEntityStateFilter();
        stateFilter.addIncludeItem(SponsoredProductsEntityState.ENABLED);
        stateFilter.addIncludeItem(SponsoredProductsEntityState.PAUSED);

        final SponsoredProductsListSponsoredProductsTargetingClausesRequestContent requestContent
                = new SponsoredProductsListSponsoredProductsTargetingClausesRequestContent();
        requestContent.setStateFilter(stateFilter);
        requestContent.setMaxResults(10);
        requestContent.setIncludeExtendedDataFields(true);

        return requestContent;
    }
}
