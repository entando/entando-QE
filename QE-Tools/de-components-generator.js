#!/usr/bin/env node

'use strict';

require('dotenv').config();

// Workaround for console.table issue on some Node versions
// See https://github.com/bahmutov/console.table/issues/15
delete console.table;
require('console.table');

const program = require('commander');
const request = require('request-promise-native');
const chalk = require("chalk");
const readline = require('readline-sync');
const fs = require('fs-extra');
const nodeGit = require("nodegit");
const fsWalkerSync = require('klaw-sync');
const path = require("path");
const imagemagick = require('imagemagick');



    var imgGen = require('js-image-generator');

const DEMO_COMPONENT_JSON_FOLDER = 'cloned_components/metadata/components/';
const DEMO_COMPONENT_IMG_FOLDER = 'cloned_components/metadata/icons/';
const TEMPLATE_COMPONENT_FOLDER = 'templates';

// Display the help if no arguments is given
if (process.argv.length === 2) {
    process.argv.push('-h');
}

program
    .version('1.0.0')
    .description("DE command line tool");


program.command('list-available-groups')
        .description('Lists all available groups from an Entando instance.')
        .action(listAvailableGroups);

        program.command('make-local-repo')
        .description('make local repo')
        .action(createLocalRepositoryIfNotExists);


        program.command('import-component')
        .description('import a component')
        .action(importComponent);

        program.command('create-icon')
        .description('create an icon')
        .action(createIcon);

    
program.on('command:*', function () {
  console.error('Invalid command: %s\nSee --help for a list of available commands.', program.args.join(' '));
  process.exit(1);
});

program.parse(process.argv);

var accessToken;

function getToken() {
    return new Promise((resolve, reject) => {
        if(accessToken) {
            // Reusing same token
            resolve(accessToken);
            return;
        }
        request({
            method: 'POST',
            uri: process.env.APP_URL + '/api/oauth/token',
            form: {
                grant_type: 'password',
                username: process.env.ADMIN_USER,
                password: process.env.ADMIN_PASSWORD,
                client_id: process.env.CLIENT_ID,
                client_secret: process.env.CLIENT_SECRET
            },
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json; charset=utf-8",
                "Connection": 'keep-alive',
                "Authorization": "Basic " + Buffer.from(process.env.CLIENT_ID + ":" + process.env.CLIENT_SECRET).toString('base64')
            }
        })
        .then(res => {
            accessToken = JSON.parse(res)["access_token"];
            resolve(accessToken);
        })
        .catch(res => {
            handleError(res, "Unable to retrieve OAuth2 token");
            reject(res);
        });
    });
}

function printSuccess(msg) {
    console.log(chalk.green.bold(msg));
}
function printError(msg) {
    console.log(chalk.red.bold(msg));
}
function printWarning(msg) {
    console.log(chalk.yellow(msg));
}
function jsonPretty(obj) {
    return JSON.stringify(obj, null, 4);
}

function getHeaders() {
    return {
        "Accept": "application/json",
        "Content-Type": "application/json; charset=utf-8",
        "Connection": 'keep-alive',
        "Authorization": "Bearer " + accessToken
    };
}

function handleError(res, msg) {
    if (msg) {
        printError("ERROR: " + msg);
    } else {
        printError("ERROR");
    }
    if (res.statusCode) {
        printError("Status code: " + res.statusCode);
        try {
            let errors;
            if (typeof res.error === 'string') {
                errors = JSON.parse(res.error).errors;
            } else {
                errors = res.error.errors;
            }
            for (let err of errors) {
                printError(err.message);
            }
        } catch (err) {
            printWarning("WARN: server response is not in JSON format:");
            printError(res.error);
        }
    } else {
        printError(res.error);
    }
}


//this function let you select which component type to import.
function importComponent() {
    
    var componentType = selectComponentType();
    
    switch(componentType) {
        case "contentModel" :
                listAvailableContentModels();
          break;
        case "contentType":
                listAvailableContentTypes();
          break;
          case "group":
          listAvailableGroups();
          break;
          case "pageModel":
                listAvailablePageModels();
          break;
          case "role":
                listAvailableRoles();
          break;
          case "widget":
                listAvailableWidgets();
          break;
      }
      
}



