<#-- @ftlvariable name="offices" type="sk.stuba.fei.team.local.domain.Office[]" -->
<#import "../../lib/pageTemplates.ftl" as pt>
<#import "/spring.ftl" as spring>
<#assign pageTitle>Administracia ambulancií</#assign>
<@pt.dashboardPage pageTitle=pageTitle>
<div class="row">
    <div class="col-md-12">
        <a class="btn btn-success" href="<@spring.url '/admin/office/add'/>" role="button">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Pridať
        </a>
    </div>
</div>

<hr>

<div class="row">
    <div class="col-md-12">
        <form class="form-inline" method="POST" action="<@spring.url '/admin/office/search'/>">
            <div class="form-group">
                <label for="text">Vyhľadanie:&nbsp;&nbsp;</label>
                <input type="text" name="text" class="form-control" id="text" placeholder="Hľadaný text"
                       value="${search!""}" style="width: 500px;">
            </div>
            &nbsp;
            <input type="submit" value="Hľadaj" class="btn btn-default">
            <a class="btn btn-default" href="<@spring.url '/admin/office/clear'/>">Zruš</a>
        </form>
    </div>
</div>

<div class="table-responsive">
    <table class="table table-striped">
        <thead>
        <tr>
            <th style="width: 60px;">#</th>
            <th style="text-align: left">Názov ambulancie</th>
            <th style="width: 75px; text-align: right">Aktívna</th>
        </tr>
        </thead>
        <tbody>
            <#list offices as office>
            <tr>
                <td>${office_index + 1}</td>
                <td style="text-align: left"><a
                        href="<@spring.url '/admin/office/edit/' + office.id />">${office.name}</a></td>
                <td><input type="checkbox" <#if office.enabled>checked</#if> disabled></td>
            </tr>
            </#list>
        </tbody>
    </table>
</div>
</@pt.dashboardPage>