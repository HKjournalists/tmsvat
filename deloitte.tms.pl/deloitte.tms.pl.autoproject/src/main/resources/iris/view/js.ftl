<#assign pojo=oneToManyPojo.getOne()>
<#assign many=oneToManyPojo.getMany()>
<#assign ones=oneToManyPojo.getOnes()>
<#assign pojoview=pojo.getDeclarationNameFirstLetterLower()+"View">
<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
<#assign pojoclassname = "${pojo.getPackageName()}.model.${declarationName}">
<#assign masterid = pojo.getDeclarationNameFirstLetterLower()+"Id">
<#include "view_d7/main_javascript.ftl"/>
<#include "view_d7/manys_javascript.ftl"/>
<#include "view_d7/ones_javascript.ftl"/>