////FUNCTIONS FOR SELECTION OF COMPONENTS FROM A LIST OF AVAILABLE ONES - 

function listAvailableGroups() {
    console.log("Getting the list of available groups...");
    retrieveGroups()
        .then(groups => {
            let availableGroups = groups;
            for (let i = 0; i < availableGroups.length; i++) {
                console.log(`${i + 1}. ${availableGroups[i].name} ${availableGroups[i].code}`);
            }

            let group;
        let groupSelected = false;
        while (!groupSelected) {
            group = readline.question("Select a group from the list: ");
            // Select a component from the list
            let groupIndex = parseInt(group);
            if (isNaN(groupIndex)) {
                // check if valid type has been inserted
                groupSelected = availableGroups.includes(group);
            } else {
                // retrieve by index
                groupIndex--;
                if (groupIndex >= 0 && groupIndex < availableGroups.length) {
                    group = availableGroups[groupIndex];
                    groupSelected = true;
                }
            }
            if (!groupSelected) {
                printError(`Invalid input: ${group}`);
            }
        }
        console.log("You have selected the group: " + group.name + " - " + group.code);
          retrieveGroup(group.code);  
        }

        
        ).catch(handleError);
}



function listAvailableContentModels(){

    console.log("Getting the list of available content models...");
    retrieveContentModels()
        .then(contentModels => {
            let availableContentModels = contentModels;
            for (let i = 0; i < availableContentModels.length; i++) {
                console.log(`${i + 1}. ${availableContentModels[i].id} ${availableContentModels[i].descr}`);
            }

            let contentModel;
        let contentModelSelected = false;
        while (!contentModelSelected) {
            contentModel = readline.question("Select a content model from the list: ");
            // Select a component from the list
            let contentModelIndex = parseInt(contentModel);
            if (isNaN(contentModelIndex)) {
                // check if valid type has been inserted
                contentModelSelected = availableContentModels.includes(contentModel);
            } else {
                // retrieve by index
                contentModelIndex--;
                if (contentModelIndex >= 0 && contentModelIndex < availableContentModels.length) {
                    contentModel = availableContentModels[contentModelIndex];
                    contentModelSelected = true;
                }
            }
            if (!contentModelSelected) {
                printError(`Invalid input: ${contentModel}`);
            }
        }
        console.log("You have selected the content model: " + contentModel.descr + " - " + contentModel.id);
          retrieveContentModel(contentModel.id);  
        }

        
        ).catch(handleError);
    
}


function listAvailableContentTypes(){

    console.log("Getting the list of available content types...");
    retrieveContentTypes()
        .then(contentTypes => {
            let availableContentTypes = contentTypes;
            for (let i = 0; i < availableContentTypes.length; i++) {
                console.log(`${i + 1}. ${availableContentTypes[i].code} ${availableContentTypes[i].name}`);
            }

            let contentType;
        let contentTypeSelected = false;
        while (!contentTypeSelected) {
            contentType = readline.question("Select a content model from the list: ");
            // Select a component from the list
            let contentTypeIndex = parseInt(contentType);
            if (isNaN(contentTypeIndex)) {
                // check if valid type has been inserted
                contentTypeSelected = availableContentTypes.includes(contentType);
            } else {
                // retrieve by index
                contentTypeIndex--;
                if (contentTypeIndex >= 0 && contentTypeIndex < availableContentTypes.length) {
                    contentType = availableContentTypes[contentTypeIndex];
                    contentTypeSelected = true;
                }
            }
            if (!contentTypeSelected) {
                printError(`Invalid input: ${contentType}`);
            }
        }
        console.log("You have selected the content type: " + contentType.code + " - " + contentType.name);
          retrieveContentType(contentType.code);  
        }

        
        ).catch(handleError);
    
}


