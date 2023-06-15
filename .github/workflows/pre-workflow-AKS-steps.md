# steps to do before running the workflow

### 1. Create a resource group
First up, create a resource group in which your AKS cluster will live. 
If you already have some resource group, you can skip this step.

```bash
az group create --name rivnegray-AKS-resource-group --location northeurope
```
_sample output:_
```json
{
  "id": "/subscriptions/6c7183a1-a19c-45f1-a44e-a5ad99c04627/resourceGroups/rivnegray-AKS-resource-group",
  "location": "northeurope",
  "managedBy": null,
  "name": "rivnegray-AKS-resource-group",
  "properties": {
    "provisioningState": "Succeeded"
  },
  "tags": null,
  "type": "Microsoft.Resources/resourceGroups"
}
```

### 2. Create the Azure Kubernetes Service
```bash
az aks create --resource-group rivnegray-AKS-resource-group --name rivnegray-AKS
--node-count 1 --enable-addons monitoring --generate-ssh-keys
```
_sample output:_
```json
{
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
.........
```

### 3. Install kubectl
```bash
sudo az aks install-cli
```
_sample output:_
```text
Merged "rivnegray-AKS" as current context in /home/globe/.kube/config
```

### 4. Create namespace
```bash
kubectl create namespace rivnegray-namespace
```
_sample output:_
```text
namespace/rivnegray-namespace created
```

### 5. Generate kubernetes manifest files (optional)
I used kompose cli tool to generate kubernetes manifest files from docker-compose.app.yml file.

You don't need to regenerate them if you don't want to change anything in the docker-compose.app.yml file.

Either way, here's how i did it:

Downloading kompose: 
```bash
curl -L https://github.com/kubernetes/kompose/releases/download/v1.28.0/kompose-linux-amd64 -o kompose
chmod +x kompose
sudo mv ./kompose /usr/local/bin/kompose
```

Generating kubernetes manifest files:
```bash
cd  ~/Projects/boardgames_shop
kompose convert -f docker-compose.app.yml -o ./k8s/
```

