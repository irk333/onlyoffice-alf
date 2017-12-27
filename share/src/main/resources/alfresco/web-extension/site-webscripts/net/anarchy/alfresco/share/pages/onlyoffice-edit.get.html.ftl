<!--
    Copyright (c) Ascensio System SIA 2016. All rights reserved.
    http://www.onlyoffice.com
-->
<html>
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=utf-8'>

    <title>ONLYOFFICE</title>

    <link href="${url.context}/res/components/onlyoffice/onlyoffice.css" type="text/css" rel="stylesheet">

    <!--Change the address on installed ONLYOFFICE™ Online Editors-->
    <script id="scriptApi" type="text/javascript" src="${onlyofficeUrl}OfficeWeb/apps/api/documents/api.js"></script>

    <script type="text/javascript">
/*
    Copyright (c) Ascensio System SIA 2016. All rights reserved.
    http://www.onlyoffice.com
*/
function key(k) {
    var result = k.replace(new RegExp("[^0-9-.a-zA-Z_=]", "g"), "_") + (new Date()).getTime();
    return result.substring(result.length - Math.min(result.length, 20));
};

var getDocumentType = function (ext) {
    if (".docx.doc.odt.rtf.txt.html.htm.mht.pdf.djvu.fb2.epub.xps".indexOf(ext) != -1) return "text";
    if (".xls.xlsx.ods.csv".indexOf(ext) != -1) return "spreadsheet";
    if (".pps.ppsx.ppt.pptx.odp".indexOf(ext) != -1) return "presentation";
    return null;
};
    </script>
    <style type="text/css">
/*
    Copyright (c) Ascensio System SIA 2016. All rights reserved.
    http://www.onlyoffice.com
*/
html {
    height: 100%;
    width: 100%;
}
body {
    background: #fff;
    color: #333;
    font-family: Arial, Tahoma,sans-serif;
    font-size: 12px;
    font-weight: normal;
    height: 100%;
    margin: 0;
    padding: 0;
    text-decoration: none;
}
form {
    height: 100%;
}
div {
    margin: 0;
    padding: 0;
}
    
   	</style>
</head>

<body>
    <div>
        <div id="placeholder"></div>
    </div>
    <script>

//    var docName = "${docTitle}";
//    var docType = docName.substring(docName.lastIndexOf(".") + 1).trim().toLowerCase();
//    var documentType = getDocumentType(docType);

    new DocsAPI.DocEditor("placeholder",
        {
            type: "desktop",
            width: "100%",
            height: "600px",
            documentType: "${documentType}",
            document: {
                title: "${docTitle}",
                url: "${docUrl}",
                fileType: "${docType}",
                key: "${key}",
                permissions: {
                    edit: true
                },
            },
            editorConfig: {
                mode: "edit",
                callbackUrl: "${callbackUrl}",
                lang: "${lang}",
                user: {
                  id: "${userId}",
                  firstname: "${firstName}",
                  lastname: "${lastName}",
                }
            },
            events: {
            },
        });
    </script>
</body>
</html>