function listAvailableRoles(){

    console.log("Getting the list of available roles...");
    retrieveRoles()
        .then(Roles => {
            let availableRoles = Roles;
            for (let i = 0; i < availableRoles.length; i++) {
                console.log(`${i + 1}. ${availableRoles[i].code} ${availableRoles[i].name}`);
            }

            let Role;
        let roleSelected = false;
        while (!roleSelected) {
            Role = readline.question("Select a role from the list: ");
            // Select a component from the list
            let roleIndex = parseInt(Role);
            if (isNaN(roleIndex)) {
                // check if valid type has been inserted
                roleSelected = availableRoles.includes(Role);
            } else {
                // retrieve by index
                roleIndex--;
                if (roleIndex >= 0 && roleIndex < availableRoles.length) {
                    Role = availableRoles[roleIndex];
                    roleSelected = true;
                }
            }
            if (!roleSelected) {
                printError(`Invalid input: ${Role}`);
            }
        }
        console.log("You have selected the role: " + Role.code + " - " + Role.name);
          retrieveRole(Role.code);  
        }

        
        ).catch(handleError);
    
}



function listAvailablePageModels(){

    console.log("Getting the list of available page models...");
    retrievePageModels()
        .then(pageModels => {
            let availablePageModels = pageModels;
            for (let i = 0; i < availablePageModels.length; i++) {
                console.log(`${i + 1}. ${availablePageModels[i].code} ${availablePageModels[i].descr}`);
            }

            let pageModel;
        let pageModelSelected = false;
        while (!pageModelSelected) {
            pageModel = readline.question("Select a page model from the list: ");
            // Select a component from the list
            let pageModelIndex = parseInt(pageModel);
            if (isNaN(pageModelIndex)) {
                // check if valid type has been inserted
                pageModelSelected = availablePageModels.includes(pageModel);
            } else {
                // retrieve by index
                pageModelIndex--;
                if (pageModelIndex >= 0 && pageModelIndex < availablePageModels.length) {
                    pageModel = availablePageModels[pageModelIndex];
                    pageModelSelected = true;
                }
            }
            if (!pageModelSelected) {
                printError(`Invalid input: ${pageModel}`);
            }
        }
        console.log("You have selected the page model: " + pageModel.code + " - " + pageModel.descr);
          retrievePageModel(pageModel.code);  
        }

        
        ).catch(handleError);
    
}



function listAvailableWidgets(){

    console.log("Getting the list of available widgets...");
    retrieveWidgets()
        .then(Widgets => {
            let availableWidgets = Widgets;
            for (let i = 0; i < availableWidgets.length; i++) {
                console.log(`${i + 1}. ${availableWidgets[i].code}`);
            }

            let widget;
        let widgetSelected = false;
        while (!widgetSelected) {
            widget = readline.question("Select a widget from the list: ");
            // Select a component from the list
            let widgetIndex = parseInt(widget);
            if (isNaN(widgetIndex)) {
                // check if valid type has been inserted
                widgetSelected = availableWidgets.includes(widget);
            } else {
                // retrieve by index
                widgetIndex--;
                if (widgetIndex >= 0 && widgetIndex < availableWidgets.length) {
                    widget = availableWidgets[widgetIndex];
                    widgetSelected = true;
                }
            }
            if (!widgetSelected) {
                printError(`Invalid input: ${widget}`);
            }
        }
        console.log("You have selected the widget: " + widget.code);
          retrieveWidget(widget.code);  
        }

        
        ).catch(handleError);
    
}










////------------------------ START LIST OF COMPONENTS API REQUESTS----------------


//this function gets all the groups from the Entando instance via API request
function retrieveGroups() {
    return new Promise((resolve, reject) => {
        getToken()
            .then(token => {
                request({
                    method: 'GET',
                    uri: process.env.APP_URL + '/api/groups',
                    headers: getHeaders()
                }).then(res => {
                    resolve(JSON.parse(res).payload);
                }).catch(res => {
                    printError("Unable to retrieve groups");
                    reject(res);
                });
            });
    });
}


