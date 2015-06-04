<#-- @ftlvariable name="isNew" type="java.lang.Boolean" -->
<#-- @ftlvariable name="display" type="sk.stuba.fei.team.local.domain.DisplayConfiguration" -->
<#import "../../lib/pageTemplates.ftl" as pt>
<#import "/spring.ftl" as spring>
<#if isNew==true>
    <#assign title>Nová obrazovka</#assign>
<#else>
    <#assign title>Editácia obrazovky</#assign>
</#if>
<@pt.dashboardPage pageTitle=title>
<div class="row">
    <div class="col-md-12">
        <a class="btn btn-info btn-sm" href="<@spring.url '/admin/display'/>" role="button">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            Späť
        </a>
    </div>
</div>
<br/>
<div class="table-responsive">
    <form name="display" action="<@spring.url '/admin/display/save'/>" method="post">
        <div class="form-group">
            <label for="office-id">ID</label>
            <input type="text" name="id" class="form-control" id="office-id" placeholder="ID"
                   value="${display.id}">
        </div>
        <div>
            <input type="hidden" value="${display.offices}">
        </div>
        <div class="form-group">
            <label for="office-facility">Ordinácie</label>
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th style="width: 60px;">ID</th>
                    <th>Názov</th>
                    <th>Ordinujúci lekár</th>
                    <th style="width: 60px;">Odobrať</th>
                </tr>
                </thead>
                <tbody>
                    <#list display.offices as office>
                    <tr>
                        <td>${office.id}</td>
                        <td>${office.name}</td>
                        <td>
                            <#list office.employees as employee>
                            ${employee.prefix_title!} ${employee.firstName!} ${employee.lastName!} ${employee.suffix_title!} <#if employee_has_next>
                                , </#if>
                            </#list>
                        </td>
                        <td class="text-center">
                            <a class="btn btn-danger btn-sm"
                               href="<@spring.url '/admin/display/removeOffice/${office.id}/${isNew?string}'/>"
                               role="button">
                                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>
                        </td>
                    </tr>
                    </#list>
                </tbody>
            </table>

        </div>
        <div class="form-group">
            <div>
                <input type="submit" value="Uložiť" class="btn btn-success">
            </div>
        </div>

    </form>
</div>
</@pt.dashboardPage>