/*
    Copyright (c) Ascensio System SIA 2016. All rights reserved.
    http://www.onlyoffice.com
*/

pObj = eval('(' + remote.call("/anarchy/onlyoffice/prepare?nodeRef=" + url.args.nodeRef) + ')'); 
model.callbackUrl = pObj.callbackUrl;
model.docTitle = pObj.docTitle;
model.docUrl = pObj.docUrl;
model.key = pObj.key;
model.onlyofficeUrl = pObj.onlyofficeUrl;
model.lang = "ru-RU";

if ("application/vnd.openxmlformats-officedocument.wordprocessingml.document" == pObj.docMimeType) {
   model.docType = "docx";
   model.documentType = "text";
} else if ("application/msword" == pObj.docMimeType) {
   model.docType = "doc";
   model.documentType = "text";
} else if ("application/vnd.oasis.opendocument.text" == pObj.docMimeType || "application/x-vnd.oasis.opendocument.text"  == pObj.docMimeType) {
   model.docType = "odt";
   model.documentType = "text";
} else if ("application/rtf" == pObj.docMimeType) {
   model.docType = "rtf";
   model.documentType = "text";
} else if ("text/plain" == pObj.docMimeType) {
   model.docType = "txt";
   model.documentType = "text";
} else if ("text/html" == pObj.docMimeType) {
   model.docType = "html";
   model.documentType = "text";
} else if ("message/rfc822" == pObj.docMimeType) {
   model.docType = "mht";
   model.documentType = "text";
}  else if ("application/pdf" == pObj.docMimeType || "application/x-pdf" == pObj.docMimeType || "application/acrobat" == pObj.docMimeType || "applications/vnd.pdf" == pObj.docMimeType || "text/pdf" == pObj.docMimeType || "text/x-pdf" == pObj.docMimeType) {
   model.docType = "pdf";
   model.documentType = "text";
} else if ("image/vnd.djvu" == pObj.docMimeType || "image/x.djvu" == pObj.docMimeType) {
   model.docType = "djvu";
   model.documentType = "text";
} else if ("application/x-fictionbook" == pObj.docMimeType) {
   model.docType = "fb2";
   model.documentType = "text";
} else if ("application/epub+zip" == pObj.docMimeType) {
   model.docType = "epub";
   model.documentType = "text";
} else if ("application/vnd.ms-xpsdocument" == pObj.docMimeType) {
   model.docType = "xps";
   model.documentType = "text";
} else if ("application/vnd.ms-excel" == pObj.docMimeType || "application/msexcel"  == pObj.docMimeType || "application/x-msexcel"  == pObj.docMimeType || "application/x-ms-excel"  == pObj.docMimeType || "application/x-excel"  == pObj.docMimeType || "application/xls"  == pObj.docMimeType) {
   model.docType = "xls";
   model.documentType = "spreadsheet";
} else if ("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" == pObj.docMimeType) {
   model.docType = "xlsx";
   model.documentType = "spreadsheet";
} else if ("application/vnd.oasis.opendocument.spreadsheet" == pObj.docMimeType || "application/x-vnd.oasis.opendocument.spreadsheet"  == pObj.docMimeType) {
   model.docType = "ods";
   model.documentType = "spreadsheet";
} else if ("text/comma-separated-values" == pObj.docMimeType || "text/csv" == pObj.docMimeType || "application/csv"  == pObj.docMimeType) {
   model.docType = "csv";
   model.documentType = "spreadsheet";
} else if ("application/vnd.ms-powerpoint" == pObj.docMimeType) {
   model.docType = "pps";
   model.documentType = "presentation";
} else if ("application/vnd.openxmlformats-officedocument.presentationml.slideshow" == pObj.docMimeType) {
   model.docType = "ppsx";
   model.documentType = "presentation";
} else if ("application/vnd.openxmlformats-officedocument.presentationml.presentation" == pObj.docMimeType) {
   model.docType = "pptx";
   model.documentType = "presentation";
} else if ("application/vnd.oasis.opendocument.presentation" == pObj.docMimeType || "application/x-vnd.oasis.opendocument.presentation" == pObj.docMimeType) {
   model.docType = "odp";
   model.documentType = "presentation";
} else {
   model.docType = null;
   model.documentType = null;  
}  

model.userId = user.id;
model.firstName = user.firstName;
model.lastName = user.lastName;