//this function gets all the content models from the Entando Instance via API request
function retrieveContentModels() {
    return new Promise((resolve, reject) => {
        getToken()
            .then(token => {
                request({
                    method: 'GET',
                    uri: process.env.APP_URL + '/api/plugins/cms/contentmodels',
                    headers: getHeaders()
                }).then(res => {
                    resolve(JSON.parse(res).payload);
                }).catch(res => {
                    printError("Unable to retrieve content models");
                    reject(res);
                });
            });
    });
}

//this function gets all the content types from the Entando instance via API requests
function retrieveContentTypes() {
    return new Promise((resolve, reject) => {
        getToken()
            .then(token => {
                request({
                    method: 'GET',
                    uri: process.env.APP_URL + '/api/plugins/cms/contentTypes',
                    headers: getHeaders()
                }).then(res => {
                    resolve(JSON.parse(res).payload);
                }).catch(res => {
                    printError("Unable to retrieve content types");
                    reject(res);
                });
            });
    });
}


function retrieveRoles() {
    return new Promise((resolve, reject) => {
        getToken()
            .then(token => {
                request({
                    method: 'GET',
                    uri: process.env.APP_URL + '/api/roles',
                    headers: getHeaders()
                }).then(res => {
                    resolve(JSON.parse(res).payload);
                }).catch(res => {
                    printError("Unable to retrieve roles");
                    reject(res);
                });
            });
    });
}


function retrievePageModels() {
    return new Promise((resolve, reject) => {
        getToken()
            .then(token => {
                request({
                    method: 'GET',
                    uri: process.env.APP_URL + '/api/pageModels',
                    headers: getHeaders()
                }).then(res => {
                    resolve(JSON.parse(res).payload);
                }).catch(res => {
                    printError("Unable to retrieve page models");
                    reject(res);
                });
            });
    });
}


function retrieveWidgets() {
    return new Promise((resolve, reject) => {
        getToken()
            .then(token => {
                request({
                    method: 'GET',
                    uri: process.env.APP_URL + '/api/widgets',
                    headers: getHeaders()
                }).then(res => {
                    resolve(JSON.parse(res).payload);
                }).catch(res => {
                    printError("Unable to retrieve widgets");
                    reject(res);
                });
            });
    });
}







/////-------------------------------- END - LIST OF COMPONENTS API REQUESTS--------------------------------




////--------------------------------------------------------------------SINGLE COMPONENT API REQUEST --------------------
//these functions get a single component by code/id from issuing an API request 

function retrieveGroup(groupCode) {
    return new Promise((resolve, reject) => {
        getToken()
            .then(token => {
                request({
                    method: 'GET',
                    uri: process.env.APP_URL + '/api/groups/' + groupCode,
                    headers: getHeaders()
                }).then(res => {
                    resolve(JSON.parse(res).payload);
                    console.log("Response from the API request for the group: " + JSON.parse(res).payload.code);
                    //return JSON.parse(res).payload;
                    createComponent("group", JSON.parse(res).payload.code, JSON.parse(res).payload.name);
                }).catch(res => {
                    printError("Unable to retrieve group");
                    reject(res);
                });
            });
            
    });

    
}



function retrieveContentModel(contentModelcode) {
    return new Promise((resolve, reject) => {
        getToken()
            .then(token => {
                request({
                    method: 'GET',
                    uri: process.env.APP_URL + '/api/plugins/cms/contentmodels/' + contentModelcode,
                    headers: getHeaders()
                }).then(res => {
                    resolve(JSON.parse(res).payload);
                    console.log("Response from the API request for the content model: " + JSON.parse(res).payload.id);
                    let contentModelId = JSON.parse(res).payload.id;
                    let contentModelName = JSON.parse(res).payload.descr;
                    createComponent("contentModel", contentModelId, contentModelName, JSON.parse(res).payload);

                    console.log("content shape: " + JSON.parse(res).payload.contentShape);

                }).catch(res => {
                    printError("Unable to retrieve content model");
                    reject(res);
                });
            });
            
    });

    
}



