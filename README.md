# serverless-api serverless API
The serverless-api project, created with [`aws-serverless-java-container`](https://github.com/aws/serverless-java-container).

The project folder also includes a `getewayLambda.yml` file. You can use this [SAM](https://github.com/awslabs/serverless-application-model) file to deploy the project to AWS Lambda and Amazon API Gateway or test in local with the [SAM CLI](https://github.com/awslabs/aws-sam-cli). 

## Pre-requisites
* [AWS CLI](https://aws.amazon.com/cli/)
* [SAM CLI](https://github.com/awslabs/aws-sam-cli)
* [Maven](https://maven.apache.org/)

## Building the project
You can use the SAM CLI to quickly build the project
```bash
$ mvn archetype:generate -DartifactId=serverless-api -DarchetypeGroupId=com.amazonaws.serverless.archetypes -DarchetypeArtifactId=aws-serverless-jersey-archetype -DarchetypeVersion=2.1.2 -DgroupId=kz.talgat -Dversion=1.0-SNAPSHOT -Dinteractive=false
$ cd serverless-api
$ sam build
Building resource 'ServerlessApiFunction'
Running JavaGradleWorkflow:GradleBuild
Running JavaGradleWorkflow:CopyArtifacts

Build Succeeded

Built Artifacts  : .aws-sam/build
Built Template   : .aws-sam/build/getewayLambda.yaml

Commands you can use next
=========================
[*] Invoke Function: sam local invoke
[*] Deploy: sam deploy --guided
```

## Testing locally with the SAM CLI

From the project root folder - where the `getewayLambda.yml` file is located - start the API with the SAM CLI.

Using a new shell, you can send a test ping request to your API:

## Deploying to AWS
To deploy the application in your AWS account, you can use the SAM CLI's guided deployment process and follow the instructions on the screen

I used GitHub actions file .github/workflows/main.yml 
```
$ sam deploy --guided
```

deployed version is <br/>
<a href="https://cravlafgui.execute-api.us-east-1.amazonaws.com/prod/api/posts">https://cravlafgui.execute-api.us-east-1.amazonaws.com/prod/api/posts</a> <br/>
<a href="https://cravlafgui.execute-api.us-east-1.amazonaws.com/prod/api/users">https://cravlafgui.execute-api.us-east-1.amazonaws.com/prod/api/users</a> <br/>
<a href="https://cravlafgui.execute-api.us-east-1.amazonaws.com/prod/api/comments">https://cravlafgui.execute-api.us-east-1.amazonaws.com/prod/api/comments</a> <br/>
