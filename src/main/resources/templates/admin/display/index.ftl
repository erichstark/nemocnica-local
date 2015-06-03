<#-- @ftlvariable name="pageTitle" type="java.lang.String" -->
<#-- @ftlvariable name="displayList" type="java.util.List<sk.stuba.fei.team.local.domain.DisplayConfiguration>" -->
<#import "../../lib/pageTemplates.ftl" as pt>
<#import "/spring.ftl" as spring>
<#assign pageTitle>Administrácia obrazoviek</#assign>
<@pt.dashboardPage pageTitle=pageTitle>
<a class="btn btn-default" href="<@spring.url '/admin/display/debug'/>" role="button">
    DEBUG - Vyvolávanie pacientov
</a>
<h2 class="sub-header">Obrazovky</h2>


<form class="form-inline" method="POST" action="<@spring.url '#'/>">
    <a class="btn btn-success" href="<@spring.url '/admin/display/add'/>" role="button">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Pridať
    </a>
    <#if displayList??>
        <#--<div class="form-group">-->
            <#--<label for="text">Vyhľadanie:</label>-->
            <#--<input type="text" name="text" class="form-control" id="text" placeholder="Hľadaný text"-->
                   <#--value="${search!""}">-->
        <#--</div>-->
        <#--<input type="submit" value="Hľadaj" class="btn btn-default">-->
    </#if>
</form>

    <#if displayList??>
    <div class="table-responsive">
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th style="width: 60px;">#</th>
                <th style="width: 60px;">ID</th>
                <th>Ambulancie</th>
                <th style="width: 60px;">Zmazať</th>
            </tr>
            </thead>
            <tbody>
                <#list displayList as display>
                <tr class='clickable-row' data-href="<@spring.url '/admin/display/edit/${display.id}'/>">
                    <td>${display_index + 1}</td>
                    <td>${display.id}</td>
                    <td>
                        <#list display.offices as office>
                        ${office.name}<#if office_has_next>, </#if>
                        </#list>
                    </td>
                    <td class="text-center">
                        <a class="btn btn-danger btn-sm" href="<@spring.url '/admin/display/delete/${display.id}'/>"
                           role="button">
                            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>
                    </td>
                </tr>
                </#list>
            </tbody>
        </table>
    </div>
    <#else>
    <h4 class="text-muted">V systéme nie sú evidované žiadne obrazovky.</h4>
    </#if>
</@pt.dashboardPage>