function retrieveContentType(contentTypecode) {
    return new Promise((resolve, reject) => {
        getToken()
            .then(token => {
                request({
                    method: 'GET',
                    uri: process.env.APP_URL + '/api/plugins/cms/contentTypes/' + contentTypecode,
                    headers: getHeaders()
                }).then(res => {
                    resolve(JSON.parse(res).payload);
                    console.log("Response from the API request for the content type: " + JSON.parse(res).payload.code);
                    createComponent("contentType", JSON.parse(res).payload.code, JSON.parse(res).payload.name, JSON.parse(res).payload);
                }).catch(res => {
                    printError("Unable to retrieve content type");
                    reject(res);
                });
            });
            
    });

    
}



function retrieveRole(roleCode) {
    return new Promise((resolve, reject) => {
        getToken()
            .then(token => {
                request({
                    method: 'GET',
                    uri: process.env.APP_URL + '/api/roles/' + roleCode,
                    headers: getHeaders()
                }).then(res => {
                    resolve(JSON.parse(res).payload);
                    console.log("Response from the API request for the role: " + JSON.parse(res).payload.code);
                    createComponent("role", JSON.parse(res).payload.code, JSON.parse(res).payload.name, JSON.parse(res).payload);
                }).catch(res => {
                    printError("Unable to retrieve role");
                    reject(res);
                });
            });
            
    });

    
}


function retrievePageModel(pageModelCode) {
    return new Promise((resolve, reject) => {
        getToken()
            .then(token => {
                request({
                    method: 'GET',
                    uri: process.env.APP_URL + '/api/pageModels/' + pageModelCode,
                    headers: getHeaders()
                }).then(res => {
                    resolve(JSON.parse(res).payload);
                    console.log("Response from the API request for the page model: " + JSON.parse(res).payload.code);
                    createComponent("pageModel", JSON.parse(res).payload.code, JSON.parse(res).payload.name);
                }).catch(res => {
                    printError("Unable to retrieve page model");
                    reject(res);
                });
            });
            
    });

    
}


function retrieveWidget(widgetCode) {
    return new Promise((resolve, reject) => {
        getToken()
            .then(token => {
                request({
                    method: 'GET',
                    uri: process.env.APP_URL + '/api/widgets/' + widgetCode,
                    headers: getHeaders()
                }).then(res => {
                    resolve(JSON.parse(res).payload);
                    console.log("Response from the API request for the widget: " + JSON.parse(res).payload.code);
                    createComponent("widget", JSON.parse(res).payload.code, JSON.parse(res).payload.name, JSON.parse(res).payload);
                }).catch(res => {
                    printError("Unable to retrieve widget");
                    reject(res);
                });
            });
            
    });

    
}


///-----------------------------------------END SINGLE COMPONENT API REQUEST-----------------------------------



function selectComponentType() {

    console.log("Select component type:");

    let availableTypes = fs.readdirSync(TEMPLATE_COMPONENT_FOLDER + "/data/");
    for (let i = 0; i < availableTypes.length; i++) {
        console.log(`${i + 1}. ${availableTypes[i]}`);
    }

    let componentType;

    let componentTypeSelected = false;
    while (!componentTypeSelected) {
        componentType = readline.question("Component type: ");
        // Retrieve component type by number or value
        let componentTypeIndex = parseInt(componentType);
        if (isNaN(componentTypeIndex)) {
            // check if valid type has been inserted
            componentTypeSelected = availableTypes.includes(componentType);
        } else {
            // retrieve by index
            componentTypeIndex--;
            if (componentTypeIndex >= 0 && componentTypeIndex < availableTypes.length) {
                componentType = availableTypes[componentTypeIndex];
                componentTypeSelected = true;
            }
        }
        if (!componentTypeSelected) {
            printError(`Invalid input: ${componentType}`);
        }
    }

    return componentType;
}




    function createLocalRepositoryIfNotExists() {
    
        return new Promise((resolve, reject) => {

            let componentDir = process.env.LOCAL_REPO_URL;
            let componentDirData = componentDir + '/data/';
            let componentDirMeta = componentDir + '/metadata/';
            let componentDirComp = componentDirMeta + '/components/';
            let componentDirIcons = componentDirMeta + '/icons/';


            if (!fs.existsSync(componentDir)) {
                fs.mkdirSync(componentDir);
            }
    
            if (!fs.existsSync(componentDirData)) {
                fs.mkdirSync(componentDirData);
            }
            if (!fs.existsSync(componentDirMeta)) {
                fs.mkdirSync(componentDirMeta);
            }
    
            if (!fs.existsSync(componentDirComp)) {
                fs.mkdirSync(componentDirComp);
            }
    
            if (!fs.existsSync(componentDirIcons)) {
                fs.mkdirSync(componentDirIcons);
            }


            if (!fs.existsSync(process.env.LOCAL_REPO_URL + "/.git")) {
                nodeGit.Repository.init(process.env.LOCAL_REPO_URL, 0).then(repo => {
                    printSuccess("Local component repository created");
                    resolve(repo);
                }).catch(res => {
                    printError("Unable initialize local repository");
                    reject(res);
                });
            }
            nodeGit.Repository.open(process.env.LOCAL_REPO_URL).then(repo => resolve(repo));
        });
    }
    

