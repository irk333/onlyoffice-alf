package net.anarchy.alfresco.onlyoffice.webscript;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.policy.BehaviourFilter;
import org.alfresco.service.cmr.lock.LockService;
import org.alfresco.service.cmr.lock.LockStatus;
import org.alfresco.service.cmr.lock.LockType;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.NodeRef;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.extensions.webscripts.AbstractWebScript;
import org.springframework.extensions.webscripts.WebScriptRequest;
import org.springframework.extensions.webscripts.WebScriptResponse;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;

/**
 * Created by cetra on 20/10/15.
 */
 /*
    Copyright (c) Ascensio System SIA 2016. All rights reserved.
    http://www.onlyoffice.com
*/
@Component(value = "webscript.net.anarchy.alfresco.onlyoffice.components.callback.post")
public class CallBack extends AbstractWebScript {
	
	private static final int STATUS_NOT_FOUND = 0; // no document with the key identifier could be found
	private static final int STATUS_EDITING = 1; // document is being edited
	private static final int STATUS_READY_FOR_SAVING = 2; // document is ready for saving
	private static final int STATUS_SAVING_ERROR = 3; // document saving error has occurred
	private static final int STATUS_CLOSED_NO_CHANGES = 4; // document is closed with no changes
	private static final int STATUS_EDITING_SAVED = 6; // document is being edited, but the current document state is saved
	private static final int STATUS_FORCE_SAVING_ERROR = 7;	// 7 - error has occurred while force saving the document.

    @Autowired
    LockService lockService;

    @Resource(name = "policyBehaviourFilter")
    BehaviourFilter behaviourFilter;

    @Autowired
    ContentService contentService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void execute(WebScriptRequest request, WebScriptResponse response) throws IOException {
    	try {
	        logger.debug("Received JSON Callback");
	        JSONObject callBackJSon = new JSONObject(request.getContent().getContent());
	
	        logger.debug(callBackJSon.toString(3));
	
	        String[] keyParts = callBackJSon.getString("key").split("_");
	        NodeRef nodeRef = new NodeRef("workspace://SpacesStore/" + keyParts[0]);
	
	        //Status codes from here: https://api.onlyoffice.com/editors/editor
	
	        switch(callBackJSon.getInt("status")) {
	            case 0:
	                logger.error("ONLYOFFICE has reported that no doc with the specified key can be found");
	                //lockService.unlock(nodeRef);
	                break;
	            case 1:
//	                if(lockService.getLockStatus(nodeRef).equals(LockStatus.NO_LOCK)) {
	                    logger.debug("Document open for editing, locking document");
//	                    behaviourFilter.disableBehaviour(nodeRef);
//	                    lockService.lock(nodeRef, LockType.WRITE_LOCK);
//	                } else {
//	                    logger.debug("Document already locked, another user has entered/exited");
//	                }
	                break;
	            case 2:case 6:
	                logger.debug("Document Updated, changing content");
//	                lockService.unlock(nodeRef);
	                updateNode(nodeRef, callBackJSon.getString("url"));
	                break;
	            case 3: case 7:
	                logger.error("ONLYOFFICE has reported that saving the document has failed");
//	                lockService.unlock(nodeRef);
	                break;
	            case 4:
	                logger.debug("No document updates, unlocking node");
//	                lockService.unlock(nodeRef);
	                break;
	        }
	
	        response.getWriter().write("{\"error\":0}");
    	} catch (JSONException ex) {
    		throw new IOException(ex);
    	}
    }

    private void updateNode(NodeRef nodeRef, String url) {
        logger.debug("Retrieving URL:{}", url);

        try {
            InputStream in = new URL( url ).openStream();
            contentService.getWriter(nodeRef, ContentModel.PROP_CONTENT, true).putContent(in);
        } catch (IOException e) {
            logger.error(ExceptionUtils.getFullStackTrace(e));
        }
    }
}

