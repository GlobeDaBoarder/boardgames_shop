## Setup on a VM with Ubuntu to deploy the application
### Install Docker
```bash
sudo apt-get update
sudo apt-get install apt-transport-https ca-certificates curl software-properties-common
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"
sudo apt-get update
sudo apt-get install docker-ce
```
### Install Docker Compose
```bash
 sudo curl -L "https://github.com/docker/compose/releases/download/v2.20.0/docker-compose-linux-x86_64" -o /usr/local/bin/doc
ker-compose
sudo chmod +x /usr/local/bin/docker-compose
```

### Add private key that you use to connect to the VM to GithubSecrets 
1. Go to your repository on Github
2. Click on Settings
3. Click on Secrets
4. Click on New repository secret
5. Name the secret as VM_PRIVATE_KEY
6. Paste the private key in the value field
7. Click on Add secret

### Add dockerhub credentials to GithubSecrets
In you dockerhub account:
1. Go to account settings
2. Click on Security
3. Click on New Access Token
4. Copy it and save it to github secrets just like in the previous step 