function addDirectoryAndCommit(repo, componentName, commitMsg) {
    
    return new Promise((resolve, reject) => {
        let oid;
        let index;
        repo.refreshIndex().then(idx => {
            index = idx;
        })
        .then(() => index.addAll(componentName))
        .then(() => index.write())
        .then(() => index.writeTree())
        .then(o => oid = o)
        .then(() => repo.getHeadCommit())
        .then(headCommit => {
            let parent = [];
            if(headCommit) {
                parent.push(headCommit);
            }
            var signature = nodeGit.Signature.default(repo);
            let commit = repo.createCommit('HEAD', signature, signature, commitMsg, oid, parent);
            printSuccess("Created commit for " + componentName);
            resolve(commit);
        }).catch(res => {
            printError("Unable to create commit for " + componentName);
            reject(res);
        });
    });
}



function createComponent(componentType, componentId, componentName, payload) {
    
       
    let componentVersion = readline.question("Component version: ");
    let contentTypeCode = componentId;
    let componentDescription = readline.question("Component description: ", {defaultInput: ""});
    
    createLocalRepositoryIfNotExists()
        .then(repo => {
            let repoDir = process.env.LOCAL_REPO_URL;
            let templatesDir = TEMPLATE_COMPONENT_FOLDER;
            
            let templatesComponentTypeDirData = templatesDir + "/data/" + componentType;
            let repoComponentDirData = repoDir + "/data/" + componentId + "_" + componentType;
            

            let templatesComponentTypeDirMetadata = templatesDir + "/metadata/components/"+ componentType + ".json";
            let repoComponentTypeDirMetadata = repoDir + "/metadata/components/" + componentId + "_" + componentType + ".json";            
            let repoComponentsMeta = repoDir + "/metadata/components/";

            fs.copySync(templatesComponentTypeDirData, repoComponentDirData);

if(componentType === 'contentModel'){
    fs.rename(repoComponentDirData + "/data/" + "template" + "_" + componentType + ".xml", repoComponentDirData + "/data/" + componentId + "_" + componentType + ".xml", 
    function(err) {
        if ( err ) console.log('ERROR: ' + err);
    });


} else {

    fs.rename(repoComponentDirData + "/data/" + "template" + "_" + componentType + ".json", repoComponentDirData + "/data/" + componentId + "_" + componentType + ".json", 
    function(err) {
        if ( err ) console.log('ERROR: ' + err);
    });



}

            

fs.copyFileSync(templatesComponentTypeDirMetadata, repoComponentTypeDirMetadata);

        let filesData = fsWalkerSync(repoComponentDirData, {nodir: true});

        filesData.forEach(fileData => {
            let content = fs.readFileSync(fileData.path, 'utf8');
            content = content.replace(/#COMPONENT_ID#/g, componentId + "_" + componentType)
                    .replace(/#COMPONENT_NAME#/g, componentName)
                    .replace(/#COMPONENT_VERSION#/g, componentVersion)
                    .replace(/#COMPONENT_DESCRIPTION#/g, componentDescription)
                    .replace(/#REPO_DIRECTORY#/g, repoDir);
            if(componentType === 'contentModel') {
                let contentModelShape = payload.contentShape;
                let contentModelType = payload.contentType;
                // contentModel needs a random numeric id
                content = content.replace(/#CONTENT_MODEL_CODE#/g, (1 + Math.random() + "").replace('.', '').substring(0,6));
                content = content.replace(/#CONTENT_MODEL_SHAPE#/, contentModelShape);
                content = content.replace(/#CONTENT_MODEL_TYPE#/g, contentModelType);
            } else if(componentType === 'contentType') {
                let jsoncontentTypeAttributes = JSON.stringify(payload.attributes, null, 2);
                    
                content = content.replace(/#CONTENT_TYPE_CODE#/, contentTypeCode);
                content = content.replace(/#CONTENT_TYPE_ATTRIBUTES#/, jsoncontentTypeAttributes);
                console.log(jsoncontentTypeAttributes);
            } else if (componentType === 'role'){
                let jsonRolePermissions = JSON.stringify(payload.permissions, null, 2);
                content = content.replace(/#ROLE_PERMISSIONS#/, jsonRolePermissions);
                console.log("Permissions: " + jsonRolePermissions);
            }
            //fs.writeFileSync(fileData.path, content);
            fs.writeFile(fileData.path, content, 'utf8', function (err) {
                if (err) return console.log(err);
             });


        });

        let filesMeta = fsWalkerSync(repoComponentsMeta, {nodir: true});

        filesMeta.forEach(fileMeta => {
            let content = fs.readFileSync(fileMeta.path, 'utf8');
            content = content.replace(/#COMPONENT_ID#/g, componentId + "_" + componentType)
                    .replace(/#COMPONENT_NAME#/g, componentName)
                    .replace(/#COMPONENT_VERSION#/g, componentVersion)
                    .replace(/#COMPONENT_DESCRIPTION#/g, componentDescription)
                    .replace(/#REPO_DIRECTORY#/g, repoDir)
                    .replace(/#COMPONENT_FOLDER_NAME#/g, componentId + "_" + componentType );
            if(componentType === 'contentModel') {
                // contentModel needs a random numeric id
                content = content.replace(/#CONTENT_MODEL_CODE#/g, (1 + Math.random() + "").replace('.', '').substring(0,6));
                

            } else if(componentType === 'contentType') {
                content = content.replace(/#CONTENT_TYPE_CODE#/, contentTypeCode + "contentType");
            }
            fs.writeFileSync(fileMeta.path, content);
        });  

            createIcon(componentId, componentType);
            
            addDirectoryAndCommit(repo, componentName, 'Added ' + componentName)
                .then(res => res);
        });
}



function createIcon(componentId, componentType){

    const generateImage = (options) => {
    
      const args = [
        "-density", `${options.resolution * options.sampling_factor}`,
        "-size", `${options.size * options.sampling_factor}x${options.size * options.sampling_factor}`,
        `canvas:${options.background_color}`,
        "-fill", options.text_color,
        "-pointsize", `${options.point_size}`,
        "-gravity", `${options.gravity}`,
        "-annotate", "+0+0",
        `${options.text_to_display}`,
        "-resample", `${options.resolution}`,
        `${options.file_location}/${options.file_name}.${options.file_extension}`
        
      ];
    
      imagemagick.convert(args, (err, data) => {
        if (err) {
          console.log(`ERROR: ${err}`);
          throw err;
        } else {
          console.log('Image complete');
        }
    
      });
    }
    
    const options = {
      background_color: "#003766",
      file_extension: 'jpg',
      file_location: 'cloned_components/metadata/icons/',
      file_name: componentId + "_" + componentType,
      gravity: 'center',
      height: 135,
      point_size: 12,
      resolution: 72,
      size: 135,
      sampling_factor: 3,
      text_color: "#FFFFFF",
      text_to_display: componentId + "\n" + "(" + componentType + ")",
      width: 100
    }
    
    // Call the generate image function
    generateImage(options);





}
