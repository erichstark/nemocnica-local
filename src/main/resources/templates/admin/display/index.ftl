<#-- @ftlvariable name="pageTitle" type="java.lang.String" -->
<#-- @ftlvariable name="rpiList" type="java.util.List<sk.stuba.fei.team.local.domain.Rpi_Office>" -->
<#import "../../lib/pageTemplates.ftl" as pt>
<#import "/spring.ftl" as spring>
<#assign pageTitle>Administrácia obrazoviek</#assign>
<@pt.dashboardPage pageTitle=pageTitle>
<div class="row">
    <div class="col-md-12">
        tu budu nejake globalne nastavenia obrazoviek...
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <a class="btn btn-info btn-sm" href="<@spring.url '/admin/rpiconfig/add'/>" role="button">Pridať obrazovku</a>
    </div>
</div>

<h2 class="sub-header">Obrazovky</h2>

<div class="row">
    <div class="col-md-12">
        <form class="form-inline" method="POST" action="<@spring.url '/admin/rpiconfig/search'/>">
            <div class="form-group">
                <label for="text">Vyhľadanie:</label>
                <input type="text" name="text" class="form-control" id="text" placeholder="Hľadaný text"
                       value="${search!""}">
            </div>
            <input type="submit" value="Hľadaj" class="btn btn-default">
            <a class="btn btn-default" href="<@spring.url '/admin/rpiconfig/clear'/>">Reset</a>
        </form>
    </div>
</div>

<div class="table-responsive">
    <table class="table table-striped table-hover">
        <thead>
        <tr>
            <th style="width: 60px;">#</th>
            <th style="width: 60px;">ID</th>
            <th>Ambulancie</th>
        </tr>
        </thead>
        <tbody>
            <#list rpiList as rpi>
            <tr class='clickable-row' data-href="<@spring.url '/admin/rpiconfig/edit/${rpi.id}'/>">
                <td>${rpi_index + 1}</td>
                <td>${rpi.id}</td>
                <td>
                    <#list rpi.offices as office>
                    ${office.name}<#if office_has_next>, </#if>
                    </#list>
                </td>
            </tr>
            </#list>
        </tbody>
    </table>
</div>

</@pt.dashboardPage>