<#setting url_escaping_charset='utf-8'>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${title}</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="${.now}" />
<script type="text/javascript" src="${base}/webstatic/js/jquery/jquery${minSuffix}.js"></script>
${head}
</head> 
<body onload="prettyPrint();">
<#include "/WEB-INF/freemarker/decorators/header.ftl">
${body}
<#include "/WEB-INF/freemarker/decorators/footer.ftl">
</body> 
</html>