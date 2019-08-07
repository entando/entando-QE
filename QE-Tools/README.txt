Description: de-components-generator.js is a tool designed to help in the creation of Entando Digital Exchange ready components importing existing components from a running Entando instance.

It basically imports a component from a running Entando instance and clones it locally creating all the necessary folders and files ready to be uploaded on a Digital Exchange repositority.

Parameters are set in a .env file.
APP_URL represents the URL of the Entando application from which you want to clone a component.
i.e.  #APP_URL=http://localhost:8090/entando-sample


DEMO_REPO_NAME represent the name of the Digital Exchange repository
i.e.
DEMO_REPO_NAME=cloned_components

DEMO_REPO_URL contains the git repository on which are stored the cloned components.

i.e.
DEMO_REPO_URL=https://github.com/de_repo/repo.git



LOCAL_REPO_NAME and LOCAL_REPO_URL represent the local folder name and path on which the cloned components are stored.

i.e.  
LOCAL_REPO_NAME=cloned_components
LOCAL_REPO_URL=cloned_components

Requirements.
nodejs and npm installed

Installation.
Install the node modules with npm install.


How to run the tool.
From the folder that contains the script run ./de-components-generator.js import-component, you will be presented with all the supported  components types.
At the moment of writing groups, roles, content models, content types and page models are supported.
 
The prompt looks like this:

Select component type:
1. contentModel
2. contentType
3. group
4. pageModel
5. role
Component type: 

just select the one you need, inserting the corresponding number.
You will be presented with a list of all the components of the selected type,fetched from the Entando instance from which you want to clone the components; from the list, select the one you want to clone inserting the corresponding number.

Getting the list of available groups...
1. Group1 group1
2. Group2 group2
Select a group from the list:

You will be prompted for the component version and for the component description insert them, all the required folders and files will be created automatically.

Issues installing cloned components.
You may not be able to install some components even though their packaging is correct from a DE point of view, and this could happen, at least in the following cases:

- installing an imported role if in its definition there are permissions set to true, and at least one of these permissions do not exist on the Entando instance on which you are installing that role;

- installing an imported content type if in its definition there are attributes not present in the instance on which you are going to install that component;

-installing a content model if in its definition is referenced a content type which is not already installed on the Entando instance on which you are going to install the component;


 


 
