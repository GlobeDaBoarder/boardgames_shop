# steps to do before running the workflow

```bash
az group create --name rivnegray-AKS-resource-group --location northeurope

```
the output should be the following:
`>{
"id": "/subscriptions/6c7183a1-a19c-45f1-a44e-a5ad99c04627/resourceGroups/rivnegray-AKS-resource-group",
"location": "northeurope",
"managedBy": null,
"name": "rivnegray-AKS-resource-group",
"properties": {
"provisioningState": "Succeeded"
},
"tags": null,
"type": "Microsoft.Resources/resourceGroups"
}`
```bash
az aks create --resource-group rivnegray-AKS-resource-group --name rivnegray-AKS
--node-count 1 --enable-addons monitoring --generate-ssh-keys
```
the output should be the following:
`>{
"aadProfile": null,
"addonProfiles": {
"omsagent": {
"config": {
"logAnalyticsWorkspaceResourceID": "/subscriptions/6c7183a1-a19c-45f1-a44e-a5ad99c04627/resourceGroups/DefaultResourceGroup-NEU/providers/Microsoft.OperationalInsights/workspaces/DefaultWorkspace-6c7183a1-a19c-45f1-a44e-a5ad99c04627-NEU",
"useAADAuth": "True"
},
"enabled": true,
"identity": null
}
},
"agentPoolProfiles": [
{
"availabilityZones": null,
"count": 1,
"creationData": null,
"currentOrchestratorVersion": "1.25.6",
"enableAutoScaling": false,
"enableEncryptionAtHost": false,
"enableFips": false,
"enableNodePublicIp": false,
"enableUltraSsd": false,
"gpuInstanceProfile": null,
"hostGroupId": null,
"kubeletConfig": null,
"kubeletDiskType": "OS",
"linuxOsConfig": null,
"maxCount": null,
"maxPods": 110,
"minCount": null,
"mode": "System",
"name": "nodepool1",
"nodeImageVersion": "AKSUbuntu-2204gen2containerd-202306.01.0",
"nodeLabels": null,
"nodePublicIpPrefixId": null,
"nodeTaints": null,
"orchestratorVersion": "1.25.6",
"osDiskSizeGb": 128,
"osDiskType": "Managed",
"osSku": "Ubuntu",
"osType": "Linux",
"podSubnetId": null,
"powerState": {
"code": "Running"
},
"provisioningState": "Succeeded",
"proximityPlacementGroupId": null,
"scaleDownMode": null,
"scaleSetEvictionPolicy": null,
"scaleSetPriority": null,
"spotMaxPrice": null,
"tags": null,
"type": "VirtualMachineScaleSets",
"upgradeSettings": {
"maxSurge": null
},
"vmSize": "Standard_DS2_v2",
"vnetSubnetId": null,
"workloadRuntime": null
}
],
"apiServerAccessProfile": null,
"autoScalerProfile": null,
"autoUpgradeProfile": null,
"azureMonitorProfile": null,
"azurePortalFqdn": "rivnegray--rivnegray-aks-re-6c7183-d52nhzfs.portal.hcp.northeurope.azmk8s.io",
"currentKubernetesVersion": "1.25.6",
"disableLocalAccounts": false,
"diskEncryptionSetId": null,
"dnsPrefix": "rivnegray--rivnegray-AKS-re-6c7183",
"enablePodSecurityPolicy": null,
"enableRbac": true,
"extendedLocation": null,
"fqdn": "rivnegray--rivnegray-aks-re-6c7183-d52nhzfs.hcp.northeurope.azmk8s.io",
"fqdnSubdomain": null,
"httpProxyConfig": null,
"id": "/subscriptions/6c7183a1-a19c-45f1-a44e-a5ad99c04627/resourcegroups/rivnegray-AKS-resource-group/providers/Microsoft.ContainerService/managedClusters/rivnegray-AKS",
"identity": {
"principalId": "5538f758-05a7-4449-9994-30c7db38d0de",
"tenantId": "99f54475-1e0e-4911-a4f0-4eeaa4a62080",
"type": "SystemAssigned",
"userAssignedIdentities": null
},
"identityProfile": {
"kubeletidentity": {
"clientId": "296466e2-fa7e-4ff7-abd7-a2d5d9fe9f81",
"objectId": "08e3dec6-b964-4619-8735-93887203761e",
"resourceId": "/subscriptions/6c7183a1-a19c-45f1-a44e-a5ad99c04627/resourcegroups/MC_rivnegray-AKS-resource-group_rivnegray-AKS_northeurope/providers/Microsoft.ManagedIdentity/userAssignedIdentities/rivnegray-AKS-agentpool"
}
},
"kubernetesVersion": "1.25.6",
"linuxProfile": {
"adminUsername": "azureuser",
"ssh": {
"publicKeys": [
{
"keyData": "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABgQC0GH5FbmzdKsDGr8AwTb/dPfX8p1KP1RpTdikznhqpqr28mS0A9mw/b/8FlHF6whEpHsJynJgOGt0LODc9SQrOFui/hs34aVusNHlxE/dgh0fXQLUuRHfN7ZKurUND+KrPtcVATjnMchHarwnWu3ZZZG/9Cv3NV+25QcgHlxko5ocBlag0CICQXdoPq+wT6JCJJMYTlePswbXsX6kIPdq7z1VkMhYC7WbHE5+3OnbLlScNI2rUbpZB/F0aGpmKHH7FHRpUireZMp7kzkRQBL6/yrdq/KtACXsWipu+gShaxsWk+ooAeSxP4BV4iHvHSW17zzgMZvCGSL28B40oJREse/SdaiUqsP9ps5ojj+3EZIEKYtaumocfmBN3PVC6hdJv5wAi5fxX4ILSJcHSbQAXTz6wlaQIGAfBXUJegfLxC5bHLIIM9NUoiG+2ccLayBaXwf8Okya+vFmsHLgE07l4DSEXhP2vmvgTS6zcbE4ABDxZb+HhWovTkobx0moCHJ8= globe@globe\n"
}
]
}
},
"location": "northeurope",
"maxAgentPools": 100,
"name": "rivnegray-AKS",
"networkProfile": {
"dnsServiceIp": "10.0.0.10",
"ipFamilies": [
"IPv4"
],
"loadBalancerProfile": {
"allocatedOutboundPorts": null,
"effectiveOutboundIPs": [
{
"id": "/subscriptions/6c7183a1-a19c-45f1-a44e-a5ad99c04627/resourceGroups/MC_rivnegray-AKS-resource-group_rivnegray-AKS_northeurope/providers/Microsoft.Network/publicIPAddresses/32752e86-7306-466a-9cae-8e10746c33d8",
"resourceGroup": "MC_rivnegray-AKS-resource-group_rivnegray-AKS_northeurope"
}
],
"enableMultipleStandardLoadBalancers": null,
"idleTimeoutInMinutes": null,
"managedOutboundIPs": {
"count": 1,
"countIpv6": null
},
"outboundIPs": null,
"outboundIpPrefixes": null
},
"loadBalancerSku": "Standard",
"natGatewayProfile": null,
"networkDataplane": null,
"networkMode": null,
"networkPlugin": "kubenet",
"networkPluginMode": null,
"networkPolicy": null,
"outboundType": "loadBalancer",
"podCidr": "10.244.0.0/16",
"podCidrs": [
"10.244.0.0/16"
],
"serviceCidr": "10.0.0.0/16",
"serviceCidrs": [
"10.0.0.0/16"
]
},
"nodeResourceGroup": "MC_rivnegray-AKS-resource-group_rivnegray-AKS_northeurope",
"oidcIssuerProfile": {
"enabled": false,
"issuerUrl": null
},
"podIdentityProfile": null,
"powerState": {
"code": "Running"
},
"privateFqdn": null,
"privateLinkResources": null,
"provisioningState": "Succeeded",
"publicNetworkAccess": null,
"resourceGroup": "rivnegray-AKS-resource-group",
"securityProfile": {
"azureKeyVaultKms": null,
"defender": null,
"imageCleaner": null,
"workloadIdentity": null
},
"servicePrincipalProfile": {
"clientId": "msi",
"secret": null
},
"sku": {
"name": "Base",
"tier": "Free"
},
"storageProfile": {
"blobCsiDriver": null,
"diskCsiDriver": {
"enabled": true
},
"fileCsiDriver": {
"enabled": true
},
"snapshotController": {
"enabled": true
}
},
"supportPlan": "KubernetesOfficial",
"systemData": null,
"tags": null,
"type": "Microsoft.ContainerService/ManagedClusters",
"windowsProfile": null,
"workloadAutoScalerProfile": {
"keda": null
}
}`
