package org.example.objectApi;

import org.example.objectApi.TargetsApi.CreateTargetApi;
import org.example.objectApi.TargetsApi.DeleteTargetApi;
import org.example.objectApi.TargetsApi.ListTargetApi;
import org.example.objectApi.TargetsApi.UpdateTargetApi;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.TargetingClausesApi;
import org.openapitools.client.model.SponsoredProductsCreateSponsoredProductsTargetingClausesRequestContent;
import org.openapitools.client.model.SponsoredProductsDeleteSponsoredProductsTargetingClausesRequestContent;
import org.openapitools.client.model.SponsoredProductsDeleteSponsoredProductsTargetingClausesResponseContent;
import org.openapitools.client.model.SponsoredProductsListSponsoredProductsTargetingClausesRequestContent;
import org.openapitools.client.model.SponsoredProductsListSponsoredProductsTargetingClausesResponseContent;
import org.openapitools.client.model.SponsoredProductsUpdateSponsoredProductsTargetingClausesRequestContent;
import org.openapitools.client.model.SponsoredProductsUpdateSponsoredProductsTargetingClausesResponseContent;

import java.io.IOException;
import java.util.Map;

public class TargetsApiService {
    private final TargetingClausesApi targetsApi;
    private final Map<String, String> authMap;

    public TargetsApiService(TargetingClausesApi targetsApi, Map<String, String> authMap) {
        this.targetsApi = targetsApi;
        this.authMap = authMap;
    }

    public String createTarget(final SponsoredProductsCreateSponsoredProductsTargetingClausesRequestContent createRequestContent) throws IOException, InterruptedException, ApiException {
        return CreateTargetApi.createTarget(targetsApi, createRequestContent, authMap);
    }

    public SponsoredProductsUpdateSponsoredProductsTargetingClausesResponseContent updateTarget(final SponsoredProductsUpdateSponsoredProductsTargetingClausesRequestContent updateRequestContent) throws IOException, InterruptedException, ApiException {
        return UpdateTargetApi.updateTarget(targetsApi, updateRequestContent, authMap);
    }

    public SponsoredProductsDeleteSponsoredProductsTargetingClausesResponseContent deleteTarget(final SponsoredProductsDeleteSponsoredProductsTargetingClausesRequestContent deleteRequestContent) throws IOException, InterruptedException, ApiException {
        return DeleteTargetApi.deleteTarget(targetsApi, deleteRequestContent, authMap);
    }

    public SponsoredProductsListSponsoredProductsTargetingClausesResponseContent listTarget(final SponsoredProductsListSponsoredProductsTargetingClausesRequestContent listRequestContent) throws IOException, InterruptedException, ApiException {
        return ListTargetApi.listTarget(targetsApi, listRequestContent, authMap);
    }
}
