package org.example.objectApi;

import org.example.objectApi.CampaignsApi.CreateCampaignApi;
import org.example.objectApi.CampaignsApi.DeleteCampaignApi;
import org.example.objectApi.CampaignsApi.ListCampaignApi;
import org.example.objectApi.CampaignsApi.UpdateCampaignApi;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.CampaignsApi;
import org.openapitools.client.model.SponsoredProductsCreateSponsoredProductsCampaignsRequestContent;
import org.openapitools.client.model.SponsoredProductsDeleteSponsoredProductsCampaignsRequestContent;
import org.openapitools.client.model.SponsoredProductsDeleteSponsoredProductsCampaignsResponseContent;
import org.openapitools.client.model.SponsoredProductsListSponsoredProductsCampaignsRequestContent;
import org.openapitools.client.model.SponsoredProductsListSponsoredProductsCampaignsResponseContent;
import org.openapitools.client.model.SponsoredProductsUpdateSponsoredProductsCampaignsRequestContent;
import org.openapitools.client.model.SponsoredProductsUpdateSponsoredProductsCampaignsResponseContent;

import java.io.IOException;
import java.util.Map;

public class CampaignsApiService {
    private final CampaignsApi campaignsApi;
    private final Map<String, String> authMap;

    public CampaignsApiService(CampaignsApi campaignsApi, Map<String, String> authMap) {
        this.campaignsApi = campaignsApi;
        this.authMap = authMap;
    }

    public String createCampaign(final SponsoredProductsCreateSponsoredProductsCampaignsRequestContent createRequestContent) throws IOException, InterruptedException, ApiException {
        return CreateCampaignApi.createCampaign(campaignsApi, createRequestContent, authMap);
    }

    public SponsoredProductsUpdateSponsoredProductsCampaignsResponseContent updateCampaign(final SponsoredProductsUpdateSponsoredProductsCampaignsRequestContent updateRequestContent) throws IOException, InterruptedException, ApiException {
        return UpdateCampaignApi.updateCampaign(campaignsApi, updateRequestContent, authMap);
    }

    public SponsoredProductsDeleteSponsoredProductsCampaignsResponseContent deleteCampaign(final SponsoredProductsDeleteSponsoredProductsCampaignsRequestContent deleteRequestContent) throws IOException, InterruptedException, ApiException {
        return DeleteCampaignApi.deleteCampaign(campaignsApi, deleteRequestContent, authMap);
    }

    public SponsoredProductsListSponsoredProductsCampaignsResponseContent listCampaign(final SponsoredProductsListSponsoredProductsCampaignsRequestContent listRequestContent) throws IOException, InterruptedException, ApiException {
        return ListCampaignApi.listCampaign(campaignsApi, listRequestContent, authMap);
    }
}
